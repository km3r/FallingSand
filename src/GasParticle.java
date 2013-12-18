/**
 * Kyle Rosenthal
 * 12/5/13
 */
public class GasParticle extends Particle{

    public GasParticle(int x, int y, int red, int green, int blue) {
        super(x, y, red, green, blue);
    }

    int move;
    public void update()
    {
        if (r.next(3) < 2 {
            move = (int)(r.nextInt(10) - 5);
            if (!collisionCheck(getX() + move,getY())) addX(move);
        }
        if (r.next(3) < 2) {
            move = (int)(r.nextInt(10) - 5);
            if (!collisionCheck(getX(),getY() + move)) addY(move);
        }

        super.update();
    }

}
