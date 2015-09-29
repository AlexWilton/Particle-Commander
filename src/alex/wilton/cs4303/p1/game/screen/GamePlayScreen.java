package alex.wilton.cs4303.p1.game.screen;

import alex.wilton.cs4303.p1.App;
import alex.wilton.cs4303.p1.game.CollisionChecker;
import alex.wilton.cs4303.p1.game.Game;
import alex.wilton.cs4303.p1.game.MissleSet;
import alex.wilton.cs4303.p1.game.ParticleWave;
import alex.wilton.cs4303.p1.game.entity.CrossHair;
import alex.wilton.cs4303.p1.game.entity.Missle;
import alex.wilton.cs4303.p1.game.entity.Planet;
import processing.core.PImage;

public class GamePlayScreen extends Screen{
    private ParticleWave particleWave;
    private MissleSet misslesInMotion;
    private Planet planet;
    private int lvlNumber, score, numberOfMissles, lvlTimeRemaining;

    public GamePlayScreen(Game game){
        super(game);
        particleWave = game.getParticleWave();
        misslesInMotion = game.getMisslesInMotion();
        planet = game.getPlanet();
        lvlNumber = game.getLvlNumber();
        score = game.getScore();
        numberOfMissles = game.getNumberOfMissles();
        lvlTimeRemaining = game.getLvlTimeRemaining();

        misslesInMotion.addMissle(new Missle(planet.getMissleBase(), 400, 400));
    }

    public void draw(){
        app.drawBackgroundImage();

        /* Draw player/score info */
        app.noFill(); app.stroke(255);
        app.rect(5, 30, 150, 90);
        app.textSize(20);
        app.text("LEVEL: " + lvlNumber, 10, 50);
        app.text("SCORE: " + score, 10, 80);
        app.text("MISSLES: " + numberOfMissles, 10, 110);
        app.text("Time to next level: " + lvlTimeRemaining / 60, 300, 50);

        /* Maybe create then draw all particles in current wave*/
        particleWave.maybeCreateNewParticles(lvlNumber);
        particleWave.integrateAll();
        particleWave.draw();

        /* Check for destroyed Cities. */
        CollisionChecker collisionChecker = new CollisionChecker(game);
        collisionChecker.checkForParticleCityCollision();
        planet.draw();

        /* Draw all missiles in flight */
        misslesInMotion.integrateAll();
        misslesInMotion.draw();


        /*Draw CrossHair and disable normal cursor*/
        CrossHair crossHair = new CrossHair();
        crossHair.draw();
        app.noCursor();

    }

    public void mousePressed(){}

}
