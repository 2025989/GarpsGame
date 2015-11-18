import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

/**
 * Write a description of class GoarpGemScore here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GoarpGemScore extends TextToImage {
    
    int goarpScore = -1;
    
    public GoarpGemScore() {
        updateGoarpScore();
    }

    /**
     * Act - do whatever the GoarpGemScore wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Add your action code here.
    }
    
    public void updateGoarpScore() {
        goarpScore++;
        
        //set image dimensions
        GreenfootImage image = new GreenfootImage(1, 1);
        image.setFont(new Font("Arial", Font.BOLD, 11));
        String scoreText = "Gems collected: "+ goarpScore;
        Dimension dim = getTextDimensions(image, scoreText);
        
        //draw string into image
        image.scale(dim.width, dim.height);
        image.setColor(new Color(225, 225, 225));
        image.drawString(scoreText, 1, dim.height+1);
        image.setColor(new Color(150, 0, 125));
        image.drawString(scoreText, 0, dim.height);
        setImage(image);
    }
    
    public int returnGoarpScore() {
        return goarpScore;
    }
    
}