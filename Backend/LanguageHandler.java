package Backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LanguageHandler 
{
    private String filePath;

    public LanguageHandler(String filePath) 
    {
        this.filePath = filePath;
    }

    public void setLanguage() 
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
