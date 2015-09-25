package alex.wilton.cs4303.p1.game;

import alex.wilton.cs4303.p1.App;
import alex.wilton.cs4303.p1.game.entity.Particle;
import processing.core.PApplet;

import java.util.HashSet;

public class ParticleWave{
    private HashSet<Particle> particles = new HashSet<>();

    public void maybeCreateNewParticles(int lvlNumber){
        boolean createNewParticle = java.lang.Math.random() < 0.02 + 0.01 * lvlNumber;
        if(createNewParticle){
            int posX = (int) (java.lang.Math.random() * App.WINDOW_WIDTH);

            particles.add(new Particle(posX, 0, 0.1f, 0.1f, 0, 0.05f));
        }
    }

    public void integrateAll(){
        for(Particle p : particles)  p.integrate();
    }

    public void draw(){
        for(Particle p : particles) p.draw();
    }

    public HashSet<Particle> getParticles() {
        return particles;
    }
}