import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Application
{
    static JFrame frame = new JFrame("Club Manager");
    static JPanel panel = new JPanel();

    static Club club = new Club();

    static ImageIcon imageIcon;

    public static void main(String[]args)
    {
        getConfig();

        initFrame();
    }

    private static void getConfig() 
    {
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
			e.printStackTrace();
		}
    }

    private static void initFrame() 
    {
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
        JMenuItem addPlayer = new JMenuItem("Spieler hinzufügen");
        JMenuItem addStadium = new JMenuItem("Stadion hinzufügen");
        menu2.add(addPlayer);
        menu2.add(addStadium);

        JMenu menu3 = new JMenu("Ansicht");
        JMenuItem showOrganisations = new JMenuItem("Organisationen anzeigen");
        menu3.add(showOrganisations);

        menubar.add(menu1);
        menubar.add(menu2);
        menubar.add(menu3);

        frame.add(panel);
        frame.setIconImage(imageIcon.getImage());
        frame.setJMenuBar(menubar);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

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
                Club.initCreateClubFrame();
            }
        });

        addPlayer.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ev)
            {
                Player.initAddPlayerFrame();
            }
        });
    }
}