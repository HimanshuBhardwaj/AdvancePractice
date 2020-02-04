package com.himanshu.practice.feb2020.feb02.srm777;

/**
 * @author Himanshu Bhardwaj
 * Date 03/Feb/2020
 */
public class StringTransformationEasy {
    public static void main(String[] args) {
        StringTransformationEasy stringTransformationEasy = new StringTransformationEasy();
        System.out.println(stringTransformationEasy.getResult("RRRRGGG", "RRG"));
        System.out.println(stringTransformationEasy.getResult("RRR", "RRR"));
        System.out.println(stringTransformationEasy.getResult("RRRGGRRRRGGG", "RGGRRRRG"));
        System.out.println(stringTransformationEasy.getResult("RRGG", "RRGGGG"));
        System.out.println(stringTransformationEasy.getResult("GGGG", "GG"));

    }

    public String getResult(String s, String t) {
        if (!isSubSequence(s, t)) {
            return "NO";
        }

        return getResultH(s, t,true,'1');
    }

    public String getResultH(String s, String t, boolean isCharNotReq, char c) {

        if (t.length() == 0) {
            if (isCharNotReq) {
                return "YES";
            } else {
                return "NO";
            }
        }

        if (s.length() == 0 && t.length() != 0) {
            return "NO";
        }

        int ps = 0;
        int pt = 0;


        if (s.charAt(ps) == t.charAt(pt)) {
            if (s.length() == 1 && t.length() == 1) {
                if (isCharNotReq) {
                    return "YES";
                } else {
                    if (c != s.charAt(ps)) {
                        return "YES";
                    } else {
                        return "NO";
                    }
                }
            }
            if (!isCharNotReq) {
                return getResultH(s.substring(1), t.substring(1), false, '1');
            } else {
                if (s.charAt(ps) != c) {
                    return getResultH(s.substring(1), t.substring(1), false, '1');
                } else {
                    return "NO";
                }

            }


        } else {
            if (s.length() < 3) {
                return "NO";
            }

            if (s.charAt(0) != s.charAt(1)) {
                return "NO";
            }
            return getResultH(s.substring(2), t, true, s.charAt(0));
        }
    }

    private boolean isSubSequence(String s, String t) {
        int ps = 0;
        int pt = 0;

        while ((ps < s.length()) && (pt < t.length())) {
            if (s.charAt(ps) == t.charAt(pt)) {
                ps++;
                pt++;
            } else {
                ps++;
            }
        }

        if (pt != t.length()) {
            return false;
        }
        return true;
    }
}
