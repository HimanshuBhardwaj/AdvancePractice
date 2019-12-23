package com.himanshu.uber;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author Himanshu Bhardwaj
 * Date 09/Dec/2019
 */
public class ScheduleWithFixDelayDecorator implements Runnable {
    private Runnable task;
    private long delay;
    private TreeMap<Long, ArrayList<Runnable>> tasks;

    public ScheduleWithFixDelayDecorator(Runnable task, long delay, TreeMap<Long, ArrayList<Runnable>> tasks) {
        this.task = task;
        this.delay = delay;
        this.tasks = tasks;
    }

    @Override
    public void run() {
        task.run();
        long currentTime = System.currentTimeMillis();

        if (!tasks.containsKey(currentTime + delay)) {
            synchronized (tasks) {
                tasks.put(currentTime + delay, new ArrayList<Runnable>());
            }
        }

        synchronized (tasks.get(currentTime + delay)) {
            tasks.get(currentTime + delay).add(this);
        }
    }
}
