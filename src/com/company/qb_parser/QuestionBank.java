package com.company.qb_parser;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionBank {

    private final ArrayList<Question> questionList;
    private ArrayList<Question> currentQuestionList;

    public QuestionBank(ArrayList<Question> questions) {
        this.questionList = new ArrayList<>(questions);
        this.currentQuestionList = new ArrayList<>(questions);
    }

    public void reset() {
        currentQuestionList = new ArrayList<>(questionList);
    }

    public void randomize() {
        Collections.shuffle(currentQuestionList);
    }

    public Question getQuestion() {
        if (currentQuestionList.size() == 0) {
            return null;
        }
        return currentQuestionList.remove(0);
    }

    public String toString() {
        String str = "";
        for (Question q: currentQuestionList) {
            str += (q + "\n");
        }
        return str;
    }

}
