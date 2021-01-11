package Backend;

import Gui.MainGui;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application
{
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    static MainGui mainGui;

    public static void main(String[]args)
    {
        getConfig();
        initMainGui();
    }

    private static void getConfig() 
    {
        //
    }

    private static void initMainGui() 
    {
        if (mainGui == null)
        {
            mainGui = new MainGui("Club Manager");
        }
    }

    public static Logger getLogger()
    {
        if (logger == null)
        {
            logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            logger.setLevel(Level.ALL);
            Handler handler = new ConsoleHandler();
            handler.setLevel(Level.ALL);
            logger.addHandler(handler);
        }
        return logger;
    }
}