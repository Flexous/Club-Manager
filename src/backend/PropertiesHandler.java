package backend;

import java.io.*;
import java.util.ArrayList;

import objects.Property;

public class PropertiesHandler 
{
    //Property-File methods

    public void setAppProperties(String filePath)
    {
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line = "";

            while ((line=br.readLine()) != null)
            {
                if (line.contains("="))
                {
                    String [] parts = line.split("=");

                    if (parts.length > 1)
                    {
                        ClubManagerConstraints.APP.getProperties().add(new Property(parts[0], parts[1]));
                    }
                }
            }

            br.close();
        }
        catch (IOException e) 
        {
            ClubManagerConstraints.APP.getLogger().warning(e.getMessage());
        }
    }

    public String getPropertyValue(String propertyName, String propertyType)
    {
        ArrayList<Property> tmpProperties = new ArrayList<>();

        if (propertyType.equals("App"))
        {
            tmpProperties = ClubManagerConstraints.APP.getProperties();
        }
        else if (propertyType.equals("Club"))
        {
            tmpProperties = ClubManagerConstraints.APP.getClub().getProperties();
        }
        else
        {
            return null;
        }

        for (Property property : tmpProperties)
        {
            if (property.getName().equals(propertyName))
            {
                return property.getValue();
            }
        }
        return null;
    }

    //Club-File methods

    public void findClubProperties(String filePath)
    {
        if (filePath == null)
        {
            return;
        }

        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line = "";

            while ((line=br.readLine()) != null)
            {
                if (line.contains("="))
                {
                    String [] parts = line.split("=");

                    if (parts.length > 1)
                    {
                        ClubManagerConstraints.APP.getClub().getProperties().add(new Property(parts[0], parts[1]));
                    }
                }
            }

            br.close();
        }
        catch (IOException e) 
        {
            ClubManagerConstraints.APP.getLogger().warning(e.getMessage());
        }
    }
}
