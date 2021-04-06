package gui.dialogs;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import backend.Application;
import backend.ClubManagerConstraints;
import objects.Player;

public class ShowPlayersDialog extends ClubManagerDialog
{
    private static final long serialVersionUID = -6650800504572708691L;

    public ShowPlayersDialog()
    {
        super();
        getPanel().setBackground(Application.getCurrentClub().getColor1());
        getPanel().setBorder(BorderFactory.createLineBorder(Application.getCurrentClub().getColor2()));
        setIconImage(new ImageIcon(Application.getCurrentClub().getLogo()).getImage());

        String [] header = {"Nummer", "Name"};

        JTable playerTable = new JTable(getData(), header);
        playerTable.setPreferredScrollableViewportSize(this.getSize());
        playerTable.setBackground(Application.getCurrentClub().getColor1());
        playerTable.setFillsViewportHeight(true);
        playerTable.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 30));
        playerTable.setRowHeight(30);

        MatteBorder border = new MatteBorder(1, 1, 0, 0, Color.BLACK);
        playerTable.setBorder(border);
        
        JScrollPane scrollPane = new JScrollPane(playerTable);
        scrollPane.getViewport().setBackground(Application.getCurrentClub().getColor1());
        //scrollPane.setBorder(new LineBorder(Application.getCurrentClub().getColor2()));

        getPanel().add(scrollPane, "grow");

        add(getPanel());
    }


    private String[][] getData()
    {
        String[][] data = new String[Application.getCurrentClub().getPlayers().size()][2];

        int index = 0;

        for (Player player : Application.getCurrentClub().getPlayers())
        {
            data[index][0] = player.getNumber()+"";
            data[index][1] = player.getName();

            index++;
        }

        return data;
    }
}
