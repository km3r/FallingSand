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
        shift = (int) (Math.random()*4);

        if (Math.random() < .5)
        {
            for (int i = 0; i < shift;i++)
            {
                if (!collisionCheck(getX() + 1,getY())) addX(1);
                else break;
            }
        }else
        {
            for (int i = 0; i < shift;i++)
            {
                if (!collisionCheck(getX() - 1,getY())) addX(-1);
                else break;
            }
        }

        //TODO SINKING IN WATER

        if (getY() > 0 && inLoc[getX()][getY()-1] != null && (inLoc[getX()][getY()-1] instanceof FallingParticle) && !(inLoc[getX()][getY()-1] instanceof Water))
        {
            swap(inLoc[getX()][getY()-1]);
        }

        super.update();
    }


}
