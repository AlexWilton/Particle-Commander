package alex.wilton.cs4303.p1.game.entity;

import alex.wilton.cs4303.p1.App;
import processing.core.PVector;

public class Missle extends Entity{
    private PVector position;
    private PVector velocity;
    private PVector acceleration;

    private int startX, startY,  targetX, targetY;
    private double angleFromBaseToTarget;


    public Missle(MissleBase base, int targetX, int targetY) {
        this.targetX = targetX;
        this.targetY = targetY;
        startX = base.getCentralX();
        startY = base.getY();
        position = new PVector(startX, startX);
        angleFromBaseToTarget = Math.atan2(targetY - startY, targetX - startX);

        velocity = new PVector((int) (10 * Math.cos(angleFromBaseToTarget)), (int) (10 * Math.sin(angleFromBaseToTarget)));
        acceleration = new PVector(0, 0);
    }

    @Override
    public void draw() {
        int trailLength = 8;
        int trailStartx = (int) (position.x - trailLength * Math.cos(angleFromBaseToTarget));
        int trailStarty = (int) (position.y - trailLength * Math.sin(angleFromBaseToTarget));
        app.strokeWeight(1);
        app.ellipse(position.x, position.y, 2, 2);
        app.line(trailStartx, trailStarty, position.x, position.y);
    }

    public void integrate() {
        /* Update position and velocity */
        position.add(velocity);
        velocity.add(acceleration);
    }

}
