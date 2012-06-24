/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package system;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

/**
 * @author Makc
 */
public class m1kcOS extends MIDlet {

    private static Display d;
    private static Desktop c;

    public void startApp() {
        d = Display.getDisplay(this);
        c = new Desktop();
        d.setCurrent(c);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
