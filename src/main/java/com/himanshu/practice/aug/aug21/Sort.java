package com.himanshu.practice.aug.aug21;

import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Sort {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str[] = br.readLine().split(" ");

        ArrayList<Element> elements = new ArrayList<>();

        for (int i = 0; i < str.length; i++) {
            elements.add(new Element(Double.parseDouble(str[i]), i));
        }

        SortI<Element> sort = new MergeSort<>();
        ArrayList<Element> elements1 = sort.sort(elements);

        for (Element e : elements1) {
            System.out.println(e);
        }
    }
}


@AllArgsConstructor
class Element implements Comparable<Element> {
    double value;
    int index;

    @Override
    public int compareTo(Element o) {
        if (Double.compare(this.value, o.value) != 0) {
            return Double.compare(this.value, o.value);
        } else {
            return Integer.compare(this.index, o.index);
        }
    }

    public String toString() {
        return "Element(value=" + this.value + ", index=" + this.index + ")";
    }
}

class MergeSort<T extends Comparable<T>> implements SortI<T> {
    @Override
    public ArrayList<T> sort(ArrayList<T> list) {
        if (list == null || list.size() <= 1) {
            return list;
        }

        return sortHelper(0, list.size() - 1, list);

        //ArrayList<T> sortedList1 = sortHelper(0, list.size() / 2, list);
        //ArrayList<T> sortedList2 = sortHelper(list.size() / 2 + 1, list.size() - 1, list);
        //return merge(sortedList1, sortedList2);
    }

    private ArrayList<T> merge(ArrayList<T> sortedList1, ArrayList<T> sortedList2) {
        ArrayList<T> newList = new ArrayList<>();

        int start1 = 0;
        int start2 = 0;


        while (start1 < sortedList1.size() && start2 < sortedList2.size()) {
            if (sortedList1.get(start1).compareTo(sortedList2.get(start2)) < 0) {
                newList.add(sortedList1.get(start1));
                start1++;
            } else {
                newList.add(sortedList2.get(start2));
                start2++;
            }
        }

        for (int i = start1; i < sortedList1.size(); i++) {
            newList.add(sortedList1.get(i));
        }

        for (int i = start2; i < sortedList2.size(); i++) {
            newList.add(sortedList2.get(i));
        }

        return newList;
    }

    private ArrayList<T> sortHelper(int start, int end, ArrayList<T> list) {
        if (start == end) {
            ArrayList<T> newArrayList = new ArrayList<>();
            newArrayList.add(list.get(start));
            return newArrayList;
        }

        int mid = (start + end) / 2;

        ArrayList<T> left = sortHelper(start, mid, list);
        ArrayList<T> right = sortHelper(mid + 1, end, list);


        return merge(left, right);
    }
}


/*

1.3 1 2 3333 -1 1.2 0.1 0.2

* */