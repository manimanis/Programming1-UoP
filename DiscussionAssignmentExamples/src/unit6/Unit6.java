package unit6;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author manianis
 */
public class Unit6 {
    public static void main(String[] args) {
        Drawing drawing = new Drawing();
        drawing.addForm(new Circle());
        drawing.addForm(new Square());
        drawing.addForm(new Triangle());
        drawing.draw();
    }
}

abstract class Shape {

    protected String type;
    protected Rectangle rectangle;

    public Shape(String type, Rectangle rectangle) {
        this.type = type;
        this.rectangle = rectangle;
    }

    public void setPosition(Point origin) {
        rectangle.setLocation(origin);
    }

    public void setSize(Dimension size) {
        rectangle.setSize(size);
    }

    public abstract void draw();
}

class Circle extends Shape {

    public Circle() {
        super("Circle", new Rectangle(0, 0, 200, 200));
    }

    @Override
    public void draw() {
        // Code to draw a circle
    }
}

class Square extends Shape {

    public Square() {
        super("Rectangle", new Rectangle(0, 0, 200, 200));
    }

    @Override
    public void draw() {
        // Code to draw a rectangle
    }
}

class Triangle extends Shape {

    protected Point summitPoint;

    public Triangle() {
        super("Triangle", new Rectangle(0, 0, 200, 200));
        summitPoint = new Point((int) (rectangle.getWidth() / 2), 0);
    }

    @Override
    public void draw() {
        // draw a triangle
    }
}

class Drawing extends Shape {

    private ArrayList<Shape> shapes = new ArrayList<>();

    public Drawing() {
        super("Drawing", new Rectangle(0, 0, 200, 200));
    }
    
    public void addForm(Shape shape) {
        shapes.add(shape);
    }
    
    @Override
    public void draw() {
        for (Shape shape : shapes) {
            shape.draw();
        }
    }   
}
