import java.awt.Rectangle;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

abstract class GameObject{
    private double width;
    private double height;
    private double x;
    private double y;
    private BufferedImage sprite;
    private Rectangle hitbox;
//------------------------------------------------------------------------------
    GameObject(double x, double y, String spriteName) {
        this.x = x;
        this.y = y;
        try {
            sprite = ImageIO.read(new File(spriteName));
        } catch (IOException ex){}
        this.width = sprite.getWidth();
        this.height = sprite.getHeight();
        this.hitbox = new Rectangle ((int) this.x, (int) this.y, (int) this.width, (int) this.height);
    }
//------------------------------------------------------------------------------
    public double getX() {
        return this.x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return this.y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getW() {
        return this.width;
    }
    public double getH() {
        return this.height;
    }
    public Rectangle getHitbox(){
        return this.hitbox;
    }
    public void setHitbox(){
        this.hitbox.setLocation((int)this.x, (int)this.y); 
    }
    
    public void draw(Graphics g){
        g.drawImage(this.sprite, (int)this.getX(), (int)this.getY(), null);        
    }
    
    public void draw2(Graphics g) {
        g.setColor(Const.HITBOX_COLOR);
        g.drawRect((int) getHitbox().getX(), (int) getHitbox().getY(), (int) getHitbox().getWidth(), (int) getHitbox().getHeight());
    }
}
