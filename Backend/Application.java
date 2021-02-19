package Backend;

import Gui.MainGui;
import Objects.Club;
import Objects.Property;

import java.util.ArrayList;
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

    public static ArrayList<Property> properties = new ArrayList<>();

    public static MainGui mainGui;
    private static Club currentClub;

    public static void main(String[]args)
    {
        propertiesHandler = new PropertiesHandler();
        propertiesHandler.setAppProperties(ClubManagerConstraints.PROPERTYFILEPATH);
        
        languageHandler = new LanguageHandler(propertiesHandler.getValueFromProperty("CurrentLanguage", "App"));
        languageHandler.setLanguage();

        String lastClubFile = ClubManagerFunctions.getLastClubFile();

        if (lastClubFile != null)
        {
            currentClub = new Club();
            propertiesHandler.findClubProperties(lastClubFile);
            currentClub = ClubManagerFunctions.getClubWithProperties();
        }

        initMainGui();
    }

    public static void initMainGui() 
    {
        if (mainGui == null)
        {
            mainGui = new MainGui("Club Manager");
            mainGui.create();
        }
    }

    public static void closeMainGui()
    {
        mainGui.dispose();
        mainGui = null;
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

    public static Club getCurrentClub()
    {
        return currentClub;
    }

    public static void setCurrentClub(Club club)
    {
        currentClub = club;
    }
}