package com.company.test;

import com.company.QuestionBank.*;

public class Test {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        int total = 13;
        int passes = 0;
        for (int i = 1; i <= 10; i++) {
            try {
                new Parser("./src/com/company/test/error" + i + ".qb").parse();
                System.out.printf("Test %s failed.\n", i);
                break;
            } catch (Exception e) {
                System.out.println(e);
                passes ++;
            }
        }
        for (int i = 1; i <= 3; i++) {
            try {
                new Parser("./src/com/company/test/pass" + i + ".qb").parse();
                passes ++;
            } catch (Exception e) {
                System.out.println(e);
                System.out.printf("Test %s failed.\n", i);
            }
        }
        System.out.printf("Total passes: %s/%s\n", passes, total);
    }
}
