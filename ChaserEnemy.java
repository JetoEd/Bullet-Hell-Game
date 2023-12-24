public class ChaserEnemy extends GameObject {
    ChaserEnemy(double x, double y) {
        super(x, y, "images/chaserEnemy.png");
    }
//------------------------------------------------------------------------------
    public boolean collidePlayer(Player player) {
        return getHitbox().intersects(player.getHitbox());
    }
    
    public boolean collidePlayerBullet(PlayerBullet playerBullet) {
        return getHitbox().intersects(playerBullet.getHitBox());
    }
    
    public void move(Player player, double chaseEnemyVelocity) {
        
        double x1 = getX() + (getW() / 2);
        double y1 = getY() + (getH() / 2);
        
        double x2 = player.getX() + (player.getW() / 2);
        double y2 = player.getY() + (player.getH() / 2);
        
        double vectorX = chaseEnemyVelocity * Math.cos(Math.toRadians(Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI));
        double vectorY = chaseEnemyVelocity * Math.sin(Math.toRadians(Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI));
        
        setX(getX() + vectorX);
        setY(getY() + vectorY);
        setHitbox();
        collidePlayer(player);
    }
}