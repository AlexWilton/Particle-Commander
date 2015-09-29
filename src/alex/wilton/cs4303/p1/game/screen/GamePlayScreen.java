package alex.wilton.cs4303.p1.game.screen;

import alex.wilton.cs4303.p1.game.CollisionChecker;
import alex.wilton.cs4303.p1.game.Game;
import alex.wilton.cs4303.p1.game.MissileSet;
import alex.wilton.cs4303.p1.game.ParticleWave;
import alex.wilton.cs4303.p1.game.entity.CrossHair;
import alex.wilton.cs4303.p1.game.entity.Planet;

public class GamePlayScreen extends Screen{
    private ParticleWave particleWave;
    private MissileSet missilesInMotion;
    private Planet planet;
    private int lvlNumber, score, numberOfMissiles, lvlTimeRemaining;

    public GamePlayScreen(Game game){
        super(game);
        particleWave = game.getParticleWave();
        missilesInMotion = game.getMissilesInMotion();
        planet = game.getPlanet();
        lvlNumber = game.getLvlNumber();
        score = game.getScore();
        numberOfMissiles = game.getNumberOfMissiles();
        lvlTimeRemaining = game.getLvlTimeRemaining();
    }

    public void draw(){
        app.drawBackgroundImage();

        /* Draw player/score info */
        app.noFill(); app.stroke(255); app.strokeWeight(2);
        app.rect(5, 30, 150, 90);
        app.textSize(20);
        app.text("LEVEL: " + lvlNumber, 10, 50);
        app.text("SCORE: " + score, 10, 80);
        app.text("MISSILES: " + numberOfMissiles, 10, 110);
        app.text("Time to next level: " + lvlTimeRemaining / 60, 300, 50);

        /* Draw all missiles in flight */
        missilesInMotion.integrateAll();
        missilesInMotion.draw();


        /* Maybe create then draw all particles in current wave*/
        particleWave.maybeCreateNewParticles(lvlNumber);
        particleWave.integrateAll();
        particleWave.draw();

        /* Check for destroyed Cities */
        CollisionChecker collisionChecker = new CollisionChecker(game);
        collisionChecker.checkForParticleCityCollision();

        /* Check for destroyed Particles */
        collisionChecker.checkForParticleExplosionCollision();

        /*Draw Planet (includes cities and missile base) */
        planet.draw();


        /*Draw CrossHair and disable normal cursor*/
        CrossHair crossHair = new CrossHair();
        crossHair.draw();
        app.noCursor();

    }

    public void mousePressed(){
        game.fireMissileAt(app.mouseX, app.mouseY);
    }


}
