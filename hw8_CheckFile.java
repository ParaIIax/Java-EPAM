package ua.univer.task8;

import java.util.Arrays;
import java.util.Locale;

public class CheckFile {
    public static String frequentWords(String text) {
        text = text.replaceAll("[^\sA-Za-zА-Яа-я0-9]", "");
        String[] words = text.split(" ");
        int maxWordsCount = 3;
        for (int i = 0; i < words.length; ++i)
            words[i] = words[i].toLowerCase(Locale.ROOT);

        int[] counts = new int[words.length];
        for (int i = 0; i < words.length; ++i) {
            int count = 0;
            for (int j = 0; j < words.length; ++j) {
                if (words[i].equals(words[j]))
                    count++;
            }
            counts[i] = count;
        }

        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts.length; j++) {
                if (counts[i] >= counts[j]) {
                    int x = counts[i];
                    counts[i] = counts[j];
                    counts[j] = x;
                    String y = words[i];
                    words[i] = words[j];
                    words[j] = y;
                }
            }
        }

        String result = "";
        result += words[0] + " ==> " + counts[0] + "\n";
        for (int i = 0; i < counts.length - 1; i++) {
            if (maxWordsCount != 1 && !result.contains(words[i + 1])) {
                result += words[i + 1] + " ==> " + counts[i + 1] + "\n";
                --maxWordsCount;
            }
        }
        String[] maxWords = result.split("\n");
        result = "";
        Arrays.sort(maxWords);
        for (int i = maxWords.length; i > 0; --i)
            result += maxWords[i - 1] + "\n";
        return result;
    }

    
    public static String longestWords(String text) {
        text = text.replaceAll("[^\sA-Za-zА-Яа-я0-9]", "");
        String[] words = text.split(" ");
        int maxWordsCount = 3;

        int[] lengths = new int[words.length];
        for (int i = 0; i < words.length; ++i) {
            lengths[i] = words[i].length();
        }

        for (int i = 0; i < lengths.length; i++) {
            for (int j = 0; j < lengths.length; j++) {
                if (lengths[i] >= lengths[j]) {
                    int x = lengths[i];
                    lengths[i] = lengths[j];
                    lengths[j] = x;
                    String y = words[i];
                    words[i] = words[j];
                    words[j] = y;
                }
            }
        }

        String result = "";
        result += words[0] + " ==> " + lengths[0] + "\n";
        for (int i = 0; i < lengths.length - 1; i++) {
            if (maxWordsCount != 1 && !result.contains(words[i + 1])) {
                result += words[i + 1] + " ==> " + lengths[i + 1] + "\n";
                --maxWordsCount;
            }
        }
        String[] maxWords = result.split("\n");
        result = "";
        for (int i = maxWords.length; i > 0; --i)
            result += maxWords[i - 1] + "\n";
        return result;
    }

    
    public static String wordDuplicates(String text) {
        text = text.replaceAll("[^\sA-Za-zА-Яа-я0-9]", "");
        int maxWordsCount = 3;
        String[] words = text.split(" ");
        String[] words1 = text.split(" ");
        String[] words2 = new String[maxWordsCount];
        for (int i = 0; i < words.length; ++i)
            words[i] = words[i].toLowerCase(Locale.ROOT);

        int[] counts = new int[words.length];
        for (int i = 0, index = 0; i < words.length; ++i) {
            int count = 0;
            for (int j = 0; j < words.length; ++j) {
                if (words[i].equals(words[j]))
                    count++;
            }
            if (count > 1) {
                counts[i] = count;
                words2[index] = words1[i];
                ++index;
                if (index > 2)
                    break;
            }
        }

        int sum = 0;
        for (int i = 0; i < words.length - 1; ++i) {
            sum += counts[i + 1];
        }
        if (sum == 0)
            return "There are no duplicates in the text";

        for (int i = 0; i < words2.length - 1; ++i) {
            if (words2[i].equals(words2[i + 1]))
                words2[i + 1] = "";
        }
        String result = "";
        for (int i = 0; i < words2.length; ++i)
            result += new StringBuilder(words2[i].toUpperCase()).reverse() + "\n";
        return result;
    }

}
