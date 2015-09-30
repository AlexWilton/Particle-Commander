package alex.wilton.cs4303.p1.game;

import alex.wilton.cs4303.p1.game.entity.Bomber;
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

    /**
     * Look to see if a particle has hit a city. If so,
     * mark the city as destroyed and check for game over.
     */
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


    public void checkForParticleBomberCollision(){
        Set<Missile> missiles = game.getMissilesInMotion().getMissles();
        Set<Bomber> bombers = game.getBomberWave().getBombers();
        for(Bomber bomber : bombers){
            for(Missile missile : missiles) {
                Vector position = bomber.getPosition();
                if(missile.willDestroyObject(position.x, position.y)){
                    bomber.setActive(false);
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
