package com.flight;

import java.util.ArrayList;
import java.util.List;

public class Problem1 {
    /*
    If a=1, b=2, c=3,….z=26. Given a string, find all possible codes that string
can generate. Give a count as well as print the strings.

For example:
Input: “1123″. You need to general all valid alphabet codes from this string.

Output List
aabc //a = 1, a = 1, b = 2, c = 3
kbc // since k is 11, b = 2, c= 3
alc // a = 1, l = 12, c = 3
aaw // a= 1, a =1, w= 23
kw // k = 11, w = 23
     */

    public static void main(String[] args) {
        String value = "abcdefghijklmnopqrtuvwxyz";

        String input = "1123";

        printCombinations(new ArrayList<>(), input);

    }

    static void print(String s, String rem) {
        if (rem.length() == 0)
            System.out.println(s);

        for (int i = 0; i < rem.length(); i++) {
            print(s + rem.charAt(i), rem.substring(0, i) + rem.substring(i + 1, rem.length()));
        }
    }

    public static void printCombinations(List<String> s, String rem) {
        if (rem.length() == 0) {
            System.out.println(s);
        } else {
            List<String> r = new ArrayList<>(s);
            r.add(rem.substring(0, 1));
            printCombinations(r, rem.substring(1, rem.length()));
            if (rem.length() > 1) {
                List<String> r1 = new ArrayList<String>(s);
                r1.add(rem.substring(0, 2));
                printCombinations(r1, rem.substring(2, rem.length()));
            }
        }
    }

}
