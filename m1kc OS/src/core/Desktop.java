/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import java.util.Vector;
import javax.microedition.lcdui.*;
import javax.microedition.lcdui.game.*;

/**
 * @author m1kc
 */
public class Desktop extends GameCanvas implements Runnable
{
    Vector apps = new Vector();
    int x,y;
    int lx = -1, ly = -1;
    boolean up = false,down = false,left = false,right = false;

    /**
     * constructor
     */
    public Desktop() {
        super(false);
        setFullScreenMode(true);
        x = getWidth() / 2;
        y = getHeight() / 2;
        test();
    } 

    public final void test()
    {
        Application a = new Application();
        a.title = "Первое окошко";
        apps.addElement(a);

        Application b = new Application(true);
        b.x = 20; b.y = 20;
        b.title = "Второе окошко";
        apps.addElement(b);

        Application c = new Application();
        c.x = 50; c.y = 30;
        c.title = "И третье";
        apps.addElement(c);
    }

    public void run()
    {
        while(true)
        {
            paint(getGraphics());
            flushGraphics();
            // Delay
            // <editor-fold defaultstate="collapsed" desc=" Thread.sleep(1); ">
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
            }
            // </editor-fold>
        }
    }

    /**
     * paint
     */
    public void paint(Graphics g) {
        // Clean
        g.setClip(0, 0, getWidth(), getHeight());
        g.setColor(0x000000);
        g.fillRect(0, 0, getWidth(), getHeight());
        // Draw windows
        for (int i=0; i<apps.size(); i++)
        {
            Application a = (Application) apps.elementAt(i);
            g.setClip(a.x, a.y, a.width, a.height);
            g.setColor(0x808080);
            g.fillRect(a.x, a.y, a.width, a.height);
            g.setColor(0xFFFFFF);
            g.drawRect(a.x, a.y, a.width-1, a.height-1);
            for (int j=0; j<a.elements.size(); j++)
            {
                Element e = (Element) a.elements.elementAt(j);
                e.prepaint();
                g.translate(a.x+e.x, a.y+e.y);
                e.paint(g);
                g.translate(-(a.x+e.x), -(a.y+e.y));
            }
            for (int j=0; j<a.elements.size(); j++)
            {
                Element e = (Element) a.elements.elementAt(j);
                if (e.overflow)
                {
                    g.setClip(a.x+e.x, a.y+e.y, e.width, e.height);
                    g.translate(a.x+e.x, a.y+e.y);
                    e.overflowPaint(g);
                    g.translate(-(a.x+e.x), -(a.y+e.y));
                }
            }
        }
        // Move cursor
        // <editor-fold defaultstate="collapsed" desc=" move! ">
        if (up)
        {
            y--;
            if (y<0) y = 0;
        }
        if (down)
        {
            y++;
            if (y>=getHeight()) y = getHeight() - 1;
        }
        if (left)
        {
            x--;
            if (x<0) x = 0;
        }
        if (right)
        {
            x++;
            if (x>=getWidth()) x = getWidth() - 1;
        }
        // </editor-fold>
        // Draw cursor
        g.setClip(0, 0, getWidth(), getHeight());
        g.setColor(0xFFFFFF);
        g.fillTriangle(x, y, x+10, y+5, x+5, y+10);
    }

    public void tab()
    {
        Application a = (Application) apps.firstElement();
        apps.removeElement(a);
        apps.addElement(a);
    }

    public Application getAppUnderCursor()
    {
        for (int i=apps.size()-1; i>=0; i--)
        {
            Application a = (Application) apps.elementAt(i);
            if (x>a.x && y>a.y && x<a.x+a.width && y<a.y+a.height) return a;
        }
        return null;
    }

    public Application getFocusedApp()
    {
        return (Application) apps.lastElement();
    }

    public Element getElementUnderCursor()
    {
        Application a = getFocusedApp();
        for (int i=0; i<a.elements.size(); i++)
        {
            Element e = (Element) a.elements.elementAt(i);
            if (x>a.x+e.x && y>a.y+e.y && x<a.x+e.x+e.width && y<a.y+e.y+e.height)
            {
                return e;
            }
        }
        return null;
    }

    public void click()
    {
        Application a = getAppUnderCursor();
        if (a != null)
        {
            if (a==getFocusedApp())
            {
                Element e = getElementUnderCursor();
                if (e != null) e.click(x,y);
            }
            else
            {
                apps.removeElement(a);
                apps.addElement(a);
            }
        }
    }

    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode) 
    {
        switch(keyCode)
        {
            case KEY_NUM1:
                tab();
                break;
            case KEY_NUM5:
                click();
                break;
            case KEY_NUM2:
                up = true;
                break;
            case KEY_NUM4:
                left = true;
                break;
            case KEY_NUM6:
                right = true;
                break;
            case KEY_NUM8:
                down = true;
                break;
        }
    }
    
    /**
     * Called when a key is released.
     */
    protected  void keyReleased(int keyCode) 
    {
        switch(keyCode)
        {
            case KEY_NUM2:
                up = false;
                break;
            case KEY_NUM4:
                left = false;
                break;
            case KEY_NUM6:
                right = false;
                break;
            case KEY_NUM8:
                down = false;
                break;
        }
    }

    /**
     * Called when a key is repeated (held down).
     */
    protected  void keyRepeated(int keyCode) {
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
        lx = x;
        ly = y;
    }

    /**
     * Called when the pointer is released.
     */
    protected  void pointerReleased(int x, int y) 
    {
        this.x = x;
        this.y = y;
        if (lx==x && ly==y) click();
    }
    
}