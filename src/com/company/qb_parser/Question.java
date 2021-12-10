package com.company.qb_parser;

import java.util.ArrayList;
import java.util.HashMap;

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
        return answer.toLowerCase().equals(model.toLowerCase());
    }

    public String getPrompt() {
        return prompt;
    }

    public String getModel() {
        return model;
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
