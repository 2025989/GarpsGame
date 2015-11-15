import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

/**
 * Write a description of class GemScore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GemScore extends TextToImage {
    
    int score = -1;
    
    public GemScore() {
        updateScore();
    }

    /**
     * Act - do whatever the GemScore wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Add your action code here.
    }
    
    public void updateScore() {
        score++;
        
        //set image dimensions
        GreenfootImage image = new GreenfootImage(1, 1);
        image.setFont(new Font("Arial", Font.BOLD, 11));
        String scoreText = "Gems collected: "+ score;
        Dimension dim = getTextDimensions(image, scoreText);
        
        //draw string into image
        image.scale(dim.width, dim.height);
        image.setColor(new Color(200, 200, 200));
        image.drawString(scoreText, 1, dim.height+1);
        image.setColor(new Color(0, 0, 0));
        image.drawString(scoreText, 0, dim.height);
        setImage(image);
        
        //stop game if won
        if (score == 10) {Greenfoot.stop();}
    }
    
    public int returnScore() {
        return score;
    }
    
}