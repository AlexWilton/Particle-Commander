package alex.wilton.cs4303.p1.game;

/**
 * Class to Represent a 2D Vector using doubles
 */
public class Vector {
    public double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Add another Vector to this Vector
     * @param v
     */
    public void add(Vector v) {
        this.x += v.x;
        this.y += v.y;
    }

}
