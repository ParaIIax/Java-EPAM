package main.java.ua.univer.task3;
import org.junit.Test;
import javax.swing.*;
import static main.java.ua.univer.task3.Task3.*;
import static org.junit.Assert.*;

public class Task3Test {

    @Test
    public void testIsSorted() {
        int [] arr = {1, 2, 3, 4, 5};
        boolean actual = IsSorted(arr, arr.length, SortOrder.ASCENDING);
        assertEquals(true, actual);
    }

    @Test
    public void testTransform() {
        int [] arr = {1, 2, 3, 4, 5};
        int [] actual = Transform(arr, arr.length, SortOrder.ASCENDING);
        int [] expected = {1, 3, 5, 7, 9};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMultiArithmeticElements() {
        int actual = MultiArithmeticElements(5, 3, 4);
        assertEquals(6160, actual);
    }

    @Test
    public void testSumGeometricElements() {
        double actual = SumGeometricElements(100, 0.5);
        assertEquals(175.0, actual, 0.001);
    }
}
