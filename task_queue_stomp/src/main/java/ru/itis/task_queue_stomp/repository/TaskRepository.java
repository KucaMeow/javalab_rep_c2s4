package ru.itis.task_queue_stomp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.task_queue_stomp.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Task findFirstByReadyIsFalseAndQueueNameIs(String queueName);
}
