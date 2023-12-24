import java.awt.Color;
import java.awt.Font; 

public class Const {
    public static final int FRAME_WIDTH = 1920;
    public static final int FRAME_HEIGHT = 1080;
    public static final int FRAME_PERIOD = 10;
    
    public static final Color HITBOX_COLOR = Color.green;
    
    public static final Font SCORE_FONT = new Font("Times New Roman", Font.BOLD, 40);
    public static final Font DEATH_FONT = new Font("Times New Roman", Font.BOLD, 60);
    public static final Font MENU_FONT = new Font("Times New Roman", Font.BOLD, 150);
    public static final Font INSTRUCTIONS_FONT = new Font("Times New Roman",Font.BOLD, 80);
    
    public static final int SECOND_DIFFICULTY_SCORE = 750;
    public static final int THIRD_DIFFICULTY_SCORE = 1500;
    
    public static final int PLAYER_BULLET_VELOCITY = 7;
    public static final int PLAYER_BULLET_LIMIT = 200;
    
    public static final int CHASE_LIMIT = 25;
    public static final int CHASER_POINTS = 10;
    
    public static final int SHOOTER_LIMIT = 10;
    public static final int SHOOTER_POINTS = 20;
    
    public static final int SHOOTER_BULLET_LIMIT = 300;
}