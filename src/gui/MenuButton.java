package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import backend.*;

public class MenuButton extends JButton
{
    private static final long serialVersionUID = 1L;
    
    String buttonImgSrc = "";

    public MenuButton(String buttonText)
    {
        setText(buttonText);
        setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 40));
        
        if (Application.getCurrentClub() != null)
        {
            int red = Application.getCurrentClub().getColor2().getRed();
            int green = Application.getCurrentClub().getColor2().getGreen();
            int blue = Application.getCurrentClub().getColor2().getBlue();
            Color color = new Color(red, green, blue);
            setBackground(color);
        }
        else
        {
            setBackground(Color.RED);
        }

        
        setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
    }

    public String getButtonImgSrc()
    {
        return buttonImgSrc;
    }

    public void setButtonImgSrc(String buttonImgSrc)
    {
        setIcon(new ImageIcon(buttonImgSrc));
    }
}
