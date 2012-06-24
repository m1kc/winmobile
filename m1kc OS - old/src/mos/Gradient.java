/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mos;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Makc
 */
public class Gradient {

    private static int checkRange(int f)
    {
      if (f>255) {f=255;}
      if (f<0) {f=0;}
      return f;
    }

    public static void Gradient(Graphics g, int x, int y, int x2, int y2, int r1, int g1, int b1, int r2, int g2, int b2)
    {
        int i;
        int rt;
        int gt;
        int bt;
        for (i=y; i<=y2; i++)
        {
            rt = r1-(r1-r2)/(y2-y)*i;
            gt = g1-(g1-g2)/(y2-y)*i;
            bt = b1-(b1-b2)/(y2-y)*i;
            rt = checkRange(rt);
            gt = checkRange(gt);
            bt = checkRange(bt);
            g.setColor(rt,gt,bt);
            g.fillRect(i, y, 1, y2-y);
        }
    }

}
