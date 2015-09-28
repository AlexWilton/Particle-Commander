package alex.wilton.cs4303.p1.game.screen;

import alex.wilton.cs4303.p1.App;
import alex.wilton.cs4303.p1.game.Game;

public class GameOverScreen extends Screen{

    int citiesRemaining, numberOfMissles, particlesDestroyed, lvlNumber, score;

    public GameOverScreen(Game game){
        super(game);
        citiesRemaining = game.getPlanet().citiesRemaining();
        numberOfMissles = game.getNumberOfMissles();
        particlesDestroyed = game.getParticlesDestroyed();
        lvlNumber = game.getLvlNumber();
        score = game.getScore();
    }

    public void draw(){
        app.cursor(); //enable default cursor
        app.background(255);
        app.textSize(35);
        app.textAlign(app.CENTER);
        app.fill(255, 0, 0);
        app.text("GAME OVER! You made it to Level " + lvlNumber + ".", App.WINDOW_WIDTH / 2, 100);

        app.fill(0, 0, 0);
        app.text("POINTS EARNED FROM THIS LEVEL", App.WINDOW_WIDTH/ 2, 200);
        app.textSize(20);

        /*REMAINING CITIES INFO*/
        app.textAlign(app.LEFT);
        app.text("Remaining Cities: " + citiesRemaining, 50, 250);
        app.textAlign(app.RIGHT);
        app.text(" (50 X " + citiesRemaining + ")   " + (50 * citiesRemaining), 600, 250);

        /*REMAINING MISSLES INFO*/
        app.textAlign(app.LEFT);
        app.text("Remaining Missles: " + numberOfMissles, 50, 280);
        app.textAlign(app.RIGHT);
        app.text(" (2 X " + numberOfMissles + ")   " + (2 * numberOfMissles), 600, 280);

        /*PARTICLES DESTROYED INFO*/
        app.textAlign(app.LEFT);
        app.text("Particles Destroyed: " + particlesDestroyed, 50, 310);
        app.textAlign(app.RIGHT);
        app.text(" (5 X " + particlesDestroyed + ")   " + (5 * particlesDestroyed), 600, 310);

        /*SUBTOTAL*/
        int subTotal = game.calculateSubTotal();
        app.text("SUBTOTAL: " + subTotal, 600, 340);

        /*NEW SCORE*/
        int oldScore = score;
        int newScore = score + subTotal;
        app.textSize(25);
        app.textAlign(app.CENTER);
        app.fill(255,0,0);
        app.text("FINAL SCORE: " + newScore + "   (" + oldScore + " + " + subTotal + ")", App.WINDOW_WIDTH / 2, 420);
      
        /*PLAY AGAIN BUTTON*/
        app.fill(0, 0, 100);
        app.rect(110, 500, 180, 50, 15);
        app.fill(255);
        app.textAlign(app.LEFT);
        app.text("PLAY AGAIN", 130, 532);

        /*QUIT BUTTON*/
        app.fill(0, 100, 0);
        app.rect(350, 500, 180, 50, 15);
        app.fill(255);
        app.textAlign(app.LEFT);
        app.text("QUIT", 410, 532);
    }

    public void mousePressed(){
     /*Listener for PLAY AGAIN button*/
        if( 110 <= app.mouseX && app.mouseX <= 110 + 180 && 500 <= app.mouseY && app.mouseY <= 500 + 50){
            app.setup();
        }

    /*Listener for QUIT button*/
        if( 350 <= app.mouseX && app.mouseX <= 350 + 180 && 500 <= app.mouseY && app.mouseY <= 500 + 50){
            System.exit(0);
        }
    }


}
