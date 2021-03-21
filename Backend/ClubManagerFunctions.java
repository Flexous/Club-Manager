package Backend;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.plaf.ColorUIResource;

import Objects.Club;
import Objects.Player;

public class ClubManagerFunctions 
{
    //App Functions
    public static void createAppFolder()
    {
        File appFolder = new File(getAppDataPath()+"/Club Manager");

        if (!appFolder.exists())
        {
            appFolder.mkdir();
        }

        File clubFolder = new File(ClubManagerConstraints.CLUBFILEPATH);

        if (!clubFolder.exists())
        {
            clubFolder.mkdir();
        }
    }

    public static void saveClubToPropertyFile()
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
                    content = content.replace(line, "CurrentClub="+Application.getCurrentClub().getName());
                    Files.write(path, content.getBytes(charset));
                    break;
                }
            }

            br.close();
        }
        catch (IOException e) 
        {
            Application.getLogger().warning(e.getMessage());
        }
    }

    public static String getAppDataPath()
    {
        return System.getenv("APPDATA").replace("\\", "/");
    }

    //Gui Functions
    public static Color getContrastColor(Color color) 
    {
        double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
        return y >= 128 ? Color.black : Color.white;
    }
    
    //Club Functions
    public static void createNewClub(Club club)
    {
        File newClubFolder = new File(ClubManagerConstraints.CLUBFILEPATH+club.getName());
        
        if (!clubFileExists(newClubFolder.getAbsolutePath()))
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
				e.printStackTrace();
			}
        }
        else
        {

        }
    }

    public static String getLastClubFile()
    {
        String clubFileName = Application.propertiesHandler.getPropertyValue("CurrentClub", "App");

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

    public static Club getClubWithProperties()
    {
        Club club = new Club();

        String name = Application.propertiesHandler.getPropertyValue("Name", "Club");

        if (name != null)
        {
            club.setName(name);
        }
        else
        {
            club.setName("Unnamed club");
        }

        String logo = Application.propertiesHandler.getPropertyValue("Logo", "Club");

        if (logo != null)
        {
            club.setLogo(logo);
        }
        else
        {
            club.setLogo(ClubManagerConstraints.DEFAULTLOGOPATH);
        }
        
        String color1 = Application.propertiesHandler.getPropertyValue("Color1", "Club");

        if (color1 != null)
        {
            String [] parts = color1.split(" ");
            
            if (parts != null)
            {
                club.setColor1(new ColorUIResource(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
            }

            String color2 = Application.propertiesHandler.getPropertyValue("Color2", "Club");

            if (color2 != null)
            {
                parts = color2.split(" ");

                if (parts != null)
                {
                    club.setColor2(new ColorUIResource(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
                }
            }
        }

        club.setProperties(Application.getCurrentClub().getProperties());

        return club;
    }

    public static boolean clubFileExists(String filePath)
    {
        return new File(filePath).exists();
    }

    public static void createNewPlayer(Player player)
    {
        File newPlayerFolder = new File(Application.getCurrentClub().getAppDataPath()+"Players/"+player.getName());

        if (!newPlayerFolder.exists())
        {
            newPlayerFolder.mkdir();
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
                e.printStackTrace();
            }
        }

        try 
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter(newPlayerFile));

            bw.write("Name="+player.getName()+"\n");

            bw.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

        Application.getCurrentClub().addPlayer(player);
    }
}
