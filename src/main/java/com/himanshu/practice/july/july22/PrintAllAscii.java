package com.himanshu.practice.july.july22;

/**
 * Created by himanshubhardwaj on 28/07/19.
 */
public class PrintAllAscii {
    public static void main(String[] args) {
        int ascii = 1;

        for (int i=ascii;i<=10000;i++) {
            System.out.println(i+"\t"+(char)i);
        }
    }

}
