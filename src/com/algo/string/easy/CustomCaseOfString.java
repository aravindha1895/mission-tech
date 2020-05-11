package com.algo.string.easy;
/**
 * You will be given a String str and you need to convert that string into a custom case.
 * Such that the first letter of every word in the string should be in the lowercase and all the remaining letters should be in the uppercase.
 * Example:
 * String s = "I got an internship at UpGrad."
 * Output: "i gOT aN iNTERNSHIP aT uPGRAD."
 * Time complexity: O(N)
 * Space complexity: O(N)
 * */
public class CustomCaseOfString {
    private static void customCase(String str) {
        // Write your code here
        StringBuilder sb = new StringBuilder();
        str=str.trim(); // Trim extra spaces at start and end
        sb.append(Character.toLowerCase(str.charAt(0)));// First char is start of word, so always lowercase
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                sb.append(str.charAt(i));
                i++;
                // Ignore extra spaces
                while (i < str.length()) {
                    if (str.charAt(i) == ' ') i++;
                    else break;
                }
                i--; // Compensate for the above while loop exit condition
                if (i + 1 < str.length()) {
                    // After spaces, this character should be the start of the word
                    sb.append(Character.toLowerCase(str.charAt(i + 1)));
                    i++;
                    continue;
                }
            } else {
                sb.append(Character.toUpperCase(str.charAt(i)));
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {
        customCase("I ' m a s t u d e n t . I ' m p r e p a r i n g f o r m y i n t e r v i e w s .");
        customCase("    I got an internship at UpGrad.     ");
        customCase("I got an internship at UpGrad.");
    }
}
