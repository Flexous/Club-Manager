package Backend;

import Gui.Dialogs.*;
import Gui.MainGui;
import Objects.Club;
import Objects.Property;
import Objects.User;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import Backend.Language.LanguageHandler;

public class Application
{
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static PropertiesHandler propertiesHandler;
    private static LanguageHandler languageHandler;
    public static LoginManager loginManager = new LoginManager();
    public static RegistrationManager registrationManager = new RegistrationManager();

    public static MainGui mainGui;
    private static User currentUser;

    public static boolean offlineMode;

    public static void main(String[]args)
    {
        propertiesHandler = new PropertiesHandler(ClubManagerConstraints.PROPERTYFILEPATH);
        propertiesHandler.getAppProperties();
        
        languageHandler = new LanguageHandler(propertiesHandler.getValueFromProperty("CurrentLanguage", "App"));
        languageHandler.setLanguage();

        //loginManager.findLastLoggedInUser();

        String value = propertiesHandler.getValueFromProperty("OfflineMode", "App");

        if (value.equals("true"))
        {
            offlineMode = true;
            //propertiesHandler.getClubProperties();            

            //currentUser = new User("");
            //currentUser.setClub(ClubManagerFunctions.getClubFromProperties());
            initMainGui();
        }
        else
        {
            offlineMode = false;

            if (!propertiesHandler.getUserProperties().isEmpty())
            {
                setCurrentUser(new User(propertiesHandler.getValueFromProperty("username", "User")));
                initMainGui();
            }
            else
            {
                //still needs work
                LoginDialog loginDialog = new LoginDialog();
                loginDialog.initLoginDialog();
            }
        }
    }

    public static void initMainGui() 
    {
        if (mainGui == null)
        {
            mainGui = new MainGui("Club Manager");
            mainGui.create();
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

    public static User getCurrentUser()
    {
        return currentUser;
    }

    public static void setCurrentUser(User user)
    {
        currentUser = user;
        propertiesHandler.setUserProperties("Files/Users/"+user.getUsername()+".user");
        currentUser.setUserProperties(propertiesHandler.getUserProperties());
    }
}