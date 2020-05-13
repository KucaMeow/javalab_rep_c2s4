package ru.itis.repositories;

import org.springframework.stereotype.Repository;
import ru.itis.models.CheckboxTest;
import ru.itis.models.CheckboxTestAnswer;
import ru.itis.models.CodeTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class CheckboxTestAnswersRepositoryJpaImpl implements CheckboxTestAnswersRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    @Override
    public void deleteAllByCheckboxTest(CheckboxTest checkboxTest) {
        for(CheckboxTestAnswer checkboxTestAnswer : entityManager.createQuery(
                "SELECT cta FROM CheckboxTestAnswer cta WHERE cta.checkboxTest.id = " + checkboxTest.getId(),
                CheckboxTestAnswer.class).getResultList()) {
            checkboxTest.getCheckboxTestAnswers().remove(checkboxTestAnswer);
            entityManager.remove(checkboxTestAnswer);
        }
    }

    @Transactional
    @Override
    public Optional<CheckboxTestAnswer> find(Long id) {
        return Optional.ofNullable(entityManager.find(CheckboxTestAnswer.class, id));
    }

    @Transactional
    @Override
    public List<CheckboxTestAnswer> findAll() {
        return entityManager.createQuery("SELECT cta FROM CheckboxTestAnswer cta", CheckboxTestAnswer.class).getResultList();
    }

    @Transactional
    @Override
    public void save(CheckboxTestAnswer entity) {
        entityManager.persist(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        entityManager.remove(find(id));
    }
}
