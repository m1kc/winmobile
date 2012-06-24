/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mos;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.lcdui.*;

/**
 * @author Makc
 */
public class Desktop extends Canvas implements CommandListener {

    public static int x;
    public static int y;

    boolean rUp=false;
    boolean lUp=false;
    boolean uUp=false;
    boolean dUp=false;

    /*App1 app1 = new App1();
    App2 app2 = new App2();
    App3 app3 = new App3();*/

    public static boolean MouseDown = false;

    int c;
    static int appsNum=5;
    static App[] apps = new App[20];
    int upperApp=0;

    public static int width;
    public static int height;

    public static Image fon,cursor,down;
    Image w_down,w_up,w_left,w_right,w_upleft,w_downleft,w_upright,w_downright;

    String exc;

    public static Font nameFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
    public static int fontHeight = nameFont.getHeight();

    /**
     * constructor
     */
    public Desktop() {
	    // Set up this canvas to listen to command events
	    setCommandListener(this);
	    // Add the Exit command
	    // addCommand(new Command("Exit", Command.EXIT, 1));
        this.setFullScreenMode(true);
        x=getWidth()/2;
        y=getHeight()/2;
        width=getWidth();
        height=getHeight();

        exc="Ошибок не возникло";

        try {
            fon = Image.createImage("/55zzz.png");
        } catch (Throwable ex) {
            ex.printStackTrace();
            fon = Image.createImage(width, height);
            exc = ex.toString();
        }

        try {
            cursor = Image.createImage("/wwwcurs3.png");
        } catch (Throwable ex) {
            ex.printStackTrace();
            cursor = Image.createImage(5, 5);
            exc = ex.toString();
        }

        try {
            down = Image.createImage("/140zz.png");
        } catch (Throwable ex) {
            ex.printStackTrace();
            down = Image.createImage(5, 5);
            exc = ex.toString();
        }
        down = Resize.resize_image(down, getWidth(), 20);

        try {
            w_down = Image.createImage("/vistawin_down.png");
        } catch (Throwable ex) {
            ex.printStackTrace();
            w_down = Image.createImage(12, 12);
            exc = ex.toString();
        }
        
        try {
            w_up = Image.createImage("/vistawin_up.png");
        } catch (Throwable ex) {
            ex.printStackTrace();
            w_up = Image.createImage(12, 12);
            exc = ex.toString();
        }

        try {
            w_left = Image.createImage("/vistawin_left.png");
        } catch (Throwable ex) {
            ex.printStackTrace();
            w_left = Image.createImage(12, 12);
            exc = ex.toString();
        }

        try {
            w_right = Image.createImage("/vistawin_right.png");
        } catch (Throwable ex) {
            ex.printStackTrace();
            w_right = Image.createImage(12, 12);
            exc = ex.toString();
        }

        try {
            w_upleft = Image.createImage("/vistawin_leftup.png");
        } catch (Throwable ex) {
            ex.printStackTrace();
            w_upleft = Image.createImage(12, 12);
            exc = ex.toString();
        }

        try {
            w_downleft = Image.createImage("/vistawin_leftdown.png");
        } catch (Throwable ex) {
            ex.printStackTrace();
            w_downleft = Image.createImage(12, 12);
            exc = ex.toString();
        }

        try {
            w_upright = Image.createImage("/vistawin_rightup.png");
        } catch (Throwable ex) {
            ex.printStackTrace();
            w_upright = Image.createImage(12, 12);
            exc = ex.toString();
        }

        try {
            w_downright = Image.createImage("/vistawin_rightdown.png");
        } catch (Throwable ex) {
            ex.printStackTrace();
            w_downright = Image.createImage(12, 12);
            exc = ex.toString();
        }

        

        if (appsNum>=1) { apps[0] = new App1(); }
        if (appsNum>=2) { apps[1] = new App2(); }
        if (appsNum>=3) { apps[2] = new App3(); }
        if (appsNum>=4) { apps[3] = new App4(); }
        if (appsNum>=5) { apps[4] = new App5(); }

        for (c=0; c<appsNum; c++)
        {
        if (appsNum>=c+1) { apps[c].Init(); }
        }

    } 

    public static String[] EnumerationToString(Enumeration enumeration, String s)
    {
        Vector vector = new Vector();
        for (; enumeration.hasMoreElements(); vector.addElement(enumeration.nextElement())) { }
        String as[] = new String[vector.size()];
        for (int i = 0; i < vector.size(); i++)
        {
            as[i] = (s.length() != 0 ? s : "/") + vector.elementAt(i);
        }

        return as;
    }

    public static void sendData(String data, int destination)
    {
        if (destination<appsNum)
        {
            apps[destination].receiveData(data);
        }
    }

    private void drawWindow(Graphics g, int n) // n - номер приложения
    {
        if (apps[n].dragMove) {apps[n].x=x-6; apps[n].y=y-6; }
        if (apps[n].dragMoveC) {apps[n].x=x-apps[n].width/2; apps[n].y=y-6; }
        if (apps[n].dragResize) {apps[n].width=x-apps[n].x+6; apps[n].height=y-apps[n].y+6; }

        g.setColor(0xffffff);
        g.fillRect(apps[n].x+12, apps[n].y+24, apps[n].width-24, apps[n].height-36);

        g.setClip(apps[n].x, apps[n].y, apps[n].width, apps[n].height);

        g.drawImage(w_upleft, apps[n].x, apps[n].y, Graphics.TOP | Graphics.LEFT);
        g.drawImage(w_downleft, apps[n].x, apps[n].y+apps[n].height-12, Graphics.TOP | Graphics.LEFT);
        g.drawImage(w_upright, apps[n].x+apps[n].width-12, apps[n].y, Graphics.TOP | Graphics.LEFT);
        g.drawImage(w_downright, apps[n].x+apps[n].width-12, apps[n].y+apps[n].height-12, Graphics.TOP | Graphics.LEFT);

        int i;

        g.setClip(apps[n].x+12, apps[n].y, apps[n].width-24, apps[n].height);
        for (i=apps[n].x+12; i<=apps[n].x+apps[n].width-12; i=i+2)
        {
            g.drawImage(w_up, i, apps[n].y, Graphics.TOP | Graphics.LEFT);
            g.drawImage(w_down, i, apps[n].y+apps[n].height-12, Graphics.TOP | Graphics.LEFT);
        }

        g.setClip(apps[n].x, apps[n].y+12, apps[n].width, apps[n].height-24);
        for (i=apps[n].y+24; i<=apps[n].y+apps[n].height-12; i=i+2)
        {
            g.drawImage(w_left, apps[n].x, i, Graphics.TOP | Graphics.LEFT);
            g.drawImage(w_right, apps[n].x+apps[n].width-12, i, Graphics.TOP | Graphics.LEFT);
        }

        g.setClip(apps[n].x, apps[n].y, apps[n].width, apps[n].height);
        
        g.setColor(0, 0, 0);
        g.drawString(apps[n].windowTitle, apps[n].x+12, apps[n].y+12-nameFont.getHeight()/2, Graphics.TOP | Graphics.LEFT);

        g.setClip(0, 0, getWidth(), getHeight());

    }

    /**
     * paint
     */
    protected void paint(Graphics g) {

        g.setFont(nameFont);

        if (lUp) {x=x-1;}
        if (rUp) {x=x+1;}
        if (dUp) {y=y+1;}
        if (uUp) {y=y-1;}

        g.setColor(0,128,128);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(fon, 0, 0, Graphics.TOP | Graphics.LEFT);

        /*if (app1.IsGraphic) {app1.paint(g);}
        if (app2.IsGraphic) {app2.paint(g);}
        if (app3.IsGraphic) {app3.paint(g);}*/

        for (c=0; c<appsNum; c++)
        {
        if ((appsNum>=c+1)&&(c!=upperApp)) { if (apps[c].IsWindowed) { drawWindow(g, c);} }
        if ((appsNum>=c+1)&&(c!=upperApp)) { if (apps[c].IsGraphic)  { apps[c].paint(g);} }
        }

        if (apps[upperApp].IsWindowed) { drawWindow(g, upperApp);}
        if (apps[upperApp].IsGraphic)  { apps[upperApp].paint(g);}

        //g.setColor(0,128,255);
        //g.fillRect(0, getHeight()-20, getWidth(), 20);
        //g.setColor(255,255,255);
        //g.fillTriangle(x, y, x, y+15, x+8, y+10);
        g.drawImage(down, 0, getHeight()-20, Graphics.TOP | Graphics.LEFT);
        g.drawImage(cursor, x, y, Graphics.TOP | Graphics.LEFT);

        repaint();
    }

    /**
     * Called when a key is pressed.
     */
    protected  void keyPressed(int keyCode) {

        if (keyCode == -4) {rUp=true;}
        if (keyCode == -3) {lUp=true;}
        if (keyCode == -2) {dUp=true;}
        if (keyCode == -1) {uUp=true;}
        if (keyCode == KEY_NUM6) {rUp=true;}
        if (keyCode == KEY_NUM4) {lUp=true;}
        if (keyCode == KEY_NUM8) {dUp=true;}
        if (keyCode == KEY_NUM2) {uUp=true;}

        for (c=0; c<appsNum; c++)
        {
        if (appsNum>=c+1) { if (apps[c].UseKeyboard) {apps[c].keyPressed(keyCode);} }
        }

        if ((keyCode==-5) || (keyCode==53))
        {
            for (c=0; c<appsNum; c++)
            {
            if (appsNum>=c+1) { if (apps[c].UseMouse) {apps[c].mouseDown(x,y);} }
            if (appsNum>=c+1) { if ((apps[c].IsWindowed)&&(x>apps[c].x)&&(y>apps[c].y)&&(x<apps[c].x+12)&&(y<apps[c].y+24) ) {apps[c].dragMove=!apps[c].dragMove;} }
            if (appsNum>=c+1) { if ((apps[c].IsWindowed)&&(x>apps[c].x+apps[c].width-12)&&(y>apps[c].y+apps[c].height-12)&&(x<apps[c].x+apps[c].width)&&(y<apps[c].y+apps[c].height) ) {apps[c].dragResize=!apps[c].dragResize;} }
            if (appsNum>=c+1) { if ((apps[c].IsWindowed)&&(x>apps[c].x)&&(y>apps[c].y)&&(x<apps[c].x+apps[c].width)&&(y<apps[c].y+24) ) {upperApp=c;} }
            }

            MouseDown=true;
        }

    }
    
    /**
     * Called when a key is released.
     */
    protected  void keyReleased(int keyCode) {

        if (keyCode==-4) {rUp=false;}
        if (keyCode==-3) {lUp=false;}
        if (keyCode==-2) {dUp=false;}
        if (keyCode==-1) {uUp=false;}
        if (keyCode==KEY_NUM6) {rUp=false;}
        if (keyCode==KEY_NUM4) {lUp=false;}
        if (keyCode==KEY_NUM8) {dUp=false;}
        if (keyCode==KEY_NUM2) {uUp=false;}

        for (c=0; c<appsNum; c++)
        {
        if (appsNum>=c+1) { if (apps[c].UseKeyboard) {apps[c].keyReleased(keyCode);} }
        }

        if (keyCode==42) {
            appsNum++;
            apps[appsNum-1] = new App1();
            apps[appsNum-1].x=apps[appsNum-1].x+20;
            apps[appsNum-1].y=apps[appsNum-1].y+20;
            apps[appsNum-1].Init();
        }

        if ((keyCode==-5) || (keyCode==53))
        {
            for (c=0; c<appsNum; c++)
            {
            if (appsNum>=c+1) { if (apps[c].UseMouse) {apps[c].mouseUp(x,y);} }
            }

            MouseDown=false;
        }

    }

    /**
     * Called when a key is repeated (held down).
     */
    protected  void keyRepeated(int keyCode) {
        if (keyCode==-4) {rUp=true;}
        if (keyCode==-3) {lUp=true;}
        if (keyCode==-2) {dUp=true;}
        if (keyCode==-1) {uUp=true;}
        if (keyCode==KEY_NUM6) {rUp=true;}
        if (keyCode==KEY_NUM4) {lUp=true;}
        if (keyCode==KEY_NUM8) {dUp=true;}
        if (keyCode==KEY_NUM2) {uUp=true;}

        for (c=0; c<appsNum; c++)
        {
        if (appsNum>=c+1) { if (apps[c].UseKeyboard) {apps[c].keyRepeated(keyCode);} }
        }
    }
    
    /**
     * Called when the pointer is dragged.
     */
    protected  void pointerDragged(int x, int y) {
        Desktop.x=x;
        Desktop.y=y;
    }

    /**
     * Called when the pointer is pressed.
     */
    protected  void pointerPressed(int x, int y) {
        Desktop.x=x;
        Desktop.y=y;

        for (c=0; c<appsNum; c++)
            {
            if (appsNum>=c+1) { if (apps[c].UseMouse) {apps[c].mouseDown(x,y);} }
            if (appsNum>=c+1) { if ((apps[c].IsWindowed)&&(x>apps[c].x)&&(y>apps[c].y)&&(x<apps[c].x+apps[c].width)&&(y<apps[c].y+24) ) {apps[c].dragMoveC=!apps[c].dragMoveC; upperApp=c;} }
            if (appsNum>=c+1) { if ((apps[c].IsWindowed)&&(x>apps[c].x+apps[c].width-12)&&(y>apps[c].y+apps[c].height-12)&&(x<apps[c].x+apps[c].width)&&(y<apps[c].y+apps[c].height) ) {apps[c].dragResize=!apps[c].dragResize;} }
            }
    }

    /**
     * Called when the pointer is released.
     */
    protected  void pointerReleased(int x, int y) {
        Desktop.x=x;
        Desktop.y=y;

        for (c=0; c<appsNum; c++)
            {
            if (appsNum>=c+1) { if (apps[c].UseMouse) {apps[c].mouseUp(x,y);} }
            if (appsNum>=c+1) { if ((apps[c].IsWindowed)&&(x>apps[c].x)&&(y>apps[c].y)&&(x<apps[c].x+apps[c].width)&&(y<apps[c].y+24) ) {apps[c].dragMoveC=!apps[c].dragMoveC;} }
            if (appsNum>=c+1) { if ((apps[c].IsWindowed)&&(x>apps[c].x+apps[c].width-12)&&(y>apps[c].y+apps[c].height-12)&&(x<apps[c].x+apps[c].width)&&(y<apps[c].y+apps[c].height) ) {apps[c].dragResize=!apps[c].dragResize;} }
            }
    }
    
    /**
     * Called when action should be handled
     */
    public void commandAction(Command command, Displayable displayable) {
    }

}
