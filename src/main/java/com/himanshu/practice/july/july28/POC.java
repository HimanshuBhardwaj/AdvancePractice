package com.himanshu.practice.july.july28;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by himanshubhardwaj on 04/08/19.
 */
public class POC {
    public static void main(String[] args) {
        ArrayList<Dob> dobs = new ArrayList<>();
        dobs.add(new Dob(1.1d));
        dobs.add(new Dob(2.1d));
        dobs.add(new Dob(3.1d));
        dobs.add(new Dob(5.1d));
        dobs.add(new Dob(4.1d));
        dobs.add(new Dob(4.1d));

        Comparator<Dob> comparator = new Comparator<Dob>() {
            @Override
            public int compare(Dob o1, Dob o2) {
                return Double.compare(o1.aDouble, o2.aDouble);
            }
        };

        Collections.sort(dobs, comparator);

        for (Dob d : dobs) {
            System.out.println(d);

        }


    }
}


class Dob {
    Double aDouble;
    String binaryString;

    public Dob(double aDouble) {
        this.aDouble = aDouble;
        binaryString = Long.toBinaryString(Double.doubleToLongBits(aDouble));
    }

    public String toString() {
        return "Dob(aDouble=" + this.aDouble + ", binaryString=" + this.binaryString + ")";
    }
}