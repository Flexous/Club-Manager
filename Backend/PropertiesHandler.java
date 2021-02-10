package Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Objects.Property;

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
                        Application.properties.add(new Property(parts[0], parts[1]));
                    }
                }
            }

            br.close();
        }
        catch (IOException e) 
        {
            Application.getLogger().warning(e.getMessage());
        }
    }

    public String getValueFromProperty(String propertyName, String propertyType)
    {
        ArrayList<Property> tmpProperties = new ArrayList<>();

        if (propertyType.equals("App"))
        {
            tmpProperties = Application.properties;
        }
        else if (propertyType.equals("Club"))
        {
            if (Application.offlineMode)
            {
                tmpProperties = Application.getCurrentClub().getProperties();
            }
            else
            {
                tmpProperties = Application.getCurrentUser().getClub().getProperties();
            }
        }
        else if (propertyType.equals("User"))
        {
            tmpProperties = Application.getCurrentUser().getProperties();
        }
        else
        {
            //Application.getLogger().warning("");
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
                        if (Application.offlineMode)
                        {
                            Application.getCurrentClub().getProperties().add(new Property(parts[0], parts[1]));
                        }
                        else
                        {
                            Application.getCurrentUser().getClub().getProperties().add(new Property(parts[0], parts[1]));
                        }
                    }
                }
            }

            br.close();
        }
        catch (IOException e) 
        {
            Application.getLogger().warning(e.getMessage());
        }
    }

    //User Properties

    public void setUserProperties(String filePath)
    {
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(filePath));

            String line = "";

            while ((line=br.readLine()) != null)
            {
                if (line.contains("=") && !line.startsWith("#"))
                {
                    String [] parts = line.split("=");

                    if (parts.length > 1)
                    {
                        Application.getCurrentUser().getProperties().add(new Property(parts[0], parts[1]));
                    }
                }
            }

            br.close();
        }
        catch (IOException e) 
        {
            Application.getLogger().warning(e.getMessage());
        }
    }
}
