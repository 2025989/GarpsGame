import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;

/**
 * Write a description of class GameOverWindow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOverWindow extends TextToImage {
    
    private long startTime;
    private GreenfootImage image = new GreenfootImage(1, 1);
    boolean stopped = false;
    
    public GameOverWindow() {
        startTime = System.currentTimeMillis();
        setImage(image);
    }

    /**
     * Act - do whatever the GameOverWindow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (stopped == false && isGameOver() == true) {
            stopped = true;
            drawGameOverWindow();
        }
    }
    
    public boolean isGameOver() {
        if ((getWorld().getObjects(Garp.class).get(0).isGarpDead() == true
        && getWorld().getObjects(Goarp.class).get(0).isGoarpDead() == true)
        || (getWorld().getObjects(GarpGemScore.class).get(0).returnGarpScore()
        + getWorld().getObjects(GoarpGemScore.class).get(0).returnGoarpScore() == 10)) {
            return true;
        }
        else {return false;}
    }
    
    public String elapsedTime() {
        long duration = (System.currentTimeMillis()-startTime)/1000;
        int hh = (int)(duration/3600);
        int mm = (int)(duration%3600/60);
        int ss = (int)(duration%60);
        String elapsedTime = String.format("Elapsed time: %02d:%02d:%02d", hh, mm, ss);
        return elapsedTime;
    }
    
    public void drawGameOverWindow() {
        String winner = "";
        int scoreGarp = getWorld().getObjects(GarpGemScore.class).get(0).returnGarpScore();
        int scoreGoarp = getWorld().getObjects(GoarpGemScore.class).get(0).returnGoarpScore();
        if (scoreGarp > scoreGoarp) {winner = "Garp won this round";}
        if (scoreGarp == scoreGoarp) {winner = "It's a tie";}
        if (scoreGarp < scoreGoarp) {winner = "Goarp won this round";}
        if (scoreGarp == 10) {winner = "Garp is victorious!";}
        if (scoreGoarp == 10) {winner = "Goarp is victorious!";}
        
        //set image background
        GreenfootImage background = new GreenfootImage(550, 300);
        if (scoreGarp > scoreGoarp) {background.setColor(new Color(0, 150, 200));}
        if (scoreGarp == scoreGoarp) {background.setColor(new Color(200, 200, 0));}
        if (scoreGarp < scoreGoarp) {background.setColor(new Color(255, 0, 100));}
        if (scoreGarp == 10) {background.setColor(new Color(0, 255, 50));}
        if (scoreGoarp == 10) {background.setColor(new Color(150, 0, 200));}
        background.setTransparency(75);
        background.fillRect(0, 0, 550, 300);
        image.drawImage(background, 0, 0);
        image.scale(550, 300);
        
        /*----------* draw strings into image *----------*/
        image.setFont(new Font("Arial", Font.BOLD, 20));
        String text1 = elapsedTime();
        Dimension dim1 = getTextDimensions(image, text1);
        image.setColor(new Color(200, 200, 200));
        image.drawString(text1, image.getWidth()/2-(int)(dim1.getWidth()/2)+1, image.getHeight()/2-99);
        image.setColor(new Color(0, 0, 0));
        image.drawString(text1, image.getWidth()/2-(int)(dim1.getWidth()/2), image.getHeight()/2-100);
        
        String text2 = "Gems collected";
        image.fillRect(image.getWidth()/2-85, 83, 170, 2);
        Dimension dim2 = getTextDimensions(image, text2);
        image.setColor(new Color(200, 200, 200));
        image.drawString(text2, image.getWidth()/2-(int)(dim2.getWidth()/2)+1, image.getHeight()/2-69);
        image.setColor(new Color(0, 0, 0));
        image.drawString(text2, image.getWidth()/2-(int)(dim2.getWidth()/2), image.getHeight()/2-70);
        
        image.setFont(new Font("Arial", Font.BOLD, 16));
        String text3 = "Garp: "+ scoreGarp;
        Dimension dim3 = getTextDimensions(image, text3);
        image.setColor(new Color(0, 0, 0));
        image.drawString(text3, image.getWidth()/12*5-(int)(dim3.getWidth()/2)+1, image.getHeight()/2-47);
        image.setColor(new Color(25, 200, 75));
        image.drawString(text3, image.getWidth()/12*5-(int)(dim3.getWidth()/2), image.getHeight()/2-48);
        
        String text4 = "Goarp: "+ scoreGoarp;
        Dimension dim4 = getTextDimensions(image, text4);
        image.setColor(new Color(0, 0, 0));
        image.drawString(text4, image.getWidth()/12*7-(int)(dim4.getWidth()/2)+1, image.getHeight()/2-47);
        image.setColor(new Color(200, 0, 175));
        image.drawString(text4, image.getWidth()/12*7-(int)(dim4.getWidth()/2), image.getHeight()/2-48);
        
        image.setFont(new Font("Arial", Font.BOLD, 48));
        String text5 = winner;
        Dimension dim5 = getTextDimensions(image, text5);
        image.setColor(new Color(0, 0, 0));
        image.drawString(text5, image.getWidth()/2-(int)(dim5.getWidth()/2)+1, image.getHeight()/2+11);
        if (scoreGarp > scoreGoarp) {image.setColor(new Color(0, 150, 200));}
        if (scoreGarp == scoreGoarp) {image.setColor(new Color(200, 200, 0));}
        if (scoreGarp < scoreGoarp) {image.setColor(new Color(255, 0, 100));}
        if (scoreGarp == 10) {image.setColor(new Color(0, 255, 50));}
        if (scoreGoarp == 10) {image.setColor(new Color(150, 0, 200));}
        image.drawString(text5, image.getWidth()/2-(int)(dim5.getWidth()/2)-1, image.getHeight()/2+9);
        
        image.setFont(new Font("Arial", Font.BOLD, 16));
        String text6 = randomJoke();
        Dimension dim6 = getTextDimensions(image, text6);
        image.setColor(new Color(200, 200, 200));
        image.drawString(text6, image.getWidth()/2-(int)(dim6.getWidth()/2)+1, image.getHeight()/2+76);
        image.setColor(new Color(0, 0, 0));
        image.drawString(text6, image.getWidth()/2-(int)(dim6.getWidth()/2), image.getHeight()/2+75);
        /*-----------------------------------------------*/
        
        setImage(image);
    }
    
    public String randomJoke() {
        String[] randomJoke = {
            "Now where do we sell all these precious gems?",
            "You should tell your parents. They'd be proud.",
            "Wow, you're really good at this.",
            "That's just awesome.",
            "You should take the rest of the day off now.",
            "Well done.",
            "Now go save the princess!",
            "What took you so long?",
            "You'd do better if you planned ahead.",
            "You can do better!",
            "This was a mistake.",
            "Maybe it's better if you don't try again.",
            "Oh well.",
            "Go home. Never come back.",
            "What were you even thinking?",
            "That's cute.",
        };
        String text = randomJoke[(int)(Math.random()*randomJoke.length)];
        return text;
    }
    
}