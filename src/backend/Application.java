package backend;

import gui.MainGui;
import objects.*;

import java.awt.*;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.*;

import backend.language.LanguageHandler;

public class Application
{
    private Logger logger;
    private PropertiesHandler propertiesHandler;
    private LanguageHandler language;

    private DbConnection dbConnection;
    private ArrayList<Property> properties = new ArrayList<>();

    private MainGui mainGui;
    private Club club;

    public static void main(String[]args)
    {
        new Application().init();
    }

    public void init()
    {
        createAppFolder();
        getLogger().info("Club Manager is starting...");

        propertiesHandler = new PropertiesHandler(this);
        propertiesHandler.loadPropertiesFromDb();

        getLanguage().loadLanguageFromDb(propertiesHandler.getPropertyValue("Language"));

        loadClubFromDb();

        initMainGui();
    }

    public LanguageHandler getLanguage()
    {
        if (language == null)
        {
            language = new LanguageHandler(this);
        }
        return language;
    }

    public DbConnection getDbConnection()
    {
        if (dbConnection == null)
        {
            dbConnection = new DbConnection(this);
        }
        return dbConnection;
    }

    public ArrayList<Property> getProperties()
    {
        return properties;
    }

    public MainGui getMainGui()
    {
        return mainGui;
    }

    public void initMainGui() 
    {
        if (mainGui == null)
        {
            mainGui = new MainGui(this);
            mainGui.init();
        }
    }

    public void closeMainGui()
    {
        mainGui.dispose();
        mainGui = null;
    }

    public Logger getLogger()
    {
        if (logger == null)
        {
            try 
            {
                logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
                logger.setLevel(Level.ALL);

                File logFolder = new File(getAppDataPath()+"/Club Manager/log");

                if (!logFolder.exists())
                {
                    logFolder.mkdir();
                    getLogger().info("Log directory created...");
                }

                FileHandler fh = new FileHandler(logFolder+"/CM_" + System.currentTimeMillis() +".log");
                SimpleFormatter formatter = new SimpleFormatter();  
                fh.setFormatter(formatter);   
                logger.addHandler(fh);
            } 
            catch (SecurityException e) 
            {
                getLogger().warning(e.getMessage());
            } 
            catch (IOException e) 
            {
                getLogger().warning(e.getMessage());
            }  

        }
        return logger;
    }

    public PropertiesHandler getPropertiesHandler()
    {
        return propertiesHandler;
    }

    public Club getClub()
    {
        return club;
    }

    public void setClub(Club club)
    {
        this.club = club;
    }

    public void createAppFolder()
    {
        File appFolder = new File(getAppDataPath()+"/Club Manager");

        if (!appFolder.exists())
        {
            appFolder.mkdir();
            getLogger().info("App directory created...");
        }
    }

    public String getAppDataPath()
    {
        return System.getenv("APPDATA").replace("\\", "/");
    }

    public void loadClubFromDb()
    {
        getDbConnection().establish();

        try 
        {         
            String sql = "select * from Clubs order by lastupdatetime desc limit 1";  

            Statement stmt = getDbConnection().get().createStatement();  
            ResultSet rs = stmt.executeQuery(sql);  
               
            Club club = new Club();

            while (rs.next())  
            {  
                club.setName(rs.getString("name"));

                String [] color1Values = rs.getString("color1").split(" ");
                club.setColor1(new Color(Integer.parseInt(color1Values[0]), Integer.parseInt(color1Values[1]), Integer.parseInt(color1Values[2])));

                if (rs.getString("color2") != null)
                {
                    String [] color2Values = rs.getString("color2").split(" ");
                    club.setColor2(new Color(Integer.parseInt(color2Values[0]), Integer.parseInt(color2Values[1]), Integer.parseInt(color2Values[2])));                    
                }

                if (rs.getString("color3") != null)
                {
                    String [] color3Values = rs.getString("color3").split(" ");
                    club.setColor3(new Color(Integer.parseInt(color3Values[0]), Integer.parseInt(color3Values[1]), Integer.parseInt(color3Values[2])));
                }

                club.setLastUpdateTime(rs.getString("lastupdatetime"));

                club.setLogo(rs.getString("logopath"));
            }

            if (!club.getName().isEmpty())
            {
                setClub(club);
                getLogger().info("Club with name " + club.getName() + " was loaded from the database.");
            }
            
            getDbConnection().close();
        } 
        catch (SQLException e) 
        {  
            getLogger().warning(e.getMessage());
        }
    }

    public void addClubToDb()
    {
        getDbConnection().establish();

        String sql = "insert into Clubs VALUES(null, ?, ?, ?, ?, ?, ?)";  
   
        try
        {  
            PreparedStatement pstmt = getDbConnection().get().prepareStatement(sql);  
            pstmt.setString(1, getClub().getName());

            pstmt.setString(2, getClub().getColor1().getRed() + " " + getClub().getColor1().getGreen() + " " + getClub().getColor1().getBlue());
            pstmt.setString(3, getClub().getColor2().getRed() + " " + getClub().getColor2().getGreen() + " " + getClub().getColor2().getBlue());
            pstmt.setString(4, getClub().getColor3().getRed() + " " + getClub().getColor3().getGreen() + " " + getClub().getColor3().getBlue());

            String timeStamp = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss:ms").format(new Date());
            pstmt.setString(5, timeStamp);
            pstmt.setString(6, getClub().getLogo());

            pstmt.executeUpdate();
            getLogger().info("Club with name " + getClub().getName() + " was added to the database.");
        } 
        catch (SQLException e) 
        {  
            getLogger().warning(e.getMessage());
        }  

        getDbConnection().close();
    }

    public Color getContrastColor(Color color) 
    {
        double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
        return y >= 128 ? Color.black : Color.white;
    }

    public Club getClubWithProperties()
    {
        club.setProperties(getClub().getProperties());

        return club;
    }

    public void loadPlayersFromDb()
    {
        getDbConnection().establish();

        try 
        {         
            String sql = "select * from Players where club = '" + getClub().getName()+"'";  

            Statement stmt = getDbConnection().get().createStatement();  
            ResultSet rs = stmt.executeQuery(sql);  
               
            while (rs.next())  
            {  
                Player player = new Player();
                
                player.setFirstname(rs.getString("firstname"));
                player.setLastname(rs.getString("lastname"));
                player.setClub(getClub());
                player.setNumber(rs.getInt("number"));
                player.setDateOfBirth(rs.getString("dateofbirth"));

                getClub().addPlayer(player);
            }
            
            getLogger().info(getClub().getPlayers().size() + " players were loaded from the database.");
            getDbConnection().close();
        } 
        catch (SQLException e) 
        {  
            getLogger().warning(e.getMessage());
        }
    }

    public void addNewPlayerToDb(Player player)
    {
        getDbConnection().establish();

        String sql = "insert into Players VALUES(null, ?, ?, ?, ?, ?)";  
   
        try
        {  
            PreparedStatement pstmt = getDbConnection().get().prepareStatement(sql);  
            pstmt.setString(1, player.getFirstname());
            pstmt.setString(2, player.getLastname());
            pstmt.setString(3, player.getClub().getName());
            pstmt.setInt(4, player.getNumber());
            pstmt.setString(5, player.getDateOfBirth());

            pstmt.executeUpdate();
            getLogger().info("Player with name " + player.getFirstname() + " " + player.getLastname() + " and number " + player.getNumber() + " was added to the database.");
        } 
        catch (SQLException e) 
        {  
            getLogger().warning(e.getMessage());
        }  

        getDbConnection().close();
    }

    public void deletePlayer(Player player)
    {
        getDbConnection().establish();

        String sql = "delete from Players where id = " + player.getId();

        try
        {  
            PreparedStatement pstmt = getDbConnection().get().prepareStatement(sql);  
            pstmt.executeUpdate();
            getLogger().info("Player with name " + player.getFirstname() + " " + player.getLastname() + " was removed from the database.");
        } 
        catch (SQLException e) 
        {  
            getLogger().warning(e.getMessage());
        }  

        getDbConnection().close();
    }

    public void savePlayer(Player player)
    {
        getClub().updatePlayer(player);
    }
}