import java.awt.event.*;

/**
 * Kyle Rosenthal
 * 12/4/13
 */
public class MouseHandleMaster implements KeyListener, MouseListener, MouseWheelListener {
    static int mouse_size = 5;

    //static Particle p;
    static int dx;
    static int dy;
    static int mX,mY;
    static boolean mouseDown = false;


    static char key = 's';

    static char oldKey = 's';


    public MouseHandleMaster(int mouseX,int mouseY)
    {
        mX = mouseX;
        mY = mouseY;
    }


    public synchronized static void spawnByLoc(int x,int y)
    {
        if (x < 0) x = 0;
        if (x >= Engine.img.getWidth() -1) x = Engine.img.getWidth() - 1;
        if (y < 0) y = 0;
        if (y >= Engine.img.getHeight() -1) y = Engine.img.getHeight() - 1;
        if (!Particle.collisionCheck(x,y))
        {
            switch (key){
                case 's':
                    new Sand(x,y);
                    break;
                case 'd':
                    new Dirt(x,y);
                    break;
                case 'w':
                    new Water(x,y);
                    break;
                case 'f':
                    new Fire(x,y);
                    break;
                case 'r':
                    new Rock(x,y);
                    break;
                case 'p':
                    Particle.flush();
                    break;
                default:
                    key = oldKey;
                    //spawnByLoc(x,y);
                    break;

            }
        }
        oldKey = key;
    }
    public void check()
    {
        if (mouseDown)
        {
            Runnable r = new PenTool();
            Thread t = new Thread(r);
            t.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'p'){
            Particle.flush();
        }else{
            key = e.getKeyChar();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (e.getButton() == 1)
        {
            mouseDown = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int x = -e.getWheelRotation();
        if (x > 0) mouse_size ++;
        if (x < 0) mouse_size --;
    }

    int lastX = 100, lastY = 100;
    class PenTool implements Runnable{


        int x,y;
        PenTool(){
            this.x = 200;
            this.y = 200;
        }
        @Override
        public void run() {
            int x;
            int y;
            try{
                x = (int) Engine.f.getMousePosition().getX() - mX;
            } catch (Exception e)
            {
                x = lastX;
            }
            try{
                y = (int) Engine.f.getMousePosition().getY() - mY;
            } catch (Exception e)
            {
                y = lastY;
            }


            for (int i = 1; i < mouse_size; i++) {
                for (int t = -(mouse_size-i); t <  mouse_size-i; t++){
                    dx = t;
                    dy = i;
                    spawnByLoc(x+dx,y+dy);
                }

            }
            for (int i = mouse_size; i > 0; i--) {
                for (int t = -i; t <  i; t++){
                    dx = t;
                    dy = i-mouse_size;
                    spawnByLoc(x+dx,y+dy);
                }

            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lastX = x;
            lastY = y;

        }
    }

}
