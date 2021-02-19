package Gui;

import Backend.*;
import Gui.Dialogs.*;

import java.lang.reflect.Field;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import net.miginfocom.swing.MigLayout;

public class MainGui extends JFrame 
{
    private static final long serialVersionUID = 1745017706827567279L;

    private CreateClubDialog addClubDialog;
    private AddPlayerToClubDialog addPlayerToClubDialog;

    private JPanel panel = new JPanel();

    public MainGui(String guiTitle)
    {
        setTitle(guiTitle);
        setIconImage(new ImageIcon(Application.propertiesHandler.getValueFromProperty("DefaultLogo", "App")).getImage());
        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH); 
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent we) 
            {
                int selection = JOptionPane.showConfirmDialog(null, "Do you want to save?", "", JOptionPane.YES_NO_OPTION);

                if (selection == JOptionPane.YES_OPTION)
                {
                    ClubManagerFunctions.saveClubToPropertyFile();
                }
            }
        } );
    }

    public void create()
    {
        panel.setLayout(new MigLayout("align 50% 50%"));
        Field field;
        try 
        {
            if (Application.getCurrentClub() != null)
            {
                Color color1 = Application.getCurrentClub().getColor1();

                if (color1 != null)
                {
                    panel.setBackground(Application.getCurrentClub().getColor1());
                }
                else
                {
                    field = Class.forName("java.awt.Color").getField(Application.propertiesHandler
                    .getValueFromProperty("DefaultColor1", "App"));
                    panel.setBackground((Color)field.get(null));
                }

                String logoPath = Application.getCurrentClub().getLogo();

                if (!logoPath.isEmpty())
                {
                    setIconImage(new ImageIcon(logoPath).getImage());
                }
            }
            else
            {
                field = Class.forName("java.awt.Color").getField(Application.propertiesHandler
                .getValueFromProperty("DefaultColor1", "App"));
                panel.setBackground((Color)field.get(null));
            }
        } 
        catch (Exception e) 
        {
            //Application.getLogger().warning("");
			e.printStackTrace();
        }

        if (Application.getCurrentClub() != null)
        {
            if (Application.getCurrentClub().getName() != "Unnamed club")
            {
                JLabel currentClubLabel = new JLabel("Your club: " + Application.getCurrentClub().getName());
                currentClubLabel.setForeground(ClubManagerFunctions.getContrastColor(panel.getBackground()));
                currentClubLabel.setFont(new Font("Arial", Font.BOLD, 40));
                panel.add(currentClubLabel, "wrap");
            }
        }
        else
        {
            JLabel createClubLabel = new JLabel("You haven't created a club yet.");
            createClubLabel.setForeground(ClubManagerFunctions.getContrastColor(panel.getBackground()));
            createClubLabel.setFont(new Font("Arial", Font.BOLD, 40));
            panel.add(createClubLabel, "wrap");
        
            MenuButton createClubButton = new MenuButton("Create Club");
            createClubButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    showCreateClubDialog();
                }
            });
    
            panel.add(createClubButton);
        }



        add(panel);
        repaint();
        revalidate();
    }

    public void showCreateClubDialog()
    {
        if (addClubDialog == null)
        {
            addClubDialog = new CreateClubDialog();
        }
        addClubDialog.setVisible(true);
    }

    public void showAddPlayerToClubDialog()
    {
        if (addPlayerToClubDialog == null)
        {
            addPlayerToClubDialog = new AddPlayerToClubDialog();
        }
        addPlayerToClubDialog.setVisible(true);
    }
}
