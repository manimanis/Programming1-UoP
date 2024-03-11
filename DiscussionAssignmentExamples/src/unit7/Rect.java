
package unit7;

import java.awt.Graphics;

/**
 *
 * @author Cyberbox
 */
public class Rect extends Shape {
    
    public Rect() {
        this( 0, 0, 100, 100);
    }

    public Rect(int xPos, int yPos, int width, int height) {
        super("Rectangle", xPos, yPos, width, height);
    }

    @Override
    public void draw(Graphics gc) {
        gc.fillRect(shapeRect.x, shapeRect.y, shapeRect.width, shapeRect.height);
    }
}
