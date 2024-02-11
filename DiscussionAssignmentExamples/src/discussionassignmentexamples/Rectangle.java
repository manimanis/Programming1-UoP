
package discussionassignmentexamples;

/**
 *
 * @author manianis
 */
public class Rectangle {
    
    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private int angle;

    public Rectangle() {
        this(0, 0, 300, 200, 0);
    }
    
    public Rectangle(int xPos, int yPos, int width, int height) {
        this(xPos, yPos, width, height, 0);
    }
    
    public Rectangle(int xPos, int yPos, int width, int height, int angle) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.angle = angle;
    }
    
    public void setPosition(int x, int y) {
        this.xPos = x;
        this.yPos = y;
    }
    
    public void setDimensions(int w, int h) {
        this.width = w;
        this.height = h;
    }
    
    public void setAngle(int angle) {
        this.angle = angle;
        adjustAngle();
    }
    
    public void moveBy(int xPos, int yPos) {
        this.xPos += xPos;
        this.yPos += yPos;
    }
    
    public void rotateBy(int angle) {
        this.angle += angle;
        adjustAngle();
    }
    
    private void adjustAngle() {
        angle = (angle % 360 + 360) % 360;
        if (angle > 180) {
            angle = angle - 360;
        }
    }
    
    public void state() {
        System.out.printf("x: %d, y: %d, width: %d, height: %d, angle: %d\n",
                xPos, yPos, width, height, angle);
    }

    void moveTo(int x, int y, int t) {
        setPosition(x, y);
    }

    void rotate(int angle, int t) {
        rotateBy(angle);
    }
}
