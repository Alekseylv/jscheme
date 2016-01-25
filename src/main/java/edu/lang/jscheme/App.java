package edu.lang.jscheme;

import edu.lang.jscheme.parser.Parser;

import java.util.Scanner;

import static edu.lang.jscheme.parser.Parser.parseTokens;

public class App {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.print(">");
            String line = in.nextLine();
            System.out.println(parseTokens(line).flatMap(Parser::parseAST).flatMap(Parser::parseExpressions));
        }
    }


}
