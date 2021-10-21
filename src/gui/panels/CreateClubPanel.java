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
    private Application app;

    public CreateClubPanel(Application app)
    {
        this.app = app;
    }

    public void init()
    {
        Club club = new Club();

        JLabel nameOfClubLabel = new JLabel(app.getLanguage().getString("NameOfClub"));
        nameOfClubLabel.setForeground(app.getContrastColor(app.getMainGui().getMainPanel().getBackground()));
        app.getMainGui().getMainPanel().add(nameOfClubLabel, "wrap");
        
        JTextField nameOfClubField = new JTextField(20);
        app.getMainGui().getMainPanel().add(nameOfClubField, "wrap");

        MenuButton chooseColor1Button = new MenuButton(app, app.getLanguage().getString("Color1"));
        chooseColor1Button.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20)); 

        JLabel color1Label = new JLabel(app.getLanguage().getString("Color1"));
        color1Label.setForeground(app.getContrastColor(app.getMainGui().getMainPanel().getBackground()));
        color1Label.setVisible(false);

        chooseColor1Button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                club.setColor1(JColorChooser.showDialog(null, app.getLanguage().getString("ColorSelection"), null));
                color1Label.setForeground(club.getColor1());
                color1Label.setVisible(true);
                app.getMainGui().getMainPanel().revalidate();
                app.getMainGui().getMainPanel().repaint();
            }
        });
        app.getMainGui().getMainPanel().add(chooseColor1Button);
        app.getMainGui().getMainPanel().add(color1Label, "gapleft 5, wrap");

        MenuButton chooseColor2Button = new MenuButton(app, app.getLanguage().getString("Color2"));
        chooseColor2Button.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20)); 

        JLabel color2Label = new JLabel(app.getLanguage().getString("Color2"));
        color2Label.setForeground(app.getContrastColor(app.getMainGui().getMainPanel().getBackground()));
        color2Label.setVisible(false);

        chooseColor2Button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                club.setColor2(JColorChooser.showDialog(null, app.getLanguage().getString("ColorSelection"), null));
                color2Label.setForeground(club.getColor2());
                color2Label.setVisible(true);
                app.getMainGui().getMainPanel().revalidate();
                app.getMainGui().getMainPanel().repaint();
            }
        });
        app.getMainGui().getMainPanel().add(chooseColor2Button);
        app.getMainGui().getMainPanel().add(color2Label, "gapleft 5, wrap");

        MenuButton chooseColor3Button = new MenuButton(app, app.getLanguage().getString("Color3"));
        chooseColor3Button.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20)); 

        JLabel color3Label = new JLabel(app.getLanguage().getString("Color3"));
        color3Label.setForeground(app.getContrastColor(app.getMainGui().getMainPanel().getBackground()));
        color3Label.setVisible(false);

        chooseColor3Button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                club.setColor3(JColorChooser.showDialog(null, app.getLanguage().getString("ColorSelection"), null));
                color3Label.setForeground(club.getColor3());
                color3Label.setVisible(true);
                app.getMainGui().getMainPanel().revalidate();
                app.getMainGui().getMainPanel().repaint();
            }
        });
        app.getMainGui().getMainPanel().add(chooseColor3Button);
        app.getMainGui().getMainPanel().add(color3Label, "gapleft 5, wrap");

        MenuButton chooseLogoBtn = new MenuButton(app, app.getLanguage().getString("BadgeOfClub"));
        chooseLogoBtn.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20));    
        
        JLabel logoLabel = new JLabel();
        logoLabel.setForeground(app.getContrastColor(app.getMainGui().getMainPanel().getBackground()));
        logoLabel.setVisible(false);

        chooseLogoBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                FileNameExtensionFilter filter = new FileNameExtensionFilter(app.getLanguage().getString("Images"), "gif", "png", "jpg");

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
        app.getMainGui().getMainPanel().add(chooseLogoBtn);
        app.getMainGui().getMainPanel().add(logoLabel, "gapleft 5, wrap");

        MenuButton createClubBtn = new MenuButton(app, app.getLanguage().getString("CreateClub"));
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

                app.setClub(club);
                app.addClubToDb();

                app.closeMainGui();
                app.initMainGui();
            }
        });
        app.getMainGui().getMainPanel().add(createClubBtn, "gaptop 150, wrap");
    }
}
