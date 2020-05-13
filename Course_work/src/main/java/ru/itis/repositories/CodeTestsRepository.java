package ru.itis.repositories;

import ru.itis.models.CodeTask;
import ru.itis.models.CodeTest;

public interface CodeTestsRepository extends CrudRepository<Long, CodeTest> {
    void deleteAllByCodeTask(CodeTask codeTask);
}
