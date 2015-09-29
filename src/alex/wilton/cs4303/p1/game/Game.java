package alex.wilton.cs4303.p1.game;

import alex.wilton.cs4303.p1.App;
import alex.wilton.cs4303.p1.game.entity.Missile;
import alex.wilton.cs4303.p1.game.entity.Particle;
import alex.wilton.cs4303.p1.game.entity.Planet;
import alex.wilton.cs4303.p1.game.screen.EndOfLvlScreen;
import alex.wilton.cs4303.p1.game.screen.GameOverScreen;
import alex.wilton.cs4303.p1.game.screen.GamePlayScreen;
import alex.wilton.cs4303.p1.game.screen.Screen;

public class Game{
    private App app;
    private Screen screen; //current screen

    private Planet planet;
    private ParticleWave particleWave;
    private MissileSet missilesInMotion;

    private int lvlNumber = 1;
    private int score = 0;
    private int numberOfMissiles = 30;
    private int particlesDestroyed = 0;

    private int lvlTimeRemaining;

    private final int STAGE_SETUP = 0;
    private final int STAGE_PLAY = 1;
    private final int STAGE_LEVELEND = 2;
    private final int STAGE_GAMEOVER = 3;
    private int gameStage = STAGE_SETUP;

    public Game(Planet planet){
        this.planet = planet;
        setupLvl();
        app = App.app;
    }

    public void setupLvl(){
        particleWave = new ParticleWave();
        missilesInMotion = new MissileSet();
        particlesDestroyed = 0;
        lvlTimeRemaining = (10 + lvlNumber * 5) * 60;
        gameStage = STAGE_PLAY;
    }

    public void draw(){
        switch(gameStage){
            default:
            case STAGE_LEVELEND:
                screen = new EndOfLvlScreen(this);
                break;
            case STAGE_GAMEOVER:
                screen = new GameOverScreen(this);
                break;
            case STAGE_PLAY:
                lvlTimeRemaining--;
                if(lvlTimeRemaining == 0) gameStage = STAGE_LEVELEND;
                screen = new GamePlayScreen(this);
                break;
        }
        screen.draw();
    }

    public int calculateSubTotal(){
        return planet.citiesRemaining() * 50 + numberOfMissiles * 2 + particlesDestroyed * 5;
    }

    public void calculateScoreAndGotoNextLevel(){
        score += calculateSubTotal();
        lvlNumber++;
        setupLvl();
    }


    public void mousePressed(){
        screen.mousePressed();
    }

    public Planet getPlanet(){
        return planet;
    }

    public int getLvlNumber() {
        return lvlNumber;
    }

    public int getScore() {
        return score;
    }

    public int getNumberOfMissiles() {
        return numberOfMissiles;
    }

    public int getParticlesDestroyed() {
        return particlesDestroyed;
    }

    public int getLvlTimeRemaining() {
        return lvlTimeRemaining;
    }

    public int getGameStage() {
        return gameStage;
    }

    public ParticleWave getParticleWave() {
        return particleWave;
    }

    public MissileSet getMissilesInMotion() { return missilesInMotion;}

    public void fireMissileAt(int targetX, int targetY) {
        if(numberOfMissiles > 0) {
            missilesInMotion.addMissle(new Missile(planet.getMissileBase(), targetX, targetY));
            numberOfMissiles--;
        }
    }


    public void particleShootDown(Particle particle){
        particlesDestroyed++;
        particleWave.remove(particle);
    }


    public void gameOver() {
        gameStage = STAGE_GAMEOVER;
    }


}