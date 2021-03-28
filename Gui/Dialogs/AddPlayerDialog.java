package Gui.Dialogs;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Backend.Application;
import Backend.ClubManagerFunctions;
import Backend.Language.Language;
import Gui.MenuButton;
import Objects.Player;

public class AddPlayerDialog extends ClubManagerDialog
{
    private static final long serialVersionUID = 5004061434132674942L;
 
    public AddPlayerDialog()
    {
        super();
        getPanel().setBackground(Application.getCurrentClub().getColor1());
        getPanel().setBorder(BorderFactory.createLineBorder(Application.getCurrentClub().getColor2()));
        setIconImage(new ImageIcon(Application.getCurrentClub().getLogo()).getImage());
        
        Player player = new Player();

        JLabel nameOfPlayerLabel = new JLabel(Language.getString("NameOfPlayer"));
        nameOfPlayerLabel.setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
        getPanel().add(nameOfPlayerLabel, "gapleft 50, wrap");

        JTextField nameOfPlayerField = new JTextField(20);
        getPanel().add(nameOfPlayerField, "gapleft 50, wrap");

        JLabel nrOfPlayerLabel = new JLabel(Language.getString("NrOfPlayer"));
        nrOfPlayerLabel.setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
        getPanel().add(nrOfPlayerLabel, "gapleft 50, wrap");

        JTextField nrOfPlayerField = new JTextField(5);
        getPanel().add(nrOfPlayerField, "gapleft 50, wrap");

        MenuButton addPlayerBtn = new MenuButton(Language.getString("AddPlayer"));
        addPlayerBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                if (nameOfPlayerField.getText().isEmpty())
                {
                    nameOfPlayerField.setBackground(Color.RED);
                    return;
                }

                if (nrOfPlayerField.getText().isEmpty())
                {
                    nrOfPlayerField.setBackground(Color.RED);
                    return;
                }

                player.setName(nameOfPlayerField.getText());

                try
                {
                    player.setNumber(Integer.parseInt(nrOfPlayerField.getText()));
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }


                ClubManagerFunctions.createNewPlayer(player);

                dispose();
            }
        });
        getPanel().add(addPlayerBtn, "gapleft 50, gaptop 150, wrap");

        add(getPanel());
    }
}
