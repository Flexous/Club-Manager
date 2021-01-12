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
                    foundProperties.add(new Property(line.split("=")[0], line.split("=")[1]));
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
