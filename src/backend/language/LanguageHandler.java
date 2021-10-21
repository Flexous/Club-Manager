package backend.language;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import backend.Application;
import objects.Property;

public class LanguageHandler 
{
    private Application app;

    private ArrayList<Property> properties = new ArrayList<>();

    public LanguageHandler(Application app)
    {
        this.app = app;
    }

    public void loadLanguageFromDb(String currentLangauge)
    {
        if (currentLangauge != null)
        {
            if (currentLangauge.equals(""))
            {
                currentLangauge = "EN";
            }
            else
            {
                app.getDbConnection().establish();

                try 
                {                 
                    Statement stmt = app.getDbConnection().get().createStatement();  
                    ResultSet rs = stmt.executeQuery("select translationkey, translationvalue from Language_Properties where language = '" + currentLangauge + "'");  
                          
                    while (rs.next()) 
                    {  
                        properties.add(new Property(rs.getString("translationkey"), rs.getString("translationvalue")));
                    }
                    
                    app.getDbConnection().close();
                } 
                catch (SQLException e) 
                {  
                    app.getLogger().warning(e.getMessage());
                }
            }
        }
    }

    public String getString(String propertyName)
    {
        if (!properties.isEmpty())
        {
            for (Property property : properties)
            {
                if (property.getKey().equals(propertyName))
                {
                    return property.getValue();
                }
            }
        }
        return propertyName;
    }
}
