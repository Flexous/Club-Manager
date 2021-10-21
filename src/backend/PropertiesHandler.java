package backend;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.Property;

public class PropertiesHandler 
{
    private Application app;

    public PropertiesHandler(Application app)
    {
        this.app = app;
    }

    //Property-File methods

    public void loadPropertiesFromDb()
    {
        app.getDbConnection().establish();

        try 
        {         
            String sql = "select * from App_Properties";  

            Statement stmt = app.getDbConnection().get().createStatement();  
            ResultSet rs = stmt.executeQuery(sql);  
                  
            while (rs.next()) 
            {  
                app.getProperties().add(new Property(rs.getString("propertykey"), rs.getString("propertyvalue")));
            }
            
            app.getDbConnection().close();
        } 
        catch (SQLException e) 
        {  
            e.printStackTrace();
        }
    }

    public String getPropertyValue(String propertyName)
    {
        for (Property property : app.getProperties())
        {
            if (property.getKey().equals(propertyName))
            {
                return property.getValue();
            }
        }
        return null;
    }

    //Club-File methods

    public void findClubProperties(String filePath)
    {
        if (filePath == null)
        {
            return;
        }

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
                        app.getClub().getProperties().add(new Property(parts[0], parts[1]));
                    }
                }
            }

            br.close();
        }
        catch (IOException e) 
        {
            app.getLogger().warning(e.getMessage());
        }
    }
}
