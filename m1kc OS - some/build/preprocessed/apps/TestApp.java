/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package apps;

import api.WindowedApplication;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author Makc
 */
public class TestApp extends WindowedApplication
{
    public TestApp()
    {
        super();
        Thread t = new Thread(){
            public void run()
            {
                while(true)
                {
                    Graphics g = buffer.getGraphics();
                    g.setColor(0xFFFFFF);
                    g.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
                    g.setColor(0x000000);
                    g.drawString("LALALA", 0, 5, 20);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        t.start();
    }
}
