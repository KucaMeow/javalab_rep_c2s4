package ru.itis.javalabhw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalabhw.model.Task;
import ru.itis.javalabhw.repository.TaskRepository;

import java.util.Random;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Override
    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    @Override
    public Task doNextTask() {
        Task taskToDo = taskRepository.findFirstByReadyIsFalse();
        if(taskToDo == null) return null;
        //Имитирую ошибки
        if((new Random()).nextInt(5) <= 3) {
            //Ошибка
            return null;
        }
        else {
            //Все ок
            taskToDo.setReady(true);
            taskRepository.save(taskToDo);
            return taskToDo;
        }
    }

}
