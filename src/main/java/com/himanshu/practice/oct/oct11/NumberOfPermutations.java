package com.himanshu.practice.oct.oct11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by himanshubhardwaj on 11/10/19.
 * #TODO: fing Bug in it
 */
public class NumberOfPermutations {
    static int PRIME = 998244353;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Element> elements = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            elements.add(new Element(Integer.parseInt(str[0]), Integer.parseInt(str[1])));
        }

        Comparator<Element> comparator = new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return Integer.compare(o1.a, o2.a);
            }
        };

        Collections.sort(elements, comparator);
        long num1 = computeNumber1(elements);


        comparator = new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                return Integer.compare(o1.b, o2.b);
            }
        };

        Collections.sort(elements, comparator);
        long num2 = computeNumber2(elements);


        comparator = new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
                if (o1.a == o2.a) {
                    return Integer.compare(o1.b, o2.b);
                } else {
                    return Integer.compare(o1.a, o2.a);
                }
            }
        };

        Collections.sort(elements, comparator);
        long num3 = computeNumber2(elements);


        for (int i = 1; i < elements.size(); i++) {
            if ((elements.get(i).b < elements.get(i - 1).b) || (elements.get(i).a < elements.get(i - 1).a)) {
                num3 = 0;
            }
        }


        long factn = factorial(n);


        long result = (factn - num1 - num2 + num3) % PRIME;
        if (result < 0) {
            result = result + PRIME;
        }

        System.out.print(result);
    }


    private static long factorial(int n) {
        long fac = 1;

        for (int i = 2; i <= n; i++) {
            fac = (fac * i) % PRIME;
        }

        return fac;
    }


    private static long computeNumber2(ArrayList<Element> elements) {
        int prevE = 0;
        int count = 0;
        int pos = 0;
        long result = 1;


        while (pos < elements.size()) {
            while ((pos < elements.size()) && elements.get(pos).b == elements.get(prevE).b) {
                pos++;
                count++;
            }

            result = (result * factorial(count)) % PRIME;
            prevE = pos;
            count = 0;
        }

        return result;
    }

    private static long computeNumber1(ArrayList<Element> elements) {
        int prevE = 0;
        int count = 0;
        int pos = 0;
        long result = 1;

        while (pos < elements.size()) {
            while ((pos < elements.size()) && elements.get(pos).a == elements.get(prevE).a) {
                pos++;
                count++;
            }

            result = (result * factorial(count)) % PRIME;
            prevE = pos;
            count = 0;
        }

        return result;
    }
}


class Element {
    int a;
    int b;

    @java.beans.ConstructorProperties({"a", "b"})
    public Element(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
