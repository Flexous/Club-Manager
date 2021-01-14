package Gui;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;

public class AddClubDialog extends ClubManagerDialog
{
    private static final long serialVersionUID = -7010188678308927116L;
 
    public AddClubDialog()
    {
        super();

        JLabel nameOfClubLabel = new JLabel("Name des Verein:");
        nameOfClubLabel.setForeground(Color.WHITE);
        getPanel().add(nameOfClubLabel);

        JButton createClubBtn = new JButton("Verein erstellen");
        createClubBtn.setBackground(Color.CYAN);
        createClubBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                //
            }
        });
        getPanel().add(createClubBtn);

        add(getPanel());
    }
}
