package alex.wilton.cs4303.p1.game;

import alex.wilton.cs4303.p1.game.entity.Planet;
import processing.core.*;

public class App extends PApplet{
    public static App app;

    public App(){
        app = this;
    }

    public static final int WINDOW_WIDTH = 650, WINDOW_HEIGHT = 700;

    private static PImage backImg = null;
    private static final String BACKGROUND_IMAGE_FILEPATH = "images/background.jpg";

    private Planet planet;
    private Game game;

    public void setup() {
        backImg = loadImage(BACKGROUND_IMAGE_FILEPATH);
        size(WINDOW_WIDTH, WINDOW_HEIGHT);
        planet = new Planet(4);
        game = new Game(planet);
        game.setupLvl();
    }

    public void drawBackgroundImage(){
        imageMode(CORNER);
        image(backImg, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void draw() {
        game.draw();
    }

    public void mousePressed() {
        game.mousePressed();
    }


}