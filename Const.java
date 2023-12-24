import java.awt.Color;

public final class Const{
    public static final int FRAME_WIDTH = 1920;
    public static final int FRAME_HEIGHT = 1080;
    public static final int RUN_SPEED = 5;
    public static final int FRAME_PERIOD = 10;
    
    public static final int BULLET_HEIGHT = 5;
    public static final int BULLET_WIDTH = 5;
    
    public static final int BULLET_STEP1 = 7;
    public static final int BULLET_STEP2 = 11;
    public static final int BULLET_STEP3 = 14;
    
    
    public static final int PLAYER_STEP = 3;
    public static final int PLAYER_WIDTH = 25;
    public static final int PLAYER_HEIGHT = 25;
    
    public static final Color BULLET_COLOR = Color.red;
    public static final Color PLAYER_COLOR = Color.blue;
    public static final Color CHASE_COLOR = Color.black;
    public static final Color HITBOX_COLOR = Color.green;
    
    public static final int CHASE_STEP1 = 4;
    public static final int CHASE_STEP2 = 6;
    public static final int CHASE_STEP3 = 7;
    
    public static final int CHASE_WIDTH = 20;
    public static final int CHASE_HEIGHT = 20;
    
    public static final int CHASE_LIMIT = 25;
    
    
    private Const(){
    }
}