package alex.wilton.cs4303.p1.game.screen;

import alex.wilton.cs4303.p1.App;
import alex.wilton.cs4303.p1.game.Game;

public class EndOfLvlScreen{
    private Game game;
    private App app = App.app;

    public EndOfLvlScreen(Game game){
        this.game = game;
    }

    public void drawScreen(){
        int citiesRemaining = game.getPlanet().citiesRemaining();
        int numberOfMissles = game.getNumberOfMissles();
        int particlesDestroyed = game.getParticlesDestroyed();
        int lvlNumber = game.getLvlNumber();
        int score = game.getScore();

        app.background(255);
        app.textSize(35);
        app.textAlign(app.CENTER);
        app.fill(0, 255, 0);
        app.text("Well Done! Level " + lvlNumber + " Survived!", App.WINDOW_WIDTH / 2, 100);

        app.fill(0, 0, 255);
        app.text("POINTS EARNED", App.WINDOW_WIDTH/ 2, 200);
        app.textSize(20);

        app.textAlign(app.LEFT);
        app.text("Remaining Cities: " + citiesRemaining, 50, 250);
        app.textAlign(app.RIGHT);
        app.text(" (50 X " + citiesRemaining + ")   " + (50 * citiesRemaining), 600, 250);

        app.textAlign(app.LEFT);
        app.text("Remaining Missles: " + numberOfMissles, 50, 280);
        app.textAlign(app.RIGHT);
        app.text(" (2 X " + numberOfMissles + ")   " + (2 * numberOfMissles), 600, 280);

        app.textAlign(app.LEFT);
        app.text("Particles Destroyed: " + particlesDestroyed, 50, 310);
        app.textAlign(app.RIGHT);
        app.text(" (5 X " + particlesDestroyed + ")   " + (5 * particlesDestroyed), 600, 310);

        int subTotal = game.calculateSubTotal();
        app.text("SUBTOTAL: " + subTotal, 600, 340);

        int oldScore = score;
        int newScore = score + subTotal;
        app.textSize(25);
        app.textAlign(app.CENTER);
        app.text("NEW SCORE: " + newScore + "   (" + oldScore + " + " + subTotal + ")", App.WINDOW_WIDTH / 2, 420);
      
      /*NEXT LEVEL BUTTON*/
        app.fill(0, 100, 0);
        app.rect(110, 500, 180, 50, 15);
        app.fill(255);
        app.textAlign(app.LEFT);
        app.text("NEXT LEVEL", 130, 530);
    }

    public void mousePressed(){
     /*Listner for NEXT LEVEL button*/
        if( 110 <= app.mouseX && app.mouseX <= 110 + 180 && 500 <= app.mouseY && app.mouseY <= 500 + 50){
            game.calculateScoreAndGotoNextLevel();
        }
    }


}
