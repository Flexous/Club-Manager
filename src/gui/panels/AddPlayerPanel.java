package gui.panels;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.*;

import backend.*;
import gui.MenuButton;
import objects.Player;

public class AddPlayerPanel
{ 
    public void init()
    {
        Player player = new Player();

        JLabel firstnameLbl = new JLabel(ClubManagerConstraints.LANGUAGE.getString("NameOfPlayer"));
        firstnameLbl.setForeground(ClubManagerConstraints.APP.getContrastColor(ClubManagerConstraints.APP.getMainGui().getMainPanel().getBackground()));
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(firstnameLbl, "gapleft 50, wrap");

        JTextField firstnameField = new JTextField(20);
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(firstnameField, "gapleft 50, wrap");

        JLabel lastnameLbl = new JLabel("Lastname");
        lastnameLbl.setForeground(ClubManagerConstraints.APP.getContrastColor(ClubManagerConstraints.APP.getMainGui().getMainPanel().getBackground()));
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(lastnameLbl, "gapleft 50, wrap");

        JTextField lastnameField = new JTextField(20);
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(lastnameField, "gapleft 50, wrap");

        JLabel nrOfPlayerLabel = new JLabel(ClubManagerConstraints.LANGUAGE.getString("NrOfPlayer"));
        nrOfPlayerLabel.setForeground(ClubManagerConstraints.APP.getContrastColor(ClubManagerConstraints.APP.getMainGui().getMainPanel().getBackground()));
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(nrOfPlayerLabel, "gapleft 50, wrap");

        JTextField nrOfPlayerField = new JTextField(5);
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(nrOfPlayerField, "gapleft 50, wrap");

        MenuButton addPlayerBtn = new MenuButton(ClubManagerConstraints.LANGUAGE.getString("AddPlayer"));
        addPlayerBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                if (firstnameField.getText().isEmpty())
                {
                    firstnameField.setBackground(Color.RED);
                    return;
                }

                if (lastnameField.getText().isEmpty())
                {
                    lastnameField.setBackground(Color.RED);
                    return;
                }

                if (nrOfPlayerField.getText().isEmpty())
                {
                    nrOfPlayerField.setBackground(Color.RED);
                    return;
                }

                player.setFirstname(firstnameField.getText());
                player.setLastname(lastnameField.getText());

                try
                {
                    player.setNumber(Integer.parseInt(nrOfPlayerField.getText()));
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

                boolean playerUpdate = false;

                for (Player existingPlayer : ClubManagerConstraints.APP.getClub().getPlayers())
                {
                    if (existingPlayer.getFirstname().equals(player.getFirstname()) && existingPlayer.getLastname().equals(player.getLastname()))
                    {
                        Object[] options = {ClubManagerConstraints.LANGUAGE.getString("Yes"), ClubManagerConstraints.LANGUAGE.getString("No")};
        
                        int selection = JOptionPane.showOptionDialog(null, ClubManagerConstraints.LANGUAGE.getString("PlayerAlreadyExists"), "", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
                        if (selection == JOptionPane.YES_OPTION)
                        {
                            ClubManagerConstraints.APP.getMainGui().getMainPanel().removeAll();

                            new EditPlayerPanel().init(player);
                            playerUpdate = true;
                        }
                        else
                        {
                            return;
                        }
                    }
                }

                if (!playerUpdate)
                {
                    ClubManagerConstraints.APP.createNewPlayer(player);

                    ClubManagerConstraints.APP.getMainGui().getMainPanel().removeAll();

                    new PlayersPanel().init();
    
                    ClubManagerConstraints.APP.getMainGui().revalidate();
                    ClubManagerConstraints.APP.getMainGui().repaint();
                }
            }
        });
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(addPlayerBtn, "gapleft 50, gaptop 150, wrap");
    }
}
