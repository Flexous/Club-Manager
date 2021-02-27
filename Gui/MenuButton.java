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
        setFont(new Font("Arial", Font.BOLD, 40));         
        setBackground(Color.RED);
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
