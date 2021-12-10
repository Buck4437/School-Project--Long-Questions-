package com.company.qb_parser;

public class ParserException extends Exception {
    public ParserException(String message, int lineCount, String line) {
        super(message + " at Line " + lineCount + ": >" + line);
    }

    public ParserException(String message, String line) {
        super(message + ": >" + line);
    }
}
