package gui.panels;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import backend.*;
import gui.*;
import objects.*;

public class PlayersPanel
{
    public void init()
    {
        String [] header = {"Nummer", "Name"};

        JTable playerTable = new JTable(getData(), header);
        playerTable.setPreferredScrollableViewportSize(ClubManagerConstraints.APP.getMainGui().getMainPanel().getSize());
        playerTable.setBackground(ClubManagerConstraints.APP.getClub().getColor1());
        playerTable.setForeground(ClubManagerConstraints.APP.getContrastColor(playerTable.getBackground()));
        playerTable.setFillsViewportHeight(true);
        playerTable.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 30));
        playerTable.setRowHeight(30);

        MatteBorder border = new MatteBorder(1, 1, 0, 0, Color.BLACK);
        playerTable.setBorder(border);
        
        JScrollPane scrollPane = new JScrollPane(playerTable);
        scrollPane.getViewport().setBackground(ClubManagerConstraints.APP.getClub().getColor1());

        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(scrollPane, "grow, wrap");

        MenuButton addPlayerBtn = new MenuButton(ClubManagerConstraints.LANGUAGE.getString("AddPlayer"));
        addPlayerBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                ClubManagerConstraints.APP.getMainGui().getMainPanel().removeAll();

                AddPlayerPanel addPlayerPanel = new AddPlayerPanel();
                addPlayerPanel.init();

                ClubManagerConstraints.APP.getMainGui().revalidate();
                ClubManagerConstraints.APP.getMainGui().repaint();
            }
        });

        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(addPlayerBtn, "wrap");
    }


    private String[][] getData()
    {
        String[][] data = new String[ClubManagerConstraints.APP.getClub().getPlayers().size()][2];

        int index = 0;

        for (Player player : ClubManagerConstraints.APP.getClub().getPlayers())
        {
            data[index][0] = player.getNumber()+"";
            data[index][1] = player.getName();

            index++;
        }

        return data;
    }
}
