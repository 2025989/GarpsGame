import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gnomus here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gnomus extends Actor {
    
    int speed = 3;
    
    /**
     * Act - do whatever the Gnomus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (getWorld().getObjects(GameOverWindow.class).get(0).isGameOver() == false) {
            if (objectCollision() == false) {rotate();}
            move(speed);
            objectCollision();
        }
    }
    
    protected void rotate() {
        int dx = getWorld().getObjects(Garp.class).get(0).getX()-getX();
        int dy = getWorld().getObjects(Garp.class).get(0).getY()-getY();
        
        if (dx > 0 && dy == 0) {setRotation(0);}
        if (dx > 0 && dy > 0) {setRotation(45);}
        if (dx == 0 && dy > 0) {setRotation(90);}
        if (dx < 0 && dy > 0) {setRotation(135);}
        if (dx < 0 && dy == 0) {setRotation(180);}
        if (dx < 0 && dy < 0) {setRotation(225);}
        if (dx == 0 && dy < 0) {setRotation(270);}
        if (dx > 0 && dy < 0) {setRotation(315);}
    }
    
    protected boolean objectCollision() {
        int dx = getWorld().getObjects(Garp.class).get(0).getX()-getX();
        int dy = getWorld().getObjects(Garp.class).get(0).getY()-getY();
        
        Actor obj = getOneObjectAtOffset(0, 0, Actor.class);
        if (obj != null) {
            if ((dx > 0 && dy < 0) && (getRotation() == 315 || getRotation() == 0)) {setRotation(270);}
            if ((dx > 0 && dy > 0) && (getRotation() == 45 || getRotation() == 0)) {setRotation(90);}
            if ((dx > 0 && dy > 0) && (getRotation() == 45 || getRotation() == 90)) {setRotation(0);}
            if ((dx < 0 && dy > 0) && (getRotation() == 135 || getRotation() == 90)) {setRotation(180);}
            if ((dx < 0 && dy > 0) && (getRotation() == 135 || getRotation() == 180)) {setRotation(90);}
            if ((dx < 0 && dy < 0) && (getRotation() == 255 || getRotation() == 180)) {setRotation(270);}
            if ((dx < 0 && dy < 0) && (getRotation() == 255 || getRotation() == 270)) {setRotation(180);}
            if ((dx > 0 && dy < 0) && (getRotation() == 315 || getRotation() == 270)) {setRotation(0);}
            return true;
        }
        else {return false;}
    }
    
}