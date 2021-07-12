package ru.benefic.geekhome.task.polymorphism;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();

        shapes.add(new Circle(5.5));
        shapes.add(new Square(6.8));
        shapes.add(new Rectangle(3.4, 7.8));

        for (Shape shape : shapes) {
            drawShape(shape);
            System.out.println("P=" + shape.getPerimeter());
            System.out.println("S=" + shape.getSquare());
            removeShape(shape);
        }
    }

    public static void drawShape(Shape shape){
        shape.draw();
    }
    public static void removeShape(Shape shape){
        shape.remove();
    }
}
