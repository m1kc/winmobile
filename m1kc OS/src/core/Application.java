/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import core.elems.*;
import java.util.Vector;

/**
 *
 * @author m1kc
 */
public class Application
{
    public int x = 0;
    public int y = 0;
    public int width = 100;
    public int height = 50;
    public Vector elements = new Vector();
    public String title = "";

    boolean undecorated;
    WindowHeader header;

    public Application()
    {
        this(false);
    }

    public Application(boolean undecorated) {
        this.undecorated = undecorated;
        if (!undecorated)
        {
            elements.addElement(new WindowHeader(this));
        }
    }

    /**
     * Кому надо - переопределит.
     */
    public void start()
    {
    }
}
