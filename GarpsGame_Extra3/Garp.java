import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Garp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Garp extends Actor {
    
    private GreenfootImage imageLeft = new GreenfootImage("GarpLeft.png");
    private GreenfootImage imageRight = new GreenfootImage("GarpRight.png");
    private GreenfootSound gemSound = new GreenfootSound("Rupee.wav");
    public boolean isGarpDead = false;
    GreenfootImage noImg = new GreenfootImage(1, 1);
            
    
    public Garp() {
        setImage(imageRight);
        noImg.clear();
    }
    
    /**
     * Act - do whatever the Garp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (isGarpDead == false
        && (getWorld().getObjects(GarpGemScore.class).get(0).returnGarpScore()
        + getWorld().getObjects(GoarpGemScore.class).get(0).returnGoarpScore() != 10)) {
            movement();
            collectingGems();
            checkExploded();
            checkMurdered();
        }
    }
    
    protected void movement() {
        if (Greenfoot.isKeyDown("D")) {
            setImage(imageRight);
            setRotation(0);
            move(5);
            if (foundRock() || atWorldEdge()) {move(-5);}
        }
        if (Greenfoot.isKeyDown("A")) {
            setImage(imageLeft);
            setRotation(180);
            move(5);
            if (foundRock() || atWorldEdge()) {move(-5);}
        }
        if (Greenfoot.isKeyDown("W")) {
            setImage(imageRight);
            setRotation(270);
            move(5);
            if (foundRock() || atWorldEdge()) {move(-5);}
        }
        if (Greenfoot.isKeyDown("S")) {
            setImage(imageRight);
            setRotation(90);
            move(5);
            if (foundRock() || atWorldEdge()) {move(-5);}
        }
    }
    
    /* collision */
    protected boolean foundRock() {
        Actor rock = getOneObjectAtOffset(0, 0, Rock.class);
        if(rock != null) {return true;}
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
    
    protected void collectingGems() {
        Actor gem = getOneObjectAtOffset(0, 0, Gem.class);
        if (gem != null) {
            getWorld().removeObject(gem);
            getWorld().getObjects(GarpGemScore.class).get(0).updateGarpScore();
            gemSound.stop();
            gemSound.play();
        }
    }
    
    /* death */
    public void checkExploded() {
        Actor bomb = getOneObjectAtOffset(0, 0, Bomb.class);
        if (bomb != null) {
            getWorld().addObject(new Explosion(), getX(), getY());
            getWorld().removeObject(bomb);
            setImage(noImg);
            isGarpDead = true;
        }
    }
    protected void checkMurdered() {
        Actor gnomus = getOneObjectAtOffset(0, 0, Gnomus.class);
        if (gnomus != null) {
            Greenfoot.playSound("screamGarp.mp3");
            setImage(noImg);
            if (getWorld().getObjects(Goarp.class).get(0).isGoarpDead() == true) {Greenfoot.stop();}
            isGarpDead = true;
        }
    }
    public boolean isGarpDead() {
        return isGarpDead;
    }
    
}