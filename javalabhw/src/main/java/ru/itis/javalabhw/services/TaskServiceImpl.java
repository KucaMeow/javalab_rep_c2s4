package ru.itis.javalabhw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalabhw.handlers.WebSocketHandler;
import ru.itis.javalabhw.model.Task;
import ru.itis.javalabhw.repository.TaskRepository;
import ru.itis.javalabhw.thread.TaskWorker;

import java.util.Map;
import java.util.Random;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    Map<String, TaskWorker> workers;
    @Autowired
    WebSocketHandler webSocketHandler;

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
        if(!workers.containsKey(task.getQueueName())) {
            workers.put(task.getQueueName(), new TaskWorker(this, task.getQueueName()));
        }
        synchronized (workers.get(task.getQueueName())) {
            workers.get(task.getQueueName()).notifyAll();
        }
    }

    @Override
    public Task getNextTask(String queueName) {
        return taskRepository.findFirstByReadyIsFalseAndQueueNameIs(queueName);
    }

    @Override
    public void sendTaskComplete(Task task) {
        task.setReady(true);
        taskRepository.save(task);
        webSocketHandler.sendTaskComplete(task);
    }


}
