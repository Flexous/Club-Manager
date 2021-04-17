package gui.panels;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import backend.*;
import gui.MenuButton;
import objects.Club;

public class CreateClubPanel 
{
    public void init()
    {
        Club club = new Club();

        JLabel nameOfClubLabel = new JLabel(ClubManagerConstraints.LANGUAGE.getString("NameOfClub"));
        nameOfClubLabel.setForeground(ClubManagerConstraints.APP.getContrastColor(ClubManagerConstraints.APP.getMainGui().getMainPanel().getBackground()));
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(nameOfClubLabel, "wrap");
        
        JTextField nameOfClubField = new JTextField(20);
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(nameOfClubField, "wrap");

        MenuButton chooseColor1Button = new MenuButton(ClubManagerConstraints.LANGUAGE.getString("Color1"));
        chooseColor1Button.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20)); 

        JLabel color1Label = new JLabel(ClubManagerConstraints.LANGUAGE.getString("Color1"));
        color1Label.setForeground(ClubManagerConstraints.APP.getContrastColor(ClubManagerConstraints.APP.getMainGui().getMainPanel().getBackground()));
        color1Label.setVisible(false);

        chooseColor1Button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                club.setColor1(JColorChooser.showDialog(null, ClubManagerConstraints.LANGUAGE.getString("ColorSelection"), null));
                color1Label.setForeground(club.getColor1());
                color1Label.setVisible(true);
                ClubManagerConstraints.APP.getMainGui().getMainPanel().revalidate();
                ClubManagerConstraints.APP.getMainGui().getMainPanel().repaint();
            }
        });
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(chooseColor1Button);
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(color1Label, "gapleft 5, wrap");

        MenuButton chooseColor2Button = new MenuButton(ClubManagerConstraints.LANGUAGE.getString("Color2"));
        chooseColor2Button.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20)); 

        JLabel color2Label = new JLabel(ClubManagerConstraints.LANGUAGE.getString("Color2"));
        color2Label.setForeground(ClubManagerConstraints.APP.getContrastColor(ClubManagerConstraints.APP.getMainGui().getMainPanel().getBackground()));
        color2Label.setVisible(false);

        chooseColor2Button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                club.setColor2(JColorChooser.showDialog(null, ClubManagerConstraints.LANGUAGE.getString("ColorSelection"), null));
                color2Label.setForeground(club.getColor2());
                color2Label.setVisible(true);
                ClubManagerConstraints.APP.getMainGui().getMainPanel().revalidate();
                ClubManagerConstraints.APP.getMainGui().getMainPanel().repaint();
            }
        });
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(chooseColor2Button);
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(color2Label, "gapleft 5, wrap");

        MenuButton chooseLogoBtn = new MenuButton(ClubManagerConstraints.LANGUAGE.getString("BadgeOfClub"));
        chooseLogoBtn.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20));    
        
        JLabel logoLabel = new JLabel();
        logoLabel.setForeground(ClubManagerConstraints.APP.getContrastColor(ClubManagerConstraints.APP.getMainGui().getMainPanel().getBackground()));
        logoLabel.setVisible(false);

        chooseLogoBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                FileNameExtensionFilter filter = new FileNameExtensionFilter(ClubManagerConstraints.LANGUAGE.getString("Images"), "gif", "png", "jpg");

                JFileChooser chooser = new JFileChooser();   
                chooser.setFileFilter(filter);   
                int fileSelected = chooser.showOpenDialog(null);

                if (fileSelected == JFileChooser.APPROVE_OPTION)
                {
                    club.setLogo(chooser.getSelectedFile().getAbsolutePath());
                    logoLabel.setText(club.getLogo());
                    logoLabel.setVisible(true);
                }
            }
        });
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(chooseLogoBtn);
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(logoLabel, "gapleft 5, wrap");

        MenuButton createClubBtn = new MenuButton(ClubManagerConstraints.LANGUAGE.getString("CreateClub"));
        createClubBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                if (nameOfClubField.getText().isEmpty())
                {
                    nameOfClubField.setBackground(Color.RED);
                    return;
                }

                club.setName(nameOfClubField.getText());

                ClubManagerConstraints.APP.setClub(club);
                ClubManagerConstraints.APP.createNewClub(club);
                ClubManagerConstraints.APP.saveClubToPropertyFile();

                ClubManagerConstraints.APP.closeMainGui();
                ClubManagerConstraints.APP.initMainGui();
            }
        });
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(createClubBtn, "gaptop 150, wrap");
    }
}
