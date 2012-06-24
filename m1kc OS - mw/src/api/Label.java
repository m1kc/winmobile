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
public class Label extends Element
{

    String text = "Text";

    public void draw(Graphics g)
    {
        g.drawString(text, x, y, Graphics.LEFT | Graphics.TOP);
    }

    public void onClick()
    {

    }

}
