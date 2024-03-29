import java.awt.Rectangle;

public class PlayerBullet extends GameObject {
    
    private double vectorX;
    private double vectorY;
    
    PlayerBullet(double x, double y, double vectorX, double vectorY) {
        super(x, y, "images/playerBullet.png");
        this.vectorX = vectorX;
        this.vectorY = vectorY;
    }
//------------------------------------------------------------------------------
    public void movePlayerBullet(){
        setX(getX() + vectorX);
        setY(getY() + vectorY);
        setHitbox();
    }
    
    public Rectangle getHitBox() {
        return this.getHitbox();
    }
    
    public void setHitbox() {
        this.getHitbox().setLocation((int) getX(), (int) getY());
    }
}
