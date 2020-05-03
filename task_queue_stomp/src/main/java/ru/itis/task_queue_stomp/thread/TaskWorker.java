package ru.itis.task_queue_stomp.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.task_queue_stomp.model.Task;
import ru.itis.task_queue_stomp.services.TaskService;

import java.util.Random;

public class TaskWorker extends Thread{

    private String queueName;
    private TaskService taskService;

    public TaskWorker(TaskService taskService, String queueName) {
        this.queueName = queueName;
        this.taskService = taskService;
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                Task task = taskService.getNextTask(queueName);
                if(task == null) {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new IllegalStateException(e);
                    }
                    continue;
                }

                //Имитирую работу
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                if((new Random()).nextInt(5) < 3) {
                    //Ничего не делаем, у нас не получилось (т.е. отказываемся, но откатывать пока нечего)
                }
                else {
                    taskService.sendTaskComplete(task);
                }
            }
        }
    }
}
