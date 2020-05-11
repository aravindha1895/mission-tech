package com.algo.basics;

/**
 * You are given an array of non-negative integers. These non-negative integers(d(i))of the array represent the
 * y coordinate and the index i of the array represents the x coordinate and forms the ordered pair as (i, d(i))
 * Now the vertical lines are drawn to each point from x-axis and you need to identify the two vertical lines such that,
 * along with the x-axis, the vertical lines form a container that can hold maximum water.
 * Consider the distance between two lines as the length of the container, height till which water can be stored
 * as the height of the container and assume breadth of the container as 5 units. Find the maximum volume of water
 * that can be stored in the container for a given input array of non-negative integers.
 * Example:
 * arr[] = { 4, 6, 5, 7, 9}
 * Output: 90
 * Explanation: Lines 6 and 9  are at a distance of 3 units. Maximum height till which water can be
 * stored = min(6,9) = 6units. So,
 * Length of container = 3 units
 * Breadth of container = 5 units
 * Height of container = 6 units
 * Volume = 3*5*6 = 90 cubic units
 * Apply the above logic on the above given example.
 * arr[] = { 4, 6, 5, 7, 9}
 * leftIndex=0 and rightIndex=n-1=4
 * (a0,a4) = (4,9) = 4*min(4,9)*5 = 80
 * 4 is less than 9 so leftIndex=1
 * (a1,a4) = (6,9) = 3*min(6,9)*5 = 90
 * 6 is less than 9 so leftIndex=2
 * (a2,a4) = (5,9) = 2*min(5,9)*5 = 50
 * 5 is less than 9 so leftIndex=3
 * (a3,a4) = (7,9) = 1*min(7,9)*5 = 35
 * 90 is maximum of all so the output is 90.
 * Time complexity O(N/2)=O(N).
 * Space complexity: O(1)
 */
public class ContainerWithMoreWater {
    private static void maxVolumeWater(int[] arr) {
        int n = arr.length;
        int l = 0, r = n - 1;
        long maxVolume = 0;
        while (l < r) {
            long currLength = Math.min(arr[l], arr[r]); // Length of actual container is the minimum of left and right length
            int width = r - l; // Right index - Left index = Width
            if (currLength * width * 5 > maxVolume) // Breadth of container is given as 5 and volume= l*b*h
                maxVolume = currLength * width * 5; // Breadth is given as 5 in problem statement
            /* If value in left is less then value at r move left, else move right, by this way we would have calculated
             * the volume or all possible maxVolume candidates */
            if (arr[l] < arr[r]) l++;
            else r--;
        }
        System.out.println(maxVolume + " cubic units");
    }

    public static void main(String[] args) {
        maxVolumeWater(new int[]{3, 1, 2, 4, 5});
        maxVolumeWater(new int[]{3, 6, 19, 23, 14, 14, 54, 32, 10, 9, 21, 27, 33, 39, 75, 17, 100, 18, 1, 2});
        maxVolumeWater(new int[]{4, 6, 5, 7, 9});
    }
}
