public class Player extends GameObject {
    
    Player(double x, double y) {
        super(x, y, "images/player.png");
    }
//------------------------------------------------------------------------------
    public void moveLeft(double playerVelocity) {
        setX(getX() - playerVelocity);
    }
    
    public void moveRight(double playerVelocity) {
        setX(getX() + playerVelocity);
    }
    
    public void moveUp(double playerVelocity) {
        setY(getY() - playerVelocity);
    }
    
    public void moveDown(double playerVelocity) {
        setY(getY() + playerVelocity);
    }
    
    public boolean collideShooterBullet(ShooterBullet shooterBullet) {
        return getHitbox().intersects(shooterBullet.getHitbox());
    }
}
