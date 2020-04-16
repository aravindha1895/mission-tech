package com.algo.dynamicprogramming.grade4;
/**
 * Given a string find minimum number of partitions / splits that has to made to make te string palindrome
 * Ex: abcbm -> Min split is 3 -> a | bcb | m so that eac part is a palindrome
 * If the given string is already palindrome (Ex: aaa ) min split should be 0
 *
 * Time Complexity:  O(n^2) , O(n^2 * l) in worst case were l = length of string. l is te time
 *  * taken to determine if portion of string is palindrome
 * Auxiliary Space: O(n^2)
 * */
public class PalindromePartitioning {
    public static void main(String[] args) {
        System.out.println("Test case 1 String : abcbm");
        System.out.println("Min partition needed = "+getMinimumPartition("abcbm"));
        System.out.println("Test case 2 String : a");
        System.out.println("Min partition needed = "+getMinimumPartition("a"));
        System.out.println("Test case 3 String : abcde");
        System.out.println("Min partition needed = "+getMinimumPartition("abcde"));
        System.out.println("Test case 4 String : abcba");
        System.out.println("Min partition needed = "+getMinimumPartition("abcba"));
    }

    private static boolean isPalindrome(String str){
        return new StringBuilder(str).reverse().toString().equals(str);
    }
    private static int getMinimumPartition(String str) {

        int n=str.length();
        int partition[][] = new int [n][n];
        for(int i=0;i<n;i++)
            partition[i][i]=0;
        for(int level=2;level<=n;level++){
            for(int i=0;i<n-level+1;i++){
                int j=level+i-1;
                if(isPalindrome(str.substring(i,j+1)))
                    partition[i][j]=0;
                else {
                    partition[i][j]=Integer.MAX_VALUE;
                    for(int k=i;k<j;k++){
                        partition[i][j]=Math.min(partition[i][j],1+partition[i][k]+partition[k+1][j]);
                    }
                }
            }
        }
        System.out.println("Dynamic programming matrix view");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                System.out.print(partition[i][j]+" ");
            }
            System.out.println();
        }
        return partition[0][n-1];

    }
}
