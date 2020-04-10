package com.algo.datastructures.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * Trie data structure is very useful in doing string insert and do prefix and normal search. It is optimized
 * for prefix based string problems
 * Time complexities
 * Insert , Delete , Search : O(l) where l is length of string. It does not take o(n) where n= Number of strings
 * In most cases o(l) is very small compared to o(n).
 * Video solution: https://www.youtube.com/watch?v=AXjmTQ8LEoI (Tusar Roy)
 */

/* Trie Node as a map which has character as key and child pointer to store subsequent characters in string.
 * SO same for string wo have same prefix has only one entry/ path to travese util prefix match and new pointer will
 * be created post that*/
class TrieNode {
    Map<Character, TrieNode> charSet;
    boolean isEndOfWord;

    public TrieNode() {
        charSet = new HashMap<>();
        isEndOfWord = false;
    }
}

public class TrieImpl {
    TrieNode root = new TrieNode();

    /*  For a string, check if its prefix is already available. If available, go to its child node and check for next character in string
     *  and go to its child and so on. If prefix is not available, create  new trie nodes from the character where prefix is not available.*/
    public void insert(String key) {
        TrieNode temp = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!temp.charSet.containsKey(c))
                temp.charSet.put(c, new TrieNode());
            temp = temp.charSet.get(c);

        }
        // After insertion of last character, mark it as end of word.
        temp.isEndOfWord = true;
    }

    /* Check if prefix is available not the entire word, example if trie as te word 'abcd', a, ab, abc,.. etc are prefix. Prefix
     * must start from start of the string so cd, bc is not prefix of abcd*/
    public boolean hasPrefix(String prefix) {
        TrieNode temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            // IF pointer associated wit char is null, then given prefix is not available
            if (temp.charSet.get(c) == null)
                return false;
            else
                temp = temp.charSet.get(c);
        }
        // If we reach this point, it means prefix is available
        return true;
    }

    /* Check if entire string is available. Similar to above prefix function, but check entire word */
    public boolean hasString(String str) {
        TrieNode temp = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            // IF pointer associated wit char is null, then given string is not available
            if (temp.charSet.get(c) == null)
                return false;
            else
                temp = temp.charSet.get(c);
        }
        // Check if this is end of word, else this is not a full word, but only prefix
        return temp.isEndOfWord;
    }

    public void delete(String str) {
        if (hasString(str)) // Perform delete operation only if trie as the given string
            delete(root, str, 0);
    }

    /* Recursive delete from last character. We cannot delete all nodes corresponding to given string because there might be other strings whose prefix depend on this.
     * . We delete the entire node if there is no dependency else just mark end of word as false.*/
    public void delete(TrieNode currentNode, String str, int index) {
        char c = str.charAt(index);
        TrieNode nextNode = currentNode.charSet.get(c);
        if (index == str.length())
            return;
        if (index == str.length() - 1) { // Last character of te string
            nextNode.isEndOfWord = false;
            if (nextNode.charSet.size() == 0) // Means there is no dependency so we delete the node itself
                currentNode.charSet.remove(c);
        } else {
            // For other characters except last one, call recursively for remaining chars as we resolve dependency from last char
            delete(nextNode, str, index + 1);
            if (nextNode.charSet.size() == 0) // Means there is no dependency so we delete the node itself
                currentNode.charSet.remove(c);
        }
    }

    public static void main(String[] args) {
        TrieImpl trie = new TrieImpl();
        trie.insert("abc");
        trie.insert("abcd");
        trie.insert("abce");
        trie.insert("bcag");
        trie.insert("bca");
        trie.insert("wer");
        trie.insert("wert");


        System.out.println("Has Prefix ab ?= " + trie.hasPrefix("ab"));
        System.out.println("Has String ab ?= " + trie.hasString("ab"));
        System.out.println();

        System.out.println("Has prefix bca ?= " + trie.hasString("bca"));
        System.out.println("Has String bca ?= " + trie.hasString("bca"));
        System.out.println();

        System.out.println("Delete bca");
        trie.delete("bca");
        System.out.println("Has prefix bca ?= " + trie.hasString("bca"));
        System.out.println("Has String bca ?= " + trie.hasString("bca"));
        System.out.println();

        System.out.println("Delete wer");
        trie.delete("wer");
        System.out.println("Has prefix wer ?= " + trie.hasString("wer"));
        System.out.println("Has String wer ?= " + trie.hasString("wer"));
        System.out.println("Has String wert ?= " + trie.hasString("wert"));
        System.out.println();

        System.out.println("Has prefix qwerty ?= " + trie.hasString("qwerty"));
        System.out.println("Has String qwerty ?= " + trie.hasString("qwerty"));

        trie.delete("delete");
    }
}
