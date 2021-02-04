package Gui;

import java.lang.reflect.Field;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import Backend.Application;
import net.miginfocom.swing.MigLayout;

public class ClubManagerDialog extends JDialog
{
    private static final long serialVersionUID = 3325463766607869978L;

    private Color mainColor;
    private Color accentColor;
 
    private JPanel panel = new JPanel();

    public ClubManagerDialog()
    {
        setPanelSettings();
        setMinimumSize(new DimensionUIResource(700, 700));
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(false);
        setVisible(false);
    }

    public void setPanelSettings()
    {
        panel.setLayout(new MigLayout());

        Field field;
        try 
        {
            String propertyName = "";
            String propertyType = "";

            if (Application.getCurrentUser() == null)
            {
                propertyName = "DefaultColor";
                propertyType = "App";
            }
            else
            {
                propertyName = "Color";
                propertyType = "User";
            }

            field = Class.forName("java.awt.Color").getField(Application.propertiesHandler
            .getValueFromProperty(propertyName+"1", propertyType));
            setMainColor((Color)field.get(null));
            panel.setBackground(getMainColor());

            field = Class.forName("java.awt.Color").getField(Application.propertiesHandler
            .getValueFromProperty(propertyName+"2", propertyType));
            setAccentColor((Color)field.get(null));
            panel.setBorder(BorderFactory.createLineBorder(getAccentColor()));
        } 
        catch (Exception e) 
        {
            //Application.getLogger().warning("");
			e.printStackTrace();
        }
    }

    public Color getMainColor()
    {
        return mainColor;
    }

    public void setMainColor(Color color)
    {
        mainColor = color;
    }

    public Color getAccentColor()
    {
        return accentColor;
    }

    public void setAccentColor(Color color)
    {
        accentColor = color;
    }

    public JPanel getPanel()
    {
        return panel;
    }
}
