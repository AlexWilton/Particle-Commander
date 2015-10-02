package alex.wilton.cs4303.p1.game.entity.collection;

import alex.wilton.cs4303.p1.game.entity.Bomber;
import alex.wilton.cs4303.p1.game.entity.Particle;
import alex.wilton.cs4303.p1.game.entity.Planet;

import java.util.HashSet;
import java.util.Set;

/**
 * Class to represent all the bombers for a given level
 */
public class BomberWave {
    private HashSet<Bomber> bombers = new HashSet<>();

    /**
     * Update all bombers in wave
     * @return Particles dropped by bombers (if any)
     */
    public Set<Particle> integrateAll(){
        Set<Particle> droppedParticles = new HashSet<>();
        for(Bomber b : bombers){
            Particle particle = b.integrate();
            if(particle != null) droppedParticles.add(particle);
        }
        return droppedParticles;
    }

    /**
     * Draw all bombers
     */
    public void draw(){
        for(Bomber b : bombers) b.draw();
    }

    /**
     * Maybe create a new bomber.
     * The higher the level number, the higher the chance of creation
     * @param targetPlanet Target Planet
     * @param lvlNumber Level Number
     */
    public void maybeCreateBomber(Planet targetPlanet, int lvlNumber){
        if(java.lang.Math.random() < 0.002 + 0.0001 * lvlNumber)
            bombers.add(Bomber.createBomber(targetPlanet, lvlNumber));
    }

    public HashSet<Bomber> getBombers() {
        return bombers;
    }
}
