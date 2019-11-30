package com.himanshu.practice.nov.nov29;

import java.util.concurrent.Callable;

public class ScheduledExecuter implements Executer{
    @Override
    public boolean scheduledExecutor(Callable task, int frequency, TimeUnit timeUnit) {
        return false;
    }
}




enum TimeUnit {
    SECOND, MUNUT, HOUR
}

interface Executer {
    public boolean scheduledExecutor(Callable task, int frequency, TimeUnit timeUnit);
}