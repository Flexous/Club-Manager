package Backend;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Gui.MainGui;

import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Application
{
    private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    static MainGui mainGui;
    static JPanel panel = new JPanel();

    static ImageIcon imageIcon;

    public static void main(String[]args)
    {
        getConfig();

        initMainGui();
    }

    private static void getConfig() 
    {
        getLogger().info("Trying to load config");

        try 
        {
            BufferedReader fileReader = new BufferedReader(new FileReader("Config/ApplicationConfig.config"));

            String line = "";

            while((line=fileReader.readLine()) != null)
            {
                if (line.startsWith("DefaultLogo"))
                {
                    imageIcon = new ImageIcon(line.split("=")[1]);
                }
                /*else if (line.startsWith("Club"))
                {
                    if (line.contains("Logo"))
                    {
                        imageIcon = new ImageIcon(line.split("=")[1]);
                    }
                }*/
            }
            
            fileReader.close();
        } 
        catch (Exception e) 
        {
            getLogger().warning("Error reading config file\n" + e.getMessage());
			e.printStackTrace();
		}
    }

    private static void initMainGui() 
    {
        if (mainGui == null)
        {
            mainGui = new MainGui("Club Manager");
        }

        panel.removeAll();

        panel.setBackground(Color.gray);

        JMenuBar menubar = new JMenuBar();

        JMenu menu1 = new JMenu("Start");
        JMenuItem createClub = new JMenuItem("Neuen Club erstellen");
        JMenuItem save = new JMenuItem("Speichern");
        JMenuItem saveUnder = new JMenuItem("Speichern unter");
        menu1.add(createClub);
        menu1.add(save);
        menu1.add(saveUnder);
        
        JMenu menu2 = new JMenu("Hinzufügen");
        JMenuItem menuAddPlayer = new JMenuItem("Spieler hinzufügen");
        JMenuItem menuAddStadium = new JMenuItem("Stadion hinzufügen");
        menu2.add(menuAddPlayer);
        menu2.add(menuAddStadium);

        JMenu menu3 = new JMenu("Ansicht");
        JMenuItem showOrganisations = new JMenuItem("Organisationen anzeigen");
        menu3.add(showOrganisations);

        menubar.add(menu1);
        menubar.add(menu2);
        menubar.add(menu3);

        File file = new File("Config/ClubConfig.config");

        if (!file.exists())
        {
            save.setVisible(false);
            saveUnder.setVisible(false);
            menu2.setVisible(false);
            menu3.setVisible(false);
        }

        createClub.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                //Club.initCreateClubFrame();
            }
        });

        menuAddPlayer.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                //Player.initAddPlayerFrame();
            }
        });
    }

    public static Logger getLogger()
    {
        if (logger == null)
        {
            logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
            logger.setLevel(Level.ALL);
            Handler handler = new ConsoleHandler();
            handler.setLevel(Level.ALL);
            logger.addHandler(handler);
        }
        return logger;
    }
}