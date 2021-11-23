package ua.univer.task5;

public class Rectangle {
    private double sideA;
    private double sideB;

    public double getSideA() {
        return sideA;
    }
    public double getSideB() {
        return sideB;
    }

    public Rectangle(double sideA, double sideB) {
        this.sideA = sideA;
        this.sideB = sideB;
    }
    public Rectangle(double sideA) {
        this.sideA = sideA;
        this.sideB = 5;
    }
    public Rectangle() {
        this.sideA = 4;
        this.sideB = 3;
    }

    public double area() {
        return sideA * sideB;
    }
    public double perimeter() {
        return (sideA + sideB) * 2;
    }
    public boolean isSquare() {
        return sideA == sideB;
    }
    public void replaceSides() {
        double x = sideA;
        sideA = sideB;
        sideB = x;
    }

}
