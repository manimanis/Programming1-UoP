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
        
        Shape shape = new Circle();
        shape.draw();
    }
}

abstract class Shape {

    protected String type;
    protected Rectangle rectangle;

    public Shape(String type, Rectangle rectangle) {
        this.type = type;
        this.rectangle = rectangle;
    }
    
    public Point getPosition() {
        return rectangle.getLocation();
    }

    public void setPosition(Point origin) {
        rectangle.setLocation(origin);
    }
    
    public Dimension getSize() {
        return rectangle.getSize();
    }

    public void setSize(Dimension size) {
        rectangle.setSize(size);
    }
    
    public void translate(int dx, int dy) {
        rectangle.x += dx;
        rectangle.y += dy;
    }

    public abstract void draw();
}

class Circle extends Shape {

    public Circle() {
        super("Circle", new Rectangle(0, 0, 200, 200));
    }

    @Override
    public void draw() {
        Point center = new Point((int) rectangle.getWidth() / 2,
                (int) rectangle.getHeight() / 2);
        int radius = center.x > center.y ? center.y : center.x;
        System.out.println("The circle center is " + center);
        System.out.println("The circle radius is " + radius);
    }
}

class Square extends Shape {

    public Square() {
        super("Rectangle", new Rectangle(0, 0, 200, 200));
    }

    @Override
    public void draw() {

    }
}

class Triangle extends Shape {

    protected Point summitPoint;

    public Triangle() {
        super("Triangle", new Rectangle(0, 0, 200, 200));
        summitPoint = new Point((int) (rectangle.getWidth() / 2), 0);
    }
    
    public void moveSummit(int toPos) {
        summitPoint.x = toPos;
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
