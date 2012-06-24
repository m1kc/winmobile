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
public class App3 extends App {

    boolean PuskIsShown=false;
    String[] Items;

    public App3() {
       
    }

    public void Init()
    {
           UseKeyboard = true;
           UseMouse = false;
           IsGraphic = true;
           IsWindowed = false;

           Items = new String[2];
           Items[0] = "Мой компьютер";
           Items[1] = "Мои документы";
    }

    public void keyPressed(int keyCode) {
        if (keyCode==35) {PuskIsShown=!PuskIsShown;}
    }

    public void keyReleased(int keyCode) {
    }

    public void keyRepeated(int keyCode) {
    }

    public void paint(Graphics g) {
        if (PuskIsShown)
        {
            // СДЕЛАТЬ НОВЫЙ ЭЛЕМЕНТ - ПЕРЕПИСАТЬ ВСЕ
            g.setColor(0,0,0);
            g.fillRect(0, Desktop.height-20-Desktop.fontHeight*Items.length, 128, Desktop.fontHeight*Items.length);
            g.setColor(255,255,255);
            int i;
            for (i=0; i<Items.length; i++)
            {
              g.drawString(Items[i], 0, Desktop.height-20-Desktop.fontHeight*Items.length+Desktop.fontHeight*i, Graphics.LEFT | Graphics.TOP);
            }
        }
    }

    public void mouseDown(int x, int y)
    {

    }

    public void mouseUp(int x, int y)
    {

    }

    public void receiveData(String s) {

    }
}
