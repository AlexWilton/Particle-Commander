package alex.wilton.cs4303.p1.game;

import alex.wilton.cs4303.p1.game.entity.City;
import alex.wilton.cs4303.p1.game.entity.Particle;

import java.util.Set;

public class CollisionChecker {
    private Game game;

    public CollisionChecker(Game game) {
        this.game = game;
    }

    public void checkForParticleCityCollision(){
        Set<Particle> particles = game.getParticleWave().getParticles();
        City[] cities = game.getPlanet().getCities();
        for(Particle p : particles){

        }

    }
}
