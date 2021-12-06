package gui.panels;

import java.awt.*;

import javax.swing.JLabel;

import backend.Application;
import backend.ClubManagerConstraints;

public class ManagerPanel 
{
    private Application app;

    public ManagerPanel(Application app)
    {
        this.app = app;
    }   
    
    public void init()
    {
        if (app.getClub().getManager() == null)
        {
            JLabel addManagerLabel = new JLabel(app.getLanguage().getString("NoManager"));
            addManagerLabel.setForeground(app.getContrastColor(app.getMainGui().getMainPanel().getBackground()));
            addManagerLabel.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 40));
            app.getMainGui().getMainPanel().add(addManagerLabel, "wrap");
        }

        app.getLogger().info("Edit Manager view now visible.");
    } 
}
