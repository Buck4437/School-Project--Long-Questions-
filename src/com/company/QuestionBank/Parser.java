package com.company.QuestionBank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    private final ArrayList<Question> questions = new ArrayList<>();
    private final BufferedReader file;
    private final QuestionParser parser = new QuestionParser();
    private int lineCount = 0;

    public Parser(String filename) throws IOException {
        file = new BufferedReader(new FileReader(filename));
    }

    public QuestionBank parse() throws IOException, ParserException {
        String prevLine = "";
        String line = getNextLine();
        while (line != null) {
            if (!isEmpty(line) && !isComment(line)) {
                Question question = parser.parseLine(line, lineCount);
                if (question != null) {
                    questions.add(question);
                }
                prevLine = line;
            }
            line = getNextLine();
        }
        if (parser.parsingQuestion()) {
            throw new ParserException("Unexpected EOF", prevLine.trim());
        }
        return new QuestionBank(questions);
    }

    private String getNextLine() throws IOException {
        lineCount += 1;
        return file.readLine();
    }

    public boolean isEmpty(String s) {
        return s.trim().equals("");
    }

    public boolean isComment(String s) {
        return s.trim().charAt(0) == '#';
    }
}

