package mos;

import java.util.Enumeration;
import javax.microedition.io.Connector;
import javax.microedition.io.file.*;

class FSThread extends Thread
{
    int mode=0;
    String dest="";

    String[] array;

    public void run()
    {
        int READ=1;

        if (mode==READ)
        {
            if (dest == "")
            {
               try
               {
                 Enumeration r = FileSystemRegistry.listRoots();
                 array = Desktop.EnumerationToString(r, "");
               }
               catch (Throwable ex)
               {
                 array = new String[1];
                 array[0] = " "+ex.toString();
               }
            }
            else
            {
                try {
                    FileConnection fc = (FileConnection) Connector.open("file:///"+dest, Connector.READ);
                    Enumeration r = fc.list();
                    array = Desktop.EnumerationToString(r,"");
                    fc.close();
                } catch (Throwable ex) {
                    array = new String[1];
                    array[0] = " "+ex.toString();
                }
            }
        }

    }

    public void interrupt()
    {

    }

    public void setMode(int m, String d)
    {
        mode=m;
        dest=d;
    }
}