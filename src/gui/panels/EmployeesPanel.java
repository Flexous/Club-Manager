package gui.panels;

import java.awt.*;

import javax.swing.JLabel;

import backend.Application;
import backend.ClubManagerConstraints;

public class EmployeesPanel 
{
    private Application app;

    public EmployeesPanel(Application app)
    {
        this.app = app;
    }   
    
    public void init()
    {
        if (app.getClub().getManager() == null)
        {
            JLabel addEmployeeLabel = new JLabel(app.getLanguage().getString("NoEmployees"));
            addEmployeeLabel.setForeground(app.getContrastColor(app.getMainGui().getMainPanel().getBackground()));
            addEmployeeLabel.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 40));
            app.getMainGui().getMainPanel().add(addEmployeeLabel, "wrap");
        }

        app.getLogger().info("Edit Employees view now visible.");
    } 
}
