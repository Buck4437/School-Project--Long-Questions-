package com.company;

import com.company.QuestionBank.Question;
import com.company.QuestionBank.QuestionBank;

import java.util.Scanner;

public class Game {

    private static final Scanner sc = new Scanner(System.in);

    public static void play(QuestionBank qb) {
        int questionNo = 0;
        int correct = 0;
        Question question = qb.getQuestion();

        if (question == null) {
            System.out.println("The question bank is currently empty. Import some questions to start playing!");
            System.out.println("Check out README.txt for more information.");
            return;
        }

        do {
            questionNo++;
            System.out.printf("Question %s:\n%s\n\nEnter your answer (Case insensitive):\n", questionNo, question.getPrompt());
            String ans = sc.nextLine();
            System.out.println();
            if (question.verify(ans)) {
                correct ++;
            }
            question = qb.getQuestion();
        } while (question != null);

        System.out.println("Your total score is " + correct + " out of " + questionNo);
        printGrade(correct / questionNo);
        System.out.println("Goodbye!");
    }

    private static void printGrade(int ratio) {
        if (ratio >= 1) {
            System.out.println("Perfect!");
        } else if (ratio >= 0.5) {
            System.out.println("Good job!");
        } else {
            System.out.println("Study more!");
        }
    }

}
