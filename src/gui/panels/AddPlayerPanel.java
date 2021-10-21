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
    private Application app;

    public AddPlayerPanel(Application app)
    {
        this.app = app;
    }

    public void init()
    {
        Player player = new Player();

        JLabel firstnameLbl = new JLabel(app.getLanguage().getString("Firstname"));
        firstnameLbl.setForeground(app.getContrastColor(app.getMainGui().getMainPanel().getBackground()));
        app.getMainGui().getMainPanel().add(firstnameLbl, "gapleft 50, wrap");

        JTextField firstnameField = new JTextField(20);
        app.getMainGui().getMainPanel().add(firstnameField, "gapleft 50, wrap");

        JLabel lastnameLbl = new JLabel(app.getLanguage().getString("Lastname"));
        lastnameLbl.setForeground(app.getContrastColor(app.getMainGui().getMainPanel().getBackground()));
        app.getMainGui().getMainPanel().add(lastnameLbl, "gapleft 50, wrap");

        JTextField lastnameField = new JTextField(20);
        app.getMainGui().getMainPanel().add(lastnameField, "gapleft 50, wrap");

        JLabel nrOfPlayerLabel = new JLabel(app.getLanguage().getString("NrOfPlayer"));
        nrOfPlayerLabel.setForeground(app.getContrastColor(app.getMainGui().getMainPanel().getBackground()));
        app.getMainGui().getMainPanel().add(nrOfPlayerLabel, "gapleft 50, wrap");

        JTextField nrOfPlayerField = new JTextField(5);
        app.getMainGui().getMainPanel().add(nrOfPlayerField, "gapleft 50, wrap");

        JLabel birthDateLbl = new JLabel(app.getLanguage().getString("Birthdate"));
        birthDateLbl.setForeground(app.getContrastColor(app.getMainGui().getMainPanel().getBackground()));
        app.getMainGui().getMainPanel().add(birthDateLbl, "gapleft 50, wrap");

        JTextField birthDateField = new JTextField(5);
        app.getMainGui().getMainPanel().add(birthDateField, "gapleft 50, wrap");

        MenuButton addPlayerBtn = new MenuButton(app, app.getLanguage().getString("AddPlayer"));
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
                player.setClub(app.getClub());

                try
                {
                    player.setNumber(Integer.parseInt(nrOfPlayerField.getText()));
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

                player.setDateOfBirth(birthDateField.getText());

                boolean playerUpdate = false;

                for (Player existingPlayer : app.getClub().getPlayers())
                {
                    if (existingPlayer.getFirstname().equals(player.getFirstname()) && existingPlayer.getLastname().equals(player.getLastname()))
                    {
                        Object[] options = {app.getLanguage().getString("Yes"), app.getLanguage().getString("No")};
        
                        int selection = JOptionPane.showOptionDialog(null, app.getLanguage().getString("PlayerAlreadyExists"), "", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            
                        if (selection == JOptionPane.YES_OPTION)
                        {
                            app.getMainGui().getMainPanel().removeAll();

                            new EditPlayerPanel(app).init(player);
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
                    app.getClub().addPlayer(player);

                    app.addNewPlayerToDb(player);

                    app.getMainGui().getMainPanel().removeAll();

                    new PlayersPanel(app).init();
    
                    app.getMainGui().revalidate();
                    app.getMainGui().repaint();
                }
            }
        });
        app.getMainGui().getMainPanel().add(addPlayerBtn, "gapleft 50, gaptop 150, wrap");
    }
}
