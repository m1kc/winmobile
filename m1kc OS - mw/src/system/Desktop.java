/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system;

import api.Application;
import java.util.Vector;
import javax.microedition.lcdui.*;

/**
 * @author Makc
 */
class Desktop extends Canvas
{

    Image wallpaper;
    Image downbar;
    Image cursor;
    
    Image canvas;
    Graphics m;
    
    int x = getWidth()/2;
    int y = getHeight()/2;

    boolean rUp=false;
    boolean lUp=false;
    boolean uUp=false;
    boolean dUp=false;

    Font nameFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);

    Vector log = new Vector();

    Vector apps = new Vector();

    String[] names;

    /**
     * constructor
     */
    public Desktop()
    {
        setFullScreenMode(true);

        try {
            wallpaper = Image.createImage("/default wallpaper.png");
            wallpaper = Resize.resize_image(wallpaper, getWidth(), getHeight());
        } catch (Throwable ex) {
            wallpaper = Image.createImage(getWidth(), getHeight());
            log.addElement("Error loading wallpaper: /default wallpaper.png ; "+ex.toString());
        }

        try {
            downbar = Image.createImage("/downbar.png");
            downbar = Resize.resize_image(downbar, getWidth(), 16);
        } catch (Throwable ex) {
            downbar = Image.createImage(getWidth(), 16);
            log.addElement("Error loading downbar: /downbar.png ; "+ex.toString());
        }

        try {
            cursor = Image.createImage("/default cursor.png");
        } catch (Throwable ex) {
            cursor = Image.createImage(1, 1);
            log.addElement("Error loading cursor: /default cursor.png ; "+ex.toString());
        }

        canvas = Image.createImage(getWidth(), getHeight());

        names = new String[1];
        names[0] = "apps.MyComputer";

    } 

    private void runtime(Graphics g) throws ClassNotFoundException
    {
        int i;
        for (i=0; i<apps.size(); i++)
        {

            int j;
            for (j=0; j<((Application) apps.elementAt(i)).objects.length; j++)
            {
                ((Application) apps.elementAt(i)).objects[j].draw(g);
            }
        }
    }

    /**
     * paint
     */
    public void paint(Graphics g)
    {
        if (lUp) {x=x-1;}
        if (rUp) {x=x+1;}
        if (dUp) {y=y+1;}
        if (uUp) {y=y-1;}

        m = canvas.getGraphics();
        m.setFont(nameFont);
        m.drawImage(wallpaper, getWidth()/2-wallpaper.getWidth()/2, getHeight()/2-wallpaper.getHeight()/2, Graphics.LEFT | Graphics.TOP);
        m.drawImage(downbar, 0, getHeight()-16, Graphics.LEFT | Graphics.TOP);
        try
        {
            runtime(m);
        }
        catch (Throwable ex)
        {

        }
        m.drawImage(cursor, x, y, Graphics.LEFT | Graphics.TOP);

        g.drawImage(canvas, 0, 0, Graphics.LEFT | Graphics.TOP);
        repaint();
    }
    
    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode)
    {
        if (keyCode == -4) {rUp=true;}
        if (keyCode == -3) {lUp=true;}
        if (keyCode == -2) {dUp=true;}
        if (keyCode == -1) {uUp=true;}
        if (keyCode == KEY_NUM6) {rUp=true;}
        if (keyCode == KEY_NUM4) {lUp=true;}
        if (keyCode == KEY_NUM8) {dUp=true;}
        if (keyCode == KEY_NUM2) {uUp=true;}

        if (keyCode==KEY_STAR)
        {
            try
            {
                Application a;
                a = (Application) Class.forName("apps.MyComputer").newInstance();
                apps.addElement(a);
            }
            catch (Throwable ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    /**
     * Called when a key is released.
     */
    protected  void keyReleased(int keyCode)
    {
        if (keyCode == -4) {rUp=false;}
        if (keyCode == -3) {lUp=false;}
        if (keyCode == -2) {dUp=false;}
        if (keyCode == -1) {uUp=false;}
        if (keyCode == KEY_NUM6) {rUp=false;}
        if (keyCode == KEY_NUM4) {lUp=false;}
        if (keyCode == KEY_NUM8) {dUp=false;}
        if (keyCode == KEY_NUM2) {uUp=false;}
    }

    /**
     * Called when a key is repeated (held down).
     */
    protected  void keyRepeated(int keyCode)
    {
    }
    
    /**
     * Called when the pointer is dragged.
     */
    protected  void pointerDragged(int x, int y)
    {
    }

    /**
     * Called when the pointer is pressed.
     */
    protected  void pointerPressed(int x, int y)
    {
    }

    /**
     * Called when the pointer is released.
     */
    protected  void pointerReleased(int x, int y)
    {
    }

}