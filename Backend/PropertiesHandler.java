package Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Objects.Property;

public class PropertiesHandler 
{
    private String filePath;
    private ArrayList<Property> appProperties = new ArrayList<>();
    private ArrayList<Property> clubProperties = new ArrayList<>();
    private ArrayList<Property> userProperties = new ArrayList<>();

    public PropertiesHandler(String filePath) 
    {
        this.filePath = filePath;
    }

    //Property-File methods

    public ArrayList<Property> getFoundProperties()
    {
        return appProperties;
    }

    public void getAppProperties()
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
                        appProperties.add(new Property(parts[0], parts[1]));
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
            tmpProperties = appProperties;
        }
        else if (propertyType.equals("Club"))
        {
            tmpProperties = clubProperties;
        }
        else if (propertyType.equals("User"))
        {
            tmpProperties = userProperties;
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

    public void getClubProperties()
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
                        clubProperties.add(new Property(parts[0], parts[1]));
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
    public ArrayList<Property> getUserProperties()
    {
        return userProperties;
    }

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
                        userProperties.add(new Property(parts[0], parts[1]));
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
