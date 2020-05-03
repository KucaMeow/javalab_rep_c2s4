package ru.itis.javalabhw.thread;

import ru.itis.javalabhw.model.Task;
import ru.itis.javalabhw.services.TaskService;

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
