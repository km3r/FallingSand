

import java.awt.image.BufferedImage;

/**
 * Kyle Rosenthal
 * 12/4/13
 */
public class Particle {
    static final int BACK_COLOR = (12 << 16) | (12 << 8) | 12;
    public static Particle inLoc[][] = new Particle[Engine.img.getWidth()][Engine.img.getHeight()];
    private int x,y, oldX,oldY;
    public boolean dead = false;
    boolean changed = false;
    boolean notUpdated = true;
    int color;
    static BufferedImage temp = Engine.img;

    public Particle(int x, int y, int red, int green, int blue) {

        dead = false;
        notUpdated = true;
        this.x = x;
        this.y = y;
        oldX = x;
        oldY = y;
        inLoc[this.x][this.y] = this;
        color = (red << 16) | (green << 8) | blue;

        Engine.img.setRGB(x,y,color);
    }

    public void update()
    {

        notUpdated = false;
        if (changed)
        {
            //collisionFix();
            temp.setRGB(oldX,oldY,BACK_COLOR);
            temp.setRGB(x,y,color);
            oldX = x;
            oldY = y;
            changed = false;
        }
    }

    public static boolean collisionCheck(int xCh,int yCh) {
        if (xCh < 0 || xCh >= Engine.img.getWidth() || yCh < 0 || yCh >= Engine.img.getHeight()) return true;
        return (inLoc[xCh][yCh] != null);
    }
    public int getX() {
        return x;
    }

    public void addX(int x)
    {
        setX(this.x + x);
    }
    public void setX(int x)
    {
        if (x < 0) x = 0;
        if (x >= Engine.img.getWidth() -1) x = Engine.img.getWidth() - 1;
        if (inLoc[x][this.y] == null){
            changed = true;
            inLoc[this.x][this.y] = null;
            if (x < 0) x = 0;
            if (x >= Engine.img.getWidth() -1) x = Engine.img.getWidth() - 1;
            this.x = x;
            inLoc[this.x][this.y] = this;
        }
    }

    public int getY() {
        return y;
    }

    public void addY(int y)
    {
        setY(this.y+y);
    }
    public void setY(int y) {
        if (y < 0) y = 0;
        if (y >= Engine.img.getHeight() -1) y = Engine.img.getHeight() - 1;
        if (inLoc[this.x][y] == null){
            changed = true;
            inLoc[this.x][this.y] = null;
            if (y < 0) y = 0;
            if (y >= Engine.img.getHeight() -1) y = Engine.img.getHeight() - 1;
            this.y = y;
            inLoc[this.x][this.y] = this;
        }
    }

    public synchronized static void updateAll()
    {
        temp = Engine.img;
        for (int i = 0; i < inLoc.length; i++)
        {
            for (int j = 0; j < inLoc[i].length; j++)
            {
                if (inLoc[i][j] != null)
                {
                    if (inLoc[i][j].notUpdated)
                    {
                        if (inLoc[i][j].dead)
                        {
                            inLoc[i][j] = null;
                            temp.setRGB(i,j,BACK_COLOR);
                        } else {
                            inLoc[i][j].update();
                        }
                    } else inLoc[i][j].notUpdated = true;
                }
            }
        }
        Engine.img = temp;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void swap(Particle p)
    {
        Particle tempP = p;
        inLoc[p.x][p.y] = this;
        p.x = x;
        p.y = y;
        inLoc[x][y] = tempP;
        x = tempP.x;
        y = tempP.y;
        temp.setRGB(p.x,p.y,p.getColor());
        temp.setRGB(x,y,getColor());

    }
    public static void flush() {
        for (int i = 0; i < inLoc.length; i++)
        {
            for (int j = 0; j < inLoc[i].length; j++)
            {
                inLoc[i][j] = null;
                temp.setRGB(i,j,BACK_COLOR);
            }
        }
    }
}
