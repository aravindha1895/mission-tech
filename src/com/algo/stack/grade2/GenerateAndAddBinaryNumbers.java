package com.algo.stack.grade2;
/**
 * program that will take the input of two positive decimal integers and
 * return the sum of the two numbers in binary format. For example:
 * Sample Input:
 * 10
 * 5
 * Sample Output:
 * 1111
 * Method to represent binary number:
 * When you append ‘0’ to the binary form of a decimal integer ‘n’,
 * then it gives you the binary form of decimal integer ‘2n’.
 * When you append ‘1’ to the binary form of a decimal integer ‘n’,
 * then it gives you the binary form of decimal integer ‘2n+1’.
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

import java.util.LinkedList;
import java.util.Queue;

public class GenerateAndAddBinaryNumbers {

    /* We are generating binary numbers using queue */
    private static String generatePrintBinary(int n) {
        if (n == 0) return "0";
        int number = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add("1");
        while (true) {
            String bin = queue.remove();
            number++;
            if (number == n)
                return bin;
            else {
                queue.add(bin + "0");
                queue.add(bin + "1");
            }
        }
    }

    /* Normal binary addition is done here */
    /*Rules:
      0 + 0 = 0.
      0 + 1 = 1.
      1 + 1 = 10
     *  */
    public String binaryAddition(String s1, String s2) {
        //Write your code here
        int n1 = s1.length() - 1;
        int n2 = s2.length() - 1;
        /*Bring both strings to equal length to add. prepend 0s to shorter string*/
        if (n1 != n2) {
            String tempStr = "";
            int diff = Math.abs(n1 - n2);
            while (diff > 0) {
                tempStr += "0";
                diff--;
            }
            if (n1 > n2) {
                s2 = tempStr + s2;
                n2 = n1;
            } else {
                s1 = tempStr + s1;
                n1 = n2;
            }
        }
        StringBuilder ans = new StringBuilder();
        boolean carry = false;
        while (n1 >= 0 && n2 >= 0) {
            char c1 = s1.charAt(n1);
            char c2 = s2.charAt(n2);
            if (c1 == '1' && c2 == '1') {
                if (carry)
                    ans.insert(0, "1");
                else {
                    carry = true;
                    ans.insert(0, "0");
                }
            } else if ((c1 == '0' && c2 == '1') || (c1 == '1' && c2 == '0')) {
                if (carry)
                    ans.insert(0, "0");
                else {
                    ans.insert(0, "1");
                }
            } else { // c1=0;c2=0
                if (carry) {
                    carry = false;
                    ans.insert(0, "1");
                } else
                    ans.insert(0, "0");
            }
            n1--;
            n2--;
        }
        // At the end if there is carry, we append it at start
        if (carry)
            ans.insert(0, "1");
        return ans.toString();
    }

    private void generateAndAdd(int n1, int n2) {
        String first = generatePrintBinary(n1);
        String second = generatePrintBinary(n2);
        String result = binaryAddition(first, second);
        System.out.println(result);

    }

    public static void main(String[] args) {
        GenerateAndAddBinaryNumbers generateAndAddBinaryNumbers = new GenerateAndAddBinaryNumbers();
        generateAndAddBinaryNumbers.generateAndAdd(1001, 100);
        generateAndAddBinaryNumbers.generateAndAdd(0, 0);
        generateAndAddBinaryNumbers.generateAndAdd(9, 0);
        generateAndAddBinaryNumbers.generateAndAdd(14, 15);
        generateAndAddBinaryNumbers.generateAndAdd(5, 7);
    }
}
