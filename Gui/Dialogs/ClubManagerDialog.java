package Gui.Dialogs;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import Backend.ClubManagerConstraints;
import net.miginfocom.swing.MigLayout;

public class ClubManagerDialog extends JDialog
{
    private static final long serialVersionUID = 3325463766607869978L;
 
    private JPanel panel = new JPanel();

    public ClubManagerDialog()
    {
        setPanelSettings();
        setIconImage(new ImageIcon(ClubManagerConstraints.DEFAULTLOGOPATH).getImage());
        setMinimumSize(new DimensionUIResource(700, 700));
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(false);
        setVisible(false);
    }

    public void setPanelSettings()
    {
        panel.setLayout(new MigLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    public JPanel getPanel()
    {
        return panel;
    }
}
