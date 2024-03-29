public class ShootingEnemy extends GameObject {
    ShootingEnemy(double x, double y) {
        super(x, y, "images/shootingEnemy.png");
    }
//------------------------------------------------------------------------------
    public boolean collidePlayer(Player player) {
        return getHitbox().intersects(player.getHitbox());
    }
    
    public boolean collidePlayerBullet(PlayerBullet playerBullet) {
        return getHitbox().intersects(playerBullet.getHitBox());
    }
}
