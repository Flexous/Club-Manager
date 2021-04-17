package gui.panels;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import backend.ClubManagerConstraints;
import gui.MenuButton;
import objects.*;

public class EditPlayerPanel
{    
    public void init(Player player)
    {
        JLabel nameOfPlayerLabel = new JLabel(ClubManagerConstraints.LANGUAGE.getString("NameOfPlayer"));
        nameOfPlayerLabel.setForeground(ClubManagerConstraints.APP.getContrastColor(ClubManagerConstraints.APP.getMainGui().getMainPanel().getBackground()));
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(nameOfPlayerLabel, "gapleft 50, wrap");

        JTextField nameOfPlayerField = new JTextField(20);
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(nameOfPlayerField, "gapleft 50, wrap");
        nameOfPlayerField.setText(player.getName());

        JLabel nrOfPlayerLabel = new JLabel(ClubManagerConstraints.LANGUAGE.getString("NrOfPlayer"));
        nrOfPlayerLabel.setForeground(ClubManagerConstraints.APP.getContrastColor(ClubManagerConstraints.APP.getMainGui().getMainPanel().getBackground()));
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(nrOfPlayerLabel, "gapleft 50, wrap");

        JTextField nrOfPlayerField = new JTextField(5);
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(nrOfPlayerField, "gapleft 50, wrap");
        nrOfPlayerField.setText(player.getNumber()+"");

        MenuButton savePlayerBtn = new MenuButton(ClubManagerConstraints.LANGUAGE.getString("AddPlayer"));
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

                ClubManagerConstraints.APP.getClub().updatePlayer(player);
            }
        });
    }
}
