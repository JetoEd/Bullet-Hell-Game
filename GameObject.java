import java.awt.Graphics;

abstract class GameObject{
    private double x;
    private double y;
    private int width;
    private int height;

//------------------------------------------------------------------------------    
    GameObject(double x, double y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
//------------------------------------------------------------------------------
    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }
    public int getW(){
        return this.width;
    }    
    public int getH(){
        return this.height;
    }    
    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
//------------------------------------------------------------------------------    
    abstract void draw(Graphics g);
}