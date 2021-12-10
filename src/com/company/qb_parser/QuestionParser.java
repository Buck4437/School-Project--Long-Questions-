package com.company.qb_parser;

import java.util.*;

public class QuestionParser {

    private static final HashMap<String, String> KEYWORDS = new HashMap<>();
    private static final HashSet<String> STARTING_KEYS = new HashSet<>();

    private Stack<String> currentKeys = new Stack<>();
    private HashSet<String> usedKeys = new HashSet<>();
    private HashMap<String, ArrayList<String>> questionParams = new HashMap<>();

    static {
        KEYWORDS.put("start", "QUESTION");
        KEYWORDS.put("prompt", "PROMPT");
        KEYWORDS.put("model", "MODEL");
        KEYWORDS.put("keywords", "KEYWORDS");

        STARTING_KEYS.addAll(Arrays.asList("start", "prompt", "model", "keywords"));

        KEYWORDS.put("end", "END");
    }

    public QuestionParser() {}

    public Question parseLine(String line, int lineCount) throws ParserException {
        line = line.trim();

        // The line is an ending keyword
        if (equalsKeyword(line, "end")) {
            if (!parsingQuestion()) {
                throw new ParserException("End statement outside question", lineCount, line);
            }
            String key = currentKeys.pop();
            if (key.equals("start")) {
                Question q = new Question(questionParams);
                // The question has no prompt
                if (!q.hasPrompt()) {
                    throw new ParserException("Prompt is missing or empty for question" , lineCount, line);
                }
                // The question has no answer
                if (!q.hasModel() && !q.hasKeyword()) {
                    throw new ParserException("Answer is missing or empty for question", lineCount, line);
                }
                reset();
                return q;
            } else {
                usedKeys.add(key);
            }
            return null;
        }

        // The line is a starting keyword
        for (String key : STARTING_KEYS) {
            if (equalsKeyword(line, key)) {
                // The keyword is in an argument
                if (parsingArgument()) {
                    throw new ParserException("Keyword present in question arguments", lineCount, line);
                } else if (parsingQuestion()) {
                    // The keyword is in a question and start
                    if (key.equals("start")) {
                        throw new ParserException("Redeclaration of question inside a question", lineCount, line);
                    }
                    // The keyword has already been used
                    if (usedKeys.contains(key)) {
                        throw new ParserException("Reuse of a previously declared argument", lineCount, line);
                    }
                    // The keyword is outside a question and not start
                } else if (!key.equals("start")) {
                    throw new ParserException("Argument keywords found outside question", lineCount, line);
                }
                currentKeys.add(key);
                return null;
            }
        }

        // The line is outside a question and is not a keyword
        if (!parsingQuestion()) {
            throw new ParserException("Invalid keywords found outside question", lineCount, line);
        }

        // The line is inside the question but not inside an argument
        if (!parsingArgument()) {
            throw new ParserException("Invalid question argument", lineCount, line);
        }

        // The line is inside an argument
        // The line is backslashed
        if (isBackslashed(line)) {
            line = line.substring(1);
        }

        if (questionParams.get(getCurrentKey()) == null) {
            ArrayList<String> li = new ArrayList<>();
            li.add(line);
            questionParams.put(getCurrentKey(), li);
        } else {
            questionParams.get(getCurrentKey()).add(line);
        }

        return null;
    }

    private String getCurrentKey() {
        return currentKeys.peek();
    }

    private boolean isBackslashed(String line) {
        return line.charAt(0) == '\\';
    }

    public boolean parsingQuestion() {
        return currentKeys.size() > 0;
    }

    public boolean parsingArgument() {
        return currentKeys.size() == 2;
    }

    private void reset() {
        currentKeys = new Stack<>();
        questionParams = new HashMap<>();
        usedKeys = new HashSet<>();
    }

    private String getKeyword(String key) {
        return KEYWORDS.get(key);
    }

    private boolean equalsKeyword(String s1, String key) {
        return s1.equals(getKeyword(key));
    }

}
