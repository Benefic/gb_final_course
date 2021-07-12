package ru.benefic.geekhome.task.polymorphism;

public class Circle extends Shape {

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    void draw() {
        System.out.println("Drawing a Circle");
    }

    @Override
    double getSquare() {
        return Math.PI * (radius * radius);
    }

    @Override
    double getPerimeter() {
        return Math.PI * 2 * radius;
    }

    @Override
    void remove() {
        System.out.println("Removing a Circle");
    }
}
