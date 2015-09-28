package alex.wilton.cs4303.p1.game.entity;

import alex.wilton.cs4303.p1.App;
import processing.core.PImage;

public class CrossHair extends Entity{
    private static PImage crossHairImg = null;
    private static final String CROSSHAIR_IMAGE_FILEPATH = "images/crosshair.gif";

    public CrossHair(){
        super();
        if(crossHairImg == null){
            crossHairImg = app.loadImage(CROSSHAIR_IMAGE_FILEPATH);
        }
    }

    @Override
    public void draw() {
        app.imageMode(App.CENTER);
        app.image(crossHairImg, app.mouseX, app.mouseY, 30, 30);
    }
}
