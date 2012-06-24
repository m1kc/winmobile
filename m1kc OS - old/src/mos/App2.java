/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mos;

import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.*;

/**
 *
 * @author Makc
 */
public class App2 extends App {

    int[] kx = new int[20];
    int[] ky = new int[20];
    int f;
    int k;
    int tk;
    int vx,vy;
    Image curs1,curs2;
    int arc;

    String ggg;

    public App2()
    {
        
    }

    public void Init()
    {

    UseKeyboard = true;
    UseMouse = false;
    IsGraphic = true;
    IsWindowed = true;
    x = 100;
    y = 100;
    width = 100;
    height = 100;
    windowTitle="Message";

    ggg="No data";

     for (f = 0; f < 20; f++) {
            kx[f] = -100;
            ky[f] = -100;
        }

     k=0;
     tk=1;
     vx=Desktop.x;
     vy=Desktop.y;

        InputStream im;

        im = InputStream.class.getResourceAsStream("/wwwcurs3.png");
        try {
            curs1 = Image.createImage(im);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        im = InputStream.class.getResourceAsStream("/cursorzz.png");
        try {
            curs2 = Image.createImage(im);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void keyPressed(int keyCode) {
    }

    public void keyReleased(int keyCode) {
    }

    public void keyRepeated(int keyCode) {
    }

    public void paint(Graphics g) {
        if (k==0) {k=1;} else {k=0;}
        for (f = 1; f < 20; f++)
        {
            g.setColor(128, 128, 128);
            //g.fillTriangle(kx[f], ky[f], kx[f], ky[f] + 15, kx[f] + 8, ky[f] + 10);
            //g.drawImage(cursor, kx[f], ky[f], Graphics.TOP | Graphics.LEFT);
            if (k==0) {kx[f-1]=kx[f];}
            if (k==0) {ky[f-1]=ky[f];}
        }
        
        if (k==0) {kx[19]=Desktop.x;}
        if (k==0) {ky[19]=Desktop.y;}

        if (tk==0) {tk=1;} else {tk=0;}
        //if (tk==0) {Desktop.x = Desktop.x+1; Desktop.y = Desktop.y+1;}
        //if (tk==1) {Desktop.x = Desktop.x-1; Desktop.y = Desktop.y-1;}

        /*
        g.setColor(0,0,0);
        g.drawLine(Desktop.x, Desktop.y, vx, vy);
        if (Desktop.x-vx > 50) {vx=vx+1;}
        if (Desktop.x-vx < -50) {vx=vx-1;}
        if (Desktop.y-vy > 50) {vy=vy+1;}
        if (Desktop.y-vy < -50) {vy=vy-1;}

        arc=arc-5;
        if (arc>360) {arc=0; }

        for (f=0; f<=200; f++)
        {
        g.drawArc(Desktop.width/2-f, Desktop.height/2-f, f*2, f*2, arc+f*(360/25), 25);
        }
         */

        g.setColor(0,0,0);
        g.drawString(ggg, x+12, y+24, Graphics.LEFT | Graphics.TOP);
        
    }

    public void mouseDown(int x, int y)
    {

    }

    public void mouseUp(int x, int y)
    {

    }

    public void receiveData(String s)
    {
      ggg=s;
    }
}
