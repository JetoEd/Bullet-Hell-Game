import java.awt.Rectangle;
import java.awt.Graphics;

public class Ammo {
    private PlayerBullet[] playerBullets;
    private ShooterBullet[] shooterBullets;
    
    Ammo() {
        this.playerBullets = new PlayerBullet[Const.PLAYER_BULLET_LIMIT];
        this.shooterBullets = new ShooterBullet[Const.SHOOTER_BULLET_LIMIT];
    }
    
//------------------------------------------------------------------------------
    public PlayerBullet[] getPlayerBullets() {
        return this.playerBullets;
    }
    
    public ShooterBullet[] getShooterBullets() {
        return this.shooterBullets;
    }
    
    public void drawPlayerBullets(Graphics g) {
        for (PlayerBullet playerBullet : this.playerBullets) {
            //if not greater than cartridge size, can shoot more bullets
            if (playerBullet != null) {
                playerBullet.draw(g);
                //Option to draw hitbox
//                playerBullet.draw2(g);
            }
        }
    }
    
    public void drawShooterBullets(Graphics g) {
        for (ShooterBullet shooterBullet : this.shooterBullets) {
            //if not greater than cartridge size, can shoot more bullets
            if (shooterBullet != null) {
                shooterBullet.draw(g);
                
                //Option to draw hitbox
//                shooterBullet.draw2(g);
            }
        }
    }
    
    public void movePlayerBullets() {
        for (int i = 0; i < this.playerBullets.length; i++) {
            if (this.playerBullets[i] != null) {
                this.playerBullets[i].movePlayerBullet();
                
                //Remove bullets when out of screen
                if ((this.playerBullets[i].getY() < 0) || (this.playerBullets[i].getY() > (Const.FRAME_HEIGHT)) || (this.playerBullets[i].getX() < 0) || (this.playerBullets[i].getX() > Const.FRAME_WIDTH)) {
                    this.removePlayerBullet(i);
                }
            }
        }
    }
    
    public void moveShooterBullets() {
        for (int i = 0; i < this.shooterBullets.length; i++) {
            if (this.shooterBullets[i] != null) {
                this.shooterBullets[i].moveShooterBullet();
                
                //Remove bullets when out of screen
                if ((this.shooterBullets[i].getY() < 0) || (this.shooterBullets[i].getY() > (Const.FRAME_HEIGHT)) || (this.shooterBullets[i].getX() < 0) || (this.shooterBullets[i].getX() > Const.FRAME_WIDTH)) {
                    this.removeShooterBullet(i);
                }
            }
        }
    }
    
    public void addPlayerBullet(Player player, double x2, double y2) {
        boolean added = false;
        for (int i = 0; i < this.playerBullets.length; i++) {
            if (this.playerBullets[i] == null && !added) {
                
                double x1 = player.getX() + (player.getW() / 2) - (15/2);
                double y1 = player.getY() + (player.getH() / 2);
                double vectorX = Const.PLAYER_BULLET_VELOCITY * Math.cos(Math.toRadians(Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI));
                double vectorY = Const.PLAYER_BULLET_VELOCITY * Math.sin(Math.toRadians(Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI));
                
                this.playerBullets[i] = new PlayerBullet(x1, y1, vectorX, vectorY);
                
                added = true;
            }
        }
    }
    
    public void addShooterBullet(ShootingEnemy shooter, double x2, double y2, double shooterBulletVelocity) {
        boolean added = false;
        for (int i = 0; i < this.shooterBullets.length; i++) {
            if (this.shooterBullets[i] == null && !added) {
                
                double x1 = shooter.getX() + (shooter.getW() / 2) - (38/2);
                double y1 = shooter.getY() + (shooter.getH() / 2);
                
                double vectorX = shooterBulletVelocity * Math.cos(Math.toRadians(Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI));
                double vectorY = shooterBulletVelocity * Math.sin(Math.toRadians(Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI));
                
                this.shooterBullets[i] = new ShooterBullet(x1, y1, vectorX, vectorY);
                
                added = true;
            }
        }
    }
    
    public void removePlayerBullet(int index) {
        this.playerBullets[index] = null;
    }
    
    public void removeShooterBullet(int index) {
        this.shooterBullets[index] = null;
    }
}
