package alex.wilton.cs4303.p1.game;

import alex.wilton.cs4303.p1.game.entity.Missile;
import alex.wilton.cs4303.p1.game.entity.Particle;
import alex.wilton.cs4303.p1.game.entity.Planet;
import alex.wilton.cs4303.p1.game.screen.*;

public class Game{
    private Screen screen; //current screen

    private Planet planet;
    private ParticleWave particleWave;
    private MissileSet missilesInMotion;

    private int lvlNumber = 1;
    private int score = 0;
    private int numberOfMissiles = 15;
    private int particlesDestroyed = 0;

    private int lvlTimeRemaining;

    private final int STAGE_SETUP = 0;
    private final int STAGE_PLAY = 1;
    private final int STAGE_LEVELEND = 2;
    private final int STAGE_GAMEOVER = 3;
    private final int STAGE_SHOP = 4;
    private int gameStage = STAGE_SETUP;

    public Game(Planet planet){
        this.planet = planet;
        setupLvl();
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
            case STAGE_SHOP:
                screen = new ShopScreen(this);
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

    /**
     * Calculate score from last level. Setup next level. Visit shop (before starting next level)
     */
    public void calculateScoreSetupNextLvlAndVisitShop(){
        score += calculateSubTotal();
        lvlNumber++;
        setupLvl();
        gameStage = STAGE_SHOP;
    }

    public void startLevel(){
        gameStage = STAGE_PLAY;
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


    /**
     * Attempt to rebuild a city at the cost of 200 points
     */
    public void attemptToBuyCity() {
        if(score >= 200 && planet.citiesRemaining() < planet.getMaxNumberOfCities()){
            score -= 200;
            planet.rebuildOneCity();
        }
    }

    /**
     * Attempt to buy 10 missiles at the cost of 25 points
     */
    public void attemptToBuyMissiles() {
        if(score >= 25){
            score -= 25;
            numberOfMissiles += 10;
        }
    }
}