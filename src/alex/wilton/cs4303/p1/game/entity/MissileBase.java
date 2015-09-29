package alex.wilton.cs4303.p1.game.entity;

import java.lang.Math;

public class MissileBase extends Entity {
    private int x, y, width, height;
    public MissileBase(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(){
        app.rect(x, y, width, height);
        app.ellipse(x + width / 2, y, (int) (width * 0.8), height * 2);
        drawMissleTurretLine();
    }

    private void drawMissleTurretLine() {
        int lineLength = 50;
        int startX = x + width/2;
        int startY = y;
        double angle = Math.atan2(app.mouseY - y, app.mouseX - startX);

        /*Set right minimum point*/
        if(angle > 0 && angle < Math.PI) angle = 0;

        int endX = (int) (startX + lineLength * Math.cos(angle));
        int endY = (int) (startY + lineLength * Math.sin(angle));

        /*Set left minimum point*/
        if(app.mouseY > startY && app.mouseX < startX){
            endX = startX - lineLength;
            endY = startY;
        }
        app.strokeWeight(3);
        app.line(startX, startY, endX, endY);
    }


    public int getCentralX() {
        return x + width/2;
    }

    public int getY() {
        return y;
    }
}
