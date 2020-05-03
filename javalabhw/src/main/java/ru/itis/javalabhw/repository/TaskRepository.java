package ru.itis.javalabhw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalabhw.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findFirstByReadyIsFalse();
}
