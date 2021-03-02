package Gui;

import Backend.*;
import Backend.Language.Language;
import Gui.Dialogs.*;

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
    private AddPlayerDialog addPlayerToClubDialog;

    private JPanel panel = new JPanel();

    public MainGui(String guiTitle)
    {
        setTitle(guiTitle);
        setIconImage(new ImageIcon(ClubManagerConstraints.DEFAULTLOGOPATH).getImage());
        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH); 
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent we) 
            {
                Object[] options = {Language.Yes, Language.No};

                int selection = JOptionPane.showOptionDialog(null, Language.DoYouWantToSave, "", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

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
                    panel.setBackground(Color.WHITE);
                }

                String logoPath = Application.getCurrentClub().getLogo();

                if (!logoPath.isEmpty())
                {
                    setIconImage(new ImageIcon(logoPath).getImage());
                }
            }
            else
            {
                panel.setBackground(Color.WHITE);
            }
        } 
        catch (Exception e) 
        {
            //Application.getLogger().warning("");
			e.printStackTrace();
        }

        if (Application.getCurrentClub() != null)
        {
            JLabel currentClubLabel = new JLabel(Language.YourClub + Application.getCurrentClub().getName());
            currentClubLabel.setForeground(ClubManagerFunctions.getContrastColor(panel.getBackground()));
            currentClubLabel.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 40));
            panel.add(currentClubLabel, "wrap");

            MenuButton addPlayerBtn = new MenuButton(Language.AddPlayer);
            addPlayerBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    showAddPlayerDialog();
                }
            });

            panel.add(addPlayerBtn, "wrap");
        }
        else
        {
            JLabel createClubLabel = new JLabel(Language.NoClubCreated);
            createClubLabel.setForeground(ClubManagerFunctions.getContrastColor(panel.getBackground()));
            createClubLabel.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 40));
            panel.add(createClubLabel, "wrap");
        
            MenuButton createClubButton = new MenuButton(Language.CreateClub);
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

    public void showAddPlayerDialog()
    {
        if (addPlayerToClubDialog == null)
        {
            addPlayerToClubDialog = new AddPlayerDialog();
        }
        addPlayerToClubDialog.setVisible(true);
    }
}
