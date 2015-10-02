package alex.wilton.cs4303.p1.game;

import ddf.minim.*;

/**
 * Simple class providing statically accessible methods for playing useful sounds
 */
public class SoundEffects {
    private static Minim minim;
    private static AudioPlayer smallExplosion;
    private static AudioPlayer bigExplosion;
    private static AudioPlayer missileLaunch;

    /**
     * Play a small explosion sound effect
     */
    public static void playSmallExplosion(){
        if(smallExplosion == null) {
            if (minim == null) {
                minim = new Minim(App.app);
            }
            smallExplosion = minim.loadFile("sounds/smallExplosion.wav");
        }
        smallExplosion.play();
        smallExplosion.rewind();
    }


    /**
     * Play a big explosion sound effect
     */
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


    /**
     * Play a missile launch sound effect
     */
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
