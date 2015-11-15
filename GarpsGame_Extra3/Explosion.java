import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Actor {
    
    private GreenfootImage[] images = new GreenfootImage[8];
    int action = 0, increment = 1;
    
    public Explosion(){
        for(int i = 0; i < images.length; i++){
            images[i] = new GreenfootImage("explosion.png");
            int sizeFactor = images[i].getWidth()/8;
            images[i].scale(sizeFactor*(i+1), sizeFactor*(i+1));
        }
    }
    
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        setImage(images[action]);
        if (action == 0 && increment == 1) {Greenfoot.playSound("Explosion.wav");}
        if (action >= images.length-1) {increment = -1;}
        action += increment;
        if (action < 0) {
            if (getWorld().getObjects(GameOverWindow.class).get(0).isGameOver() == true) {
                Greenfoot.stop();
            }
            getWorld().removeObject(this);
       }
    }
    
}