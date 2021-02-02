package Backend;

import java.io.File;
import java.io.IOException;

import Objects.Club;

public class ClubHandler 
{
    public static void addNewClub(Club club)
    {
        try 
        {
            File newClubFile = new File("");

            if (!newClubFile.exists())
            {
                newClubFile.createNewFile();
            }

            //still needs work
			//FileWriter fileWriter = new FileWriter(newClubFile);
        } 
        catch (IOException e) 
        {
            Application.getLogger().warning(e.getMessage());
		}
    }    
}
