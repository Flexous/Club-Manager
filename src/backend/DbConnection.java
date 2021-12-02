package backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection
{
    private Application app;

    private Connection conn;

    public DbConnection(Application app)
    {
        this.app = app;
    }

    public void establish()
    {
        try 
        {  
            String url = "jdbc:sqlite:files/CM.db";  
            conn = DriverManager.getConnection(url);        
        } 
        catch (SQLException e) 
        {  
            app.getLogger().warning(e.getMessage());
        }
    }

    public void close()
    {
        if (conn != null)
        {
            try 
            {
                conn.close();
            } 
            catch (SQLException e) 
            {
                app.getLogger().warning(e.getMessage());
            }
        }
    }

    public Connection get()
    {
        return conn;
    }
}
