package Objects;

import java.util.ArrayList;

public class User 
{
    private String username;
    
    private ArrayList<Property> properties = new ArrayList<>();

    public User(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public ArrayList<Property> getProperties()
    {
        return properties;
    }

    public void setUserProperties(ArrayList<Property> properties)
    {
        this.properties = properties;
    }
}
