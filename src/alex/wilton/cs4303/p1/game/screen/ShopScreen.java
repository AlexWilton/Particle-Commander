package alex.wilton.cs4303.p1.game.screen;

import alex.wilton.cs4303.p1.App;
import alex.wilton.cs4303.p1.game.Game;

public class ShopScreen extends Screen{
    int numberOfCities, numberOfMissles, nextLevelNumber, score;

    public ShopScreen(Game game){
        super(game);
        numberOfCities = game.getPlanet().citiesRemaining();
        numberOfMissles = game.getNumberOfMissiles();
        nextLevelNumber = game.getLvlNumber();
        score = game.getScore();
    }


    public void draw(){
        app.cursor(); //enable default cursor
        app.background(255);
        app.textSize(35);
        app.textAlign(App.CENTER);
        app.fill(0, 0, 255);
        app.text("Shopping Time", App.WINDOW_WIDTH / 2, 100);

        app.fill(0);
        app.textSize(16);
        app.text("In order to survive level " + nextLevelNumber + " you might ", App.WINDOW_WIDTH / 2, 175);
        app.text("need to trade in some points for extra missiles or cities", App.WINDOW_WIDTH / 2, 200);
        app.textSize(20);

        app.textAlign(app.LEFT);
        app.text("Current City Count: " + numberOfCities, 50, 250);
        app.text("Current Missile Count: " + numberOfMissles, 50, 280);

        app.textSize(25);
        app.textAlign(app.CENTER);

        /*BUY CITY BUTTON*/
        app.fill(0, 0, 100);
        app.rect(110, 320, 190, 80, 15);
        app.fill(255);
        app.textAlign(app.LEFT);
        app.text("REBUILD CITY", 120, 352);
        app.text("(Costs 200)", 135, 382);

        /*BUY MISSILES BUTTON*/
        app.fill(0, 0, 100);
        app.rect(350, 320, 190, 80, 15);
        app.fill(255);
        app.textAlign(app.LEFT);
        app.text("MISSILES (x10)", 352, 352);
        app.text("(Costs 25)", 380, 382);

        app.textSize(25);
        app.textAlign(app.CENTER);
        app.fill(255, 0, 0);
        app.text("REMAINING SCORE: " + score, App.WINDOW_WIDTH / 2, 460);
      
      /*START LEVEL BUTTON*/
        app.fill(0, 100, 0);
        app.rect(230, 500, 180, 50, 15);
        app.fill(255);
        app.textAlign(app.LEFT);
        app.text("START LEVEL", 240, 530);
    }

    public void mousePressed(){
        /*Listener for BUY CITY*/
        if( 110 <= app.mouseX && app.mouseX <= 110 + 190 && 320 <= app.mouseY && app.mouseY <= 320 + 80){
            game.attemptToBuyCity();
        }

        /*Listener for BUY MISSILLES*/
        if( 350 <= app.mouseX && app.mouseX <= 350 + 190 && 320 <= app.mouseY && app.mouseY <= 320 + 80){
            game.attemptToBuyMissiles();
        }

        /*Listener for START LEVEL button*/
        if( 230 <= app.mouseX && app.mouseX <= 230 + 180 && 500 <= app.mouseY && app.mouseY <= 500 + 50){
            game.startLevel();
        }
    }


}
