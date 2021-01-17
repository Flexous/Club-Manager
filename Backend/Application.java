package Backend;

import Gui.LoginDialog;
import Gui.MainGui;
import Objects.Club;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;

public class Application
{
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static PropertiesHandler propertiesHandler;
    //private static LanguageHandler languageHandler;
    private static LoginManager loginManager = new LoginManager();

    static MainGui mainGui;

    public static void main(String[]args)
    {
        /*try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());    
        } 
        catch (Exception e) 
        {
            getLogger().warning(e.getMessage());
        }*/


        propertiesHandler = new PropertiesHandler("Files/ClubManager.properties");
        propertiesHandler.findProperties();
        
        //languageHandler = new LanguageHandler(propertiesHandler.getValueFromProperty("CurrentLanguage"));
        //languageHandler.setLanguage();

        loginManager.checkLastLogin();

        if (loginManager.isLoginCompleted())
        {
            initMainGui();
        }
        else
        {
            LoginDialog loginDialog = new LoginDialog();
        }
    }

    private static void initMainGui() 
    {
        if (mainGui == null)
        {
            mainGui = new MainGui("Club Manager");
        }
    }

    public static void createNewClub(String nameOfClub)
    {
        Club club = new Club(nameOfClub);
        
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