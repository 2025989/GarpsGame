import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

/**
 * Write a description of class GarpGemScore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GarpGemScore extends TextToImage {
    
    int garpScore = -1;
    
    public GarpGemScore() {
        updateGarpScore();
    }

    /**
     * Act - do whatever the GarpGemScore wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Add your action code here.
    }
    
    public void updateGarpScore() {
        garpScore++;
        
        //set image dimensions
        GreenfootImage image = new GreenfootImage(1, 1);
        image.setFont(new Font("Arial", Font.BOLD, 11));
        String scoreText = "Gems collected: "+ garpScore;
        Dimension dim = getTextDimensions(image, scoreText);
        
        //draw string into image
        image.scale(dim.width, dim.height);
        image.setColor(new Color(225, 225, 225));
        image.drawString(scoreText, 1, dim.height+1);
        image.setColor(new Color(0, 100, 50));
        image.drawString(scoreText, 0, dim.height);
        setImage(image);
    }
    
    public int returnGarpScore() {
        return garpScore;
    }
    
}