public class testing{
    public static void main(String[] args){
        
        int coinFlip = (int) Math.floor(Math.random()*(2 - 1 + 1) + 1);
        System.out.println(coinFlip);
        
        if (coinFlip == 1){
            int coinFlip2 = (int) Math.floor(Math.random()*(2 - 1 + 1) + 1);
            System.out.println(coinFlip2);
            
            if (coinFlip2 == 1){
                
                int enemyX = (int) Math.floor(Math.random()*((Const.FRAME_WIDTH + 30) - (-30) + 1) + (-30));
                int enemyY = -30;
                System.out.println(enemyX + ", " + enemyY);
            }
            
            else if (coinFlip2 == 2){
                
                int enemyX = (int) Math.floor(Math.random()*((Const.FRAME_WIDTH + 30) - (-30) + 1) + (-30));
                int enemyY = 1110;
                System.out.println(enemyX + ", " + enemyY);
            }
            
            
        }
        
        else if (coinFlip == 2){
            
            int coinFlip2 = (int) Math.floor(Math.random()*(2 - 1 + 1) + 1);
            System.out.println(coinFlip2);
            
            
            
            if (coinFlip2 == 1){
                int enemyX = -30;
                int enemyY = (int) Math.floor(Math.random()*((Const.FRAME_WIDTH + 30) - (-30) + 1) + (-30));
                System.out.println(enemyX + ", " + enemyY);
            }
            
            else if (coinFlip2 == 2){
                int enemyX = 1950;
                int enemyY = (int) Math.floor(Math.random()*((Const.FRAME_WIDTH + 30) - (-30) + 1) + (-30));
                System.out.println(enemyX + ", " + enemyY);
            }
        }
    }
}