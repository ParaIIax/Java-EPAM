package ua.univer.task5;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestRectangle {
    @Test
    public void testArea() {
        assertEquals(35, new Rectangle(5, 7).area(), 0.01);
    }
    @Test
    public void testPerimeter() {
        assertEquals(24, new Rectangle(5, 7).perimeter(), 0.01);
    }
    @Test
    public void testIsSquare() {
        assertEquals(false, new Rectangle(5, 7).isSquare());
    }
    @Test
    public void testReplaceSides() {
        Rectangle rectangle = new Rectangle(5, 7);
        rectangle.replaceSides();
        assertEquals(7, rectangle.getSideA(), 0.01);
        assertEquals(5, rectangle.getSideB(), 0.01);
    }

}
