package alex.wilton.cs4303.p1.game.entity.collection;

import alex.wilton.cs4303.p1.game.entity.Bomber;
import alex.wilton.cs4303.p1.game.entity.Particle;
import alex.wilton.cs4303.p1.game.entity.Planet;

import java.util.HashSet;
import java.util.Set;

public class BomberWave {
    private HashSet<Bomber> bombers = new HashSet<>();

    public Set<Particle> integrateAll(){
        Set<Particle> droppedParticles = new HashSet<>();
        for(Bomber b : bombers){
            Particle particle = b.integrate();
            if(particle != null) droppedParticles.add(particle);
        }
        return droppedParticles;
    }

    public void draw(){
        for(Bomber b : bombers) b.draw();
    }

    public void maybeCreateBomber(Planet targetPlanet, int lvlNumber){
        if(java.lang.Math.random() < 0.002 + 0.0001 * lvlNumber)
            bombers.add(Bomber.createBomber(targetPlanet, lvlNumber));
    }

    public HashSet<Bomber> getBombers() {
        return bombers;
    }
}
