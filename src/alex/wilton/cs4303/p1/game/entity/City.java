package alex.wilton.cs4303.p1.game.entity;

import alex.wilton.cs4303.p1.game.App;
import alex.wilton.cs4303.p1.game.SoundEffects;
import processing.core.*;

/**
 * Class for modelling and drawing a City
 */
public class City extends Entity{
    private static PImage cityImg = App.app.loadImage("images/city.gif");

    public final int x, y, width, height;
    private boolean destroyed = false;

    private int numberOfExplodingFrameRemaining = (int) (60 * 0.5); //0.5 seconds (typically 60 frames a second)

    public City(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Draw City if city isn't destroyed.
     * If city is exploding, draw explosion
     */
    public void draw(){
        if(!destroyed) {
            app.imageMode(App.CORNER);
            app.image(cityImg, x, y, width, height);
        }else if(numberOfExplodingFrameRemaining > 0) {
            drawExplosion();
            numberOfExplodingFrameRemaining--;
        }

    }

    /**
     * Draw Explosion, radius decreases based on frames left till end of explosion
     */
    private void drawExplosion() {
        //alternate colour between red and yellow
        if(numberOfExplodingFrameRemaining % 2 == 0)
            app.fill(255,255,0);
        else
            app.fill(255,0,0);
        app.strokeWeight(0);
        app.ellipse(x + width/2, y + height/2, (float) (numberOfExplodingFrameRemaining * 1.5), (float) (numberOfExplodingFrameRemaining * 1.5));
        app.fill(255); //reset colour to white
    }

    public void setDestroyed(boolean destroyed){
        this.destroyed = destroyed;
        if(destroyed) {
            SoundEffects.playBigExplosion();
        }
    }

    public boolean isDestroyed(){
        return destroyed;
    }

    public boolean containsPoint(int pointX, int pointY) {
        //check x co-ordinate
        if(!(x <= pointX && pointX <= x + width)) return false;

        //check y co-ordinate
        if(!(y <= pointY && pointY <= y + height)) return false;

        return true;
    }

    public boolean getDestroyed() {
        return destroyed;
    }
}
