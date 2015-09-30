package alex.wilton.cs4303.p1.game.entity.collection;

import alex.wilton.cs4303.p1.game.App;
import alex.wilton.cs4303.p1.game.entity.Particle;

import java.util.HashSet;
import java.util.Set;

public class ParticleWave{
    private HashSet<Particle> particles = new HashSet<>();

    /**
     * Randomly generate a double between 0 and 1
     * @return random double
     */
    public static double rand(){
        return java.lang.Math.random();
    }

    /**
     * Maybe (may or may not) create new particles at top screen
     * with random velocity and acceleration towards the planet.
     * The higher the level, the higher the probability that
     * a particle will be created.
     * @param lvlNumber Level Number
     */
    public void maybeCreateNewParticles(int lvlNumber){
        boolean createNewParticle = rand() <  0.01 + 0.008 * lvlNumber;
        if(createNewParticle){
            /*START PARTICLE AT RANDOM POINT AT TOP OF SCREEN*/
            int posX = (int) (rand() * App.WINDOW_WIDTH);
            int posY = 0;

            /*SET VELOCITY RANDOMLY - but bias faster speeds for higher levels*/
            float velocityX = (float) (rand() * lvlNumber * 0.1);
            if( rand() < 0.5) velocityX *= -1; //left or right direction chosen at random
            float velocityY = (float) (rand() * lvlNumber  * 0.1);

            /*ACCELERATION TOWARDS THE PLANET IS ALWAYS CONSTANT*/
            float accelerationX = 0;
            float accelerationY =0.03f;

            particles.add(new Particle(posX, posY, velocityX, velocityY, accelerationX, accelerationY));
        }
    }

    /**
     * Maybe (may or may not) split particles into two particles
     * If split, one particle will head more to the left while
     * the other will head more to the right.
     * Higher the level number, the higher the chance of split.
     * @param lvlNumber Level Number
     */
    public void maybeSpitSomeParticles(int lvlNumber){
        HashSet<Particle> newParticles = new HashSet<>();
        for(Particle p : particles){
            Particle newParticle = p.maybeSplit(lvlNumber);
            if(newParticle != null){
                newParticles.add(newParticle);}
        }
        particles.addAll(newParticles);
    }

    /**
     * Update position and velocity of all particles
     */
    public void integrateAll(){
        HashSet<Particle> oldParticles = new HashSet<>();
        for(Particle p : particles){
            if(!p.integrate()) oldParticles.add(p);
        }
        particles.removeAll(oldParticles);
    }

    /**
     * Add Particle to Particle Wave
     * @param newParticles Particle to add
     */
    public void addParticles(Set<Particle> newParticles){
        particles.addAll(newParticles);
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