package com.himanshu.uber;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author Himanshu Bhardwaj
 * Date 09/Dec/2019
 */
public interface ScheduleAtFixRate {

    void ScheduleAtFixRate(Runnable task, long delay);
}
