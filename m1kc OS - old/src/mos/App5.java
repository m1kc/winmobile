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
public class App5 extends App {

    int[] xs = new int[10];
    int[] ys = new int[10];
    int i;
    int score=0;

    public App5()
    {

    }

    public void Init()
    {
        UseKeyboard = false;
        UseMouse = true;
        IsGraphic = true;
        IsWindowed = false;
        windowTitle = "";

        for (i=0; i<=9; i++)
        {
        xs[i]=Desktop.width/10*i;
        ys[i]=Desktop.height+i*2;
        }
    }

    public void keyPressed(int keyCode)
    {

    }

    public void keyReleased(int keyCode)
    {

    }

    public void keyRepeated(int keyCode)
    {

    }

    public void paint(Graphics g)
    {
        for (i=0; i<=9; i++)
        {
        g.setColor(255,0,0);
        //g.fillArc(xs[i], ys[i], 20, 20, 0, 360);
        ys[i]=ys[i]-1;
        if (ys[i]<0) {ys[i]=Desktop.height;}
        }
        g.setColor(255,255,255);
        //g.drawString("Очки: "+score, 5, 50, Graphics.LEFT | Graphics.TOP);
    }

    public void mouseDown(int x, int y)
    {
        for (i=0; i<=9; i++)
        {
        if ((x>xs[i])&&(y>ys[i])&&(x<xs[i]+20)&&(y<ys[i]+20)) {xs[i]=Desktop.width/10*i; ys[i]=Desktop.height; score++;}
        }
    }

    public void mouseUp(int x, int y)
    {

    }

    public void receiveData(String s) {

    }

}
