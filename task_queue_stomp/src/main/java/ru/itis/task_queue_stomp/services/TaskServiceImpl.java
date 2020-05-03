package ru.itis.task_queue_stomp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.itis.task_queue_stomp.model.Message;
import ru.itis.task_queue_stomp.model.Task;
import ru.itis.task_queue_stomp.repository.TaskRepository;
import ru.itis.task_queue_stomp.thread.TaskWorker;

import java.io.IOException;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;
    @Autowired
    Map<String, TaskWorker> workers;
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    ObjectMapper objectMapper;

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
        sendTaskCompleteUtil(task);

    }

    private void sendTaskCompleteUtil(Task task) {

        try {
            template.convertAndSend("/receive/" + task.getQueueName(),
                    objectMapper.writeValueAsString(
                            Message.builder()
                                    .queueName(task.getQueueName())
                                    .messageId(task.getId())
                                    .command("completed")
                                    .build()
                    )
            );
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

}
