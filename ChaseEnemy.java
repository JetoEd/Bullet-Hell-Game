import java.awt.Rectangle;
import java.awt.Graphics;

public class ChaseEnemy extends GameObject {    
    private int step1;
    private int step2;
    private int step3;
    private Rectangle hitBox; 
    
    public ChaseEnemy(int x, int y, int width, int height, Rectangle hitBox){
        super(x, y, width, height);
        this.step1 = Const.CHASE_STEP1;
        this.step2 = Const.CHASE_STEP2;
        this.step3 = Const.CHASE_STEP3;
        this.hitBox = hitBox;
    }
//------------------------------------------------------------------------------
    @Override
    public void draw(Graphics g){
        g.setColor(Const.CHASE_COLOR);
        g.fillRect((int)getX(), (int)getY(), getW(), getH());
    }
    public void draw2(Graphics g){
        g.setColor(Const.HITBOX_COLOR);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }
//------------------------------------------------------------------------------    
    public Rectangle getHitBox(){
        return this.hitBox;
    }
    public void setBox(){
        this.hitBox.setLocation((int)getX(), (int)getY()); 
    }
    
    public void collidePlayer(Player player){
        if (hitBox.intersects(player.getHitBox())){
            System.out.println("You have died");
        }
    }
    
//    public void collideBullet(Bullet bullet){
        
    public void move(Player player){
        double x2 = player.getX() + (player.getW()/2);
        double y2 = player.getY() + (player.getH()/2);
        double x1 = getX() + (getW()/2);
        double y1 = getY() + (getH()/2);
        int vectorX = (int)(Math.round(this.step1 * Math.cos(Math.toRadians(Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI))));
        int vectorY = (int)(Math.round(this.step1 * Math.sin(Math.toRadians(Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI))));
        setBox();
        collidePlayer(player);
        setX(getX() + vectorX);
        setY(getY() + vectorY);
        
    }
}