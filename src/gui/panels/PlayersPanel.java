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
        MenuButton addPlayerBtn = new MenuButton(app, "");
        addPlayerBtn.setIcon(new ImageIcon("files/img/Add.png"));
        addPlayerBtn.setToolTipText(app.getLanguage().getString("Add Player"));
        
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

        scrollPane.setViewportView(jt);
        
        app.getMainGui().getMainPanel().add(addPlayerBtn, "wrap");
        app.getMainGui().getMainPanel().add(scrollPane, "grow");

        app.getLogger().info("Player view now visible.");
    } 
}
