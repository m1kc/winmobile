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
public class App1 extends App {

    int kk=0;
    int mouse=0;

    int i;
    String[] array;

    Image disk,folder,file;

    String path = "";

    FSThread fst;
    boolean waitingFS = false;

    public App1()
    {
        
    }

    public void Init()
    {
        UseKeyboard = true;
        UseMouse = true;
        IsGraphic = true;
        IsWindowed = true;
        x = 50;
        y = 50;
        width = 150;
        height = 100;
        windowTitle = "Мой компьютер";
        
        try {
            disk = Image.createImage("/disko.png");
        } catch (Throwable ex) {
            disk = Image.createImage(16, 16);
        }

        try {
            folder = Image.createImage("/diricon.png");
        } catch (Throwable ex) {
            folder = Image.createImage(16, 16);
        }

        try {
            file = Image.createImage("/motorf.png");
        } catch (Throwable ex) {
            file = Image.createImage(16, 16);
        }

        path="";

           fst = new FSThread();
           fst.setMode(1, path);
           fst.start();
           waitingFS = true;

        array = new String[1];
        array[0]=" Чтение...";
    }

    public void keyPressed(int keyCode)
    {
        kk=keyCode;
        if (keyCode==Canvas.KEY_NUM1) {Desktop.sendData(array[0],1); }
    }

    public void keyReleased(int keyCode)
    {
        kk=0;
    }

    public void keyRepeated(int keyCode)
    {
        kk=keyCode;
    }

    public void paint(Graphics g)
    {
        if ((waitingFS)&&(!fst.isAlive())) {array = fst.array; waitingFS=false;}

        g.setColor(255,255,255);
        if (!(kk==0)) {g.drawString("Код клавиши: "+kk, 5, 5, Graphics.TOP | Graphics.LEFT);} else {g.drawString("Не нажата клавиша", 5, 5, Graphics.TOP | Graphics.LEFT);}

        for (i=0; i<array.length; i++)
        {
        if (Elements.LButton(g, array[i].substring(1), x+12, y+24+Desktop.fontHeight*i, width-24, Desktop.fontHeight, 16))
        {
           fst = new FSThread();
           path = path+array[i].substring(1);
           fst.setMode(1, path);
           fst.start();
           waitingFS = true;
        }

        g.setClip(x+12, y+24, width-24, height-36);
        if (array[i].endsWith(":/")) g.drawImage(disk, x+12, y+24+Desktop.fontHeight*i, Graphics.TOP | Graphics.LEFT);
        else if (array[i].endsWith("/")) g.drawImage(folder, x+12, y+24+Desktop.fontHeight*i, Graphics.TOP | Graphics.LEFT);
        else g.drawImage(file, x+12, y+24+Desktop.fontHeight*i, Graphics.TOP | Graphics.LEFT);
        }

        g.setClip(0, 0, Desktop.width, Desktop.height);

        //Elements.Button(g,"Кнопка",x+12,y+24,50,20);
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
