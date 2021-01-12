package Gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class ClubManagerDialog extends JDialog
{
    private static final long serialVersionUID = 3325463766607869978L;
 
    private JPanel panel = new JPanel();

    public ClubManagerDialog()
    {
        setPanelSettings();
        setMinimumSize(new DimensionUIResource(520, 520));
        setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(false);
    }

    public void setPanelSettings()
    {
        this.panel.setBackground(Color.BLACK);
        this.panel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
    }

    public JPanel getPanel()
    {
        return this.panel;
    }
}
