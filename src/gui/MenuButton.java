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
        setForeground(app.getContrastColor(getBackground()));
    }
}
