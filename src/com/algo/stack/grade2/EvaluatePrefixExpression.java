package com.algo.stack.grade2;

import java.util.Stack;

/**
 * Prefix notation is also known as ‘polish notation’ and is a format of writing algebraic expressions where
 * operators are written before the operands like x + y can be written as + x y in prefix notation.
 * Given an algebraic expression in prefix notation, your task is to evaluate and print its result
 * using the stack data structure.
 * * For example:
 * * +-1068
 * Logic:
 * To solve a given prefix expression, you need to perform the following steps:
 * Begin from the right-hand side of the expression.
 * Add all the operands to the stack until an operator is encountered.
 * Here, 8 6 and 10 will be added to the stack.
 * When an operator comes, pop out the two operands from the stack and evaluate the expression by putting the operator in between the operands.
 * Later, push the result back in the stack.
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * Some tough examples:
 */
public class EvaluatePrefixExpression {
    private static int evaluatePrefixExpression(String input) {
        Stack<String> stack = new Stack<String>();
        int n = input.length();
        for (int i = n - 1; i >= 0; i--) {
            Character curr = input.charAt(i);
            if (Character.isDigit(curr)) {
                stack.push("" + curr);
            } else if (((char) curr) != '(' && ((char) curr) != ')') {
                int p1 = Integer.valueOf(stack.pop());
                int p2 = Integer.valueOf(stack.pop());
                int result = 0;
                switch ((char) curr) {
                    case '+':
                        result = p1 + p2;
                        break;
                    case '-':
                        result = p1 - p2;
                        break;
                    case '*':
                        result = p1 * p2;
                        break;
                    case '/':
                        result = p1 / p2;
                        break;
                    default:
                        result = p1 + p2;
                        break;
                }
                stack.push("" + result);
            }
        }
        return Integer.valueOf(stack.pop());
    }

    public static void main(String[] args) {
        System.out.println(evaluatePrefixExpression("*-6/52-/841"));
        System.out.println(evaluatePrefixExpression("*7(*017)"));
        System.out.println(evaluatePrefixExpression("(+7(*812)(*2(+94)7)3)"));
    }
}
