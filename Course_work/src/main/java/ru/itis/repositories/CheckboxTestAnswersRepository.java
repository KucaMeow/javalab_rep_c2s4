package ru.itis.repositories;


import ru.itis.models.CheckboxTest;
import ru.itis.models.CheckboxTestAnswer;

public interface CheckboxTestAnswersRepository extends CrudRepository<Long, CheckboxTestAnswer> {
    void deleteAllByCheckboxTest(CheckboxTest checkboxTest);
}
