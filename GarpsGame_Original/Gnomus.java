import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gnomus here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gnomus extends Actor {
    
    public Gnomus() {
        rotate();
    }
    
    /**
     * Act - do whatever the Gnomus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (getWorld().getObjects(GameOverWindow.class).get(0).isGameOver() == false) {
            move(5);
            if (atWorldEdge()) {
                move(-5);
                rotate();
            }
            else if (Greenfoot.getRandomNumber(100) < 4) { //2% chance of going left, 2% chance of going right
                rotate();
            }
            objectCollision();
        }
    }
    
    public boolean atWorldEdge() {
        if ( /*left*/   getX()-getImage().getWidth()/2 <= 0
        ||   /*right*/  getX()+getImage().getWidth()/2 >= getWorld().getWidth()
        ||   /*top*/    getY()-getImage().getHeight()/2 <= 0
        ||   /*bottom*/ getY()+getImage().getHeight()/2 >= getWorld().getHeight()) {
            return true;
        }
        else {return false;}
    }
    
    protected void rotate() {
         setRotation(getRotation()-180+Greenfoot.getRandomNumber(360));
    }
    
    protected void objectCollision() {
        Actor obj = getOneObjectAtOffset(0, 0, Actor.class);
        if (obj != null) {
            move(-5);
            turn(180);
            setRotation(getRotation()-90+Greenfoot.getRandomNumber(180));
        }
    }
    
}