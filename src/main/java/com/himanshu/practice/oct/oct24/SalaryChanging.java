package com.himanshu.practice.oct.oct24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by himanshubhardwaj on 24/10/19.
 * Statement: https://codeforces.com/contest/1251/problem/D
 * Algo: Binary Search
 * Submission: https://codeforces.com/contest/1251/submission/63670854
 */
public class SalaryChanging {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        PrintWriter pw = new PrintWriter(System.out);

        while (t-- > 0) {
            String[] str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            long s = Long.parseLong(str[1]);
            ArrayList<Employee> employees = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                str = br.readLine().split(" ");
                employees.add(new Employee(Long.parseLong(str[0]), Long.parseLong(str[1])));
            }
            pw.append(maximumMedianSalary(s, employees) + "\n");
        }
        pw.flush();
        pw.close();
    }

    private static long maximumMedianSalary(long s, ArrayList<Employee> employees) {
        Comparator<Employee> employeeComparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return Long.compare(o1.l, o2.l);
            }
        };
        Collections.sort(employees, employeeComparator);
        long lowerBound = employees.get(employees.size() / 2).l;


        employeeComparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return Long.compare(o1.r, o2.r);
            }
        };

        Collections.sort(employees, employeeComparator);
        long upperBound = employees.get(employees.size() / 2).r;

        while ((upperBound - lowerBound) >= 100) {
            long mid = (upperBound + lowerBound) / 2;
            boolean possibility = isPossible(s, mid, employees);

            if (possibility) {
                lowerBound = mid;
            } else {
                upperBound = mid;
            }
        }

        for (long i = upperBound; i >= lowerBound; i--) {
//        for (long i = 68; i >= lowerBound; i--) {
            boolean possibility = isPossible(s, i, employees);
            if (possibility) {
                return i;
            }
        }
        return 0l;
    }

    private static boolean isPossible(long s, long median, ArrayList<Employee> employees) {
        int countLower = 0;
        int countUpper = 0;
        int countBetween = 0;
        long cost = 0l;

        ArrayList<Employee> betweenEmployees = new ArrayList<>();


        for (Employee e : employees) {
            if (e.r < median) {
                cost += e.l;
                countLower++;
            } else if (e.l > median) {
                cost += e.l;
                countUpper++;
            } else {
                betweenEmployees.add(e); //e.l<median<=e.r
            }
        }

        if (countUpper >= (employees.size() - (employees.size() / 2))) {
            return false;
        }


        if (countLower > employees.size() / 2) {
            return false;
        }


        Comparator<Employee> employeeComparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return Long.compare(o1.l, o2.l);
            }
        };
        Collections.sort(betweenEmployees, employeeComparator);


        for (int i = 0; i < betweenEmployees.size(); i++) {
            if ((countLower + i) < (employees.size() / 2)) {
                cost += betweenEmployees.get(i).l;
            } else {
                cost += median;
            }
        }

//        if ((countLower + betweenEmployees.size()) < (employees.size() / 2)) {
//            cost += (((employees.size() / 2) + 1 - countLower - betweenEmployees.size()) * median);
//        }

        if (cost <= s) {
            return true;
        } else {
            return false;
        }
    }
}


class Employee {
    long l;
    long r;

    @java.beans.ConstructorProperties({"l", "r"})
    public Employee(long l, long r) {
        this.l = l;
        this.r = r;
    }
}


/*

1
19 1175
44 87
68 100
93 98
76 79
10 58
74 99
87 97
23 75
62 73
100 100
71 76
73 87
76 82
44 84
54 90
2 82
67 99
85 97
66 88


*
* */