package Gui;

import java.awt.Color;

import javax.swing.JLabel;

public class AddClubDialog extends ClubManagerDialog
{
    private static final long serialVersionUID = -7010188678308927116L;
 
    public AddClubDialog()
    {
        super();

        JLabel nameOfClubLabel = new JLabel("Name des Verein:");
        nameOfClubLabel.setForeground(Color.WHITE);
        this.getPanel().add(nameOfClubLabel);

        add(this.getPanel());
    }
}
