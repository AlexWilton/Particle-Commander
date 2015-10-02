package alex.wilton.cs4303.p1.game.entity;

import alex.wilton.cs4303.p1.game.App;

/**
 * Abstract Class for an Entity
 */
public abstract class Entity{
    protected App app = App.app;

    public abstract void draw();

}
