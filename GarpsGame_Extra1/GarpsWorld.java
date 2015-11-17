import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GarpsWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GarpsWorld extends World {
    
    private GreenfootSound music = new GreenfootSound("Zelda.mp3");
    
    /**
     * Constructor for objects of class GarpsWorld.
     * 
     */
    public GarpsWorld() {    
        // Create a new world with 700x500 cells with a cell size of 1x1 pixels.
        super(700, 500, 1);
        populateTheWorld();
    }
    
    public void started() {
        if (getObjects(GameOverWindow.class).get(0).isGameOver() == false) {
            music.playLoop();
        }
    }
    public void stopped() {
        music.pause();
    }
    
    protected void populateTheWorld() {
        GemScore gemScore = new GemScore();
        int xCounter = gemScore.getImage().getWidth()/2+8;
        int yCounter = getHeight()-gemScore.getImage().getHeight()/2-4;
        
        addObject(new GemScore(), xCounter, yCounter);
        addObject(new GameOverWindow(), getWidth()/2, getHeight()/2);
        addObject(new Garp(), getWidth()/2, getHeight()/2);
        randomlyPlaceObject(new Gnomus());
        for (int i = 0; i < 10; i++) {
            if (i < 10) {randomlyPlaceObject(new Gem());}
            if (i < 06) {randomlyPlaceObject(new Rock());}
            if (i < 04) {randomlyPlaceObject(new Bomb());}
        }
        
        setPaintOrder(
            GemScore.class, GameOverWindow.class,
            Explosion.class,
            Garp.class, Gnomus.class,
            Gem.class, Bomb.class, Rock.class,
            Ghost.class
        );
    }
    
    protected void randomlyPlaceObject(Actor obj) {
        int x = Greenfoot.getRandomNumber(getWidth()-40)+20;
        int y = Greenfoot.getRandomNumber(getHeight()-55)+20;
        addObject(new Ghost(), x, y);
        while (getObjects(Ghost.class).get(0).collision() == true) {
            removeObject(getObjects(Ghost.class).get(0));
            x = Greenfoot.getRandomNumber(getWidth()-40)+20;
            y = Greenfoot.getRandomNumber(getHeight()-55)+20;
            addObject(new Ghost(), x, y);
        }
        removeObject(getObjects(Ghost.class).get(0));
        addObject(obj, x, y);
    }
    
}