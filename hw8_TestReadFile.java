package ua.univer.task8;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class TestReadFile {
    @Test
    public void testReadFromFile() {
        String words = ReadFile.readFromFile("C:/test/note.txt");
        assertArrayEquals(("Bahia was the lead " +
                "ship of a two-vessel class of cruisers " +
                "built for Brazil by the British company Armstrong Whitworth.").split(" "), words.split(" "));
    }
}
