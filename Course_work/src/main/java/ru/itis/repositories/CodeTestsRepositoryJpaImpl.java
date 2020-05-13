package ru.itis.repositories;

import org.springframework.stereotype.Repository;
import ru.itis.models.CodeTask;
import ru.itis.models.CodeTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class CodeTestsRepositoryJpaImpl implements CodeTestsRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public void deleteAllByCodeTask(CodeTask codeTask) {
        for(CodeTest codeTest : entityManager.createQuery(
                        "SELECT ct FROM CodeTest ct WHERE ct.codeTask.id = " + codeTask.getId(),
                        CodeTest.class).getResultList()) {
            codeTask.getTests().remove(codeTest);
            entityManager.remove(codeTest);
        }
    }

    @Transactional
    @Override
    public Optional<CodeTest> find(Long id) {
        return Optional.ofNullable(entityManager.find(CodeTest.class, id));
    }

    @Transactional
    @Override
    public List<CodeTest> findAll() {
        return entityManager.createQuery("SELECT ct FROM CodeTest ct", CodeTest.class).getResultList();
    }

    @Transactional
    @Override
    public void save(CodeTest entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(find(id));
    }
}
