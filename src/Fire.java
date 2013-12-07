

/**
 * Kyle Rosenthal
 * 12/5/13
 */
public class Fire extends GasParticle {
    int time;
    final int LIFE_TIME = (int) ( 70 + (Math.random()* 20));
    public Fire(int x, int y) {
        super(x, y, 200,20,20);
        time = 0;
    }
    public void update()
    {
        time++;
        if (time > LIFE_TIME) dead = true;
        addY((int)(-1*Math.random() * time / 20));
        if (getY() < Engine.img.getHeight() && Math.random() < .2)
        {
            if(!collisionCheck(getX(),getY() - 1 ) )
            {
                addY(-1);
            } else if (!collisionCheck(getX() + 1,getY()) && !collisionCheck(getX() - 1,getY()) )
            {
                if (Math.random() < .5){
                    if (!collisionCheck(getX() + 1,getY() - 1) ){
                        setX(getX() + 1);
                        setY(getY() - 1);
                    } else if (!collisionCheck(getX() - 1,getY() - 1) ){
                        setX(getX() - 1);
                        setY(getY() - 1);
                    }
                } else{
                    if (!collisionCheck(getX() - 1,getY() - 1) ){
                        setX(getX() - 1);
                        setY(getY() - 1);
                    } else if (!collisionCheck(getX() + 1,getY() - 1) ){
                        setX(getX() + 1);
                        setY(getY() - 1);
                    }
                }
            }
        }
        super.update();
    }
}
