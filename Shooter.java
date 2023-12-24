import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;

public class Shooter{
    JFrame gameWindow;
    GamePanel gamePanel;   
    KeyListener keyListener;
    MouseListener mouseListener;
    MouseMotionListener mouseMotionListener; 
    boolean leftIsHeld, rightIsHeld , upIsHeld, downIsHeld;
//    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    double mouseX;
    double mouseY;
    Player player;
    
    Ammo ammo = new Ammo();
    EnemyNumber chaseEnemy = new EnemyNumber();
//------------------------------------------------------------------------------
    Shooter(){
        gameWindow = new JFrame("Game Window");
        gameWindow.setSize(Const.FRAME_WIDTH,Const.FRAME_HEIGHT);
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setResizable(false);
        
        gamePanel = new GamePanel();
        gameWindow.add(gamePanel);
        
        keyListener = new MyKeyListener();
        gamePanel.addKeyListener(keyListener);
        
        mouseListener = new BasicMouseListener();
        gamePanel.addMouseListener(mouseListener);
        
        mouseMotionListener = new MyMouseMotionListener(); 
        gamePanel.addMouseMotionListener(mouseMotionListener);
                
        Rectangle playerHitbox = new Rectangle((Const.FRAME_WIDTH - (Const.PLAYER_WIDTH)/2)/2, (Const.FRAME_HEIGHT - (Const.PLAYER_HEIGHT/2))/2, Const.PLAYER_WIDTH, Const.PLAYER_HEIGHT);
        player = new Player((Const.FRAME_WIDTH - (Const.PLAYER_WIDTH))/2, (Const.FRAME_HEIGHT - (Const.PLAYER_HEIGHT))/2, Const.PLAYER_WIDTH, Const.PLAYER_HEIGHT, playerHitbox);
        
        gameWindow.requestFocus();
        gameWindow.setVisible(true);    
    }
//------------------------------------------------------------------------------  
    public void run(){
        while (true){
            gameWindow.repaint();
            try  {Thread.sleep(Const.FRAME_PERIOD);} catch(Exception e){}
            
            player.setHitBox();
            
            if (leftIsHeld){player.moveLeft();}
            if (rightIsHeld){player.moveRight();}
            if (upIsHeld){player.moveUp();}
            if (downIsHeld){player.moveDown();}
            
            if ((player.getX() < 0 || player.getX() > Const.FRAME_WIDTH - player.getW()) || (player.getY() < 0 || player.getY() > (Const.FRAME_HEIGHT - player.getH()) - 40)){
                System.out.println("OUT OF BOUNDS");
            }
            
            ammo.moveBullets(player, mouseX, mouseY);
            chaseEnemy.moveEnemies(player);
            
//            for (int i = 0; i < chaseEnemy.getEnemies().length; i++){
//                player.collides(chaseEnemy.getEnemies()[i]);
//            }
                 
//            service.schedule(EnemyNumber::chaseEnemy.addEnemy(player), 1, TimeUnit.SECONDS);
            
        }
    }  
//------------------------------------------------------------------------------
    public class MyKeyListener implements KeyListener{   
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_A){
                leftIsHeld = true;
            } else if (key == KeyEvent.VK_D){
                rightIsHeld = true;
            } else if (key == KeyEvent.VK_W){
                upIsHeld = true;
            } else if (key == KeyEvent.VK_S){
                downIsHeld = true;
            } 
            
            //Change to automatic with delay 
            else if (key == KeyEvent.VK_SPACE){
                chaseEnemy.addEnemy(player);
            }
            
        }
        public void keyReleased(KeyEvent e){
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_A){
                leftIsHeld = false;
            } else if (key == KeyEvent.VK_D){
                rightIsHeld = false;
            } else if (key == KeyEvent.VK_W){
                upIsHeld = false;
            } else if (key == KeyEvent.VK_S){
                downIsHeld = false;
            }
        }   
        public void keyTyped(KeyEvent e){
        }           
    }     
//------------------------------------------------------------------------------
    public class BasicMouseListener implements MouseListener{
        public void mouseClicked(MouseEvent e){
        }
        public void mousePressed(MouseEvent e){
            ammo.addBullet(player, mouseX, mouseY);
        }
        public void mouseReleased(MouseEvent e){
        }
        public void mouseEntered(MouseEvent e){
        }
        public void mouseExited(MouseEvent e){
        }
    }
//------------------------------------------------------------------------------
    public class MyMouseMotionListener implements MouseMotionListener{
        public void mouseMoved(MouseEvent e){
            mouseX = e.getX();
            mouseY = e.getY();
        }
        public void mouseDragged(MouseEvent e){
        }         
    }
//------------------------------------------------------------------------------ 
    public class GamePanel extends JPanel{
        GamePanel(){
            setFocusable(true);
            requestFocusInWindow();
        }
        
        @Override
        public void paintComponent(Graphics g){ 
            super.paintComponent(g); 
            player.draw(g);
            player.draw2(g);
            ammo.drawBullets(g);
            chaseEnemy.drawEnemies(g);
        }    
    }    
}