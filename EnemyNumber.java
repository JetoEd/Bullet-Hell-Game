import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyNumber{
    private ChaseEnemy[] enemies;
    
    EnemyNumber(){
        this.enemies = new ChaseEnemy[Const.CHASE_LIMIT];
    }
//------------------------------------------------------------------------------      
    public void drawEnemies(Graphics g){
        for (int i = 0; i < this.enemies.length; i++){
            //if not greater than cartridge size, can shoot more bullets
            if (this.enemies[i] != null){
                this.enemies[i].draw(g);
                this.enemies[i].draw2(g);
            }
        }
    }
    
    public void moveEnemies(Player player){
        for (int i = 0; i < this.enemies.length; i++){
            if (this.enemies[i] != null){
                this.enemies[i].move(player);
                
                
//                //Remove bullets when out of screen
//                if ((this.bullets[i].getY() < 0) || (this.bullets[i].getY() > (Const.FRAME_HEIGHT - 40))){
//                    this.removeBullet(i);
//                }
//                else if ((this.bullets[i].getX() < 0)|| (this.bullets[i].getX() > Const.FRAME_WIDTH)){
//                    this.removeBullet(i);
//                }
                
            }
        }
    }
//------------------------------------------------------------------------------  
    public void addEnemy(Player player){
        boolean added = false;
        for (int i = 0; i < this.enemies.length; i++){
            if (this.enemies[i] == null && !added){
                
                int coinFlip = (int) Math.floor(Math.random()*(2 - 1 + 1) + 1);
                
                if (coinFlip == 1){
                    int coinFlip2 = (int) Math.floor(Math.random()*(2 - 1 + 1) + 1);

                    if (coinFlip2 == 1){
                        int enemyX = (int) Math.floor(Math.random()*((Const.FRAME_WIDTH + 30) - (-30) + 1) + (-30));
                        int enemyY = -30;
                        Rectangle enemyHitbox = new Rectangle (enemyX, enemyY, Const.CHASE_WIDTH, Const.CHASE_HEIGHT);
                        this.enemies[i] = new ChaseEnemy(enemyX, enemyY, Const.CHASE_WIDTH, Const.CHASE_HEIGHT, enemyHitbox);
                    }
                    
                    else if (coinFlip2 == 2){
                        int enemyX = (int) Math.floor(Math.random()*((Const.FRAME_WIDTH + 30) - (-30) + 1) + (-30));
                        int enemyY = 1110;
                        Rectangle enemyHitbox = new Rectangle (enemyX, enemyY, Const.CHASE_WIDTH, Const.CHASE_HEIGHT);
                        this.enemies[i] = new ChaseEnemy(enemyX, enemyY, Const.CHASE_WIDTH, Const.CHASE_HEIGHT, enemyHitbox);
                    }
                }
                
                else if (coinFlip == 2){
                    
                    int coinFlip2 = (int) Math.floor(Math.random()*(2 - 1 + 1) + 1);
                    
                    if (coinFlip2 == 1){
                        int enemyX = -30;
                        int enemyY = (int) Math.floor(Math.random()*((Const.FRAME_WIDTH + 30) - (-30) + 1) + (-30));
                        Rectangle enemyHitbox = new Rectangle (enemyX, enemyY, Const.CHASE_WIDTH, Const.CHASE_HEIGHT);
                        this.enemies[i] = new ChaseEnemy(enemyX, enemyY, Const.CHASE_WIDTH, Const.CHASE_HEIGHT, enemyHitbox);
                    }
                    
                    else if (coinFlip2 == 2){
                        int enemyX = 1950;
                        int enemyY = (int) Math.floor(Math.random()*((Const.FRAME_WIDTH + 30) - (-30) + 1) + (-30));
                        Rectangle enemyHitbox = new Rectangle (enemyX, enemyY, Const.CHASE_WIDTH, Const.CHASE_HEIGHT);
                        this.enemies[i] = new ChaseEnemy(enemyX, enemyY, Const.CHASE_WIDTH, Const.CHASE_HEIGHT, enemyHitbox);
                    }
                }
                added = true;
            }
        }
    }
    
    public void removeEnemy(int index){
        this.enemies[index] = null;
    }
    
    public ChaseEnemy[] getEnemies(){
        return this.enemies;
    }
}