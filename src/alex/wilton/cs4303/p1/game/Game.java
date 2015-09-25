package alex.wilton.cs4303.p1.game;

import alex.wilton.cs4303.p1.App;
import alex.wilton.cs4303.p1.game.entity.Planet;
import alex.wilton.cs4303.p1.game.screen.EndOfLvlScreen;

public class Game{
    private App app;
    private Planet planet;
    private ParticleWave particleWave;

    private int lvlNumber = 1;
    private int score = 0;
    private int numberOfMissles = 30;
    private int particlesDestroyed = 0;

    private int lvlTimeRemaining;

    private final int STAGE_SETUP = 0;
    private final int STAGE_PLAY = 1;
    private final int STAGE_LEVELEND = 2;
    private int gameStage = STAGE_SETUP;

    public Game(Planet planet){
        this.planet = planet;
        setupLvl();
        app = App.app;
    }

    public void drawGamePlayInfo(){
        app.noFill(); app.stroke(255);
        app.rect(5, 30, 150, 90);
        app.textSize(20);
        app.text("LEVEL: " + lvlNumber, 10, 50);
        app.text("SCORE: " + score, 10, 80);
        app.text("MISSLES: " + numberOfMissles, 10, 110);

        app.text("Time to next level: " + lvlTimeRemaining / 60, 300, 50);
    }

    public void setupLvl(){
        particleWave = new ParticleWave();
        lvlTimeRemaining = (1 + lvlNumber * 5) * 60;
        gameStage = STAGE_PLAY;
    }

    public void draw(){
        lvlTimeRemaining--;
        if(lvlTimeRemaining == 0) gameStage = STAGE_LEVELEND;
        switch(gameStage){
            case STAGE_LEVELEND:
                EndOfLvlScreen screen = new EndOfLvlScreen(this);
                screen.drawScreen();

                break;
            default:
            case STAGE_PLAY:
                drawGamePlayInfo();
                particleWave.maybeCreateNewParticles(lvlNumber);
                particleWave.integrateAll();
                particleWave.draw();
                break;
        }

    }

    public int calculateSubTotal(){
        return planet.citiesRemaining() * 50 + numberOfMissles * 2 + particlesDestroyed * 5;
    }

    public void calculateScoreAndGotoNextLevel(){
        score += calculateSubTotal();
        lvlNumber++;
        setupLvl();
    }


    public void mousePressed(){
        switch(gameStage){
            case STAGE_LEVELEND:
                EndOfLvlScreen screen = new EndOfLvlScreen(this);
                screen.mousePressed();
                break;
            default:
                break;
        }
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

    public int getNumberOfMissles() {
        return numberOfMissles;
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


}