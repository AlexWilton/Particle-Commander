package alex.wilton.cs4303.p1.game.entity;

import alex.wilton.cs4303.p1.App;
import processing.core.*;

public class Particle extends Entity{
    private boolean alive = true;
    private PVector position, velocity, acceleration;

    public Particle(int x, int y, float xVel, float yVel, float xAcc, float yAcc) {
        position = new PVector(x, y) ;
        velocity = new PVector(xVel, yVel) ;
        acceleration = new PVector(xAcc, yAcc) ;
    }

    // update position and velocity
    public void integrate() {
        position.add(velocity) ;
        velocity.add(acceleration) ;

        //mark Particle as dead if it falls over the screen
        if ((position.x < 0) || (position.x > App.WINDOW_WIDTH)) alive = false;
    }

    public void draw(){
        if(alive)
            App.app.ellipse(position.x, position.y, 5, 5);
    }

    public PVector getPosition(){ return position;}
    public PVector getVelocity(){ return velocity;}
    public PVector getAcceleration(){ return acceleration;}
}