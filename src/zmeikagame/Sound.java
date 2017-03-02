package zmeikagame;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Sound extends Thread implements LineListener 
{
    //Thread t = null;
    private boolean relesed = false;
    private  Clip clip = null;
    private boolean playing = false;
    
    
    
    
    public Sound(File f)
    {
        try
        {
            AudioInputStream stream = AudioSystem.getAudioInputStream(f);
            clip = AudioSystem.getClip();
            clip.open(stream);
            clip.addLineListener(this);
            relesed = true;
        }catch(IOException | UnsupportedAudioFileException | LineUnavailableException e)
        {
            e.printStackTrace();
            relesed = false;
        }
    }
    
    
    public  boolean isRelesed()
    {
        return relesed;
    }
    
    public boolean isPlaying()
    {
        return playing;
        
    }
    public void play(boolean breackOld)
    {
        if(relesed)
        {
            if(breackOld)
            {
                clip.stop();
                clip.setFramePosition(0);
                clip.start();
                playing = true;
                
            }else if(!breackOld)
            {
                clip.setFramePosition(0);
                clip.start();
                playing = true;
            }
        }
    }
    public void play()
    {
        play(true);
    }
//    public void stop()
//    {
//        if(playing)
//        {
//            clip.stop();
//        }
//    }
    
    public void joiin()
    {
        if(!relesed)
        {
            return;
        }
        synchronized(clip)
        {
            try
            {
                while(playing)clip.wait();
            }catch(InterruptedException e)
            {
                
            }
        }
    }
    
    public static Sound playSound(String s)
    {
        File f = new File(s);
	Sound snd = new Sound(f);
	snd.play();
	return snd;
    }
    
    public void run()
    {
        File f = new File("E:\\JAVA\\ZmeikaGame\\Cookie-monster-Omnomnom_freemuzichka.wav");
	Sound snd = new Sound(f);
	snd.play(false);
    }
//    public static void playapple(String s)
//    {
//        File f = new File(s);
//	Sound snd = new Sound(f);
//	snd.play();
//    }
    {
        
    }

    @Override
    public void update(LineEvent event) 
    {
        if(event.getType() == LineEvent.Type.STOP)
        {
           playing = false;
           synchronized(clip)
           {
               clip.notify();
           }
           
        }
    }
}
