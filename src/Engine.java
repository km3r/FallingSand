import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * Kyle Rosenthal
 * 12/4/13
 */

//TODO: Fix error
//TODO: Erasing by point

class Engine implements Runnable{
    static final int HEIGHT = 400;
    static final int WIDTH = 400;
    public static JFalling f;
    public static BufferedImage img;

    class JFalling extends JFrame{

        int xI = 0,
                yI = 0;


        JFalling(BufferedImage bImg)
        {
            img = bImg;
        }
        public void setImg(BufferedImage bImg)
        {
            img = bImg;
        }


        public void setxIyI(int x, int y)
        {
            xI = x;
            yI = y;
        }
        public void paint(Graphics g)
        {
            g.drawImage(img,xI,yI,null);
        }

    }
    @Override
    public void run(){




        BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
        int color = (12 << 16) | (12 << 8) | 12;
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                image.setRGB(j,i,color);

            }
        }
        f = new JFalling(image);
        JOptionPane.showMessageDialog(f,
                "Click to spawn particles. " +
                        "\n S for sand. " +
                        "\t\t      D for dirt. " +
                        "\n W for water. " +
                        "\t\t   F for fire. " +
                        "\n R for rock." +
                        "\t\t        P to clear all." +
                        "\n   Scroll to increase mouse size",
                "Controls",
                JOptionPane.PLAIN_MESSAGE);
        f.setTitle("Falling Sand --by-- Kyle Rosenthal");

        f.setSize(WIDTH, HEIGHT);


        BufferedImage icoB = new BufferedImage(32,32,BufferedImage.TYPE_INT_RGB);

        for (int j = 0; j < icoB.getWidth()/2 - 2;j++)
            for (int i = (icoB.getWidth()/2) - j; i < icoB.getWidth()/2 + j;i++)
                    icoB.setRGB(i,j+ 8,Color.YELLOW.getRGB());

        f.setVisible(true);
        f.setIconImage(icoB);
        Insets insets = f.getInsets();
        f.setxIyI(insets.left,insets.top);
        f.setSize(WIDTH + insets.left + insets.right,HEIGHT + insets.top + insets.bottom);
        f.setBackground(Color.BLACK);
        f.setResizable(false);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

        MouseHandleMaster mlm = new MouseHandleMaster(insets.left,insets.top);

        f.addMouseListener(mlm);
        f.addKeyListener(mlm);
        f.addMouseWheelListener(mlm);


        while (true)
        {

            mlm.check();

            Particle.updateAll();
            sync(180);
            f.repaint();
        }
    }

    long timeOld = System.currentTimeMillis();
    public void sync(int fps)
    {
        int x =(int) ((1000/fps)-(System.currentTimeMillis() - timeOld ));
        if (x < 0) x = 0;
        try {
            Thread.sleep(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        {

        }
        timeOld = System.currentTimeMillis();
    }
}