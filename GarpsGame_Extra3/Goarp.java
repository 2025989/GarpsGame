import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Goarp here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Goarp extends Actor {
    
    private GreenfootImage imageLeft = new GreenfootImage("GoarpLeft.png");
    private GreenfootImage imageRight = new GreenfootImage("GoarpRight.png");
    private GreenfootSound gemSound = new GreenfootSound("Rupee.wav");
    public boolean isGoarpDead = false;
    GreenfootImage noImg = new GreenfootImage(1, 1);
            
    
    public Goarp() {
        setImage(imageLeft);
        setRotation(180);
        noImg.clear();
    }
    
    /**
     * Act - do whatever the Goarp wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() {
        if (isGoarpDead == false
        && (getWorld().getObjects(GarpGemScore.class).get(0).returnGarpScore()
        + getWorld().getObjects(GoarpGemScore.class).get(0).returnGoarpScore() != 10)) {
            movement();
            collectingGems();
            checkExploded();
            checkMurdered();
        }
    }
    
    protected void movement() {
        if (Greenfoot.isKeyDown("right")) {setRotation(0);}
        if (Greenfoot.isKeyDown("left")) {setRotation(180);}
        if (Greenfoot.isKeyDown("up")) {setRotation(270);}
        if (Greenfoot.isKeyDown("down")) {setRotation(90);}
        if (Greenfoot.isKeyDown("up") && Greenfoot.isKeyDown("right")) {setRotation(315);}
        if (Greenfoot.isKeyDown("up") && Greenfoot.isKeyDown("left")) {setRotation(225);}
        if (Greenfoot.isKeyDown("down") && Greenfoot.isKeyDown("right")) {setRotation(45);}
        if (Greenfoot.isKeyDown("down") && Greenfoot.isKeyDown("left")) {setRotation(135);}
        
        if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("left")
        || Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("down")) {
            if (Greenfoot.isKeyDown("left")) {setImage(imageLeft);}
            else {setImage(imageRight);}
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
            getWorld().getObjects(GoarpGemScore.class).get(0).updateGoarpScore();
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
            isGoarpDead = true;
        }
    }
    protected void checkMurdered() {
        Actor gnomus = getOneObjectAtOffset(0, 0, Gnomus.class);
        if (gnomus != null) {
            Greenfoot.playSound("screamGoarp.mp3");
            setImage(noImg);
            if(getWorld().getObjects(Garp.class).get(0).isGarpDead() == true) {Greenfoot.stop();}
            isGoarpDead = true;
        }
    }
    public boolean isGoarpDead() {
        return isGoarpDead;
    }
    
}