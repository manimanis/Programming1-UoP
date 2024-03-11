/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit7;

import java.awt.Graphics;

/**
 *
 * @author Cyberbox
 */
public class Triangle extends Shape {
    
    int[] xPoints = new int[4];
    int[] yPoints = new int[4];

    public Triangle() {
        this(0, 0, 100, 100);
    }   
    
    public Triangle(int xPos, int yPos, int width, int height) {
        super("Triangle", xPos, yPos, width, height);
        setShapeX(xPos);
    }

    @Override
    public void setShapeX(int x) {
        super.setShapeX(x);
        refreshPolygonCoords();
    }

    @Override
    public void setShapeY(int y) {
        super.setShapeY(y);
        refreshPolygonCoords();
    }

    @Override
    public void setShapeWidth(int width) {
        super.setShapeWidth(width);
        refreshPolygonCoords();
    }

    @Override
    public void setShapeHeight(int height) {
        super.setShapeHeight(height);
        refreshPolygonCoords();
    }
        
    private void refreshPolygonCoords() {
        xPoints[0] = shapeRect.x;
        yPoints[0] = shapeRect.y + shapeRect.height;
        
        xPoints[1] = shapeRect.x + shapeRect.width;
        yPoints[1] = shapeRect.y + shapeRect.height;
        
        xPoints[2] = shapeRect.x + shapeRect.width / 2;
        yPoints[2] = shapeRect.y;
        
        xPoints[3] = shapeRect.x;
        yPoints[3] = shapeRect.y + shapeRect.height;
    }
    
    @Override
    public void draw(Graphics gc) {
        gc.fillPolygon(xPoints, yPoints, xPoints.length);
    }
    
}
