package Gui;

import javax.swing.JFrame;

public class MainGui extends JFrame 
{
    private static final long serialVersionUID = 1745017706827567279L;
 
    private AddClubDialog addClubDialog;
    private AddPlayerToClubDialog addPlayerToClubDialog;

    private String guiTitle;

    public MainGui(String guiTitle)
    {
        this.guiTitle = guiTitle;
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
    }

    public String getGuiTitle()
    {
        return this.guiTitle;
    }

    public void setGuiTitle(String guiTitle)
    {
        this.guiTitle = guiTitle;
    }

    public void showAddClubDialog()
    {
        if (this.addClubDialog == null)
        {
            this.addClubDialog = new AddClubDialog();
        }
        this.addClubDialog.setVisible(true);
    }

    public void showAddPlayerToClubDialog()
    {
        if (this.addPlayerToClubDialog == null)
        {
            this.addPlayerToClubDialog = new AddPlayerToClubDialog();
        }
        this.addPlayerToClubDialog.setVisible(true);
    }
}
