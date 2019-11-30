package com.himanshu.practice.nov.nov24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class B {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> str = new ArrayList<>();
        str.add("121.18.19.20");
        str.add("This line has junk text.");

        String s = "a.b.c";
        String ss[] = s.split("\\.");
        System.out.println(ss.length);



//        System.out.println(Result.checkIPs(str).get(0));
//        System.out.println(Result.checkIPs(str).get(1));

    }
}


class ResultB {

    /*
     * Complete the 'checkIPs' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts STRING_ARRAY ip_array as parameter.
     */

    public static List<String> checkIPs(List<String> ip_array) {
        ArrayList<String> result = new ArrayList<>();

        for (String ip : ip_array) {
            result.add(checkType(ip));
        }

        // Write your code here
        return result;

    }


    private static String checkType(String ip) {
        if (isIPV4(ip)) {
            return "IPv4";
        }

        if (isIPV6(ip)) {
            return "IPv6";
        }

        return "Neither";
    }

    private static boolean isIPV4(String ip) {
        try {
            String str[] = ip.split("\\.");
            if (str.length == 4) {
                for (String s : str) {
                    int num = Integer.parseInt(s);
                    if (0 <= num && 255 >= num) {
                        continue;
                    }
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    private static boolean isIPV6(String ip) {
        try {
            String str[] = ip.split(":");
            int numEmpty = 0;
            for (String s : str) {
                if ("".equals(s)) {
                    numEmpty++;
                    continue;
                }
                if (!validHexaDecimal(s)) {
                    return false;
                }
            }

            if (numEmpty > 1) {
                return false;
            }

            if (str.length <= 8) {
                return true;
            }

        } catch (Exception e) {
            return false;
        }

        return false;
    }

    private static boolean validHexaDecimal(String s) {
        s = s.toLowerCase();
        char[] ch = s.toCharArray();

        for (char c : ch) {
            if ((('0' <= c) && ('9' >= c))) {
                continue;
            }
            if (('a' <= c) && ('f' >= c)) {
                continue;
            }
            return false;
        }

        return true;
    }

}