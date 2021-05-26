package gui.panels;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

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

        JLabel firstnameLbl = new JLabel("Firstname");
        firstnameLbl.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20));
        panel.add(firstnameLbl);

        JLabel lastnameLbl = new JLabel("Lastname");
        lastnameLbl.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20));
        panel.add(lastnameLbl, "wrap");

        for (Player player : ClubManagerConstraints.APP.getClub().getPlayers())
        {
            JTextField numberTxtField = new JTextField(" "+player.getNumber()+" ");
            numberTxtField.setBackground(ClubManagerConstraints.APP.getClub().getColor1());
            numberTxtField.setBorder(new LineBorder(ClubManagerConstraints.APP.getClub().getColor2()));
            numberTxtField.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20));
            numberTxtField.setEditable(false);
            panel.add(numberTxtField);

            JTextField firstnameTxtField = new JTextField(" "+player.getFirstname()+" ");
            firstnameTxtField.setBackground(ClubManagerConstraints.APP.getClub().getColor1());
            firstnameTxtField.setBorder(new LineBorder(ClubManagerConstraints.APP.getClub().getColor2()));
            firstnameTxtField.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20));
            firstnameTxtField.setEditable(false);
            panel.add(firstnameTxtField);

            JTextField lastnameTxtField = new JTextField(" "+player.getLastname()+" ");
            lastnameTxtField.setBackground(ClubManagerConstraints.APP.getClub().getColor1());
            lastnameTxtField.setBorder(new LineBorder(ClubManagerConstraints.APP.getClub().getColor2()));
            lastnameTxtField.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20));
            lastnameTxtField.setEditable(false);
            panel.add(lastnameTxtField);

            MenuButton editBtn = new MenuButton("");
            Image icon = new ImageIcon(ClubManagerConstraints.EDITLOGOPATH).getImage();
            editBtn.setIcon(new ImageIcon(icon));
            
            MenuButton deleteBtn = new MenuButton("");
            icon = new ImageIcon(ClubManagerConstraints.DELETELOGOPATH).getImage();
            deleteBtn.setIcon(new ImageIcon(icon));

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
                    firstnameTxtField.setEditable(true);
                    lastnameTxtField.setEditable(true);
                    confirmBtn.setVisible(true);
                    cancelBtn.setVisible(true);
                    editBtn.setEnabled(false);
                }
            });

            deleteBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    ClubManagerConstraints.APP.deletePlayer(player);

                    ClubManagerConstraints.APP.getMainGui().getMainPanel().removeAll();

                    new PlayersPanel().init();
    
                    ClubManagerConstraints.APP.getMainGui().revalidate();
                    ClubManagerConstraints.APP.getMainGui().repaint();
                }
            });

            confirmBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    numberTxtField.setEditable(false);
                    firstnameTxtField.setEditable(false);
                    lastnameTxtField.setEditable(false);
                    editBtn.setEnabled(true);
                    confirmBtn.setVisible(false);
                    cancelBtn.setVisible(false);

                    player.setNumber(Integer.parseInt(numberTxtField.getText()));
                    player.setFirstname(firstnameTxtField.getText());
                    ClubManagerConstraints.APP.getClub().updatePlayer(player);
                }
            });

            cancelBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    numberTxtField.setEditable(false);
                    firstnameTxtField.setEditable(false);
                    lastnameTxtField.setEditable(false);
                    editBtn.setEnabled(true);
                    confirmBtn.setVisible(false);
                    cancelBtn.setVisible(false);
                }
            });

            panel.add(editBtn, "gapleft 50");
            panel.add(deleteBtn);
            panel.add(confirmBtn);
            panel.add(cancelBtn, "wrap");
        }
        
        MenuButton addPlayerBtn = new MenuButton("");
        Image icon = new ImageIcon(ClubManagerConstraints.ADDLOGOPATH).getImage();
        addPlayerBtn.setIcon(new ImageIcon(icon));
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

        panel.add(addPlayerBtn, "gaptop 50, wrap");

        scrollPane.setViewportView(panel);
        
        ClubManagerConstraints.APP.getMainGui().getMainPanel().add(scrollPane, "grow, wrap");
    }
}
