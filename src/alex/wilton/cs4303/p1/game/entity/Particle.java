package alex.wilton.cs4303.p1.game.entity;

import alex.wilton.cs4303.p1.App;
import processing.core.*;

public class Particle extends Entity{
    private boolean alive = true;
    private PVector position, velocity, acceleration;

    private static final int MAXIMUM_VELOCITY = 2; // Used to simulate Drag

    public Particle(int x, int y, float xVel, float yVel, float xAcc, float yAcc) {
        position = new PVector(x, y) ;
        velocity = new PVector(xVel, yVel) ;
        acceleration = new PVector(xAcc, yAcc) ;
    }


    public void integrate() {
        /* Update position and velocity */
        position.add(velocity);
        velocity.add(acceleration);

        /* Impose maximum velocity for simulating drag */
        if(velocity.mag() > MAXIMUM_VELOCITY) velocity.setMag(MAXIMUM_VELOCITY);

        /* Mark Particle as dead if they stray more than half a screen from the view */
        if ((position.x < -App.WINDOW_WIDTH /2) || (position.x > App.WINDOW_WIDTH * 1.5 )) alive = false;
    }

    public void draw(){
        if(alive)
            App.app.ellipse(position.x, position.y, 5, 5);
    }

    public PVector getPosition(){ return position;}
    public PVector getVelocity(){ return velocity;}
    public PVector getAcceleration(){ return acceleration;}

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}