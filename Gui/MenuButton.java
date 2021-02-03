package Gui;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.Field;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import Backend.Application;

public class MenuButton extends JButton
{
    private static final long serialVersionUID = 1L;
    
    String buttonImgSrc = "";

    public MenuButton(String buttonText)
    {
        setText(buttonText);
        setFont(new Font("Arial", Font.BOLD, 40)); 

        Field field;

        try 
        {
            field = Class.forName("java.awt.Color").getField(Application.propertiesHandler
            .getValueFromProperty("DefaultTextColor", "App"));
            setForeground((Color)field.get(null));
        } 
        catch (Exception e) 
        {
            //Application.getLogger().warning("");
			e.printStackTrace();
        }
        
        try 
        {
            String propertyName = "";
            String propertyType = "";

            if (Application.currentUser == null)
            {
                propertyName = "DefaultColor2";
                propertyType = "App";
            }
            else
            {
                propertyName = "Color2";
                propertyType = "User";
            }

            field = Class.forName("java.awt.Color").getField(Application.propertiesHandler
            .getValueFromProperty(propertyName, propertyType));
            setBackground((Color)field.get(null));
        } 
        catch (Exception e) 
        {
            //Application.getLogger().warning("");
			e.printStackTrace();
        }
        //setBorder(new LineBorder(Color.WHITE, 1, true));
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
