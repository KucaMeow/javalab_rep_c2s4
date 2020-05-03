package ru.itis.javalabhw.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalabhw.handlers.TaskSubscribeHandler;
import ru.itis.javalabhw.model.Task;
import ru.itis.javalabhw.services.TaskService;

@Component
public class TaskWorker extends Thread{

    @Autowired
    TaskService taskServiceImpl;
    @Autowired
    TaskSubscribeHandler taskSubscribeHandler;

    public TaskWorker() {
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            //Имитирую работу
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }

            synchronized (taskServiceImpl) {
                Task task = taskServiceImpl.doNextTask();
                if(task != null)
                    taskSubscribeHandler.sendTaskComplete(task);
            }
        }
    }
}
