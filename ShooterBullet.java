public class ShooterBullet extends GameObject {
    private double vectorX;
    private double vectorY;
    
    ShooterBullet(double x, double y, double vectorX, double vectorY) {
        super(x, y, "images/shooterBullet.png");
        this.vectorX = vectorX;
        this.vectorY = vectorY;
    }
//------------------------------------------------------------------------------
    public void moveShooterBullet() {
        setX(getX() + vectorX);
        setY(getY() + vectorY);
        setHitbox();
    }
}
