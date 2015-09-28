package alex.wilton.cs4303.p1.game.screen;

import alex.wilton.cs4303.p1.App;
import alex.wilton.cs4303.p1.game.Game;

/**
 * Screen.
 * Show information to player and wait for input (Mouse click)
 */
public abstract class Screen{
    protected Game game;
    protected App app = App.app;

    public Screen(Game game){
        this.game = game;
    }

    /**
     * Draw Screen
     */
    public abstract void draw();

    /**
     * Respond to mouse click events (e.g. listen for button clicks)
     */
    public abstract void mousePressed();

}
