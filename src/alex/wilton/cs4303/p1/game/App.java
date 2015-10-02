package alex.wilton.cs4303.p1.game;

import alex.wilton.cs4303.p1.game.entity.Planet;
import processing.core.*;

/**
 * App class represents the Application as a whole.
 * New Game is created and the frame draw and
 * mouse pressed handlers specified.
 */
public class App extends PApplet{
    /**
     * app object is made globally accessible in order that specific screens can load images into the app
     */
    public static App app;
    public static final int WINDOW_WIDTH = 650, WINDOW_HEIGHT = 700;

    public App(){
        app = this;
    }

    private Game game;

    public void setup() {
        size(WINDOW_WIDTH, WINDOW_HEIGHT);
        Planet planet = new Planet(4);
        game = new Game(planet);
    }


    public void draw() {
        game.draw();
    }

    public void mousePressed() {
        game.mousePressed();
    }


}