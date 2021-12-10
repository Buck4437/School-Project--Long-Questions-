package com.company.QuestionBank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question {

    private final String prompt;
    private final String model;
    private final ArrayList<String> keywords;

    public Question(HashMap<String, ArrayList<String>> params) {
        this.prompt = params.get("prompt") == null ? "" : String.join("\n", params.get("prompt"));
        this.model = params.get("model") == null ? "" : String.join("\n", params.get("model"));
        this.keywords = params.get("keywords");
    }

    public boolean verify(String answer) {
        boolean correct;
        if (hasKeyword()) {
            System.out.println("The keywords are:\n" + keywords);
            ArrayList<String> ans_key = keywordsInAns(answer);
            System.out.println("Your answer contains these keywords:\n" + ans_key);

            correct = keywords.size() == ans_key.size();
        } else {
            if (hasModel()) {
                printModel();
            }
            correct = answer.toLowerCase().equals(model.toLowerCase());
        }
        if (correct) {
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect!");
            if (hasModel() && hasKeyword()) {
                printModel();
            }
        }
        System.out.println();
        return correct;
    }

    private ArrayList<String> keywordsInAns(String answer) {
        ArrayList<String> kw = new ArrayList<>();
        for (String keyword : keywords) {
            Pattern p = Pattern.compile("\\b" + keyword.toLowerCase() + "\\b");
            Matcher m = p.matcher(answer.toLowerCase());
            if (m.find()) {
                kw.add(keyword);
            }
        }
        return kw;
    }

    public void printModel() {
        System.out.println("The model answer is: " + model);
    }

    public String getPrompt() {
        return prompt;
    }

    public boolean hasPrompt() {
        return !prompt.trim().equals("");
    }

    public boolean hasModel() {
        return !model.trim().equals("");
    }

    public boolean hasKeyword() {
        return keywords != null && keywords.size() != 0;
    }

    @Override
    public String toString() {
        return "Question{" +
                "prompt='" + prompt + '\'' +
                ", model='" + model + '\'' +
                ", keywords=" + keywords +
                '}';
    }
}
