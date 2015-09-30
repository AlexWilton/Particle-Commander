package alex.wilton.cs4303.p1.game.entity;

import alex.wilton.cs4303.p1.game.App;
import alex.wilton.cs4303.p1.game.Vector;
import processing.core.PImage;

/**
 * A Bomber flies across the screen and drops particles when over one city
 *
 */
public class Bomber extends Entity{
    private int height = 20, width = 30;
    private static PImage bomberImg;
    private static final String BOMBER_IMAGE_FILEPATH = "images/spaceship.png";
    static{
        bomberImg = App.app.loadImage(BOMBER_IMAGE_FILEPATH);
    }


    private static final int MAX_ALLOWED_HEIGHT = App.WINDOW_HEIGHT - 200;

    private boolean bombDropped = false;
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

    private Particle dropBomb() {
        return new Particle((int) position.x, (int) position.y, 0, 0, 0, .1f);
    }

    private boolean isOverCity() {
        int fudgeFactor = 15;
        for(City c : targetPlanet.getCities()){
            if(c.isDestroyed()) continue;
            if(c.x + fudgeFactor < position.x && position.x + fudgeFactor < c.x + c.width) return true;
        }
        return false;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean containsPoint(double x, double y) {
        //check x
        if(!(position.x - width /2 < x && x < position.x + width/2))
            return false;

        //check y
        if(!(position.y - height/2  < y && y < position.y + height))
            return false;

        return true;
    }

    public Vector getPosition() {
        return position;
    }
}
