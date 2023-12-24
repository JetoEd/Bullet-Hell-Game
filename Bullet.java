import java.awt.Graphics;

public class Bullet extends GameObject{
    
    private int vectorX;
    private int vectorY;
    private int bulletV1;
    private int bulletV2;
    private int bulletV3;
    
    public Bullet(double x, double y, int vectorX, int vectorY){
        super(x, y, Const.BULLET_WIDTH, Const.BULLET_HEIGHT);
        this.bulletV1 = Const.BULLET_STEP1;
        this.bulletV2 = Const.BULLET_STEP2;
        this.bulletV3 = Const.BULLET_STEP3;
        this.vectorX = vectorX;
        this.vectorY = vectorY;
        
    }    
//------------------------------------------------------------------------------    
    @Override
    public void draw(Graphics g){
        g.setColor(Const.BULLET_COLOR);
        g.fillRect((int) getX(), (int) getY(), getW(), getH());
    }
    
//------------------------------------------------------------------------------
    public int vectorX(Player player, double x2, double y2){
        double x1 = player.getX() + (player.getW()/2);
        double y1 = player.getY() + (player.getH()/2);
        int vectorX = (int)(Math.round(bulletV1 * Math.cos(Math.toRadians(Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI))));
        return vectorX;
    }
    
    public int vectorY(Player player, double x2, double y2){
        double x1 = player.getX() + (player.getW()/2);
        double y1 = player.getY() + (player.getH()/2);
        int vectorY = (int)(Math.round(bulletV1 * Math.sin(Math.toRadians(Math.atan2(y2 - y1, x2 - x1) * 180 / Math.PI))));
        return vectorY;
    }
//------------------------------------------------------------------------------   
    public void move(){
        setX(getX() + vectorX);
        setY(getY() + vectorY);
    }
}