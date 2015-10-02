package alex.wilton.cs4303.p1.game.screen;

import alex.wilton.cs4303.p1.game.App;
import alex.wilton.cs4303.p1.game.CollisionChecker;
import alex.wilton.cs4303.p1.game.GameModel;
import alex.wilton.cs4303.p1.game.entity.collection.BomberWave;
import alex.wilton.cs4303.p1.game.entity.collection.MissileSet;
import alex.wilton.cs4303.p1.game.entity.collection.ParticleWave;
import alex.wilton.cs4303.p1.game.entity.CrossHair;
import alex.wilton.cs4303.p1.game.entity.Planet;
import processing.core.PImage;

/**
 * Class for displaying Game Play Screen
 */
public class GamePlayScreen extends Screen{
    private ParticleWave particleWave;
    private MissileSet missilesInMotion;
    private BomberWave bomberWave;
    private Planet planet;
    private int lvlNumber, score, numberOfMissiles, lvlTimeRemaining;

    private static PImage backgroundImage = App.app.loadImage("images/background.jpg");

    public GamePlayScreen(GameModel gameModel){
        super(gameModel);
        particleWave = gameModel.getParticleWave();
        missilesInMotion = gameModel.getMissilesInMotion();
        bomberWave = gameModel.getBomberWave();
        planet = gameModel.getPlanet();
        lvlNumber = gameModel.getLvlNumber();
        score = gameModel.getScore();
        numberOfMissiles = gameModel.getNumberOfMissiles();
        lvlTimeRemaining = gameModel.getLvlTimeRemaining();
    }

    public void draw(){
        drawBackgroundImage();

        /* Draw player/score info */
        drawPlayerScoreInfo();

        /* Draw all missiles in flight */
        missilesInMotion.integrateAll();
        missilesInMotion.draw();

        /* Maybe create bomber and draw all bombers in flight */
        bomberWave.maybeCreateBomber(planet, lvlNumber);
        particleWave.addParticles(bomberWave.integrateAll());
        bomberWave.draw();

        /* Maybe create particles, maybe split particles then draw all particles in current wave*/
        particleWave.maybeCreateNewParticles(lvlNumber);
        particleWave.maybeSpitSomeParticles(lvlNumber);
        particleWave.integrateAll();
        particleWave.draw();

        /* Check for destroyed Cities */
        CollisionChecker collisionChecker = new CollisionChecker(gameModel);
        collisionChecker.checkForParticleCityCollision();
        collisionChecker.checkForBombersHitByMissileExplosion();

        /* Check for destroyed Particles */
        collisionChecker.checkForParticleExplosionCollision();

        /*Draw Planet (includes cities and missile base) */
        planet.draw();


        /*Draw CrossHair and disable normal cursor*/
        CrossHair crossHair = new CrossHair();
        crossHair.draw();
        app.noCursor();

    }

    public void drawBackgroundImage(){
        app.imageMode(App.CORNER);
        app.image(backgroundImage, 0, 0, App.WINDOW_WIDTH, App.WINDOW_HEIGHT);
    }

    private void drawPlayerScoreInfo() {
        app.textAlign(App.LEFT);
        app.noFill(); app.stroke(255); app.strokeWeight(2);
        app.rect(5, 30, 150, 90);
        app.textSize(20);
        app.text("LEVEL: " + lvlNumber, 10, 50);
        app.text("SCORE: " + score, 10, 80);
        app.text("MISSILES: " + numberOfMissiles, 10, 110);
        app.text("Time to next level: " + lvlTimeRemaining / 60, 300, 50);

    }

    public void mousePressed(){
        gameModel.fireMissileAt(app.mouseX, app.mouseY);
    }


}
