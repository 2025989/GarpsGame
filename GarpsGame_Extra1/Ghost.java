import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ghost here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ghost extends Actor {
    
    /**
     * Act - do whatever the Ghost wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        // Add your action code here.
    }
    
    public boolean collision() {
        Actor obj = getOneObjectAtOffset(0, 0, Actor.class);
        if (obj != null) {
            return true;
        }
        else {return false;}
    }
    
}