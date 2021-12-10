package com.company;

import com.company.qb_parser.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ParserException {
        QuestionBank qb = new Parser("./src/com/company/answers.qb").parse();
        qb.randomize();
        play(qb);
    }

    public static void play(QuestionBank qb) {
        int questionNo = 0;
        int correct = 0;
        Question question = qb.getQuestion();

        if (question == null) {
            System.out.println("The question bank is currently empty. Import some questions to start playing!");
            return;
        }

        do {
            questionNo++;
            System.out.printf("Question %s:\n%s\n", questionNo, question.getPrompt());
            String ans = sc.nextLine();
            if (question.verify(ans)) {
                System.out.println("Your answer is correct!");
                correct++;
            } else {
                System.out.println("Your answer is wrong!");
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
        }
        System.out.println("Better luck next time!");
    }
}
