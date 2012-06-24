/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package api;

import javax.microedition.lcdui.Image;

/**
 *
 * @author Makc
 */
public class WindowedApplication
{
    public Image buffer;
    public int x,y,width,height;
    private int lw,lh;
    public String title;
    public int uid;

    public WindowedApplication()
    {
        x = 20;
        y = 20;
        width = 150;
        height = 90;
        title = "No title";
        // uid!
        refreshBuffer();
    }

    private void refreshBuffer()
    {
        if ((lw!=width)||(lh!=height)) buffer = Image.createImage(width-20, height-20);
        lw = width;
        lh = height;
    }
}
