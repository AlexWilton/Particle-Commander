package alex.wilton.cs4303.p1.game;

import alex.wilton.cs4303.p1.game.entity.Missle;
import alex.wilton.cs4303.p1.game.entity.Particle;

import java.util.HashSet;


public class MissleSet{
    private HashSet<Missle> missles = new HashSet<>();
    /**
     * Update position and velocity of all missles
     */
    public void integrateAll(){
        for(Missle m : missles)  m.integrate();
    }

    /**
     * Draw all particles
     */
    public void draw(){
        for(Missle m : missles) m.draw();
    }

    public HashSet<Missle> getMissles() {
        return missles;
    }

    public void addMissle(Missle missle){
        missles.add(missle);
    }
}
