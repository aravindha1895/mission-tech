package com.algo.stack.grade1;

import java.util.Stack;

/**
 * For any programming language structure or for any algebraic expression, it is mandatory to have
 * all parenthesis “( )” balanced. A balanced algebraic expression is given to you that may contain opening
 * and closing parenthesis, however, there may be instances where these parentheses are duplicated.
 * For example:
 * (((1*2))+5)
 * Here, 1*2 is enclosed within two pairs of parenthesis whereas just one pair was enough.
 * Your task is to detect the duplicate parenthesis in such given balanced algebraic expression.
 * Time complexity: O(N)
 * Space complexity: O(N)
 */
public class DetectDuplicateParenthesis {
    public static void findDuplicateParenthesis(String inputString) {

        String in = inputString;
        Stack<Character> s = new Stack<Character>();
        int i;
        for (i = 0; i < in.length(); i++) {
            char c = in.charAt(i);
            if (c != ')')
                s.push(c);
            else {
                if (!s.isEmpty() && s.peek() == '(')
                    break;
                else {
                    while (!s.isEmpty() && s.peek() != '(')
                        s.pop();
                    if (!s.isEmpty()) s.pop();// Popping out '(' parenthesis
                }
            }
        }
        /*Actually the initial logic if(s.isEmpty()) used is wrong it fails is there are no parenthesis
        below condition is right
        s.isEmpty() is used only to check balanced expr not duplicate parenthesis as in this case*/
        // if(s.isEmpty())
        if (i == in.length()) // We would have broke out of loop in case of duplicate and this case would have failed
            System.out.println(inputString+" does not contain duplicate parenthesis");
        else
            System.out.println(inputString+" contain duplicate parenthesis");
    }

    public static void main(String[] args) {
        findDuplicateParenthesis("((a+3)/2)");
        findDuplicateParenthesis("UpGrad");
        findDuplicateParenthesis("(((a+1))+(b+1))");
        findDuplicateParenthesis("(((a+1))+((b+1)))");
    }
}
