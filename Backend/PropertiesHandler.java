package Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PropertiesHandler 
{
    private String filePath;
    private ArrayList<Property> foundProperties = new ArrayList<>();

    public PropertiesHandler(String filePath) 
    {
        this.filePath = filePath;
    }

    public ArrayList<Property> getFoundProperties()
    {
        return this.foundProperties;
    }

    public void findProperties()
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
                        foundProperties.add(new Property(parts[0], parts[1]));
                    }
                }
            }

            br.close();
        }
        catch (IOException e) 
        {
            Application.getLogger().warning(e.getMessage());
            e.printStackTrace();
        }
    }

    public String getValueFromProperty(String propertyName)
    {
        for (Property property : foundProperties)
        {
            if (property.getName().equals(propertyName))
            {
                return property.getValue();
            }
        }
        return null;
    }
}
