import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Club 
{
    private String name;
    private String logoSrc="";
    private ArrayList<Player>players = new ArrayList<>();

    public Club(String name)
    {
        this.name = name;
    }

    public static void initCreateClubFrame()
    {
        Application.panel.removeAll();
        Application.frame.setTitle("Club erstellen");

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);

        JLabel foundationYearLabel = new JLabel("Gründungsjahr:");
        JTextField foundationYearField = new JTextField(4);

        JButton confirmButton = new JButton("Bestätigen");
        
        Application.panel.add(nameLabel);
        Application.panel.add(nameField);
        Application.panel.add(foundationYearLabel);
        Application.panel.add(foundationYearField);
        Application.panel.add(confirmButton);

        Application.frame.repaint();
        Application.frame.revalidate();
    }
}
