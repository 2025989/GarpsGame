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
    boolean victory = false;
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
        if (getWorld().getObjects(Garp.class).get(0).isDead() == true
        || getWorld().getObjects(GemScore.class).get(0).returnScore() == 10
        || getWorld().getObjects(Gnomus.class).get(0).isGnomusDead() == true) {
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
        String gameOverMessage = "";
        int score = getWorld().getObjects(GemScore.class).get(0).returnScore();
        if (score == 10 || getWorld().getObjects(Gnomus.class).get(0).isGnomusDead() == true) {
            victory = true;
        }
        if (victory == true) {gameOverMessage = "Garp is victorious!";}
        else {gameOverMessage = "Garp died.";}
        
        //set background
        GreenfootImage background = new GreenfootImage(475, 300);
        if (victory == true) {background.setColor(new Color(0, 255, 100));}
        else {background.setColor(new Color(255, 0, 100));}
        background.setTransparency(75);
        background.fillRect(0, 0, 475, 300);
        image.drawImage(background, 0, 0);
        
        //set text variables
        String text1 = elapsedTime();
        String text2 = "Gems collected: "+ score;
        String text3 = gameOverMessage;
        String text4 = randomJoke();
        
        //set dimensions
        image.scale(475, 300);
        image.setFont(new Font("Arial", Font.BOLD, 20));
        Dimension dim1 = getTextDimensions(image, text1);
        Dimension dim2 = getTextDimensions(image, text2);
        image.setFont(new Font("Arial", Font.BOLD, 48));
        Dimension dim3 = getTextDimensions(image, text3);
        image.setFont(new Font("Arial", Font.BOLD, 16));
        Dimension dim4 = getTextDimensions(image, text4);
        
        /*----------* draw strings into image *----------*/
        image.setFont(new Font("Arial", Font.BOLD, 20));
        image.setColor(new Color(200, 200, 200));
        image.drawString(text1, image.getWidth()/2-(int)(dim1.getWidth()/2)+1, image.getHeight()/2-79);
        image.setColor(new Color(0, 0, 0));
        image.drawString(text1, image.getWidth()/2-(int)(dim1.getWidth()/2), image.getHeight()/2-80);
        
        image.setColor(new Color(200, 200, 200));
        image.drawString(text2, image.getWidth()/2-(int)(dim2.getWidth()/2)+1, image.getHeight()/2-49);
        image.setColor(new Color(0, 0, 0));
        image.drawString(text2, image.getWidth()/2-(int)(dim2.getWidth()/2), image.getHeight()/2-50);
        
        image.setFont(new Font("Arial", Font.BOLD, 48));
        image.drawString(text3, image.getWidth()/2-(int)(dim3.getWidth()/2)+1, image.getHeight()/2+11);
        if (victory == true) {image.setColor(new Color(100, 255, 0));}
        else {image.setColor(new Color(255, 100, 0));}
        image.drawString(text3, image.getWidth()/2-(int)(dim3.getWidth()/2)-1, image.getHeight()/2+9);
        
        image.setFont(new Font("Arial", Font.BOLD, 16));
        image.setColor(new Color(200, 200, 200));
        image.drawString(text4, image.getWidth()/2-(int)(dim4.getWidth()/2)+1, image.getHeight()/2+76);
        image.setColor(new Color(0, 0, 0));
        image.drawString(text4, image.getWidth()/2-(int)(dim4.getWidth()/2), image.getHeight()/2+75);
        /*-----------------------------------------------*/
        
        setImage(image);
    }
    
    public String randomJoke() {
        String text = "";
        String[] victoryJoke = {
            "Now where do we sell all these precious gems?",
            "You should tell your parents. They'd be proud.",
            "Wow, you're really good at this.",
            "That's just awesome.",
            "You should take the rest of the day off now.",
            "Well done.",
            "Now go save the princess!",
            "What took you so long?"
        };
        String[] failureJoke = {
            "You'd do better if you planned ahead.",
            "You can do better!",
            "This was a mistake.",
            "Maybe it's better if you don't try again.",
            "Oh well.",
            "Go home. Never come back.",
            "What were you even thinking?",
            "That's cute.",
        };

        if (victory == true) {
            if (getWorld().getObjects(Gnomus.class).get(0).isGnomusDead() == true) {
                text = "Gnomus died.";
            }
            else {text = victoryJoke[(int)(Math.random()*victoryJoke.length)];}
        }
        else {text = failureJoke[(int)(Math.random()*failureJoke.length)];}
        
        return text;
    }
    
}