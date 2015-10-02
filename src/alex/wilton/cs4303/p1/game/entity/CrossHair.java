package alex.wilton.cs4303.p1.game.entity;

import alex.wilton.cs4303.p1.game.App;
import processing.core.PImage;

/**
 * Class for representing and drawing the CrossHair
 */
public class CrossHair extends Entity{
    private static PImage crossHairImg = App.app.loadImage("images/crosshair.gif");

    public CrossHair(){super();}

    @Override
    public void draw() {
        app.imageMode(App.CENTER);
        app.image(crossHairImg, app.mouseX, app.mouseY, 30, 30);
    }
}
