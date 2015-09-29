package alex.wilton.cs4303.p1.game;

import alex.wilton.cs4303.p1.game.entity.Missile;

import java.util.HashSet;


public class MissileSet {
    private HashSet<Missile> missiles = new HashSet<>();
    /**
     * Update position and velocity of all missles
     */
    public void integrateAll(){
        for(Missile m : missiles)  m.integrate();
    }

    /**
     * Draw all particles
     */
    public void draw(){
        for(Missile m : missiles) m.draw();
    }

    public HashSet<Missile> getMissles() {
        return missiles;
    }

    public void addMissle(Missile missile){
        missiles.add(missile);
    }
}
