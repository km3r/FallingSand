
/**
 * Kyle Rosenthal
 * 12/4/13
 */
public class FallingParticle extends Particle {

    public FallingParticle(int x, int y, int red, int green, int blue) {
        super(x, y, red, green, blue);
    }
    public void update()
    {
        if (getY() < Engine.img.getHeight() - 1)
        {
            if(!collisionCheck(getX(),getY() + 1))
            {
                setY(getY() + 1);
            } else
            {
                int shift = (int) (Math.random()*4 - 2);
                if (!collisionCheck(getX() + shift,getY()) ) addX(shift);
                if (Math.random() < .5){
                    if (!collisionCheck(getX() + 1,getY() + 1) ){
                        setX(getX() + 1);
                        setY(getY() + 1);
                    } else if (!collisionCheck(getX() - 1,getY() + 1) && !collisionCheck(getX() - 1,getY()) ){
                        setX(getX() - 1);
                        setY(getY() + 1);
                    }
                } else{
                    if (!collisionCheck(getX() - 1,getY() - 1)  ){
                        setX(getX() - 1);
                        setY(getY() + 1);
                    } else if (!collisionCheck(getX() + 1,getY() + 1 )){
                        setX(getX() + 1);
                        setY(getY() + 1);
                    }
                }
            }
        }

        super.update();
    }

}
