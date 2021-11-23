package ua.univer.task5;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestArrayRectangles {
    @Test
    public void testAddRectangle() {
        ArrayRectangles rectangles = new ArrayRectangles(5);
        assertEquals(true, rectangles.addRectangle(new Rectangle(5, 7)));
    }
    @Test
    public void testNumberMaxArea() {
        ArrayRectangles rectangles = new ArrayRectangles(new Rectangle(4, 3), new Rectangle(5, 7), new Rectangle(4, 5));
        assertEquals(1, rectangles.numberMaxArea());
    }
    @Test
    public void testNumberMinPerimeter() {
        ArrayRectangles rectangles = new ArrayRectangles(new Rectangle(4, 3), new Rectangle(5, 7), new Rectangle(4, 5));
        assertEquals(0, rectangles.numberMinPerimeter());
    }
    @Test
    public void testNumberSquare() {
        ArrayRectangles rectangles = new ArrayRectangles(5);
        rectangles.addRectangle(new Rectangle(5, 7));
        assertEquals(4, rectangles.numberSquare());
    }

}
