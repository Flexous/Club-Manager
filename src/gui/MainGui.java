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
                setLayout(new MigLayout("", "[20%][80%]", "[100%]"));

                menuPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                mainPanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                getContentPane().setBackground(Color.WHITE);

                String logoPath = app.getClub().getLogo();

                if (!logoPath.isEmpty())
                {
                    setIconImage(new ImageIcon(logoPath).getImage());
                }

                JLabel currentClubLabel = new JLabel(app.getClub().getName());
                currentClubLabel.setForeground(app.getContrastColor(mainPanel.getBackground()));
                currentClubLabel.setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 20));
                menuPanel.add(currentClubLabel, "top, wrap");
        
                MenuButton editClubBtn = new MenuButton(app, app.getLanguage().getString("ShowClub"));
                editClubBtn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        
                    }
                });

                menuPanel.add(editClubBtn, "top, wrap, gaptop 50");

                MenuButton showPlayersBtn = new MenuButton(app, app.getLanguage().getString("ShowPlayers"));
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
    
                menuPanel.add(showPlayersBtn, "wrap, gaptop 50");

                MenuButton showEmployeesBtn = new MenuButton(app, "Trainer/Betreuer");
                showEmployeesBtn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        mainPanel.removeAll();
                        revalidate();
                        repaint();
                    }
                });

                menuPanel.add(showEmployeesBtn, "wrap, gaptop 50");

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
    }
}
