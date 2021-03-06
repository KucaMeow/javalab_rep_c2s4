package ru.itis.javalabhw.services;

import ru.itis.javalabhw.model.Task;

public interface TaskService {
    void saveTask(Task task);
    Task getNextTask(String queueName);
    void sendTaskComplete(Task task);
}
