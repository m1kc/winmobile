/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Makc
 */
public abstract class Element
{
    int x=0;
    int y=0;
    int width=0;
    int height=0;
    public abstract void draw(Graphics g);
    public abstract void onClick();
}
