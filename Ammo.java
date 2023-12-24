import java.awt.Graphics;

public class Ammo{
    final int CARTRIDGE_SIZE = 250;    
    private Bullet[] bullets;
    
    Ammo(){
        this.bullets = new Bullet[CARTRIDGE_SIZE];
    }
//------------------------------------------------------------------------------
    public Bullet[] getBullets(){
        return this.bullets;
    }
    
    public void drawBullets(Graphics g){
        for (int i = 0; i < this.bullets.length; i++){
            //if not greater than cartridge size, can shoot more bullets
            if (this.bullets[i] != null){
                this.bullets[i].draw(g);
            }
        }
    }
    
    public void moveBullets(Player player, double x2, double y2){
        for (int i = 0; i < this.bullets.length; i++){
            if (this.bullets[i] != null){
                this.bullets[i].move();
                
                //Remove bullets when out of screen
                if ((this.bullets[i].getY() < 0) || (this.bullets[i].getY() > (Const.FRAME_HEIGHT - 40))){
                    this.removeBullet(i);
                }
                else if ((this.bullets[i].getX() < 0)|| (this.bullets[i].getX() > Const.FRAME_WIDTH)){
                    this.removeBullet(i);
                }
                
                //Add remove when touch enemies
            }
        }
    }
//------------------------------------------------------------------------------  
    public void addBullet(Player player, double x2, double y2){
        boolean added = false;
        
        double x1 = player.getX() + (player.getW()/2);
        double y1 = player.getY() + (player.getH()/2);
        int vectorX = (int)(Math.round(Const.BULLET_STEP1 * Math.cos(Math.toRadians(Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI))));
        int vectorY = (int)(Math.round(Const.BULLET_STEP1 * Math.sin(Math.toRadians(Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI))));
        
        
        for (int i = 0; i < this.bullets.length; i++){
            if (this.bullets[i] == null && !added){
                
                double bulletX = player.getX() + player.getW()/2 - Const.BULLET_WIDTH/2;
                double bulletY = player.getY() + player.getH()/2;
                
                this.bullets[i] = new Bullet(bulletX, bulletY, vectorX, vectorY);
                
                added = true;
            }
        }
    }
    
    public void removeBullet(int index){
        this.bullets[index] = null;
    }    
}