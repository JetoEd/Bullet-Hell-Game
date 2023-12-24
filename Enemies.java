import java.awt.Graphics;
import java.util.Random;

public class Enemies {
    Random random = new Random();
    private ChaserEnemy[] chaser;
    private ShootingEnemy[] shooter;
    
    Enemies() {
        this.chaser = new ChaserEnemy[Const.CHASE_LIMIT];
        this.shooter = new ShootingEnemy[Const.SHOOTER_LIMIT];
    }
    
//------------------------------------------------------------------------------
    public void drawChaser(Graphics g) {
        for (ChaserEnemy chaserEnemy : this.chaser) {
            //if not greater than cartridge size, can shoot more bullets
            if (chaserEnemy != null) {
                chaserEnemy.draw(g);
                //Option to draw hitbox
//                chaserEnemy.draw2(g);
            }
        }
    }
    
    public void drawShooter(Graphics g) {
        for (ShootingEnemy shootingEnemy : this.shooter) {
            //if not greater than cartridge size, can shoot more bullets
            if (shootingEnemy != null) {
                shootingEnemy.draw(g);
                //Option to draw hitbox
//                shootingEnemy.draw2(g);
            }
        }
    }
    
    public void moveChaser(Player player, double chaserVelocity) {
        for (ChaserEnemy chaseEnemy : this.chaser) {
            if (chaseEnemy != null) {
                chaseEnemy.move(player, chaserVelocity);
            }
        }
    }
    
    public double randomCoord(double lowerLimit, double upperLimit){
        return Math.floor(Math.random() * (upperLimit - lowerLimit - 1) + lowerLimit);
    }
    
    public void addChaser() {
        boolean added = false;
        for (int i = 0; i < this.chaser.length; i++) {
            if (this.chaser[i] == null && !added) {
                boolean any = random.nextBoolean();
                
                if (any) {
                    
                    double enemyX = randomCoord(-50, Const.FRAME_WIDTH + 50);
                    
                    boolean any2 = random.nextBoolean();
                    double enemyY;
                    
                    if (any2) {
                        enemyY = -50;
                    } else {
                        enemyY = Const.FRAME_HEIGHT + 50;
                    }
                    this.chaser[i] = new ChaserEnemy(enemyX, enemyY);
                    
                } else {
                    
                    double enemyY = randomCoord(-50, Const.FRAME_HEIGHT + 50);
                    
                    boolean any2 = random.nextBoolean();
                    double enemyX;
                    
                    if (any2) {
                        enemyX = -50;
                    } else {
                        enemyX = Const.FRAME_WIDTH + 50;
                    }
                    this.chaser[i] = new ChaserEnemy(enemyX, enemyY);
                }
                added = true;
            }
        }
    }
    
    public void addShooter() {
        boolean added = false;
        for (int i = 0; i < this.shooter.length; i++) {
            if (this.shooter[i] == null && !added) {
                
                boolean any = random.nextBoolean();
                boolean any2 = random.nextBoolean();
                double enemyX;
                
                if (any) {
                    enemyX = (int) Math.floor(Math.random() * ((Const.FRAME_WIDTH - 50) - 50 + 1) + 50);
                    double enemyY;
                    if (any2) {
                        enemyY = 140;
                    } else {
                        enemyY = Const.FRAME_HEIGHT - 30 - 100;
                    }
                    this.shooter[i] = new ShootingEnemy(enemyX, enemyY);
                    
                } else {
                    double enemyY = (int) Math.floor(Math.random() * ((Const.FRAME_HEIGHT - 100) - 130 + 1) + 130);
                    if (any2) {
                        enemyX = 30 + 50;
                    } else {
                        enemyX = Const.FRAME_WIDTH - 30 - 50;
                    }
                    this.shooter[i] = new ShootingEnemy(enemyX, enemyY);
                }
                added = true;
            }
        }
    }
    
    public void removeChaser(int index) {
        this.chaser[index] = null;
    }
    
    public void removeShooter(int index) {
        this.shooter[index] = null;
    }
    
    public ChaserEnemy[] getChasers() {
        return this.chaser;
    }
    
    public ShootingEnemy[] getShooters() {
        return this.shooter;
    }
}
