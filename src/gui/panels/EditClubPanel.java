package gui.panels;

import backend.Application;

public class EditClubPanel
{
    private Application app;

    public EditClubPanel(Application app)
    {
        this.app = app;
    }

    public void init()
    {
        app.getLogger().info("Edit Club view now visible.");
    } 
}
