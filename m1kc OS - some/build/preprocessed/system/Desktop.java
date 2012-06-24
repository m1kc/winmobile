/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system;

import api.WindowedApplication;
import java.util.Vector;
import javax.microedition.lcdui.*;
//import com.nokia.mid.ui.DirectGraphics;

/**
 * @author Makc
 */
public class Desktop extends Canvas
{
    private Vector log = new Vector();

    private Image bg;
    private Image cursor;

    private int x,y;
    private int speed;
    private boolean dl,dr,dd,du = false;

    Vector apps = new Vector();

    /**
     * constructor
     */
    public Desktop()
    {
        setFullScreenMode(true);
        bg = loadImage("/default wallpaper.png");
        cursor = loadImage("/default cursor.png");
        x = getWidth()/2;
        y = getHeight()/2;
        speed = 1;
    }

    private Image loadImage(String s)
    {
        try {
            return Image.createImage(s);
        } catch (Throwable ex) {
            log.addElement("Error while loading image: "+s);
            return Image.createImage(1, 1);
        }
    }

    /**
     * paint
     */
    public void paint(Graphics g)
    {
        // Совершенно необходимые действия
        stepMouse();
        // Чистим экран
        g.setColor(0x000000);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(bg, getWidth()/2, getHeight()/2, Graphics.HCENTER | Graphics.VCENTER);
        // Здесь будут выполняться wp-шки

        // Рисуем приложения

        // Рисуем демонов

        // Рисуем курсор
        g.drawImage(cursor, x, y, Graphics.LEFT | Graphics.TOP);
        // И начинаем все с начала
        repaint();
    }

    private void stepMouse()
    {
        if (dl) x-=speed;
        if (dr) x+=speed;
        if (du) y-=speed;
        if (dd) y+=speed;
        if (x<0) x=0;
        if (x>=getWidth()) x = getWidth()-1;
        if (y<0) y=0;
        if (y>=getHeight()) y = getHeight()-1;
    }

    private void run(String f)
    {
        try
        {
            Object o = Class.forName(f).newInstance();
            if (o.getClass() == WindowedApplication.class)
            {
                apps.addElement(o);
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }

    }

    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode)
    {
        int z = this.getGameAction(keyCode);
        if (z==Desktop.LEFT) dl=true;
        if (z==Desktop.RIGHT) dr=true;
        if (z==Desktop.UP) du=true;
        if (z==Desktop.DOWN) dd=true;
    }
    
    /**
     * Called when a key is released.
     */
    protected  void keyReleased(int keyCode)
    {
        int z = this.getGameAction(keyCode);
        if (z==Desktop.LEFT) dl=false;
        if (z==Desktop.RIGHT) dr=false;
        if (z==Desktop.UP) du=false;
        if (z==Desktop.DOWN) dd=false;
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
        this.x = x;
        this.y = y;
    }

    /**
     * Called when the pointer is pressed.
     */
    protected  void pointerPressed(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Called when the pointer is released.
     */
    protected  void pointerReleased(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

}