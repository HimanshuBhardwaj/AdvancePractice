package com.himanshu.practice.dec.dec31;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

/**
 * @author Himanshu Bhardwaj
 * Date 31/Dec/2019
 */
public class ClassyNumbers {
    public static void main(String[] args) throws Exception {
        solution1();
    }

    static void solution1() throws Exception {
        ArrayList<Long> numbers = new ArrayList<>();
        char[] chars = new char[18];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = '0';
        }

        generateNumbers(chars, 0, 0, 0, numbers);
        generateNumbers(chars, 0, 0, 1, numbers);
        generateNumbers(chars, 0, 0, 2, numbers);
        generateNumbers(chars, 0, 0, 3, numbers);
        numbers.add(1000000000000000000l);

        Collections.sort(numbers);

        TreeSet<Number> numbers1 = new TreeSet<>();

        for (int i = 0; i < numbers.size(); i++) {
            numbers1.add(new Number(i, numbers.get(i)));
        }


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int t = Integer.parseInt(br.readLine());
        Number number1 = new Number(0, 0);
        Number number2 = new Number(0, 0);

        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            long l = Long.parseLong(str[0]);
            long r = Long.parseLong(str[1]);

            number1.number = l - 1;
            number2.number = r;
            pw.append((numbers1.floor(number2).index - numbers1.floor(number1).index) + "\n");
        }
        pw.flush();
        pw.close();

    }

    //by default, everything is zero
    static void generateNumbers(char[] chars, int index, int num, int maxNum, ArrayList<Long> numbers) {
        if ((chars.length - index) < (maxNum - num)) {
            return;
        }

        if (maxNum == num) {
            numbers.add(Long.parseLong(new String(chars)));
            return;
        }


        if (index > chars.length - 1) {
            numbers.add(Long.parseLong(new String(chars)));
            return;
        }


        for (int i = 9; i >= 0; i--) {
            if (i == 0) {
                chars[index] = '0';
                generateNumbers(chars, index + 1, num, maxNum, numbers);
            } else {
                chars[index] = (char) ('0' + i);
                generateNumbers(chars, index + 1, num + 1, maxNum, numbers);
            }
        }


    }
}

class Number implements Comparable<Number> {
    int index;
    long number;

    @java.beans.ConstructorProperties({"index", "number"})
    public Number(int index, long number) {
        this.index = index;
        this.number = number;
    }

    @Override
    public int compareTo(Number o) {
        return Long.compare(this.number, o.number);
    }
}

