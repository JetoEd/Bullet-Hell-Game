import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShooterGame {
    JFrame gameWindow;
    GamePanel gamePanel;
    KeyListener keyListener;
    MouseListener mouseListener;
    MouseMotionListener mouseMotionListener;
    Player player;
    Ammo ammo = new Ammo();
    Enemies enemies = new Enemies();
    Background background = new Background();
    MenuBackground menuBackground = new MenuBackground();
    StartButton startButton = new StartButton(Const.FRAME_WIDTH/3 + 100, Const.FRAME_HEIGHT/3);
    ControlsButton controlsButton = new ControlsButton(Const.FRAME_WIDTH/3, Const.FRAME_HEIGHT/2 + 50);
    BackButton backButton = new BackButton(0, Const.FRAME_HEIGHT - 280);
    ChaserEnemy[] chasers = enemies.getChasers();
    ShootingEnemy[] shooters = enemies.getShooters();
    PlayerBullet[] playerBullets = ammo.getPlayerBullets();
    ShooterBullet[] shooterBullets = ammo.getShooterBullets();
    boolean leftIsHeld, rightIsHeld, upIsHeld, downIsHeld;
    boolean alive, level1, level2, level3;
    double mouseX, mouseY;
    long startTime1, currentTime1, elapsedTime1, startTime2, currentTime2, elapsedTime2, startTime3, currentTime3, elapsedTime3, startTime4, currentTime4, score;
    int chasersKilled = 0, shootersKilled = 0;
    int chaserSpawnTime = 2, shooterSpawnTime = 3;
    double shooterFiringRate = 2;
    double chaserVelocity = 4;
    double shooterBulletVelocity = 4;
    double playerVelocity = 3;

    boolean menu, controls;

//------------------------------------------------------------------------------ 
    ShooterGame() {
        gameWindow = new JFrame("Game Window");
        
        gamePanel = new GamePanel();
        gameWindow.add(gamePanel);
        
        keyListener = new MyKeyListener();
        gamePanel.addKeyListener(keyListener);
        
        mouseListener = new BasicMouseListener();
        gamePanel.addMouseListener(mouseListener);
        
        mouseMotionListener = new MyMouseMotionListener();
        gamePanel.addMouseMotionListener(mouseMotionListener);
        
        player = new Player(Const.FRAME_WIDTH/2, Const.FRAME_HEIGHT/2);
        
        gameWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
        gameWindow.setUndecorated(true);
        
        gameWindow.requestFocus();
        gameWindow.setVisible(true);
    }
//------------------------------------------------------------------------------
    public void resetPositions() {
        score = 0;
        chasersKilled = 0;
        shootersKilled = 0;
        chaserSpawnTime = 2;
        shooterFiringRate = 2;
        shooterSpawnTime = 3;
        chaserVelocity = 4;
        shooterBulletVelocity = 4;
        playerVelocity = 3;
        level1 = true;
        level2 = false;
        level3 = false; 
        
        startTime1 = System.currentTimeMillis();
        startTime2 = System.currentTimeMillis();
        startTime3 = System.currentTimeMillis();
        startTime4 = System.currentTimeMillis();
        
        for (int i = 0; i < chasers.length; i++) {
            enemies.removeChaser(i);
        }
        for (int i = 0; i < shooters.length; i++) {
            enemies.removeShooter(i);
        }
        for (int i = 0; i < playerBullets.length; i++) {
            ammo.removePlayerBullet(i);
        }
        for (int i = 0; i < shooterBullets.length; i++) {
            ammo.removeShooterBullet(i);
        }
        
        player.setX((Const.FRAME_WIDTH - player.getW()) / 2);
        player.setY((Const.FRAME_HEIGHT - player.getH()) / 2);
    }
//------------------------------------------------------------------------------
    public void run() {

        menu = true;
        
        while (true) {

            startTime1 = System.currentTimeMillis();
            startTime2 = System.currentTimeMillis();
            startTime3 = System.currentTimeMillis();
            startTime4 = System.currentTimeMillis();
            
            if (menu){
            }
            
            while (alive) {

                gameWindow.repaint();
                try {Thread.sleep(Const.FRAME_PERIOD);} catch (Exception e){}
                //Player movement 
                if (leftIsHeld){
                    player.moveLeft(playerVelocity);
                }
                if (rightIsHeld){
                    player.moveRight(playerVelocity);
                }
                if (upIsHeld){
                    player.moveUp(playerVelocity);
                }
                if (downIsHeld){
                    player.moveDown(playerVelocity);
                }
                
                player.setHitbox();
                
                //Checks if chaser enemy has hit a player bullet
                for (int i = 0; i < chasers.length; i++) {
                    if (chasers[i] != null) {
                        for (int j = 0; j < playerBullets.length; j++) {
                            if (playerBullets[j] != null) {
                                if (chasers[i] != null && playerBullets[j] != null) {
                                    if (chasers[i].collidePlayerBullet(playerBullets[j])) {
                                        chasersKilled++;
                                        enemies.removeChaser(i);
                                        ammo.removePlayerBullet(j);
                                    }
                                }
                                
                            }
                        }
                    }
                }
                
                //Checks if shooter enemy has been by a player bullet
                for (int i = 0; i < shooters.length; i++) {
                    if (shooters[i] != null) {
                        for (int j = 0; j < playerBullets.length; j++) {
                            if (playerBullets[j] != null) {
                                if (shooters[i] != null && playerBullets[j] != null) {
                                    if (shooters[i].collidePlayerBullet(playerBullets[j])) {
                                        shootersKilled++;
                                        enemies.removeShooter(i);
                                        ammo.removePlayerBullet(j);
                                    }
                                }
                            }
                        }
                    }
                }
                
                ammo.movePlayerBullets();
                ammo.moveShooterBullets();
                
                enemies.moveChaser(player, chaserVelocity);
                
                //Current Score
                currentTime4 = System.currentTimeMillis();
                score = ((currentTime4 - startTime4) / 80) + ((long) chasersKilled * Const.CHASER_POINTS) + ((long) shootersKilled * Const.SHOOTER_POINTS);
                
                //Second difficulty when reach certain amount of points
                if (score >= Const.SECOND_DIFFICULTY_SCORE && score <= Const.THIRD_DIFFICULTY_SCORE) {
                    chaserVelocity = 4.4;
                    shooterBulletVelocity = 4.5;
                    shooterSpawnTime = 2;
                    playerVelocity = 3.5;
                    level1 = false; 
                    level2 = true;
                    level3 = false; 
                }
                
                //Third difficulty when reach another amount of points
                if (score >= Const.THIRD_DIFFICULTY_SCORE) {
                    chaserVelocity = 4.65;
                    shooterBulletVelocity = 5.5;
                    playerVelocity = 3.75;
                    chaserSpawnTime = 1;
                    shooterSpawnTime = 1;
                    level1 = false; 
                    level2 = false; 
                    level3 = true; 
                }
                
                //Spawn chase enemies automatically
                currentTime1 = System.currentTimeMillis();
                elapsedTime1 = (currentTime1 - startTime1) / 1000;
                if (elapsedTime1 == chaserSpawnTime) {
                    startTime1 = currentTime1;
                    enemies.addChaser();
                }
                
                //Spawn shooter enemies automatically
                currentTime3 = System.currentTimeMillis();
                elapsedTime3 = (currentTime3 - startTime3) / 1000;
                if (elapsedTime3 == shooterSpawnTime) {
                    startTime3 = currentTime3;
                    enemies.addShooter();
                }
                
                //Shooter enemies shoot automatically
                currentTime2 = System.currentTimeMillis();
                elapsedTime2 = (currentTime2 - startTime2) / 1000;
                if (elapsedTime2 == shooterFiringRate) {
                    startTime2 = currentTime2;
                    for (int i = 0; i < enemies.getShooters().length; i++) {
                        if (shooters[i] != null) {
                            ammo.addShooterBullet(shooters[i], (player.getX() + (player.getW() / 2)), player.getY() + (player.getH() / 2), shooterBulletVelocity);
                        }
                    }
                }
            }
            gameWindow.repaint();
        }
    }
//------------------------------------------------------------------------------
    public class MyKeyListener implements KeyListener {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_A) {
                leftIsHeld = true;
            } else if (key == KeyEvent.VK_D) {
                rightIsHeld = true;
            } else if (key == KeyEvent.VK_W) {
                upIsHeld = true;
            } else if (key == KeyEvent.VK_S) {
                downIsHeld = true;
            } else if (key == KeyEvent.VK_SPACE && alive && !menu) {
                ammo.addPlayerBullet(player, mouseX, mouseY);
            } else if (key == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            } else if (key == KeyEvent.VK_R && !alive && !menu) {
                resetPositions();
                alive = true;
            }

//            else if (key == KeyEvent.VK_T && !alive && menu) {
//                alive = true;
//                menu = false;
//            }

        }
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_A) {
                leftIsHeld = false;
            } else if (key == KeyEvent.VK_D) {
                rightIsHeld = false;
            } else if (key == KeyEvent.VK_W) {
                upIsHeld = false;
            } else if (key == KeyEvent.VK_S) {
                downIsHeld = false;
            }
        }
        public void keyTyped(KeyEvent e) {
        }
    }
//------------------------------------------------------------------------------
    public class BasicMouseListener implements MouseListener {
        public void mouseClicked(MouseEvent e) {
        }
        public void mousePressed(MouseEvent e) {
            
            //Start Button
            if (menu && mouseX >= (Const.FRAME_WIDTH/3 + 100) && mouseX <= (Const.FRAME_WIDTH/3 + 100 + startButton.getW()) && mouseY >= Const.FRAME_HEIGHT/3 && mouseY <= Const.FRAME_HEIGHT/3 + startButton.getH()) {
                menu = false; 
                alive = true; 
                level1 = true; 
            }
            
            //Controls Button 
            else if (menu && mouseX >= Const.FRAME_WIDTH/3 && mouseX <= Const.FRAME_WIDTH/3 + controlsButton.getW() && mouseY >= Const.FRAME_HEIGHT/2 + 50 && mouseY <= Const.FRAME_HEIGHT/2 + 50 + controlsButton.getH()){
                System.out.println("active");
                menu = true;
                controls = true;
            }

            //Back Button
            else if(controls && mouseX >= 0 && mouseX <= backButton.getW() && mouseY >= Const.FRAME_HEIGHT - 280 && mouseY <= Const.FRAME_HEIGHT - 280 + backButton.getH()){
                controls = false;
                menu = true;
            }
            
            //Shoots Bullet 
            else if (alive && !menu && !controls ) {
                ammo.addPlayerBullet(player, mouseX, mouseY);
            }
        }
        public void mouseReleased(MouseEvent e) {
        }
        public void mouseEntered(MouseEvent e) {
        }
        public void mouseExited(MouseEvent e) {
        }
    }
//------------------------------------------------------------------------------
    public class MyMouseMotionListener implements MouseMotionListener {
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
        }
        public void mouseDragged(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
        }
    }
//------------------------------------------------------------------------------
    public class GamePanel extends JPanel {
        GamePanel() {
            setFocusable(true);
            requestFocusInWindow();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (menu && !controls){
                menuBackground.draw(g);
                g.setColor(Color.white);
                g.setFont(Const.MENU_FONT);
                g.drawString("SPACE SURVIVAL", 370, 180);
                startButton.draw(g);
                controlsButton.draw(g);
            }
            
            else if (controls){
                menuBackground.draw(g);
                backButton.draw(g);
                g.setColor(Color.white);
                g.setFont(Const.INSTRUCTIONS_FONT);
                g.drawString("W = MOVE UP", 150, 175);
                g.drawString("A = MOVE LEFT", 150, 275);
                g.drawString("S = MOVE DOWN", 150, 375);
                g.drawString("D = MOVE RIGHT", 150, 475);
                g.drawString("PRESS R TO REPLAY GAME", (int) (backButton.getW() + 40), 850);
                g.drawString("SHOOT WITH MOUSE CLICK OR SPACE", (int) (backButton.getW() + 40), 1000);
            }
            
            else if (!menu && !controls){
                background.draw(g);

                player.draw(g);
                //Option to draw player hitbox
//            player.draw2(g);
                
                ammo.drawPlayerBullets(g);
                ammo.drawShooterBullets(g);
                enemies.drawChaser(g);
                enemies.drawShooter(g);

                //Top black bar
                g.setColor(Color.black);
                g.fillRect(0, 0, Const.FRAME_WIDTH, 80);

                //Level display
                if (level1) {
                    g.setColor(Color.WHITE);
                    g.setFont(Const.SCORE_FONT);
                    g.drawString("DIFFICULTY 1", 40, 55);
                } else if (level2) {
                    g.setColor(Color.WHITE);
                    g.setFont(Const.SCORE_FONT);
                    g.drawString("DIFFICULTY 2", 40, 55);
                } else if (level3) {
                    g.setColor(Color.WHITE);
                    g.setFont(Const.SCORE_FONT);
                    g.drawString("DIFFICULTY 3", 40, 55);
                }
                
                //Scoreboard
                if (alive) {
                    g.setColor(Color.WHITE);
                    g.setFont(Const.SCORE_FONT);
                    g.drawString("CURRENT SCORE: " + score, 410, 55);
                    g.drawString("CHASERS KILLED: " + chasersKilled, 930, 55);
                    g.drawString("MONSTERS KILLED: " + shootersKilled, 1410, 55);
                }
                
                //Death
                else if (!alive) {
                    g.setColor(Color.WHITE);
                    g.setFont(Const.SCORE_FONT);
                    g.drawString("FINAL SCORE: " + score, 410, 55);
                    g.drawString("CHASERS KILLED: " + chasersKilled, 930, 55);
                    g.drawString("MONSTERS KILLED: " + shootersKilled, 1410, 55);
                    g.setFont(Const.DEATH_FONT);
                    g.drawString("PRESS R TO PLAY AGAIN OR ESCAPE TO END THE GAME", 150, 200);
                }
                
                
                //Checks if player has hit game border, if it has, draw border collision death screen
                if (player.getX() < 0 || player.getX() > Const.FRAME_WIDTH - player.getW() || player.getY() < 83 || player.getY() > (Const.FRAME_HEIGHT - player.getH())) {
                    //Border collision death screen
                    g.setColor(Color.WHITE);
                    g.setFont(Const.DEATH_FONT);
                    g.drawString("HIT BORDER", 760, 140);
                    alive = false;
                }
                
                //Checks if chaser enemy has collided with player
                for (ChaserEnemy chaser : chasers) {
                    if (chaser != null) {
                        //Chaser collision death screen
                        if (chaser.collidePlayer(player)) {
                            g.setColor(Color.WHITE);
                            g.setFont(Const.DEATH_FONT);
                            g.drawString("KILLED BY CHASER", 700, 140);
                            alive = false;
                        }
                    }
                }
                
                //Checks if shooter enemy has collided with player
                for (ShootingEnemy shooter : shooters) {
                    if (shooter != null) {
                        //Shooter collision death screen
                        if (shooter.collidePlayer(player)) {
                            g.setColor(Color.WHITE);
                            g.setFont(Const.DEATH_FONT);
                            g.drawString("KILLED BY MONSTER", 650, 140);
                            alive = false;
                        }
                    }
                }
                
                //Checks if a bullet fired from a shooter has collided with player
                for (ShooterBullet shooterBullet : shooterBullets) {
                    if (shooterBullet != null) {
                        //Bullet collision death screen
                        if (player.collideShooterBullet(shooterBullet)) {
                            g.setColor(Color.WHITE);
                            g.setFont(Const.DEATH_FONT);
                            g.drawString("KILLED BY ENERGY BALL", 600, 140);
                            alive = false;
                        }
                    }
                }
                
            }
        }
    }
//------------------------------------------------------------------------------
    public static void main(String[] args) {
        ShooterGame startGame = new ShooterGame();
        startGame.run();
    }
}