package edu.lang.jscheme;

import static edu.lang.jscheme.parser.SchemeParser.parseTokens;

import java.util.Scanner;

import edu.lang.jscheme.data.SchemeFunctionApplication;
import edu.lang.jscheme.interpretor.SchemeInterpreter;
import edu.lang.jscheme.parser.SchemeParser;

public class REPL {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        SchemeInterpreter interpreter = new SchemeInterpreter();

        while (true) {
            System.out.println();
            System.out.print(">");
            String line = in.nextLine();
            System.out.println(parseTokens(line).flatMap(SchemeParser::parseAST).flatMap(SchemeParser::lexer)
                    .map(interpreter::eval).map(x -> x.toSchemeString().string));
        }
    }


    /*

    (define (foreach l f) (if (null? l) () (foreach (cdr l) f (f (car l)))))
    (define (generate n) ((define (gen n acc) (if (< 0 n) (gen (- n 1) (cons n acc)) acc)) n ()))

    (foreach (generate 100) print)

     */
}
