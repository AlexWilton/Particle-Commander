package alex.wilton.cs4303.p1.game.entity;

import alex.wilton.cs4303.p1.game.App;
import alex.wilton.cs4303.p1.game.Vector;
import processing.core.PImage;

/**
 * Class to model and draw a Bomber.
 * A Bomber flies across the screen and drops particles when over one city.
 */
public class Bomber extends Entity{
    private int height = 20, width = 30;
    private static PImage bomberImg = App.app.loadImage("images/spaceship.png");

    /* bomber must fly at least 200 away from the bottom of the screen */
    private static final int MAX_ALLOWED_HEIGHT = App.WINDOW_HEIGHT - 200;

    private boolean bombDropped = false; //each bomber has one bomb to drop
    private boolean active = true;

    private Planet targetPlanet;

    private Vector position;
    private Vector velocity;

    public Bomber(Planet targetPlanet, Vector position, Vector velocity) {
        this.targetPlanet = targetPlanet;
        this.position = position;
        this.velocity = velocity;
    }

    /**
     * Create a Bomber. The higher the level, the closer the bomber is allowed to get to the planet
     * @param levelNumber Level Number
     * @return Bomber
     */
    public static Bomber createBomber(Planet targetPlanet, int levelNumber){
        int height = 100 + (int) (Math.random() * 100 * levelNumber);
        if(height > MAX_ALLOWED_HEIGHT) height = MAX_ALLOWED_HEIGHT;


        boolean startOnLeft = Math.random() < 0.5;
        Vector position, velocity;
        if(startOnLeft){
            position = new Vector(0, height);
            velocity = new Vector(1, 0);
        }else{
            position = new Vector(App.WINDOW_WIDTH, height);
            velocity = new Vector(-1, 0);
        }

        return new Bomber(targetPlanet, position, velocity);
    }


    @Override
    public void draw() {
        if(active) {
            app.imageMode(App.CENTER);
            app.image(bomberImg, (float) position.x, (float) position.y, width, height);
        }
    }

    /**
     * Update Bomber for one unit time
     * @return Particle if particle dropped, else null
     */
    public Particle integrate() {
        if(!active) return null;

        position.add(velocity);

        /* Mark Bomber as dead if they stray over the screen from the view */
        if ((position.x < 0) || (position.x > App.WINDOW_WIDTH )) active = false;

        if(!bombDropped && isOverCity()) {
            bombDropped = true;
            return dropBomb();
        }else{
            return null;
        }
    }

    /**
     * Create a particle which will act as a bomb dropped by the bomber
     * @return Particle (which will act as a bomb)
     */
    private Particle dropBomb() {
        return new Particle((int) position.x, (int) position.y, 0, 0, 0, .02f);
    }

    /**
     * Check if bomber is currently directly above an alive city
     * @return boolean
     */
    private boolean isOverCity() {
        int fudgeFactor = 15;
        for(City c : targetPlanet.getCities()){
            if(c.isDestroyed()) continue;
            if(c.x + fudgeFactor < position.x && position.x + fudgeFactor < c.x + c.width) return true;
        }
        return false;
    }


    public boolean isActive() {return active;}

    public void setActive(boolean active) {this.active = active;}

    public Vector getPosition() {return position;}

    public boolean getActive() {return active;}
}
