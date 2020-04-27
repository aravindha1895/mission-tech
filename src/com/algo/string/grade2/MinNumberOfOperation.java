package com.algo.string.grade2;

/**
 * You will be given two strings. You need to find the minimum number of operations to be performed
 * on the first string to transform it into the second string. The only operation allowed is to
 * remove any character in the first string at ith position where i > 0 and inserting it at 0th position.
 * Example:
 * str1 = eabcd
 * str2 = abcde
 * Output: 4
 * eabcd -> deabc -> cdeab -> bcdea -> abcde
 * Before directly diving in to count the minimum number of operations to be performed,
 * check whether the first string can be transformed into the second string or not.
 * To verify this you need to check whether the two strings are anagrams to each other or not.
 * If the two strings are anagrams, count the number of minimum operations to be performed to transform
 * the first string into the second string. The idea is to start matching the characters from the end of
 * both strings. If the last characters match, then our task reduces to n-1 characters.
 * If last characters don’t match, then find the position of the matching character of the last character
 * of the second string in the first string. The difference between these two positions indicates that
 * these many characters of the first string must be moved to the front of the first string.
 * Time complexity: O(N)
 * Space complexity: O(1)
 */
public class MinNumberOfOperation {
    private static Integer minimumNumberOfOperations(String str1, String str2) {
        // If they are not anagrams, then str1 cannot be transformed to str2
        if (Anagrams.areAnagrams(str1, str2) == false) {
            return null;
        }
        int i, j;
        i = str1.length() - 1;
        j = str2.length() - 1;
        int count = 0;
        while (i >= 0) {
            // If character dont match, then swap is needed. So increment count
            if (str1.charAt(i) != str2.charAt(j)) {
                count++;
            } else j--;
            /*  Always move 1st str (i--) as we need to transform str1 to str2.
            If it was vice versa, then it would have been j-- unconditionally here, and i--
             in above else condition*/
            i--;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(minimumNumberOfOperations("statue of liberty ", "built to stay free"));
        System.out.println(minimumNumberOfOperations("eabcd", "abcde"));
    }
}
