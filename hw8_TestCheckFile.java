package ua.univer.task8;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCheckFile {
    @Test
    public void testFrequentWords() {
        assertEquals("the ==> 2\n" +
                "of ==> 2\n" +
                "built ==> 1\n", CheckFile.frequentWords(ReadFile.readFromFile("C:/test/note.txt")));
    }
    @Test
    public void LongestWords() {
        assertEquals("twovessel ==> 9\n" +
                "Armstrong ==> 9\n" +
                "Whitworth ==> 9\n", CheckFile.longestWords(ReadFile.readFromFile("C:/test/note.txt")));
    }
    @Test
    public void WordDuplicates() {
        assertEquals("EHT\n" + "FO\n\n", CheckFile.wordDuplicates(ReadFile.readFromFile("C:/test/note.txt")));
    }
}
