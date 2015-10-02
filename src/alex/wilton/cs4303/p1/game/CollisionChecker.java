package alex.wilton.cs4303.p1.game;

import alex.wilton.cs4303.p1.game.entity.Bomber;
import alex.wilton.cs4303.p1.game.entity.City;
import alex.wilton.cs4303.p1.game.entity.Missile;
import alex.wilton.cs4303.p1.game.entity.Particle;
import processing.core.PVector;

import java.util.HashSet;
import java.util.Set;

/**
 * Class for providing functionality to check for collisions and
 * then performing the appropriate action in collision detected.
 */
public class CollisionChecker {
    /**
     * Game to be checked
     */
    private Game game;

    /**
     * Collision Checker
     * @param game Game which will be checked
     */
    public CollisionChecker(Game game) {
        this.game = game;
    }

    /**
     * Look to see if a particle has hit a city. If so,
     * mark the city as destroyed and check for game over.
     */
    public void checkForParticleCityCollision(){
        Set<Particle> particles = game.getParticleWave().getParticles();
        City[] cities = game.getPlanet().getCities();
        for(Particle particle : particles){
            for(City city : cities){
                if(city.getDestroyed()) continue; //only check alive cities
                PVector pPosition = particle.getPosition();
                if(city.containsPoint((int) pPosition.x, (int) pPosition.y)){
                    city.setDestroyed(true);
                    checkForGameOver();
                }
            }
        }

    }


    /**
     * Check for Bombers which have been hit my missile explosion.
     * Center of bomber needs to be in the explosion for bomber to
     * be destroyed. If destroyed, play big explosion sound effect.
     */
    public void checkForBombersHitByMissileExplosion(){
        Set<Missile> missiles = game.getMissilesInMotion().getMissles();
        Set<Bomber> bombers = game.getBomberWave().getBombers();
        for(Bomber bomber : bombers){
            if(!bomber.getActive()) continue; //only check active bombers
            for(Missile missile : missiles) {
                Vector position = bomber.getPosition();
                if(missile.willDestroyObject(position.x, position.y)){
                    bomber.setActive(false);
                    SoundEffects.playBigExplosion();
                    break;
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
                PVector position = particle.getPosition();
                if(missile.willDestroyObject(position.x, position.y)){
                    particle.setActive(false);
                    particlesShotDown.add(particle);
                    break;
                }
            }
        }
        for(Particle p : particlesShotDown) game.particleShootDown(p);
    }

    /**
     * If game is over (no cities remaining), move to game over screen
     */
    public void checkForGameOver() {
       if(game.getPlanet().citiesRemaining() ==0){
           game.gameOver();
       }
    }
}
