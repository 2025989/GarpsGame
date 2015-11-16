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
        GarpGemScore gemScore = new GarpGemScore();
        int xCounter = gemScore.getImage().getWidth()/2+8;
        int yCounter = getHeight()-gemScore.getImage().getHeight()/2-4;

        addObject(new GarpGemScore(), xCounter, yCounter);
        addObject(new GoarpGemScore(), getWidth()-xCounter, yCounter);
        addObject(new GameOverWindow(), getWidth()/2, getHeight()/2);
        randomlyPlaceObjectLeft(new Garp());
        randomlyPlaceObjectRight(new Goarp());
        randomlyPlaceObject(new Gnomus());
        for (int i = 0; i < 10; i++) {
            if (i < 10) {randomlyPlaceObject(new Gem());}
            if (i < 06) {randomlyPlaceObject(new Rock());}
            if (i < 04) {randomlyPlaceObject(new Bomb());}
        }

        setPaintOrder(
            GarpGemScore.class, GoarpGemScore.class, GameOverWindow.class,
            Explosion.class,
            Garp.class, Goarp.class, Gnomus.class,
            Gem.class, Bomb.class, Rock.class
        );
    }

    protected void randomlyPlaceObject(Actor obj) {
        int x = Greenfoot.getRandomNumber(getWidth()-40)+20;
        int y = Greenfoot.getRandomNumber(getHeight()-55)+20;
        addObject(obj, x, y);
    }

    protected void randomlyPlaceObjectLeft(Actor obj) {
        int x = Greenfoot.getRandomNumber(getWidth()/2-20)+20;
        int y = Greenfoot.getRandomNumber(getHeight()-55)+20;
        addObject(obj, x, y);
    }

    protected void randomlyPlaceObjectRight(Actor obj) {
        int x = Greenfoot.getRandomNumber(getWidth()/2-20)+getWidth()/2;
        int y = Greenfoot.getRandomNumber(getHeight()-55)+20;
        addObject(obj, x, y);
    }
    
}