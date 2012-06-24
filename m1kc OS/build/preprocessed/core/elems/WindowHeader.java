/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core.elems;

import core.*;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author m1kc
 */
public class WindowHeader extends Element
{
    Application parent;

    public WindowHeader(Application parent)
    {
        this.parent = parent;
        x = 0;
        y = 0;
        width = parent.width;
        height = 20;
    }

    public void paint(Graphics g)
    {
        g.setColor(0x000080);
        g.fillRect(0, 0, width, height);
        g.setColor(0xFFFFFF);
        g.drawString(parent.title, 5, 0, Graphics.LEFT | Graphics.TOP);
    }

    public void prepaint()
    {
        width = parent.width;
    }

    public void click(int x, int y)
    {
        height = 200;
        overflow = true;
    }

    public void overflowPaint(Graphics g) {
        paint(g);
    }
}
