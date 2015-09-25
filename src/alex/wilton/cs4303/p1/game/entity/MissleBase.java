package alex.wilton.cs4303.p1.game.entity;

import alex.wilton.cs4303.p1.App;
import processing.core.*;

public class MissleBase extends Entity {
    private static PImage baseImg = null;
    private static final String BASE_IMAGE_FILEPATH = "images/base.gif";
    static{
        baseImg = App.app.loadImage(BASE_IMAGE_FILEPATH);
    }
    private int x, y, width, height;

    public MissleBase(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void draw(){
//       rect(x,y,width,height);
        App.app.imageMode(App.app.CORNER);
        App.app.image(baseImg, x, y, width, height);
    }

    

}      
