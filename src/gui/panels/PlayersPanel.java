package gui.panels;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import backend.*;
import gui.*;
import net.miginfocom.swing.MigLayout;
import objects.*;

public class PlayersPanel
{
    private Application app;

    public PlayersPanel(Application app)
    {
        this.app = app;
    }

    public void init()
    {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().setBackground(Color.WHITE);

        String [] column = {app.getLanguage().getString("NrOfPlayer"), app.getLanguage().getString("Firstname"), app.getLanguage().getString("Lastname")};

        String [][] data = new String[app.getClub().getPlayers().size()][5];

        int rowNr = 0;

        for (Player player : app.getClub().getPlayers())
        {
            data[rowNr][0] = player.getNumber()+"";
            data[rowNr][1] = player.getFirstname();
            data[rowNr][2] = player.getLastname();

            rowNr++;
        }   
        
        JPanel panel = new JPanel(new MigLayout());

        JTable jt = new JTable(data, column);
        panel.add(jt);

        MenuButton addPlayerBtn = new MenuButton(app, app.getLanguage().getString("AddPlayer"));
        addPlayerBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                app.getMainGui().getMainPanel().removeAll();

                AddPlayerPanel addPlayerPanel = new AddPlayerPanel(app);
                addPlayerPanel.init();

                app.getMainGui().revalidate();
                app.getMainGui().repaint();
            }
        });

        scrollPane.setViewportView(jt);
        
        app.getMainGui().getMainPanel().add(scrollPane, "grow, wrap");
        app.getMainGui().getMainPanel().add(addPlayerBtn);
    } 
}
