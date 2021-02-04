package Backend.Language;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Objects.Property;

public class LanguageHandler 
{
    private String filePath;

    private ArrayList<Property> languageStrings = new ArrayList<>();

    public LanguageHandler(String filePath) 
    {
        this.filePath = filePath;
    }

    public void setLanguage() 
    {
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader("Files/Languages/"+filePath));

            String line = "";

            while ((line=br.readLine()) != null)
            {
                if (line.contains("=") && !line.startsWith("#"))
                {
                    String [] parts = line.split("=");

                    if (parts.length > 1)
                    {
                        languageStrings.add(new Property(parts[0], parts[1]));
                    }
                }
            }

            br.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}
