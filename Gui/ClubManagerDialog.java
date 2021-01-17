package Gui;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import Backend.Application;
import net.miginfocom.swing.MigLayout;

public class ClubManagerDialog extends JDialog
{
    private static final long serialVersionUID = 3325463766607869978L;
 
    private JPanel panel = new JPanel();

    public ClubManagerDialog()
    {
        setPanelSettings();
        setMinimumSize(new DimensionUIResource(700, 700));
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        //setResizable(true);
        setVisible(false);

        MenuButton closeDialogBtn = new MenuButton("");
        closeDialogBtn.setButtonImgSrc(Application.propertiesHandler.getValueFromProperty("CloseWindowLogo"));
        closeDialogBtn.setBackground(Color.RED);
        
        closeDialogBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                setVisible(false);
            }
        });
        panel.add(closeDialogBtn, "gapleft 550, wrap");
    }

    public void setPanelSettings()
    {
        panel.setLayout(new MigLayout());
        panel.setBackground(Color.BLACK);
        panel.setBorder(BorderFactory.createLineBorder(Color.CYAN));
    }

    public JPanel getPanel()
    {
        return panel;
    }
}
