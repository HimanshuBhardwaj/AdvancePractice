package com.himanshu.practice.july.july18;

import java.util.ArrayList;

/**
 * Created by himanshubhardwaj on 18/07/19.
 */
public class GenericIterator {

    public static void main(String[] args) {
        Iterator<String> iterator = new Iterator<>("Himanshu Bhardwaj",10);

        while (iterator.hasNext()) {
            System.out.println(iterator.getNext());
        }
    }


    static class Iterator<E> {
        ArrayList<E> list;
        int pos;

        public Iterator(E e, int r) {
            list = new ArrayList();
            for (int i = 0; i < r; i++) {
                list.add(e);
            }
            pos = 0;
        }

        boolean hasNext() {
            return ((pos >= 0) && (pos < list.size()));
        }

        E getNext() {
            if (hasNext()) {
                pos++;
                return list.get(pos-1);
            }
            return null;
        }
    }
}
