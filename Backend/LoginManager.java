package Backend;

import java.io.File;

import Objects.User;

public class LoginManager 
{
    private boolean loginCompleted;

    public void findLastLoggedInUser()
    {
        File directory = new File("Files/Users");
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;
    
        if (files != null)
        {
            for (File file : files)
            {
                if (file.lastModified() > lastModifiedTime)
                {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                    Application.propertiesHandler.setUserProperties(chosenFile.getPath());
                    return;
                }
            }
        }
        else
        {
            
        }
    } 

    public boolean userExists(User user)
    {
        File directory = new File("Files/Users");
        File[] files = directory.listFiles(File::isFile);

        for (File file : files)
        {
            if (file.getName().startsWith(user.getUsername()))
            {
                Application.propertiesHandler.setUserProperties(file.getPath());
            }
        }

        return true;
    }

    public boolean isLoginCompleted()
    {
        return loginCompleted;
    }
}
