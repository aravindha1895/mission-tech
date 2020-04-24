package com.algo.puzzle;
//TODO: Time complexity update
/**
 * You will be given a set of words and an m x n matrix of letters.
 * You have to find out all the words which are possible from the given matrix.
 * Every two adjacent letters in the possible word, should be neighbours(consider neighbours in all directions,
 * i.e. horizontal, vertical and diagonal) to each other in the given matrix.
 * Note:
 * No word should contain multiple instances of the same cell.
 * Example:
 * If the given set of words is {APPLE, APP, LEMON, GRAPE} and the matrix of letters is
 * A	E	M	L	N
 * L	P	P	O	E
 * Then print all the possible words from the above matrix
 * The possible words will be:
 * APP
 * APPLE
 * LEMON
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class WordsInBogie {
    List<String> wordsFound = new ArrayList<>();

    public boolean isStartingWithPrefix(String prefix, String[] wordSet) {
        for (int i = 0; i < wordSet.length; i++) {
            if (wordSet[i].startsWith(prefix))
                return true;
        }
        return false;
    }

    public boolean isWordInGivenSet(String word, String[] wordSet) {
        for (int i = 0; i < wordSet.length; i++) {
            if (wordSet[i].equals(word))
                return true;
        }
        return false;
    }

    public boolean isSafe(char bogie[][], int x, int y, boolean[][] visited) {
        if (x >= 0 && y >= 0 && x < bogie.length && y < bogie[x].length && visited[x][y] == false)
            return true;
        return false;
    }

    public void matchPattern(char bogie[][], int x, int y, String[] wordSet, StringBuilder wordFormed, boolean[][] visited) {
        visited[x][y] = true;
        // Can movie in Diagonal, horizontal, Vertical directions
        int[] rows = {0, 0, 1, 1, 1, -1, -1, -1};
        int[] cols = {-1, 1, -1, 0, 1, -1, 0, 1};
        int row, col;
        for (int i = 0; i < 8; i++) {
            row = x + rows[i];
            col = y + cols[i];
            if (isSafe(bogie, row, col, visited)) {
                wordFormed.append(bogie[row][col]);
                // Check if any of the words has prefix after appending new character
                if (isStartingWithPrefix(wordFormed.toString(), wordSet)) {
                    if (isWordInGivenSet(wordFormed.toString(), wordSet)) {
                        wordsFound.add(wordFormed.toString());
                        /* Proceed even if word is found, because this word might be a prefix of another word */
                        matchPattern(bogie, row, col, wordSet, wordFormed, visited);
                    } else {
                        matchPattern(bogie, row, col, wordSet, wordFormed, visited);
                    }
                } else { // If not remove the last appended character
                    wordFormed.deleteCharAt(wordFormed.length() - 1);
                }
            }
        }
    }

    public void resetVisited(boolean[][] visited) {
        for (int i = 0; i < visited.length; i++)
            for (int j = 0; j < visited[i].length; j++)
                visited[i][j] = false;
    }

    public static void main(String args[]) {
        // Set of words to be found
        String setOfWords[] = {"APPLE", "APP", "LEMON", "GRAPE"};
        // The puzzle matrix
        char[][] boggie = {{'A', 'E', 'M', 'L', 'N'},
                {'L', 'P', 'P', 'O', 'E'}};
        WordsInBogie wordsInBogie = new WordsInBogie();
        int m = boggie.length;
        int n = boggie[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                /* Each word starts with different letter, so clear visited each time
                   because already visited letter can be start of some other word.
                 */
                wordsInBogie.resetVisited(visited);
                // If none of the word starts with this character, dont proceed with 'this' as starting letter
                if (wordsInBogie.isStartingWithPrefix(String.valueOf(boggie[x][y]), setOfWords))
                    wordsInBogie.matchPattern(boggie, x, y, setOfWords, new StringBuilder().append(boggie[x][y]), visited);
            }
        }
        // wordsInBogie.wordsFound.sort((o1, o2) -> o1.compareTo(o2));// Sort words in lexicographical order
        wordsInBogie.wordsFound.sort(Comparator.naturalOrder());// Replacement of above line to sort
        System.out.println("The words found are:");
        for (int i = 0; i < wordsInBogie.wordsFound.size(); i++) {
            System.out.println(wordsInBogie.wordsFound.get(i));
        }
    }
}
