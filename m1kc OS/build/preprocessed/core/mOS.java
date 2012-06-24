/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author m1kc
 */
public class mOS extends MIDlet {

    public static Display display;
    public static Desktop desktop;
    public static Form debugForm;

    public void startApp() {
        display = Display.getDisplay(this);
        desktop = new Desktop();
        debugForm = new Form(null);
        display.setCurrent(desktop);
        new Thread(desktop).start();
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
