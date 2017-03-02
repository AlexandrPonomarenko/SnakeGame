package zmeikagame;
import static zmeikagame.ZmeikaGame.*;


public class Zmeika 
{
    ZmeikaGame main;
    
    public int direct = 3;
    public int length = 10;
    
    public int zmeiX[] = new int [main.WIDTH * main.HEIGHT];
    public int zmeiY[] = new int [main.WIDTH * main.HEIGHT];
    public Zmeika(int x0,int y0,int x1,int y1)
    {
        zmeiX[0] = x0;
        zmeiY[0] = y0;
        zmeiX[1] = x1;
        zmeiY[1] = y1;
    }
    
    public void move()
    {
        
        
        for(int d = length;d > 0;d--)
        {
            zmeiX[d] = zmeiX[d - 1];
            zmeiY[d] = zmeiY[d - 1];
        }
        if(direct == 0)
        {
            zmeiX[0]++;
        }
        if(direct == 1)
        {
            zmeiY[0]++;
        }
        if(direct == 2)
        {
            zmeiX[0]--;
        }
        if(direct == 3)
        {
            zmeiY[0]--;
        }
        
        
        for(int d = length; d > 0; d--)
        {
            if((zmeiX[0] == zmeiX[d]) & (zmeiY[0] == zmeiY[d])) length = d - 2;
        }
        if (zmeiX[0] > WIDTH ) zmeiX[0] = 0;
        if (zmeiX[0] < 0 ) zmeiX[0] = WIDTH - 1;
        if (zmeiY[0] > HEIGHT - 1 ) zmeiY[0] = 0;
        if (zmeiY[0] < 0 ) zmeiY[0] = HEIGHT - 1;
        
        
        if(length < 2) length = 2;
        
        
    }
}
