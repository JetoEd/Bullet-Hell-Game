import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;

public class Player extends GameObject {    
    private int playerVx;
    private int step;
    private Rectangle hitBox; 
    
    public Player(int x, int y, int width, int height, Rectangle hitBox){
        super(x, y, width, height);
        this.playerVx = 0;
        this.step = Const.PLAYER_STEP;
        this.hitBox = hitBox;
    }
//------------------------------------------------------------------------------    
    @Override
    public void draw(Graphics g){
        g.setColor(Const.PLAYER_COLOR);
        g.fillRect((int)getX(), (int)getY(), getW(), getH());
    }
    
    public void draw2(Graphics g){
        g.setColor(Const.HITBOX_COLOR);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }
//------------------------------------------------------------------------------    
    public void moveLeft(){
        setX(getX() - this.step);
    }
    public void moveRight(){
        setX(getX() + this.step);
    }    
    public void moveUp(){
        setY(getY() - this.step);
    }    
    public void moveDown(){
        setY(getY() + this.step);
    }
    public void setSpeed(int speed){
        this.playerVx = speed;
    }
    public Rectangle getHitBox(){
        return this.hitBox;
    }
    public void setHitBox(){
        this.hitBox.setLocation((int)getX(), (int)getY()); 
    }
}