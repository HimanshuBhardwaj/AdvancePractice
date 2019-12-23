package com.himanshu.uber;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.Semaphore;

/**
 * @author Himanshu Bhardwaj
 * Date 09/Dec/2019
 */
public class Scheduler implements RunTask, ScheduleWithFixDelay {
    private TreeMap<Long, ArrayList<Runnable>> runTaskAPItasks;
    private RunTaskImpl runTask;

    private TreeMap<Long, ArrayList<Runnable>> ScheduleWithFixDelaytasks;
    private ScheduleWithFixDelayImpl scheduleWithFixDelay;
    Semaphore semaphore;


    public Scheduler(int concurrency) {
        Semaphore semaphore = new Semaphore(concurrency);


        runTaskAPItasks = new TreeMap<>();
        this.runTask = new RunTaskImpl(runTaskAPItasks);
        Thread runTaskThread = new Thread(runTask);
        runTaskThread.setDaemon(true);
        runTaskThread.start();


        ScheduleWithFixDelaytasks = new TreeMap<>();
        this.scheduleWithFixDelay = new ScheduleWithFixDelayImpl(ScheduleWithFixDelaytasks);
        Thread ScheduleWithFixDelayThread = new Thread(scheduleWithFixDelay);
        ScheduleWithFixDelayThread.setDaemon(true);
        ScheduleWithFixDelayThread.start();


    }


    @Override
    public void runTask(Runnable task, long time) {
        runTask.runTask(task, time);
    }

    @Override
    public void scheduleWithFixedDelay(Runnable task, long delay) {
        scheduleWithFixDelay.scheduleWithFixedDelay(task, delay);
    }
}
