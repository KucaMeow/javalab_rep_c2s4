package ru.itis.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.dto.MessageDto;
import ru.itis.models.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MessagesRepositoryJpaImpl implements MessagesRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Optional<Message> find(Long id) {

        Message message = entityManager.find(Message.class, id);
        if(message != null)
            return Optional.of(message);
        else
            return Optional.empty();
    }

    @Override
    @Transactional
    public List<Message> findAll() {
        return entityManager.createQuery("SELECT m FROM Message m", Message.class).getResultList();
    }

    @Override
    @Transactional
    public void save(Message entity) {
        entityManager.persist(entity);
    }

    @Override
    @Transactional
    public void save(MessageDto entity) {
        entityManager.persist(Message.builder()
                .message(entity.getMessage())
                .build());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        entityManager.remove(find(id));
    }
}
