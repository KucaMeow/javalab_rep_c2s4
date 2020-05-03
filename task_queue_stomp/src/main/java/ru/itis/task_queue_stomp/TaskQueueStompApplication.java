package ru.itis.task_queue_stomp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.itis.task_queue_stomp.thread.TaskWorker;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class TaskQueueStompApplication {
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    @Bean
    public Map<String, TaskWorker> workers() {
        return new HashMap<>();
    }
    public static void main(String[] args) {
        SpringApplication.run(TaskQueueStompApplication.class, args);
    }

}
