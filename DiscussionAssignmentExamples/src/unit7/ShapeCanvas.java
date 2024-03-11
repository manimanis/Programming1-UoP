/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package unit7;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JPanel;

/**
 *
 * @author Cyberbox
 */
public class ShapeCanvas extends JPanel {

    private Shape shape = new Rect();

    public ShapeCanvas() {
        initComponents();
    }

    public Shape getShape() {
        return shape;
    }
    
    public String getShapeName() {
        return shape.getType();
    }
    
    public int getShapeX() {
        return shape.getShapeX();
    }
    
    public int getShapeY() {
        return shape.getShapeY();
    }
    
    public int getShapeWidth() {
        return shape.getShapeWidth();
    }
    
    public int getShapeHeight() {
        return shape.getShapeWidth();
    }

    public void setShapePosition(int x, int y) {
        shape.setShapeX(x);
        shape.setShapeY(y);
        updateUI();
    }
    
    public void setShapeSize(int width, int height) {
        shape.setShapeWidth(width);
        shape.setShapeHeight(height);
        updateUI();
    }
    
    void setShape(String shapeName) {
        Rectangle rect = shape.getShapeRect();
        if ("Rectangle".equals(shapeName)) {
            shape = new Rect(rect.x, rect.y, rect.width, rect.height);
        } else if ("Circle".equals(shapeName)) {
            shape = new Circle(rect.x, rect.y, rect.width, rect.height);
        } else if ("Triangle".equals(shapeName)) {
            shape = new Triangle(rect.x, rect.y, rect.width, rect.height);
        }
        updateUI();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        shape.draw(g);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
