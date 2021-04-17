package gui;

import backend.*;
import gui.panels.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class MainGui extends JFrame 
{
    private static final long serialVersionUID = 1745017706827567279L;

    private JPanel menuPanel = new JPanel();
    private JPanel mainPanel = new JPanel();

    public MainGui()
    {
        setTitle("Club Manager");
        setIconImage(new ImageIcon(ClubManagerConstraints.DEFAULTLOGOPATH).getImage());
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent we) 
            {
                Object[] options = {ClubManagerConstraints.LANGUAGE.getString("Yes"), ClubManagerConstraints.LANGUAGE.getString("No")};

                int selection = JOptionPane.showOptionDialog(null, ClubManagerConstraints.LANGUAGE.getString("DoYouWantToSave"), "", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                if (selection == JOptionPane.YES_OPTION)
                {
                    ClubManagerConstraints.APP.saveClubToPropertyFile();
                }
                else if (selection == JOptionPane.CLOSED_OPTION)
                {
                    return;
                }
                
                ClubManagerConstraints.APP.getLogger().info("Club Manager is closing.");
                System.exit(0);
            }
        } );
    }

    public JPanel getMenuPanel()
    {
        return menuPanel;
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public void init()
    {
        menuPanel.setLayout(new MigLayout("fill, align 20% 20%"));
        mainPanel.setLayout(new MigLayout("fill"));

        try 
        {
            if (ClubManagerConstraints.APP.getClub() != null)
            {
                setLayout(new MigLayout("", "[20%][80%]", "[100%]"));

                if (ClubManagerConstraints.APP.getClub().getColor1() != null)
                {
                    menuPanel.setBackground(ClubManagerConstraints.APP.getClub().getColor1());
                    mainPanel.setBackground(ClubManagerConstraints.APP.getClub().getColor1());
                }
                else
                {
                    menuPanel.setBackground(Color.WHITE);
                    mainPanel.setBackground(Color.WHITE);
                }

                if (ClubManagerConstraints.APP.getClub().getColor2() != null)
                {
                    menuPanel.setBorder(BorderFactory.createLineBorder(ClubManagerConstraints.APP.getClub().getColor2()));
                    mainPanel.setBorder(BorderFactory.createLineBorder(ClubManagerConstraints.APP.getClub().getColor2()));
                    getContentPane().setBackground(ClubManagerConstraints.APP.getClub().getColor2());
                }
                else
                {
                    menuPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    mainPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                    getContentPane().setBackground(Color.WHITE);
                }

                String logoPath = ClubManagerConstraints.APP.getClub().getLogo();

                if (!logoPath.isEmpty())
                {
                    setIconImage(new ImageIcon(logoPath).getImage());
                }

                JLabel currentClubLabel = new JLabel(ClubManagerConstraints.APP.getClub().getName());
                currentClubLabel.setForeground(ClubManagerConstraints.APP.getContrastColor(mainPanel.getBackground()));
                currentClubLabel.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20));
                menuPanel.add(currentClubLabel, "top, wrap");
        
                MenuButton editClubBtn = new MenuButton(ClubManagerConstraints.LANGUAGE.getString("ShowClub"));
                editClubBtn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        
                    }
                });

                menuPanel.add(editClubBtn, "top, wrap");

                MenuButton showPlayersBtn = new MenuButton(ClubManagerConstraints.LANGUAGE.getString("ShowPlayers"));
                showPlayersBtn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        mainPanel.removeAll();
                        new PlayersPanel().init();
                        revalidate();
                        repaint();
                    }
                });
    
                menuPanel.add(showPlayersBtn, "top, wrap");

                MenuButton exitAppBtn = new MenuButton(ClubManagerConstraints.LANGUAGE.getString("Exit"));
                exitAppBtn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        Object[] options = {ClubManagerConstraints.LANGUAGE.getString("Yes"), ClubManagerConstraints.LANGUAGE.getString("No")};

                        int selection = JOptionPane.showOptionDialog(null, ClubManagerConstraints.LANGUAGE.getString("DoYouWantToSave"), "", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        
                        if (selection == JOptionPane.YES_OPTION)
                        {
                            ClubManagerConstraints.APP.saveClubToPropertyFile();
                        }
                        else if (selection == JOptionPane.CLOSED_OPTION)
                        {
                            return;
                        }
                        
                        System.exit(0);
                    }
                });

                menuPanel.add(exitAppBtn, "top, wrap");

                add(menuPanel, "grow");
            }
            else
            {
                setLayout(new MigLayout("align 50% 50%"));

                getContentPane().setBackground(Color.WHITE);
                mainPanel.setBackground(Color.WHITE);

                JLabel createClubLabel = new JLabel(ClubManagerConstraints.LANGUAGE.getString("NoClubCreated"));
                createClubLabel.setForeground(ClubManagerConstraints.APP.getContrastColor(mainPanel.getBackground()));
                createClubLabel.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 40));
                mainPanel.add(createClubLabel, "wrap");
                
                MenuButton createClubButton = new MenuButton(ClubManagerConstraints.LANGUAGE.getString("CreateClub"));
                createClubButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        mainPanel.removeAll();
                        new CreateClubPanel().init();
                        revalidate();
                        repaint();
                    }
                });
        
                mainPanel.add(createClubButton);
            }
        } 
        catch (Exception e) 
        {
            ClubManagerConstraints.APP.getLogger().warning(e.getMessage());
        }

        add(mainPanel, "grow");
        repaint();
        revalidate();
    }
}
