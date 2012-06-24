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
public class App4 extends App {

    int cl=255;
    int c=-5;
    int flag=0;
    boolean drag=false;
    int kx=20;
    int ky=20;

    public App4()
    {

    }

    public void Init()
    {
        UseKeyboard = false;
        UseMouse = true;
        IsGraphic = true;
        IsWindowed = false;
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
        cl=cl+c;
        if (cl>=255) {c=-c;}
        if (cl<=128) {c=-c;}
        g.setColor(cl,cl,cl);
        g.drawString("ЧАСЫ", Desktop.width/2, Desktop.height/2, Graphics.TOP | Graphics.HCENTER);
        if (flag==1) {g.drawString("Попал!", 10, 50, Graphics.TOP | Graphics.LEFT);}
        g.setColor(255,0,0);
        g.drawArc(20, 20, 20, 20, 0, 360);
        g.setColor(cl, cl, cl);
        g.fillRect(kx, ky, 20, 20);
        if (drag) {kx=Desktop.x-10; ky=Desktop.y-10;}
    }

    public void mouseDown(int x, int y)
    {
    if ((x>20) && (x<40) && (y>20) && (y<40)) {flag=1;}
    if ((x>kx) && (y>ky) && (x<kx+20) && (y<ky+20)) {drag=!drag;}
    }

    public void mouseUp(int x, int y)
    {
    flag=0;
    }

    public void receiveData(String s) {

    }

}
