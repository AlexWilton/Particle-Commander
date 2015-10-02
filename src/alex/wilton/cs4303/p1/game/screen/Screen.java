package alex.wilton.cs4303.p1.game.screen;

import alex.wilton.cs4303.p1.game.App;
import alex.wilton.cs4303.p1.game.GameModel;

/**
 * Screen.
 * Show information to player and wait for input (Mouse click)
 */
public abstract class Screen{
    protected GameModel gameModel;
    protected App app = App.app;

    public Screen(GameModel gameModel){
        this.gameModel = gameModel;
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
