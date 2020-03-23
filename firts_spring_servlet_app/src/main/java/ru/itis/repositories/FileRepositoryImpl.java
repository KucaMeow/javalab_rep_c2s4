package ru.itis.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.itis.models.FileInfo;

import java.io.File;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class FileRepositoryImpl implements FileRepository {
    //language=SQL
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM files WHERE id = ?";
    //language=SQL
    private static final String SQL_SELECT_BY_NAME = "SELECT * FROM files WHERE name = ?";
    //language=SQL
    private static final String SQL_SELECT_ALL = "SELECT * FROM files";
    //language=SQL
    private static final String SQL_INSERT = "INSERT INTO files(name, uploadUser, size, path, state, type, extention, origName) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    //language=SQL
    private final static String SQL_DELETE= "DELETE FROM files WHERE id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<FileInfo> fileRowMapper = (row, rowNumber) ->
            FileInfo.builder()
                    .id(row.getLong("id"))
                    .type(row.getString("type"))
                    .size(row.getLong("size"))
                    .name(row.getString("name"))
                    .extention(row.getString("extention"))
                    .path(row.getString("path"))
                    .state(row.getInt("state"))
                    .uploadUser(row.getString("uploadUser"))
                    .build();

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<FileInfo> find(Long id) {
        try {
            FileInfo fileInfo = jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, new Object[]{id}, fileRowMapper);
            return Optional.ofNullable(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    private Optional<FileInfo> find(String name) {
        try {
            FileInfo fileInfo = jdbcTemplate.queryForObject(SQL_SELECT_BY_NAME, new Object[]{name}, fileRowMapper);
            return Optional.ofNullable(fileInfo);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<FileInfo> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, fileRowMapper);
    }

    @Override
    public void save(FileInfo entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_INSERT, new String[] {"id"});
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getUploadUser());
            statement.setLong(3, entity.getSize());
            statement.setString(4, entity.getPath());
            statement.setInt(5, entity.getState());
            statement.setString(6, entity.getType());
            statement.setString(7, entity.getExtention());
            statement.setString(8, entity.getOrigName());
            return statement;
        }, keyHolder);

        entity.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }

    @Override
    public File getFile(String fileName) {
        Optional<FileInfo> fileInfo = find(fileName);
        return fileInfo.map(info -> new File(info.getPath())).orElse(null);
    }
}
