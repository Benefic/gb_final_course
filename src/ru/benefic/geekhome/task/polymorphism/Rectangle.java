package ru.benefic.geekhome.task.polymorphism;

public class Rectangle extends Shape {

    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    void draw() {
        System.out.println("Drawing a Rectangle");
    }

    @Override
    double getSquare() {
        return width * height;
    }

    @Override
    double getPerimeter() {
        return width * height * 2;
    }

    @Override
    void remove() {
        System.out.println("Removing a Rectangle");
    }
}
