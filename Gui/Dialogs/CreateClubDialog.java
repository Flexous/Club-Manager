package Gui.Dialogs;

import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import Backend.Application;
import Backend.ClubManagerFunctions;
import Gui.MenuButton;
import Objects.Club;

public class CreateClubDialog extends ClubManagerDialog
{
    private static final long serialVersionUID = -7010188678308927116L;
    
    public CreateClubDialog()
    {
        super();

        Club club = new Club();

        JLabel nameOfClubLabel = new JLabel("Name des Vereins:");
        nameOfClubLabel.setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
        getPanel().add(nameOfClubLabel, "gapleft 50, wrap");
        
        JTextField nameOfClubField = new JTextField(20);
        getPanel().add(nameOfClubField, "gapleft 50, wrap");

        JLabel logoOfClubLabel = new JLabel("Logo des Vereins:");
        logoOfClubLabel.setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
        getPanel().add(logoOfClubLabel, "gapleft 50, gaptop 20, wrap");

        MenuButton chooseLogoBtn = new MenuButton("Bild auswählen");
        chooseLogoBtn.setFont(new Font("Arial", Font.BOLD, 20));        
        chooseLogoBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Bilder", "gif", "png", "jpg");

                JFileChooser chooser = new JFileChooser();   
                chooser.setFileFilter(filter);   
                int fileSelected = chooser.showOpenDialog(null);

                if (fileSelected == JFileChooser.APPROVE_OPTION)
                {
                    club.setLogoSrc(chooser.getSelectedFile().getAbsolutePath());
                }
            }
        });
        getPanel().add(chooseLogoBtn, "gapleft 50, wrap");

        MenuButton createClubBtn = new MenuButton("Verein erstellen");
        createClubBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                club.setName(nameOfClubField.getText());

                if (!Application.offlineMode)
                {
                    Application.getCurrentUser().setClub(club);
                }

                ClubManagerFunctions.createNewClubFile(club);

                dispose();
            }
        });
        getPanel().add(createClubBtn, "gapleft 50, gaptop 150, wrap");

        add(getPanel());
    }
}
