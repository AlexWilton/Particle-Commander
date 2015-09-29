package alex.wilton.cs4303.p1.game;

import alex.wilton.cs4303.p1.App;
import alex.wilton.cs4303.p1.game.entity.Particle;
import processing.core.PApplet;

import java.util.HashSet;

public class ParticleWave{
    private HashSet<Particle> particles = new HashSet<>();

    /**
     * Randomly generate a double between 0 and 1
     * @return random double
     */
    private double rand(){
        return java.lang.Math.random();
    }

    /**
     * Maybe (may or may not) create new particles at top screen
     * with random velocity and acceleration towards the planet.
     * The higher the level, the higher the probability that
     * a particle will be created.
     * @param lvlNumber
     */
    public void maybeCreateNewParticles(int lvlNumber){
        boolean createNewParticle = rand() < 0.01 + 0.005 * lvlNumber;
        if(createNewParticle){
            /*START PARTICLE AT RANDOM POINT AT TOP OF SCREEN*/
            int posX = (int) (rand() * App.WINDOW_WIDTH);
            int posY = 0;

            /*SET VELOCITY RANDOMLY - but bias faster speeds for higher levels*/
            float velocityX = (float) (rand() * lvlNumber);
            if( rand() < 0.5) velocityX *= -1; //left or right direction chosen at random
            float velocityY = (float) (rand() * lvlNumber);

            /*ACCELERATION TOWARDS THE PLANET IS ALWAYS CONSTANT*/
            float accelerationX = 0;
            float accelerationY =0.03f;

            particles.add(new Particle(posX, posY, velocityX, velocityY, accelerationX, accelerationY));
        }
    }

    /**
     * Update position and velocity of all particles
     */
    public void integrateAll(){
        for(Particle p : particles)  p.integrate();
    }

    /**
     * Draw all particles
     */
    public void draw(){
        for(Particle p : particles) p.draw();
    }

    public HashSet<Particle> getParticles() {
        return particles;
    }

    public void remove(Particle particle) {
        particles.remove(particle);
    }
}