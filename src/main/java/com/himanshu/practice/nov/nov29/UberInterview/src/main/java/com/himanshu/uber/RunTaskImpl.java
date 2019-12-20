package com.himanshu.uber;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author Himanshu Bhardwaj
 * Date 09/Dec/2019
 */
public class RunTaskImpl implements RunTask, Runnable {
    private TreeMap<Long, ArrayList<Runnable>> tasks;

    public RunTaskImpl(TreeMap<Long, ArrayList<Runnable>> tasks) {
        this.tasks = tasks;
    }


    @Override
    public void runTask(Runnable task, long time) {
        if (!tasks.containsKey(time)) {
            synchronized (task) {
                if (!tasks.containsKey(time)) {
                    tasks.put(time, new ArrayList<Runnable>());
                }
            }
        }
        synchronized (tasks.get(time)) {
            tasks.get(time).add(task);
        }
    }


    //we might scledown/normalise timing
    //This will run as demon thread
    @Override
    public void run() {
        long previousTime = -1;

        while (!Thread.currentThread().isInterrupted()) {
            long currentTime = System.currentTimeMillis();
            if (previousTime == currentTime) {
                continue;

            }
            previousTime = currentTime;
            if (tasks.containsKey(currentTime)) {
                synchronized (tasks.get(currentTime)) {
                    if (tasks.containsKey(System.currentTimeMillis())) {
                        for (Runnable tasks : tasks.get(currentTime)) {
                            Thread taskThread = new Thread(tasks);
                            taskThread.start();
                        }
                    }
                }
            }
        }
    }
}
