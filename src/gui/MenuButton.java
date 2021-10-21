package gui;

import java.awt.*;
import javax.swing.*;

import backend.*;

public class MenuButton extends JButton
{
    private static final long serialVersionUID = 1L;
    
    public MenuButton(Application app, String buttonText)
    {
        setText(buttonText);
        setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 40));
        
        if (app.getClub() != null)
        {
            int red = app.getClub().getColor2().getRed();
            int green = app.getClub().getColor2().getGreen();
            int blue = app.getClub().getColor2().getBlue();
            Color color = new Color(red, green, blue);
            setBackground(color);
        }
        else
        {
            setBackground(Color.RED);
        } 
        setForeground(app.getContrastColor(getBackground()));
    }
}
