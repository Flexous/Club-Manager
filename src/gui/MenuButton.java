package gui;

import java.awt.*;
import javax.swing.*;

import backend.*;

public class MenuButton extends JButton
{
    private static final long serialVersionUID = 1L;
    
    public MenuButton(String buttonText)
    {
        setText(buttonText);
        setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 40));
        
        if (ClubManagerConstraints.APP.getClub() != null)
        {
            int red = ClubManagerConstraints.APP.getClub().getColor2().getRed();
            int green = ClubManagerConstraints.APP.getClub().getColor2().getGreen();
            int blue = ClubManagerConstraints.APP.getClub().getColor2().getBlue();
            Color color = new Color(red, green, blue);
            setBackground(color);
        }
        else
        {
            setBackground(Color.RED);
        } 
        setForeground(ClubManagerConstraints.APP.getContrastColor(getBackground()));
    }
}
