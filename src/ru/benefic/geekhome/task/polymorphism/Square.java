package ru.benefic.geekhome.task.polymorphism;

public class Square extends Shape {

    private final double size;

    public Square(double size) {
        this.size = size;
    }

    @Override
    void draw() {
        System.out.println("Drawing a Square");
    }

    @Override
    double getSquare() {
        return size * size;
    }

    @Override
    double getPerimeter() {
        return size * 4;
    }

    @Override
    void remove() {
        System.out.println("Removing a Square");
    }
}
