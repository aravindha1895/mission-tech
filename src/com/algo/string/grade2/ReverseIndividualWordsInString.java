package com.algo.string.grade2;
/**
 * You will be given a string. You need to reverse the individual words of the string.
 * Example:
 * str = I’m a student. I’m preparing for my interviews.
 * Output: m’I a .tneduts m’I gniraperp rof ym .sweivretni
 * Time complexity: O(N)
 * Space complexity: O(N)
 * */
import java.util.Stack;

public class ReverseIndividualWordsInString {
    /* Stack is used to reverse a word */
    private static void reverseWords(String str) {
        Stack<Character> stack = new Stack<Character>();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<str.length();i++){
            // When it is not end of word (Space signifies EOW), pus to stack
            if(str.charAt(i)!=' '){
                stack.push(str.charAt(i));
            } else {
                // If char == ' ', it is end of word, pop out the existing word in stack to reverse the word
                while(!stack.isEmpty()){
                    sb.append(stack.pop());
                }
                sb.append(str.charAt(i)); // Append the encountered space too
            }
        }
        // Reversing the last word.
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        reverseWords("    I'm a student.   I'm    preparing    for   my    interviews.   ");
        reverseWords("I ' m a s t u d e n t . I ' m p r e p a r i n g f o r m y i n t e r v i e w s ");
        reverseWords("I'm a student. I'm preparing for my interviews.");
    }

}
