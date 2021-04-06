package gui.dialogs;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import backend.*;
import backend.language.*;
import gui.MenuButton;
import objects.Club;

public class CreateClubDialog extends ClubManagerDialog
{
    private static final long serialVersionUID = -7010188678308927116L;
    
    public CreateClubDialog()
    {
        super();

        Club club = new Club();

        JLabel nameOfClubLabel = new JLabel(Language.getString("NameOfClub"));
        nameOfClubLabel.setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
        getPanel().add(nameOfClubLabel, "gapleft 50, wrap");
        
        JTextField nameOfClubField = new JTextField(20);
        getPanel().add(nameOfClubField, "gapleft 50, wrap");

        MenuButton chooseColor1Button = new MenuButton(Language.getString("Color1"));
        chooseColor1Button.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20)); 

        JLabel color1Label = new JLabel(Language.getString("Color1"));
        color1Label.setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
        color1Label.setVisible(false);

        chooseColor1Button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                club.setColor1(JColorChooser.showDialog(null, Language.getString("ColorSelection"), null));
                color1Label.setForeground(club.getColor1());
                color1Label.setVisible(true);
                revalidate();
                repaint();
            }
        });
        getPanel().add(chooseColor1Button, "gapleft 50");
        getPanel().add(color1Label, "gapleft 5, wrap");

        MenuButton chooseColor2Button = new MenuButton(Language.getString("Color2"));
        chooseColor2Button.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20)); 

        JLabel color2Label = new JLabel(Language.getString("Color2"));
        color2Label.setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
        color2Label.setVisible(false);

        chooseColor2Button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                club.setColor2(JColorChooser.showDialog(null, Language.getString("ColorSelection"), null));
                color2Label.setForeground(club.getColor2());
                color2Label.setVisible(true);
                revalidate();
                repaint();
            }
        });
        getPanel().add(chooseColor2Button, "gapleft 50");
        getPanel().add(color2Label, "gapleft 5, wrap");

        MenuButton chooseLogoBtn = new MenuButton(Language.getString("BadgeOfClub"));
        chooseLogoBtn.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20));    
        
        JLabel logoLabel = new JLabel();
        logoLabel.setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
        logoLabel.setVisible(false);

        chooseLogoBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                FileNameExtensionFilter filter = new FileNameExtensionFilter(Language.getString("Images"), "gif", "png", "jpg");

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
        getPanel().add(chooseLogoBtn, "gapleft 50");
        getPanel().add(logoLabel, "gapleft 5, wrap");

        MenuButton createClubBtn = new MenuButton(Language.getString("CreateClub"));
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

                Application.setCurrentClub(club);
                ClubManagerFunctions.createNewClub(club);
                ClubManagerFunctions.saveClubToPropertyFile();

                dispose();
                Application.closeMainGui();
                Application.initMainGui();
            }
        });
        getPanel().add(createClubBtn, "gapleft 50, gaptop 150, wrap");

        add(getPanel());
    }
}
