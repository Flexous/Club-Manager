package Backend;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import Objects.Club;

public class ClubManagerFunctions 
{
    //App Functions

    //Gui Functions
    public static Color getContrastColor(Color color) 
    {
        double y = (299 * color.getRed() + 587 * color.getGreen() + 114 * color.getBlue()) / 1000;
        return y >= 128 ? Color.black : Color.white;
    }
    
    //Club Functions
    public static void createNewClubFile(Club club)
    {
        File newClubFile = new File(ClubManagerConstraints.CLUBFILEPATH+club.getName()+".club");

        if (!clubFileExists(newClubFile.getAbsolutePath()))
        {
            try 
            {
				newClubFile.createNewFile();

                BufferedWriter bw = new BufferedWriter(new FileWriter(newClubFile));
            
                bw.write("Name="+club.getName()+"\n");
                bw.write("Logo="+club.getLogoSrc()+"\n");
                bw.write("Color1="+club.getColor1().getRed()+club.getColor1().getGreen()+club.getColor1().getBlue()+"\n");
                bw.write("Color2="+club.getColor2().getRGB()+"\n");

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

    public static Club getClubFromProperties()
    {
        Club club = new Club();
        club.setName(Application.propertiesHandler.getValueFromProperty("Name", "Club"));
        club.setLogoSrc(Application.propertiesHandler.getValueFromProperty("Logo", "Club"));

        return club;
    }

    public static boolean clubFileExists(String filePath)
    {
        if (new File(filePath).exists())
        {
            return true;
        }

        return false;
    }
}
