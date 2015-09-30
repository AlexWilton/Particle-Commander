package alex.wilton.cs4303.p1.game;

import ddf.minim.*;


public class Sound {
    private static Minim minim;
    private static AudioPlayer smallExplosion;
    private static AudioPlayer bigExplosion;
    private static AudioPlayer missileLaunch;

    public static void playSmallExplosion(){
        if(bigExplosion == null) {
            if (minim == null) {
                minim = new Minim(App.app);
            }
            smallExplosion = minim.loadFile("sounds/smallExplosion.wav");
        }
        smallExplosion.play();
        smallExplosion.rewind();
    }

    public static void playBigExplosion(){
        if(bigExplosion == null) {
            if (minim == null) {
                minim = new Minim(App.app);
            }
            bigExplosion = minim.loadFile("sounds/bigExplosion.wav");
        }
        bigExplosion.play();
        bigExplosion.rewind();
    }

    public static void playMissileLaunch() {
        if(missileLaunch == null) {
            if (minim == null) {
                minim = new Minim(App.app);
            }
            missileLaunch = minim.loadFile("sounds/missileLaunch.wav");
        }
        missileLaunch.play();
        missileLaunch.rewind();
    }
}
