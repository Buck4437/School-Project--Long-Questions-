package com.company.QuestionBank;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionBank {

    private final ArrayList<Question> currentQuestionList;

    public QuestionBank(ArrayList<Question> questions) {
        this.currentQuestionList = new ArrayList<>(questions);
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
        StringBuilder str = new StringBuilder();
        for (Question q: currentQuestionList) {
            str.append(q).append("\n");
        }
        return str.toString();
    }

}
