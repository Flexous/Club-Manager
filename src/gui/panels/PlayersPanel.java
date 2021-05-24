package gui.panels;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import backend.*;
import gui.*;
import net.miginfocom.swing.MigLayout;
import objects.*;

public class PlayersPanel
{
    public void init()
    {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().setBackground(ClubManagerConstraints.APP.getClub().getColor1());
        
        JPanel panel = new JPanel(new MigLayout());
        panel.setBackground(ClubManagerConstraints.APP.getClub().getColor1());

        JLabel numberLbl = new JLabel("Number");
        numberLbl.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20));
        panel.add(numberLbl, "gapright 50");

        JLabel nameLbl = new JLabel("Name");
        nameLbl.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20));
        panel.add(nameLbl, "wrap");

        for (Player player : ClubManagerConstraints.APP.getClub().getPlayers())
        {
            JTextField numberTxtField = new JTextField(" "+player.getNumber()+" ");
            numberTxtField.setBackground(ClubManagerConstraints.APP.getClub().getColor1());
            numberTxtField.setBorder(new LineBorder(ClubManagerConstraints.APP.getClub().getColor2()));
            numberTxtField.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20));
            numberTxtField.setEditable(false);
            panel.add(numberTxtField);

            JTextField nameTxtField = new JTextField(" "+player.getName()+" ");
            nameTxtField.setBackground(ClubManagerConstraints.APP.getClub().getColor1());
            nameTxtField.setBorder(new LineBorder(ClubManagerConstraints.APP.getClub().getColor2()));
            nameTxtField.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20));
            nameTxtField.setEditable(false);
            panel.add(nameTxtField);

            MenuButton editBtn = new MenuButton("");
            Image icon = new ImageIcon(ClubManagerConstraints.EDITLOGOPATH).getImage();
            editBtn.setIcon(new ImageIcon(icon));

            MenuButton confirmBtn = new MenuButton("");
            icon = new ImageIcon(ClubManagerConstraints.CONFIRMLOGOPATH).getImage();
            confirmBtn.setIcon(new ImageIcon(icon));
            confirmBtn.setVisible(false);

            MenuButton cancelBtn = new MenuButton("");
            icon = new ImageIcon(ClubManagerConstraints.CANCELLOGOPATH).getImage();
            cancelBtn.setIcon(new ImageIcon(icon));
            cancelBtn.setVisible(false);

            editBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    numberTxtField.setEditable(true);
                    nameTxtField.setEditable(true);
                    confirmBtn.setVisible(true);
                    cancelBtn.setVisible(true);
                    editBtn.setEnabled(false);
                }
            });

            confirmBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    numberTxtField.setEditable(false);
                    nameTxtField.setEditable(false);
                    editBtn.setEnabled(true);
                    confirmBtn.setVisible(false);
                    cancelBtn.setVisible(false);

                    player.setNumber(Integer.parseInt(numberTxtField.getText()));
                    player.setName(nameTxtField.getText());
                    ClubManagerConstraints.APP.getClub().updatePlayer(player);
                }
            });

            cancelBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    numberTxtField.setEditable(false);
                    nameTxtField.setEditable(false);
                    editBtn.setEnabled(true);
                    confirmBtn.setVisible(false);
                    cancelBtn.setVisible(false);
                }
            });

            panel.add(editBtn, "gapleft 50");
            panel.add(confirmBtn);
            panel.add(cancelBtn, "wrap");
        }

        scrollPane.setViewportView(panel);
        
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
