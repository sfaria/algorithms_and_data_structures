package com.sfaria.iq;

public final class ArraysAndStrings {

    // -------------------- Questions --------------------

    /**
     * Implement an algorithm to determine if a string has all unique characters.
     * What if you cannot use additional data structures?
     */
    public static void question_1_1(String input) {
        int length = input.length();
        for (int i = 0; i < length; i++) {
            char currentChar = input.charAt(i);
            for (int j = 0; j < length; j++) {
                if (j != i && currentChar == input.charAt(j)) {
                    System.out.println("This string has dupes!");
                    return;
                }
            }
        }
        System.out.println("This string has no dupes!");
    }

    /**
     * Given two strings, write a method to decide if one is a permutation of the other.
     */
    public static boolean question_1_3(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] counts = new int[256];
        for (int i = 0; i < s1.length(); i++) {
            int character = s1.charAt(i);
            counts[character]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            int character = s2.charAt(i);
            if (counts[character]-- < 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Write a method to replace all spaces in a string with '%20'. You may assume that the
     * string has sufficient space at the end of the string ot hold the additional characters,
     * and that you are given the "true" length of the string. (Note: if implementing in Java,
     * please use a character array so that you can perform this operation in place.)
     */
    public static char[] question_1_4(char[] str, int length) {
        int spaceCount = 0;
        for (int i = 0; i < length; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }

        int writeIndex = length + (spaceCount * 2) - 1;
        for (int bufferIndex = length - 1; bufferIndex >= 0; bufferIndex--) {
            char currentChar = str[bufferIndex];
            if (currentChar != ' ') {
                str[writeIndex] = currentChar;
                writeIndex--;
            } else {
                str[writeIndex--] = '0';
                str[writeIndex--] = '2';
                str[writeIndex--] = '%';
            }
        }
        return str;
    }

    /**
     * Implement a method to perform basic string compression using the counts of repeated
     * characters. For example, the string aabcccccaaa would become a2b1c5a3. If the "compressed"
     * string would not become smaller than the original string, your method should return the
     * original string. You can assume the string has only upper and lower case letters (a-z).
     */
    public static String question_1_5(String s) {
        int originalLength = s.length();
        int newLength = 0;

        StringBuilder sb = new StringBuilder();
        int currentChar = -1;
        int currentCharCount = 0;
        for (int i = 0; i < originalLength; i++) {
            char c = s.charAt(i);
            if (currentChar == -1) {
                // init
                currentChar = c;
                currentCharCount = 1;
            } else {
                if (currentChar != c) {
                    sb.append((char) currentChar);
                    sb.append(currentCharCount);
                    if (sb.length() >= originalLength) {
                        return s;
                    }
                    currentChar = c;
                    currentCharCount = 0;
                }
                currentCharCount++;
            }
        }

        sb.append((char) currentChar);
        sb.append(currentCharCount);
        if (sb.length() >= originalLength) {
            return s;
        }

        return sb.toString();
    }

    /**
     * Given an image represented by an NxN matrix, where each pixel in the image is 4 bytes,
     * write a method to rotate the image by 90 degrees. Can you do this in place?
     */
    public static int[][] problem_1_6(int[][] matrix) {
        for (int y = 0; y < matrix.length; y++) {
            int[] row = matrix[y];

        }

        return null;
    }

    // -------------------- Main --------------------

    public static void main(String[] args) {
        System.err.println(question_1_5("aabcccccaaa"));
        System.err.println(question_1_5("aab"));
        System.err.println(question_1_5("aaaaaaaaaaaaaaaaaaaaaaaaax"));
    }
}
