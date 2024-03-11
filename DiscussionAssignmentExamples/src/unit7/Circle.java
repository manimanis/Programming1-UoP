
package unit7;

import java.awt.Graphics;

/**
 *
 * @author Cyberbox
 */
public class Circle extends Shape {

    public Circle() {
        this(0, 0, 100, 100);
    }
    
    public Circle(int xPos, int yPos, int width, int height) {
        super("Circle", xPos, yPos, width, height);
    }

    @Override
    public void draw(Graphics gc) {
        gc.fillOval(shapeRect.x, shapeRect.y, shapeRect.width, shapeRect.height);
    }
    
}
