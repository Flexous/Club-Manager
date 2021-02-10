package Backend.UserHandling;

import Backend.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class RegistrationManager 
{
    private int minPasswordLength = 8;

    public boolean checkUserRegistration(String username, String password)
    {
        if (!checkIfUserExists(username))
        {
            if (password.length() >= minPasswordLength)
            {
                createNewUser(username, password);
                return true;
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Password too short!", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        return false;
    }

    public boolean checkIfUserExists(String username)
    {
        File directory = new File("Files/Users");

        if (!directory.exists())
        {
            return false;
        }

        File[] files = directory.listFiles(File::isFile);

        if (files.length == 0)
        {
            return false;
        }

        for (File file : files)
        {
            if (file.getName().startsWith(username))
            {
                return true;
            }
        } 
        return false;
    }

    public void createNewUser(String username, String password)
    {
        File file = new File("Files/Users/"+username+".user");
        try 
        {
            file.createNewFile();
            
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            
            bw.write("#User specific gui theme colors\n");
            bw.write("Color1="+Application.propertiesHandler.getValueFromProperty("DefaultColor1", "App")+"\n");
            bw.write("Color2="+Application.propertiesHandler.getValueFromProperty("DefaultColor2", "App")+"\n");

            bw.close();
        } 
        catch (IOException e) 
        {
            Application.getLogger().warning(e.getMessage());
			e.printStackTrace();
        }

    }
}
