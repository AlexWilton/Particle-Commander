package alex.wilton.cs4303.p1.game.screen;

import alex.wilton.cs4303.p1.game.App;
import alex.wilton.cs4303.p1.game.Game;

public class EndOfLvlScreen extends Screen{
    int citiesRemaining, numberOfMissiles, particlesDestroyed, lvlNumber, score;

    public EndOfLvlScreen(Game game){
        super(game);
        citiesRemaining = game.getPlanet().citiesRemaining();
        numberOfMissiles = game.getNumberOfMissiles();
        particlesDestroyed = game.getParticlesDestroyed();
        lvlNumber = game.getLvlNumber();
        score = game.getScore();
    }


    public void draw(){
        app.cursor(); //enable default cursor
        app.background(255);
        app.textSize(35);
        app.textAlign(app.CENTER);
        app.fill(0, 255, 0);
        app.text("Well Done! Level " + lvlNumber + " Survived!", App.WINDOW_WIDTH / 2, 100);

        app.fill(0, 0, 255);
        app.text("POINTS EARNED", App.WINDOW_WIDTH / 2, 200);
        app.textSize(20);

        /*REMAINING CITIES INFO*/
        app.textAlign(app.LEFT);
        app.text("Remaining Cities: " + citiesRemaining, 50, 250);
        app.textAlign(app.RIGHT);
        app.text((50 * citiesRemaining), 600, 250);
        app.text(" (50 X " + citiesRemaining + ")   ", 530, 250);

        /*REMAINING MISSLES INFO*/
        app.textAlign(app.LEFT);
        app.text("Remaining Missiles: " + numberOfMissiles, 50, 280);
        app.textAlign(app.RIGHT);
        app.text((2 * numberOfMissiles), 600, 280);
        app.text(" (2 X " + numberOfMissiles + ")   ", 530, 280);

        /*PARTICLES DESTROYED INFO*/
        app.textAlign(app.LEFT);
        app.text("Particles Destroyed: " + particlesDestroyed, 50, 310);
        app.textAlign(app.RIGHT);
        app.text((5 * particlesDestroyed), 600, 310);
        app.text(" (5 X " + particlesDestroyed + ")   ", 530, 310);
        int subTotal = game.calculateSubTotal();
        app.text("SUBTOTAL: " + subTotal, 600, 340);

        int oldScore = score;
        int newScore = score + subTotal;
        app.textSize(25);
        app.textAlign(app.CENTER);
        app.text("NEW SCORE: " + newScore + "   (" + oldScore + " + " + subTotal + ")", App.WINDOW_WIDTH / 2, 420);
      
      /*SHOP BUTTON*/
        app.fill(0, 100, 0);
        app.rect(110, 500, 180, 50, 15);
        app.fill(255);
        app.textAlign(app.LEFT);
        app.text("SHOP", 165, 535);

       /*SKIP SHOP BUTTON*/
        app.fill(255, 0, 0);
        app.rect(350, 500, 180, 50, 15);
        app.fill(255);
        app.textAlign(app.LEFT);
        app.text("SKIP SHOP", 375, 535);
    }

    public void mousePressed(){
        /*Listener for SHOP button*/
        if( 110 <= app.mouseX && app.mouseX <= 110 + 180 && 500 <= app.mouseY && app.mouseY <= 500 + 50){
            game.calculateScoreSetupNextLvlAndVisitShop();
        }

        /*Listener for SKIP SHOP button*/
        if( 350 <= app.mouseX && app.mouseX <= 350 + 180 && 500 <= app.mouseY && app.mouseY <= 500 + 50){
            game.calculateScoreSetupNextLvlAndVisitShop();
            game.startLevel();
        }
    }


}
