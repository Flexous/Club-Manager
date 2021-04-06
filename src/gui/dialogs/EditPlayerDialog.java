package gui.dialogs;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import backend.Application;
import backend.ClubManagerFunctions;
import backend.language.*;
import gui.MenuButton;
import objects.*;

public class EditPlayerDialog extends ClubManagerDialog
{
    private static final long serialVersionUID = -8819397283537491753L;

    public EditPlayerDialog(Player player)
    {
        super();
        getPanel().setBackground(Application.getCurrentClub().getColor1());
        getPanel().setBorder(BorderFactory.createLineBorder(Application.getCurrentClub().getColor2()));
        setIconImage(new ImageIcon(Application.getCurrentClub().getLogo()).getImage());

        JLabel nameOfPlayerLabel = new JLabel(Language.getString("NameOfPlayer"));
        nameOfPlayerLabel.setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
        getPanel().add(nameOfPlayerLabel, "gapleft 50, wrap");

        JTextField nameOfPlayerField = new JTextField(20);
        getPanel().add(nameOfPlayerField, "gapleft 50, wrap");
        nameOfPlayerField.setText(player.getName());

        JLabel nrOfPlayerLabel = new JLabel(Language.getString("NrOfPlayer"));
        nrOfPlayerLabel.setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
        getPanel().add(nrOfPlayerLabel, "gapleft 50, wrap");

        JTextField nrOfPlayerField = new JTextField(5);
        getPanel().add(nrOfPlayerField, "gapleft 50, wrap");
        nrOfPlayerField.setText(player.getNumber()+"");

        MenuButton savePlayerBtn = new MenuButton(Language.getString("AddPlayer"));
        savePlayerBtn.addActionListener(new ActionListener()
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
    }    
}
