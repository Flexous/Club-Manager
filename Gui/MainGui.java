package Gui;

import Backend.Functions;
import Gui.Dialogs.*;

import java.lang.reflect.Field;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import Backend.Application;
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
        setResizable(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight()-10;
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void create()
    {
        panel.setLayout(new MigLayout("align 50% 50%"));
        Field field;
        try 
        {
            String propertyName = "";
            String propertyType = "";

            if (Application.getCurrentUser() == null)
            {
                propertyName = "DefaultColor1";
                propertyType = "App";
            }
            else
            {
                propertyName = "Color1";
                propertyType = "User";
            }

            field = Class.forName("java.awt.Color").getField(Application.propertiesHandler
            .getValueFromProperty(propertyName, propertyType));
            panel.setBackground((Color)field.get(null));
        } 
        catch (Exception e) 
        {
            //Application.getLogger().warning("");
			e.printStackTrace();
        }

        if (Application.propertiesHandler.getValueFromProperty("Club", "User") == null);
        {
            JLabel createClubLabel = new JLabel("You haven't created a club yet.");
            createClubLabel.setForeground(Functions.getContrastColor(panel.getBackground()));
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
