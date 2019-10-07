package com.himanshu.practice.aug.aug21;

import java.util.ArrayList;

public interface SortI<T extends Comparable<T>> {
    ArrayList<T> sort(ArrayList<T> list);
}
