package SPO;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by user on 28.03.2016.
 */
public class Processor {
    public static String count(String str) {

//        Scanner s=new Scanner(System.in);
//        String str = s.nextLine();
        if(str.contains("+")||str.contains("-")||str.contains("*")||str.contains("/")) {
            Lexer lex = new Lexer(str);
            ArrayList<Token> tokens = lex.strToTokens();
            Parser parser = new Parser(tokens);
            ExprNode e = parser.expression();
            WalkTree walkTree = new WalkTree();
            walkTree.walkTree(e);

            return Integer.toString(walkTree.calcTree(e));
        }
        return str;
    }

}
