package ru.itis.repositories;

import ru.itis.models.FileInfo;

import java.io.File;

public interface FileRepository extends CrudRepository<Long, FileInfo> {
    File getFile(String fileName);
}
