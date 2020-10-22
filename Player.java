import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Player 
{
    String name;
    String imgSrc="";
    String nationality;
    int age;

    public Player(String name)
    {
        this.name = name;
    }    

    public static void initAddPlayerFrame()
    {
        Application.panel.removeAll();
        Application.frame.setTitle("Spieler hinzufügen");

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);

        JButton confirmButton = new JButton("Bestätigen");
        
        Application.panel.add(nameLabel);
        Application.panel.add(nameField);
        Application.panel.add(confirmButton);

        Application.frame.repaint();
        Application.frame.revalidate();
    }
}
