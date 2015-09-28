package alex.wilton.cs4303.p1.game.entity;

import processing.core.PVector;

public class Missle extends Entity{
    private PVector position;
    private PVector velocity;
    private PVector acceleration;
    private int targetX, targetY;
    private MissleBase baseOfOrigin;

    public Missle(MissleBase baseOfOrigin, int targetX, int targetY){
        this.baseOfOrigin = baseOfOrigin;
        this.targetX = targetX;
        this.targetY = targetY;

        position = new PVector(targetX, targetY);
    }

    @Override
    public void draw() {
        app.ellipse(position.x, position.y, 2, 2);
        double angle = Math.atan2(targetY - position.y, targetX - position.x);
        int trailLength = 15;
        int trailStartx = (int) (position.x - trailLength * Math.cos(angle));
        int trailStarty = (int) (position.y - trailLength * Math.sin(angle));
        app.strokeWeight(1);
        app.line(trailStartx, trailStarty, position.x, position.y);
    }
}
