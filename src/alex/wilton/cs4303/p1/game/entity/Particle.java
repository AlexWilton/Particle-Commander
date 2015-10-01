package alex.wilton.cs4303.p1.game.entity;

import alex.wilton.cs4303.p1.game.App;
import processing.core.*;

public class Particle extends Entity{
    private boolean active = true;
    private PVector position, velocity, acceleration;

    private static final float DAMPENING = .99f; // Simulate drag by using an damping factor

    public Particle(PVector position, PVector velocity, PVector acceleration){
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public Particle(int x, int y, float xVel, float yVel, float xAcc, float yAcc) {
        this(new PVector(x, y), new PVector(xVel, yVel), new PVector(xAcc, yAcc));
    }


    /**
     * Update Particle for a single unit of time
     * @return True if particle is still active, else false if particle is no longer active
     */
    public boolean integrate() {

        /* Update position and velocity */
        position.add(velocity);
        velocity.add(acceleration);

        /* Apply dampening to simulating drag */
        acceleration.mult(DAMPENING);

        /* Mark Particle as dead if they stray more than half a screen from the view */
        if ((position.x < -App.WINDOW_WIDTH /2) || (position.x > App.WINDOW_WIDTH * 1.5 )) active = false;

        /* Mark Particle as dead if it strays past the bottom of the screen */
        if(position.y > App.WINDOW_HEIGHT) active = false;

        return active;
    }

    public void draw(){
        if(active)
            App.app.ellipse(position.x, position.y, 5, 5);
    }

    /**
     * Maybe split particle into two. Higher the level, the more likely the split.
     * If split, current particle moves left and new particle moves right
     * @param lvlNumber Level Number
     * @return The new particle (if split occurs), else null if no split occurs.
     */
    public Particle maybeSplit(int lvlNumber){
        boolean split = java.lang.Math.random() < 0.0004 + 0.0002 * lvlNumber;
        if(split){
            //this particle moves left a bit
            PVector moveLeft = new PVector(-0.3f, velocity.y);
            velocity.set(moveLeft);

            //new particle moves right a bit
            PVector moveRight = new PVector(0.3f, velocity.y);
            return new Particle(position.get(), moveRight, acceleration.get());
        }else{
            return null;
        }
    }

    public PVector getPosition(){ return position;}
    public PVector getVelocity(){ return velocity;}
    public PVector getAcceleration(){ return acceleration;}

    public void setActive(boolean active) {
        this.active = active;
    }
}