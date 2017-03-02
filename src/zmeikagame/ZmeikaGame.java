package zmeikagame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import zmeikagame.Apple.*;
import java.math.*;
import static zmeikagame.Sound.*;




public class ZmeikaGame extends JPanel implements ActionListener
{
    public static final int KLETKA = 20;
    public static final int HEIGHT = 30;
    public static final int WIDTH = 45;
    public static final int SPEED = 5;
    
    
    Apple a = new Apple((int) (Math.random() * WIDTH), (int) (Math.random() * HEIGHT));
    Zmeika z = new Zmeika(10, 10, 9, 10);
    Timer t = new Timer(1000/SPEED, this);

    public ZmeikaGame() 
    { 
        t.start();
        addKeyListener(new Keyboard());
        setFocusable(true);
        playSound("E:\\JAVA\\ZmeikaGame\\dendy_games_super_mario_8_bit.wav");
    }
    
    
    
    public void paint(Graphics g)
    {
        g.setColor(color(15,50,20));
        g.fillRect(0,0,WIDTH * KLETKA,HEIGHT * KLETKA);
        g.setColor(color(255,216,0));
        
        
        for(int xx = 0;xx <= WIDTH * KLETKA; xx+=KLETKA)
        {
            g.drawLine(xx, 0, xx, HEIGHT * KLETKA);
        }
        
        for(int yy = 0;yy <= HEIGHT * KLETKA; yy+=KLETKA)
        {
            g.drawLine(0, yy, WIDTH * KLETKA, yy);
        }
        
        for(int d = 0;d < z.length;d++)
        {
            g.setColor(color(20, 30, 150));
            g.fillRect(z.zmeiX[d] * KLETKA + 1, z.zmeiY[d] * KLETKA +1, KLETKA -1 , KLETKA - 1);
        }
        
        g.setColor(color(255,0,0));
        g.fillRect(a.posX * KLETKA + 1, a.posY * KLETKA + 1, KLETKA - 1, KLETKA - 1);
       
    }
    public Color color(int red, int green,int blue )
    {
        return new Color(red,green,blue);
    }
    
    public void actionPerformed(ActionEvent arg0)
    {
        z.move();
        
        if((z.zmeiX[0] == a.posX) & (z.zmeiY[0] == a.posY))
        {
            Sound sss = new Sound(new File("E:\\JAVA\\ZmeikaGame\\Cookie-monster-Omnomnom_freemuzichka.wav"));
            sss.start();
            a.setRandomPosition();
            z.length++;
        }
        
        for(int k = 1; k < z.length;k++)
        {
            if((z.zmeiX[k] == a.posX) & (z.zmeiY[k] == a.posY))
            {
                a.setRandomPosition();
                
            }
        }
       repaint();
    }
    

    
    public static void main(String[] args) 
    {
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(WIDTH * KLETKA + 8 ,HEIGHT * KLETKA + 9);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.add(new ZmeikaGame());
        f.setVisible(true);
        
     
        
    }

    private class Keyboard extends KeyAdapter
    {
        public void keyPressed(KeyEvent ke)
        {
            int key = ke.getKeyCode();

            if((key == KeyEvent.VK_RIGHT) & z.direct != 2)z.direct = 0;
            if((key == KeyEvent.VK_DOWN) & z.direct != 3)z.direct = 1;
            if((key == KeyEvent.VK_LEFT) & z.direct != 0)z.direct = 2;
            if((key == KeyEvent.VK_UP) & z.direct != 1)z.direct = 3;
        }
    }
    
    
    
}
