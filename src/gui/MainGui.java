package gui;

import backend.*;
import gui.panels.*;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Handler;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class MainGui extends JFrame 
{
    private static final long serialVersionUID = 1745017706827567279L;

    private Application app;

    private JPanel menuPanel = new JPanel();
    private JPanel mainPanel = new JPanel();

    public MainGui(Application app)
    {
        this.app = app;

        try 
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) 
                {
            app.getLogger().warning(e.getMessage());
        }

        setTitle("Club Manager");
        setIconImage(new ImageIcon().getImage());
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent we) 
            {
                app.getLogger().info("Club Manager is closing.");
                for (Handler handler : app.getLogger().getHandlers())
                {
                    handler.close();
                }
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
        menuPanel.setLayout(new MigLayout());
        mainPanel.setLayout(new MigLayout("fill"));

        try 
        {
            if (app.getClub() != null)
            {
                setLayout(new MigLayout("", "[10%][90%]", "[100%]"));

                menuPanel.setBorder(BorderFactory.createLineBorder(app.getClub().getColor2()));
                menuPanel.setBackground(app.getClub().getColor1());
                mainPanel.setBorder(BorderFactory.createLineBorder(app.getClub().getColor2()));
                mainPanel.setBackground(app.getClub().getColor1());
                getContentPane().setBackground(Color.WHITE);

                boolean logoAvailable = true;

                String logoPath = app.getClub().getLogo();

                if (logoPath != null)
                {
                    if (!logoPath.isEmpty())
                    {
                        setIconImage(new ImageIcon(logoPath).getImage());
                    }
                    else
                    {
                        logoAvailable = false;
                    }
                }
                
                JLabel currentClubLabel = new JLabel(app.getClub().getName());
                currentClubLabel.setForeground(app.getContrastColor(mainPanel.getBackground()));
                currentClubLabel.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20));
                menuPanel.add(currentClubLabel, "top, wrap");
        
                MenuButton editClubBtn = new MenuButton(app, "");

                if (logoAvailable)
                {
                    ImageIcon imageIcon = new ImageIcon(logoPath);
                    Image image = imageIcon.getImage();
                    Image newimg = image.getScaledInstance(50, 50,  java.awt.Image.SCALE_SMOOTH);
                    ImageIcon newImageIcon = new ImageIcon(newimg);

                    editClubBtn.setIcon(newImageIcon);
                }

                editClubBtn.setToolTipText(app.getLanguage().getString("ShowClub"));
                editClubBtn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        
                    }
                });

                menuPanel.add(editClubBtn, "top, wrap, gaptop 50");

                MenuButton showPlayersBtn = new MenuButton(app, "");
                showPlayersBtn.setIcon(new ImageIcon("files/img/Players.png"));
                showPlayersBtn.setToolTipText(app.getLanguage().getString("ShowPlayers"));
                showPlayersBtn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        app.loadPlayersFromDb();

                        mainPanel.removeAll();
                        new PlayersPanel(app).init();
                        revalidate();
                        repaint();
                    }
                });
    
                menuPanel.add(showPlayersBtn, "wrap");

                MenuButton showManagerBtn = new MenuButton(app, "");
                showManagerBtn.setIcon(new ImageIcon("files/img/Manager.png"));
                showManagerBtn.setToolTipText(app.getLanguage().getString("ShowManager"));
                showManagerBtn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        app.loadManagerFromDb();

                        mainPanel.removeAll();
                        new ManagerPanel(app).init();
                        revalidate();
                        repaint();
                    }
                });

                menuPanel.add(showManagerBtn, "wrap");

                MenuButton showEmployeesBtn = new MenuButton(app, "");
                showEmployeesBtn.setIcon(new ImageIcon("files/img/Employees.png"));
                showEmployeesBtn.setToolTipText(app.getLanguage().getString("ShowEmployees"));
                showEmployeesBtn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        app.loadEmployeesFromDb();

                        mainPanel.removeAll();
                        new EmployeesPanel(app).init();
                        revalidate();
                        repaint();
                    }
                });

                menuPanel.add(showEmployeesBtn, "wrap");

                add(menuPanel, "grow");
            }
            else
            {
                setLayout(new MigLayout("align 50% 50%"));

                getContentPane().setBackground(Color.WHITE);
                mainPanel.setBackground(Color.WHITE);

                JLabel createClubLabel = new JLabel(app.getLanguage().getString("NoClubCreated"));
                createClubLabel.setForeground(app.getContrastColor(mainPanel.getBackground()));
                createClubLabel.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 40));
                mainPanel.add(createClubLabel, "wrap");
                
                MenuButton createClubButton = new MenuButton(app, app.getLanguage().getString("CreateClub"));
                createClubButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        mainPanel.removeAll();
                        new CreateClubPanel(app).init();
                        revalidate();
                        repaint();
                    }
                });
        
                mainPanel.add(createClubButton);
            }
        } 
        catch (Exception e) 
        {
            app.getLogger().warning(e.getMessage());
        }

        add(mainPanel, "grow");
        repaint();
        revalidate();
        app.getLogger().info("Gui is visible now.");
    }
}
