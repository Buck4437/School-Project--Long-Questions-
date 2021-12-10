package com.company;

import com.company.QuestionBank.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParserException {
        QuestionBank qb = new Parser("./src/com/company/answers.qb").parse();
        qb.randomize();
        Game.play(qb);
    }

}
