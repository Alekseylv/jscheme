package edu.lang.jscheme;

import static edu.lang.jscheme.parser.SchemeParser.parseTokens;

import java.util.Scanner;

import edu.lang.jscheme.interpretor.SchemeInterpretor;
import edu.lang.jscheme.parser.SchemeParser;

public class App {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        SchemeInterpretor interpretor = new SchemeInterpretor();

        while (true) {
            System.out.println();
            System.out.print(">");
            String line = in.nextLine();
            System.out.println(parseTokens(line).flatMap(SchemeParser::parseAST).flatMap(SchemeParser::lexer).map(interpretor::eval));
        }
    }

}
