import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Gnomus here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gnomus extends Actor {
    
    GreenfootImage noImg = new GreenfootImage(1, 1);
    public boolean isGnomusDead = false;
    
    public Gnomus() {
        noImg.clear();
    }
    /**
     * Act - do whatever the Gnomus wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (getWorld().getObjects(GameOverWindow.class).get(0).isGameOver() == false) {
            movement();
            checkExploded();
        }
    }
    
    protected void movement() {
        if (Greenfoot.isKeyDown("D")) {
            setRotation(0);
            move(3);
            if (objectCollision() || atWorldEdge()) {move(-3);}
        }
        if (Greenfoot.isKeyDown("A")) {
            setRotation(180);
            move(3);
            if (objectCollision() || atWorldEdge()) {move(-3);}
        }
        if (Greenfoot.isKeyDown("W")) {
            setRotation(270);
            move(3);
            if (objectCollision() || atWorldEdge()) {move(-3);}
        }
        if (Greenfoot.isKeyDown("S")) {
            setRotation(90);
            move(3);
            if (objectCollision() || atWorldEdge()) {move(-3);}
        }
    }
    
    /* collision */
    protected boolean objectCollision() {
        Actor rock = getOneObjectAtOffset(0, 0, Rock.class);
        Actor gem = getOneObjectAtOffset(0, 0, Gem.class);
        if(rock != null || gem != null) {return true;}
        else {return false;}
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
    
    /* death */
    public void checkExploded() {
        Actor bomb = getOneObjectAtOffset(0, 0, Bomb.class);
        if (bomb != null) {
            getWorld().addObject(new Explosion(), getX(), getY());
            getWorld().removeObject(bomb);
            setImage(noImg);
            isGnomusDead = true;
        }
        else {isGnomusDead = false;}
    }
    public boolean isGnomusDead() {
        return isGnomusDead;
    }
    
}