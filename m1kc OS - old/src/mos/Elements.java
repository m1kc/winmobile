/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mos;

import javax.microedition.lcdui.*;

/**
 *
 * @author Makc
 */
public class Elements {

    public static boolean Button(Graphics g, String title, int x, int y, int width, int height)
    {
        if ((Desktop.x>x)&&(Desktop.y>y)&&(Desktop.x<x+width)&&(Desktop.y<y+height))
        {
        g.setColor(225,225,225);
        g.fillRoundRect(x, y, width, height, 10, 10);
        g.setColor(0,0,0);
        g.drawRoundRect(x, y, width, height, 10, 10);
        g.setClip(x, y, width, height);
        g.setColor(0,0,0);
        g.drawString(title, x+width/2, y+height/2-Desktop.nameFont.getHeight()/2, Graphics.HCENTER | Graphics.TOP);
        g.setClip(0, 0, Desktop.width, Desktop.height);
        if (Desktop.MouseDown) {Desktop.MouseDown=false; return true;} else {return false;}
        }
        else
        {
        g.setColor(255,255,255);
        g.fillRoundRect(x, y, width, height, 10, 10);
        g.setColor(128,128,128);
        g.drawRoundRect(x, y, width, height, 10, 10);
        g.setClip(x, y, width, height);
        g.setColor(0,0,0);
        g.drawString(title, x+width/2, y+height/2-Desktop.nameFont.getHeight()/2, Graphics.HCENTER | Graphics.TOP);
        g.setClip(0, 0, Desktop.width, Desktop.height);
        return false;
        }
    }

    public static boolean LButton(Graphics g, String title, int x, int y, int width, int height, int step)
    {
        if ((Desktop.x>x)&&(Desktop.y>y)&&(Desktop.x<x+width)&&(Desktop.y<y+height))
        {
        g.setColor(0,128,255);
        g.fillRect(x, y, width, height);
        g.setClip(x, y, width, height);
        g.setColor(255,255,255);
        g.drawString(title, x+step, y+height/2-Desktop.nameFont.getHeight()/2, Graphics.LEFT | Graphics.TOP);
        g.setClip(0, 0, Desktop.width, Desktop.height);
        if (Desktop.MouseDown) {Desktop.MouseDown=false; return true;} else {return false;}
        }
        else
        {
        g.setClip(x, y, width, height);
        g.setColor(0,0,0);
        g.drawString(title, x+step, y+height/2-Desktop.nameFont.getHeight()/2, Graphics.LEFT | Graphics.TOP);
        g.setClip(0, 0, Desktop.width, Desktop.height);
        return false;
        }
    }
}
