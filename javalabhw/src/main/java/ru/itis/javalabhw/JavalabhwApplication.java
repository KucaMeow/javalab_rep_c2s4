package ru.itis.javalabhw;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.itis.javalabhw.thread.TaskWorker;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class JavalabhwApplication {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    @Bean
    public Map<String, TaskWorker> workers() {
        return new HashMap<>();
    }

    public static void main(String[] args) {
        SpringApplication.run(JavalabhwApplication.class, args);
    }
}
