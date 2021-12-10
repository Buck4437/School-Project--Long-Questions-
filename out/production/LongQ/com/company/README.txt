- Introduction
This documentation is meant to provide instruction on using and writing .qb files,
which can be used to play quiz game.



- Reading .qb files
All questions are stored using .qb format.
.qb files can be read by the QuestionParser, located in the QuestionBank package.
To read a file, instantiate a new QuestionParser object (with the path name as argument).
Then, call the parse() method.
The method will either return a QuestionBank object if the file is parsed successfully,
or throw an exception if an error is present in the file.
The exception can be used to debug your .qb file.



- Writing .qb files
--- How .qb files are read
.qb files are read line by line, ignoring whitespaces around each line and empty lines
Therefore, indentations and empty lines can be added to improve the readability of the files.
All keywords are CASE-SENSITIVE and should be placed on separate lines.


--- Creating a question
All questions begin with the keyword QUESTION and ends with the keyword END.

e.g.

QUESTION
    # ... some code
END

QUESTION
    # ... some more code
END


--- Arguments
There are 3 arguments provided for the question.
To add an argument, start a line with the keyword for the argument, then enter your data.
End all arguments with the keyword END.

List of arguments:
> PROMPT (Required)
This data is displayed as the prompt in each question.

e.g.
QUESTION
    PROMPT
        What is love?
        This is the second line
    END
    # ... other arguments
END

> KEYWORDS (Optional, required if MODEL is undefined)
This data is presented as a list of words, which will be used to mark the player's answer.

e.g.
QUESTION
    KEYWORDS
        MERCURY
        BROMINE
    END
    # ... other arguments
END

> MODEL (Optional, required if KEYWORDS is undefined)
This data is displayed as the model answer.
If keywords are missing, this will be used to mark the player's answer.

e.g.
QUESTION
    MODEL
        Never gonna give you up
    END
    # ... other arguments
END

NOTE: DO NOT ENTER MULTIPLE LINES OF ANSWERS IF KEYWORDS IS UNDEFINED,
AS THE PLAYER'S ANSWER CAN ONLY CONTAIN A SINGLE LINE.


You can only put argument keywords directly inside a question.
Otherwise, an exception will be thrown.

e.g.
QUESTION
    not a valid keywords
END
=> Exception


An exception will also be thrown if keywords are repeated in a question.

e.g.
QUESTION
    PROMPT
        Q1
    END

    PROMPT
        Q2
    END
END
=> Exception



--- Commenting
You can turn a line into a comment by appending the line with a "#".
The parser will ignore all comments when parsing the file.
This allows you to comment on part of the file without affecting the parsing.

e.g.
# This is a comment
# This is another comment
# Parser will ignore these comments



--- Escaping
If you want to use a keyword or empty lines in your question, you can do so by appending the line with a "\".
This will escape the line and it will be treated as normal data.

e.g.
QUESTION
    PROMPT
        # This is valid! The prompt will become "END".
        \END
        # This will not be treated as an empty line and become part of the data.
        \
    END
    #... other arguments
END


--- Exceptions
Whenever a parser encounters an error, the parser will halt immediately.
Following that, an exception will be thrown alongside with a helpful message, telling you the location of the issue.
This will help you work out the problem and fix the issues.