package Gui;

import java.awt.event.ActionListener;
import java.io.FileFilter;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

import Backend.Application;
import Backend.ClubHandler;
import Objects.Club;

public class AddClubDialog extends ClubManagerDialog
{
    private static final long serialVersionUID = -7010188678308927116L;
    
    public AddClubDialog()
    {
        super();

        Club club = new Club();

        JLabel nameOfClubLabel = new JLabel("Name des Vereins:");
        nameOfClubLabel.setForeground(Color.WHITE);
        getPanel().add(nameOfClubLabel, "gapleft 50, wrap");
        
        JTextField nameOfClubField = new JTextField(20);
        getPanel().add(nameOfClubField, "gapleft 50, wrap");

        JLabel logoOfClubLabel = new JLabel("Logo des Vereins:");
        logoOfClubLabel.setForeground(Color.WHITE);
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
                Application.currentClub = club;

                ClubHandler.addNewClub(club);

                dispose();
            }
        });
        getPanel().add(createClubBtn, "gapleft 50, gaptop 150, wrap");

        add(getPanel());
    }
}
