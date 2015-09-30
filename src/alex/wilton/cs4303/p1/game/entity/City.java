package alex.wilton.cs4303.p1.game.entity;

import alex.wilton.cs4303.p1.game.App;
import alex.wilton.cs4303.p1.game.Sound;
import processing.core.*;

public class City extends Entity{
    private static PImage cityImg;
    private static final String CITY_IMAGE_FILEPATH = "images/city.gif";
    static{
        cityImg = App.app.loadImage(CITY_IMAGE_FILEPATH);
    }

    public final int x, y, width, height;
    private boolean destroyed = false;

    private int currentExplosionRadius = (int) (60 * 0.5); //0.5 seconds (typically 60 frames a second)

    public City(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(){
        if(!destroyed) {
            app.imageMode(App.CORNER);
            app.image(cityImg, x, y, width, height);
        }else{
            if(currentExplosionRadius > 0) drawExplosion();
            currentExplosionRadius--;
        }
    }

    /**
     * Draw Explosion, radius decreases based on frames left till end of explosion
     */
    private void drawExplosion() {
        //alternate colour between red and yellow
        if(currentExplosionRadius % 2 == 0)
            app.fill(255,255,0);
        else
            app.fill(255,0,0);
        app.strokeWeight(0);
        app.ellipse(x + width/2, y + height/2, (float) (currentExplosionRadius * 1.5), (float) (currentExplosionRadius * 1.5));
        app.fill(255); //reset colour to white
    }

    public void setDestroyed(boolean destroyed){
        this.destroyed = destroyed;
        if(destroyed) {
            Sound.playBigExplosion();
        }
    }

    public boolean isDestroyed(){
      return destroyed; 
    }

    public boolean containsPoint(int pointX, int pointY) {
        if( x <= pointX && pointX <= x + width && y <= pointY && pointY <= y + height)
            return true;
        return false;
    }


}
