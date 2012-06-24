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
public abstract class App {

    public boolean UseKeyboard;
    public boolean UseMouse;
    public boolean IsGraphic;
    public boolean IsWindowed;
    public int x, y, width, height;
    public boolean dragMove = false;
    public boolean dragMoveC = false;
    public boolean dragResize = false;
    public String windowTitle;

    abstract public void Init();
    abstract public void keyPressed(int keyCode);
    abstract public void keyReleased(int keyCode);
    abstract public void keyRepeated(int keyCode);
    abstract public void paint(Graphics g);
    abstract public void mouseDown(int x, int y);
    abstract public void mouseUp(int x, int y);
    abstract public void receiveData(String s);

}
