package Gui;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Backend.Application;

public class MainGui extends JFrame 
{
    private static final long serialVersionUID = 1745017706827567279L;

    private MenuButton openAddClubDialogBtn = new MenuButton("Add Club");
    private MenuButton openAddPlayerToClubDialogBtn = new MenuButton("Add Player to Club");

    private AddClubDialog addClubDialog;
    private AddPlayerToClubDialog addPlayerToClubDialog;

    private JPanel panel = new JPanel();

    public MainGui(String guiTitle)
    {
        setTitle(guiTitle);
        createMainGuiMenu();
        setIconImage(new ImageIcon(Application.propertiesHandler.getValueFromProperty("DefaultLogo")).getImage());
        setResizable(false);
        setUndecorated(true);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
    }

    public void createMainGuiMenu()
    {
        this.panel.setBackground(Color.BLACK);

        this.openAddClubDialogBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                showAddClubDialog();
            } 
        });

        this.openAddPlayerToClubDialogBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                showAddPlayerToClubDialog();
            } 
        });

        MenuButton closeMainGuiBtn = new MenuButton("");
        closeMainGuiBtn.setButtonImgSrc(Application.propertiesHandler.getValueFromProperty("CloseWindowLogo"));
        closeMainGuiBtn.setBackground(Color.RED);

        closeMainGuiBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
            }
        });

        this.panel.add(this.openAddClubDialogBtn);
        this.panel.add(this.openAddPlayerToClubDialogBtn);

        //Special buttons cause epic

        this.panel.add(closeMainGuiBtn);

        this.add(panel);
    }

    public void showAddClubDialog()
    {
        if (this.addClubDialog == null)
        {
            this.addClubDialog = new AddClubDialog();
        }
        this.addClubDialog.setVisible(true);
    }

    public void showAddPlayerToClubDialog()
    {
        if (this.addPlayerToClubDialog == null)
        {
            this.addPlayerToClubDialog = new AddPlayerToClubDialog();
        }
        this.addPlayerToClubDialog.setVisible(true);
    }
}
