package Gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class MenuButton extends JButton
{
    private static final long serialVersionUID = 1L;
    
    public MenuButton(String buttonText)
    {
        this.setText(buttonText);
        this.setFont(new Font("Arial", Font.PLAIN, 40));        
        this.setBackground(Color.CYAN);
    }
}
