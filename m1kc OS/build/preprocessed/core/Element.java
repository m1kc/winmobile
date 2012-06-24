/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author m1kc
 */
public abstract class Element
{
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean overflow = false;
    public abstract void paint(Graphics g);
    public abstract void overflowPaint(Graphics g);
    public abstract void prepaint();
    public abstract void click(int x,int y);
}
