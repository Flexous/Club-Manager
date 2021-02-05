package Backend;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import Objects.Club;

public class ClubManagerFunctions 
{
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
			} 
            catch (IOException e) 
            {
				e.printStackTrace();
			}
        }
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
