/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unit7;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Cyberbox
 */
public abstract class Shape {
    protected String type;
    protected Rectangle shapeRect;

    public Shape(String type, int xPos, int yPos, int width, int height) {
        this.type = type;
        this.shapeRect = new Rectangle(xPos, yPos, width, height);
    }

    public String getType() {
        return type;
    }

    public Rectangle getShapeRect() {
        return shapeRect;
    }

    public void setShapeRect(Rectangle shapeRect) {
        this.shapeRect = shapeRect;
    }
    
    public int getShapeX() {
        return shapeRect.x;
    }
    
    public void setShapeX(int x) {
        shapeRect.x = x;
    }
    
    public int getShapeY() {
        return shapeRect.y;
    }
    
    public void setShapeY(int y) {
        shapeRect.y = y;
    }
    
    public int getShapeWidth() {
        return shapeRect.width;
    }
    
    public void setShapeWidth(int width) {
        shapeRect.width = width;
    }
    
    public int getShapeHeight() {
        return shapeRect.height;
    }
    
    public void setShapeHeight(int height) {
        shapeRect.height = height;
    }
    
    public abstract void draw(Graphics gc);
}


