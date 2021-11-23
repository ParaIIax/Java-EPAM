package ua.univer.task5;

public class ArrayRectangles {
    private Rectangle [] rectangle_array;

    public ArrayRectangles(int n) {
        this.rectangle_array = new Rectangle[n];
        for (int i = 0; i < n; ++i) {
            this.rectangle_array[i] = new Rectangle(0,0);
        }
    }
    public ArrayRectangles(Rectangle...rectangle_array) {
        for (int i = 0; i < rectangle_array.length; ++i) {
            this.rectangle_array = rectangle_array;
        }
    }

    public boolean addRectangle(Rectangle rectangle) {
        for (int i = 0; i < rectangle_array.length; ++i) {
            if (rectangle_array[i].getSideA() == 0 && rectangle_array[i].getSideB() == 0) {
                rectangle_array[i] = rectangle;
                return true;
            }
        }
        return false;
    }
    public int numberMaxArea() {
        Rectangle rectangleMaxArea = this.rectangle_array[0];
        int x = 0;
        for (int i = 0; i < rectangle_array.length; ++i) {
            if (rectangle_array[i].area() > rectangleMaxArea.area()) {
                rectangleMaxArea = rectangle_array[i];
                x = i;
            }
        }
        return x;
    }
    public int numberMinPerimeter() {
        Rectangle rectangleMaxArea = this.rectangle_array[0];
        int x = 0;
        for (int i = 0; i < rectangle_array.length; ++i) {
            if (rectangle_array[i].perimeter() < rectangleMaxArea.perimeter()) {
                rectangleMaxArea = rectangle_array[i];
                x = i;
            }
        }
        return x;
    }
    public int numberSquare() {
        int x = 0;
        for (Rectangle rectangle : rectangle_array) {
            if (rectangle.isSquare()) {
                ++x;
            }
        }
        return x;
    }

}
