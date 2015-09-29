package alex.wilton.cs4303.p1.game;

import alex.wilton.cs4303.p1.game.entity.City;
import alex.wilton.cs4303.p1.game.entity.Missile;
import alex.wilton.cs4303.p1.game.entity.Particle;
import processing.core.PVector;

import java.util.HashSet;
import java.util.Set;

public class CollisionChecker {
    private Game game;

    public CollisionChecker(Game game) {
        this.game = game;
    }

    public void checkForParticleCityCollision(){
        Set<Particle> particles = game.getParticleWave().getParticles();
        City[] cities = game.getPlanet().getCities();
        for(Particle particle : particles){
            for(City city : cities){
                PVector pPosition = particle.getPosition();
                if(city.containsPoint((int) pPosition.x, (int) pPosition.y)){
                    city.setDestroyed(true);
                    checkForGameOver();
                }
            }
        }

    }

    /**
     * Look for particles caught in a missile's explosion radius
     */
    public void checkForParticleExplosionCollision(){
        Set<Particle> particlesShotDown = new HashSet<>();

        Set<Particle> particles = game.getParticleWave().getParticles();
        Set<Missile> missiles = game.getMissilesInMotion().getMissles();
        for(Particle particle : particles){
            for(Missile missile : missiles){
                if(missile.willDestroyParticle(particle)){
                    particle.setAlive(false);
                    particlesShotDown.add(particle);
                    break;
                }
            }
        }

        for(Particle p : particlesShotDown) game.particleShootDown(p);
    }

    public void checkForGameOver() {
       if(game.getPlanet().citiesRemaining() ==0){
           game.gameOver();
       }
    }
}
