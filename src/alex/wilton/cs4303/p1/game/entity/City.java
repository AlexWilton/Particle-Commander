package alex.wilton.cs4303.p1.game.entity;

import alex.wilton.cs4303.p1.App;
import processing.core.*;

public class City extends Entity{
    private static PImage cityImg = null;
    private static final String CITY_IMAGE_FILEPATH = "images/city.gif";
    static{
        cityImg = App.app.loadImage(CITY_IMAGE_FILEPATH);
    }

    private int x, y, width, height;
    private boolean destroyed = false;

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
        }
    }

    public void setDestroyed(boolean destroyed){
       this.destroyed = destroyed;
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
