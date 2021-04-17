package backend;

import gui.MainGui;
import objects.*;

import java.awt.*;
import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.logging.*;

import javax.swing.JOptionPane;
import javax.swing.plaf.ColorUIResource;

public class Application
{
    private Logger logger;
    private PropertiesHandler propertiesHandler;

    private ArrayList<Property> properties = new ArrayList<>();

    private MainGui mainGui;
    private Club club;

    public static void main(String[]args)
    {
        ClubManagerConstraints.APP.getLogger().info("Club Manager was started.");
        new Application().init();
    }

    public void init()
    {
        createAppFolder();

        ClubManagerConstraints.APP.propertiesHandler = new PropertiesHandler();
        ClubManagerConstraints.APP.propertiesHandler.setAppProperties(ClubManagerConstraints.PROPERTYFILEPATH);

        ClubManagerConstraints.LANGUAGE.getLanguageProperties(ClubManagerConstraints.APP.propertiesHandler.getPropertyValue("CurrentLanguage", "App"));

        String lastClubFile = getLastClubFile();

        if (lastClubFile != null)
        {
            ClubManagerConstraints.APP.club = new Club();
            ClubManagerConstraints.APP.propertiesHandler.findClubProperties(lastClubFile);
            ClubManagerConstraints.APP.club = getClubWithProperties();
            ClubManagerConstraints.APP.club.setAppDataPath(ClubManagerConstraints.CLUBFILEPATH+ClubManagerConstraints.APP.club.getName()+"/");
            
            ClubManagerConstraints.APP.club.findAllPlayers();
        }

        initMainGui();
    }

    public ArrayList<Property> getProperties()
    {
        return ClubManagerConstraints.APP.properties;
    }

    public MainGui getMainGui()
    {
        return ClubManagerConstraints.APP.mainGui;
    }

    public void initMainGui() 
    {
        if (ClubManagerConstraints.APP.mainGui == null)
        {
            ClubManagerConstraints.APP.mainGui = new MainGui();
            ClubManagerConstraints.APP.mainGui.init();
        }
    }

    public void closeMainGui()
    {
        ClubManagerConstraints.APP.mainGui.dispose();
        ClubManagerConstraints.APP. mainGui = null;
    }

    public Logger getLogger()
    {
        if (ClubManagerConstraints.APP.logger == null)
        {
            try 
            {
                ClubManagerConstraints.APP.logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
                ClubManagerConstraints.APP.logger.setLevel(Level.ALL);

                File logFolder = new File(getAppDataPath()+"/Club Manager/log");

                if (!logFolder.exists())
                {
                    logFolder.mkdir();
                }

                FileHandler fh = new FileHandler(logFolder+"/CM_" + System.currentTimeMillis() +".log");
                SimpleFormatter formatter = new SimpleFormatter();  
                fh.setFormatter(formatter);   
                ClubManagerConstraints.APP.logger.addHandler(fh);
            } 
            catch (SecurityException e) 
            {
                ClubManagerConstraints.APP.getLogger().warning(e.getMessage());
            } 
            catch (IOException e) 
            {
                ClubManagerConstraints.APP.getLogger().warning(e.getMessage());
            }  

        }
        return ClubManagerConstraints.APP.logger;
    }

    public PropertiesHandler getPropertiesHandler()
    {
        return ClubManagerConstraints.APP.propertiesHandler;
    }

    public Club getClub()
    {
        return ClubManagerConstraints.APP.club;
    }

    public void setClub(Club club)
    {
        ClubManagerConstraints.APP.club = club;
    }

    public void createAppFolder()
    {
        File appFolder = new File(getAppDataPath()+"/Club Manager");

        if (!appFolder.exists())
        {
            appFolder.mkdir();
        }
        
        File propertyFile = new File(appFolder+"/ClubManager.properties");
        
        if (!propertyFile.exists())
        {
        	try 
        	{
				propertyFile.createNewFile();
				
				BufferedWriter fileWriter = new BufferedWriter(new FileWriter(propertyFile));
				
				fileWriter.write("DefaultLanguage=ENG\n");
				fileWriter.write("CurrentLanguage=GER\n");
				fileWriter.write("CurrentClub=\n");
				
				fileWriter.close();
			} 
        	catch (IOException e) 
        	{
                ClubManagerConstraints.APP.getLogger().warning(e.getMessage());
			}
        }
        
        File clubFolder = new File(ClubManagerConstraints.CLUBFILEPATH);

        if (!clubFolder.exists())
        {
            clubFolder.mkdir();
        }
        
    }

    public String getAppDataPath()
    {
        return System.getenv("APPDATA").replace("\\", "/");
    }

    public void saveClubToPropertyFile()
    {
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(ClubManagerConstraints.PROPERTYFILEPATH));

            String line = "";

            while ((line=br.readLine()) != null)
            {
                if (line.contains("=") && line.startsWith("CurrentClub"))
                {
                    Path path = Paths.get(ClubManagerConstraints.PROPERTYFILEPATH);
                    Charset charset = StandardCharsets.UTF_8;
                    String content = new String(Files.readAllBytes(path), charset);
                    content = content.replace(line, "CurrentClub="+ClubManagerConstraints.APP.getClub().getName());
                    Files.write(path, content.getBytes(charset));
                    break;
                }
            }

            br.close();
        }
        catch (IOException e) 
        {
            ClubManagerConstraints.APP.getLogger().warning(e.getMessage());
        }
    }

    public Color getContrastColor(Color color) 
    {
        double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
        return y >= 128 ? Color.black : Color.white;
    }

    public void createNewClub(Club club)
    {
        File newClubFolder = new File(ClubManagerConstraints.CLUBFILEPATH+club.getName());
        
        if (!newClubFolder.exists())
        {
            club.setAppDataPath(newClubFolder.getAbsolutePath());

            File newClubFile = new File(newClubFolder.getAbsolutePath()+"/"+club.getName()+".club");

            try 
            {
                newClubFolder.mkdir();
				newClubFile.createNewFile();

                File playerFolder = new File(newClubFolder.getAbsolutePath()+"/Players");

                if (!playerFolder.exists())
                {
                    playerFolder.mkdir();
                }

                BufferedWriter bw = new BufferedWriter(new FileWriter(newClubFile));
            
                bw.write("Name="+club.getName()+"\n");
                bw.write("Logo="+club.getLogo()+"\n");

                if (club.getColor1() != null)
                {
                    bw.write("Color1="+club.getColor1().getRed()+ " " +club.getColor1().getGreen()+ " " +club.getColor1().getBlue()+"\n");
                }
                else
                {
                    bw.write("Color1=\n");
                }

                if (club.getColor2() != null)
                {
                    bw.write("Color2="+club.getColor2().getRed()+ " " +club.getColor2().getGreen()+ " " +club.getColor2().getBlue()+"\n");
                }
                else
                {
                    bw.write("Color2=\n");
                }

                bw.close();
			} 
            catch (IOException e) 
            {
				ClubManagerConstraints.APP.getLogger().warning(e.getMessage());
			}
        }
        else
        {

        }
    }

    public String getLastClubFile()
    {
        String clubFileName = ClubManagerConstraints.APP.getPropertiesHandler().getPropertyValue("CurrentClub", "App");

        if (clubFileName != null)
        {
            if (new File(ClubManagerConstraints.CLUBFILEPATH+clubFileName+"/"+clubFileName+".club").exists())
            {
                return ClubManagerConstraints.CLUBFILEPATH+clubFileName+"/"+clubFileName+".club";
            }
        }

        File directory = new File(ClubManagerConstraints.CLUBFILEPATH);

        String[] directories = directory.list(new FilenameFilter() 
        {
            @Override
            public boolean accept(File current, String name) 
            {
              return new File(current, name).isDirectory();
            }
        });

        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;
    
        if (directories != null)
        {
            for (String folderName : directories)
            {
                File file = new File(ClubManagerConstraints.CLUBFILEPATH+folderName);

                if (file.lastModified() > lastModifiedTime)
                {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }

        if (chosenFile != null)
        {
            return chosenFile.getAbsolutePath()+"/"+chosenFile.getName()+".club";
        }
        return null;
    }

    public Club getClubWithProperties()
    {
        Club club = new Club();

        String name = ClubManagerConstraints.APP.getPropertiesHandler().getPropertyValue("Name", "Club");

        if (name != null)
        {
            club.setName(name);
        }
        else
        {
            club.setName("Unnamed club");
        }

        String logo = ClubManagerConstraints.APP.getPropertiesHandler().getPropertyValue("Logo", "Club");

        if (logo != null)
        {
            club.setLogo(logo);
        }
        else
        {
            club.setLogo(ClubManagerConstraints.DEFAULTLOGOPATH);
        }
        
        String color1 = ClubManagerConstraints.APP.getPropertiesHandler().getPropertyValue("Color1", "Club");

        if (color1 != null)
        {
            String [] parts = color1.split(" ");
            
            if (parts != null)
            {
                club.setColor1(new ColorUIResource(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
            }

            String color2 = ClubManagerConstraints.APP.getPropertiesHandler().getPropertyValue("Color2", "Club");

            if (color2 != null)
            {
                parts = color2.split(" ");

                if (parts != null)
                {
                    club.setColor2(new ColorUIResource(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
                }
            }
        }

        club.setProperties(ClubManagerConstraints.APP.getClub().getProperties());

        return club;
    }

    public void createNewPlayer(Player player)
    {
        File newPlayerFolder = new File(ClubManagerConstraints.APP.getClub().getAppDataPath()+"/Players/#"+
        player.getNumber() + " - " + player.getName());

        if (!newPlayerFolder.exists())
        {
            newPlayerFolder.mkdir();
        }
        else
        {
            Object[] options = {ClubManagerConstraints.LANGUAGE.getString("Yes"), ClubManagerConstraints.LANGUAGE.getString("No")};

            int selection = JOptionPane.showOptionDialog(null, ClubManagerConstraints.LANGUAGE.getString("PlayerAlreadyExists"), "", 
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (selection == JOptionPane.YES_OPTION)
            {
                saveClubToPropertyFile();
            }
            else
            {
                return;
            }
        }

        File newPlayerFile = new File(newPlayerFolder+"/"+player.getName()+".player");

        if (!newPlayerFile.exists())
        {
            try 
            {
                newPlayerFile.createNewFile();
            } 
            catch (IOException e) 
            {
                ClubManagerConstraints.APP.getLogger().warning(e.getMessage());
            }
        }

        try 
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(newPlayerFile));
            bw.write("Name="+player.getName()+"\n");
            bw.write("Number="+player.getNumber()+"\n");
            bw.close();
        } 
        catch (IOException e) 
        {
            ClubManagerConstraints.APP.getLogger().warning(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ClubManagerConstraints.APP.getClub().addPlayer(player);
        ClubManagerConstraints.APP.getLogger().info("Player with name " + player.getName() + " and number " + player.getNumber());
    }

    public void savePlayer(Player player)
    {
        ClubManagerConstraints.APP.getClub().updatePlayer(player);
    }
}