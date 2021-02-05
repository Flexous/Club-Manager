package Backend.Language;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Backend.*;
import Objects.*;

public class LanguageHandler 
{
    private String fileName;

    private ArrayList<Property> languageStrings = new ArrayList<>();

    public LanguageHandler(String fileName) 
    {
        this.fileName = fileName;
    }

    public void setLanguage() 
    {
        try 
        {
            BufferedReader br = new BufferedReader(new FileReader(ClubManagerConstraints.LANGUAGEFILEPATH+fileName));

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
