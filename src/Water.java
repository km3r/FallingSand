/**
 * Kyle Rosenthal
 * 12/5/13
 */
public class Water extends FallingParticle{
    public Water(int x, int y) {
        super(x, y, 5, 25, 220);
    }
    public Water(int x, int y,int red, int blue,int green)
    {
        super(x,y,red,blue,green);
    }
    int shift;
    public void update()
    {
        shift = (int) (Math.random()*8 - 4);

        for (int i = (int)Math.random()*10; i > 0;i--)
        {
            if (!collisionCheck(getX() + shift,getY())) addX(shift);
            else break;
        }

        /* /TODO SINKING IN WATER
        if (inLoc[getX()][getY()-1] != null && (inLoc[getX()][getY()-1] instanceof FallingParticle))
        {
            swap(inLoc[getX()][getY()-1]);
        }
        */

        super.update();
    }


}
