package gui.panels;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import backend.Application;
import gui.MenuButton;
import objects.*;

public class EditPlayerPanel
{    
    private Application app;

    public EditPlayerPanel(Application app)
    {
        this.app = app;
    }

    public void init(Player player)
    {
        JLabel nameOfPlayerLabel = new JLabel(app.getLanguage().getString("NameOfPlayer"));
        nameOfPlayerLabel.setForeground(app.getContrastColor(app.getMainGui().getMainPanel().getBackground()));
        app.getMainGui().getMainPanel().add(nameOfPlayerLabel, "gapleft 50, wrap");

        JTextField nameOfPlayerField = new JTextField(20);
        app.getMainGui().getMainPanel().add(nameOfPlayerField, "gapleft 50, wrap");
        nameOfPlayerField.setText(player.getFirstname());

        JLabel nrOfPlayerLabel = new JLabel(app.getLanguage().getString("NrOfPlayer"));
        nrOfPlayerLabel.setForeground(app.getContrastColor(app.getMainGui().getMainPanel().getBackground()));
        app.getMainGui().getMainPanel().add(nrOfPlayerLabel, "gapleft 50, wrap");

        JTextField nrOfPlayerField = new JTextField(5);
        app.getMainGui().getMainPanel().add(nrOfPlayerField, "gapleft 50, wrap");
        nrOfPlayerField.setText(player.getNumber()+"");

        MenuButton savePlayerBtn = new MenuButton(app, app.getLanguage().getString("AddPlayer"));
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

                player.setFirstname(nameOfPlayerField.getText());

                try
                {
                    player.setNumber(Integer.parseInt(nrOfPlayerField.getText()));
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }

                app.getClub().updatePlayer(player);
            }
        });
    }
}
