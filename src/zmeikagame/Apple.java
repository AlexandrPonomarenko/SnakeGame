
package zmeikagame;

import static zmeikagame.ZmeikaGame.main;
import zmeikagame.ZmeikaGame;
import static zmeikagame.ZmeikaGame.KLETKA;
import static zmeikagame.ZmeikaGame.WIDTH;


public class Apple 
{
    ZmeikaGame main;
    
    public int posX;
    public int posY;
    
    
    public Apple(int startX, int startY)
    {
        posX = startX;
        posY = startY;
    }
    public void setRandomPosition()
    {
        posX = (int) (Math.random() * main.WIDTH);
        posY = (int) (Math.random() * main.HEIGHT);
    }
}
