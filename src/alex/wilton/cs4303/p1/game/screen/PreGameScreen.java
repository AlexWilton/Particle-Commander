package alex.wilton.cs4303.p1.game.screen;

import alex.wilton.cs4303.p1.App;
import alex.wilton.cs4303.p1.game.Game;
import processing.core.PImage;

public class PreGameScreen extends Screen{
    private static PImage missileLauncherImg;
    private static final String MISSILE_LAUNCHER_IMAGE_FILEPATH = "images/missileLauncher.jpg";
    static{
        missileLauncherImg = App.app.loadImage(MISSILE_LAUNCHER_IMAGE_FILEPATH);
    }


    public PreGameScreen(Game game){
        super(game);
    }


    public void draw(){
        app.cursor(); //enable default cursor
        app.background(255);
        app.textSize(35);
        app.textAlign(App.CENTER);
        app.fill(0);
        app.text("PARTICLE COMMANDER", App.WINDOW_WIDTH / 2, 50);

        app.fill(0, 0, 255);
        app.textSize(16);
        app.text("Use missiles to defend your cities.", App.WINDOW_WIDTH / 2, 95);
        app.text("Trade in points in-between levels to buy missiles or rebuild cities.", App.WINDOW_WIDTH / 2, 115);



        app.imageMode(App.CENTER);
        app.image(missileLauncherImg, App.WINDOW_WIDTH / 2, 300, App.WINDOW_WIDTH / 2, 300);


      /*START LEVEL BUTTON*/
        app.textSize(25);
        app.fill(0, 100, 0);
        app.rect(230, 500, 180, 50, 15);
        app.fill(255);
        app.textAlign(app.LEFT);
        app.text("START", 282, 532);

        app.textSize(13);
        app.textAlign(app.CENTER);
        app.fill(0, 0, 255);
        app.text("HINT: Only use missiles to defend cities or when more than one particle can be destroyed.", App.WINDOW_WIDTH / 2, 600);


    }

    public void mousePressed(){
        /*Listener for START LEVEL button*/
        if( 230 <= app.mouseX && app.mouseX <= 230 + 180 && 500 <= app.mouseY && app.mouseY <= 500 + 50){
            game.startLevel();
        }
    }


}
