package ru.itis.task_queue_stomp.services;

import ru.itis.task_queue_stomp.model.Task;

public interface TaskService {
    void saveTask(Task task);
    Task getNextTask(String queueName);
    void sendTaskComplete(Task task);
}
