package alex.wilton.cs4303.p1.game;

/**
 * Class to Represent a 2D Vector using doubles.
 * This class is needed as the inherit PVector class doesn't offer sufficient accuracy as it uses floats.
 */
public class Vector {
    public double x, y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Add another Vector to this Vector
     * @param v Vector to add
     */
    public void add(Vector v) {
        this.x += v.x;
        this.y += v.y;
    }

}
