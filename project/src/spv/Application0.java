//********************************************************************/
//* Copyright (C) 2017                                               */
//* FILIP CERNATESCU : milpgame at yahoo.com                         */
//* License terms: MIT-License 	                                     */
//********************************************************************/

package spv;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class Application0 extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
       frame0=new MainWindow(this);
       show(frame0);
       System.out.println(
     getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
       System.out.println(System.getProperty("user.dir"));
       path=System.getProperty("user.dir");
    

    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of Application0
     */
    public static Application0 getApplication() {
        return Application.getInstance(Application0.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(Application0.class, args);

    }


    public MainWindow frame0=null;
    /*demonstration mode 1 essential 2 full*/
    public int demonstrationDisplayMode=1;
    public SampleGUI3 sample3=null;
    public SampleGUI2 sample2=null;
    public SampleGUI1 sample1=null;
    public String path="";
}