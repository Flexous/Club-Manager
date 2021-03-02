package Gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Backend.*;

public class MenuButton extends JButton
{
    private static final long serialVersionUID = 1L;
    
    String buttonImgSrc = "";

    public MenuButton(String buttonText)
    {
        setText(buttonText);
        setFont(new Font(ClubManagerConstraints.APPFONT, Font.BOLD, 40));
        
        int red = Application.getCurrentClub().getColor2().getRed();
        int green = Application.getCurrentClub().getColor2().getGreen();
        int blue = Application.getCurrentClub().getColor2().getBlue();
        Color color = new Color(red, green, blue);
        setBackground(color);
        
        setForeground(ClubManagerFunctions.getContrastColor(getBackground()));
    }

    public String getButtonImgSrc()
    {
        return this.buttonImgSrc;
    }

    public void setButtonImgSrc(String buttonImgSrc)
    {
        setIcon(new ImageIcon(buttonImgSrc));
    }
}
