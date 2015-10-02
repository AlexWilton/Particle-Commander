package alex.wilton.cs4303.p1.game.entity.collection;

import alex.wilton.cs4303.p1.game.entity.Missile;

import java.util.HashSet;

/**
 * Class to represent a set of Missiles for a given level
 */
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

    public void addMissile(Missile missile){
        missiles.add(missile);
    }
}
