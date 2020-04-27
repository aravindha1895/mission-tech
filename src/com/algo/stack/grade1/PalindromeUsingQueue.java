package com.algo.stack.grade1;
/**
 * Check if a string is palindrome using queue. Stack can also be used.
 * Time complexity: O(N)
 * Space complexity: O(N) -> Extra queue
 * */
import java.util.LinkedList;
import java.util.Queue;

public class PalindromeUsingQueue {
    public static void checkPalindrome(String input) {
        Queue<Character> q = new LinkedList<>();
        // Enqueue from the end of the string
        for(int i=input.length()-1;i>=0;i--)
            q.add(input.charAt(i));
        boolean isPalindrome = true;
        int k=0;
        // Dequeue and check with original input if it is a palindrome
        while(!q.isEmpty()){
            char x= q.remove();
            if(x!=input.charAt(k)){
                isPalindrome = false;
                break;
            }
            k++;
        }
        if(isPalindrome)
            System.out.println(input + " is a palindrome.");
        else
            System.out.println(input + " is not a palindrome.");
    }

    public static void main(String[] args) {
        checkPalindrome("DAD");
        checkPalindrome("1110111");
        checkPalindrome("Abaaa");
        checkPalindrome(".&.*.&.");
        checkPalindrome("DAd");
    }
}
