package backend.language;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import objects.Property;

public class Language 
{
    private static ArrayList<Property> properties = new ArrayList<>();

    public static void getLanguageProperties(String currentLangauge)
    {
        if (currentLangauge != null)
        {
            BufferedReader fileReader = null;

            if (currentLangauge.equals("GER"))
            {
                try 
                {
                    fileReader = new BufferedReader(new FileReader("src\\backend\\language\\Deutsch.txt"));
                } 
                catch (FileNotFoundException e) 
                {
                    e.printStackTrace();
                }
            }
            else if (currentLangauge.equals("ENG"))
            {
                try 
                {
                    fileReader = new BufferedReader(new FileReader("Language/English.txt"));
                } 
                catch (FileNotFoundException e) 
                {
                    e.printStackTrace();
                }
            }

            String line = "";

            try 
            {
                while ((line=fileReader.readLine()) != null)
                {
                    String [] parts = line.split("=");
                    
                    if (parts.length == 2)
                    {
                        properties.add(new Property(parts[0], parts[1]));
                    }
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
    }

    public static String getString(String propertyName)
    {
        if (!properties.isEmpty())
        {
            for (Property property : properties)
            {
                if (property.getName().equals(propertyName))
                {
                    return property.getValue();
                }
            }
        }
        return propertyName;
    }
}
