package Gui;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Backend.Application;
import net.miginfocom.swing.MigLayout;

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
        createMainGuiMenuWithoutClub();
        setIconImage(new ImageIcon(Application.propertiesHandler.getValueFromProperty("DefaultLogo", "App")).getImage());
        setResizable(true);
        //setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight()-10;
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void createMainGuiMenuWithoutClub()
    {
        panel.setLayout(new MigLayout());
        panel.setBackground(Color.BLACK);

        openAddClubDialogBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                showAddClubDialog();
            } 
        });

        openAddPlayerToClubDialogBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                showAddPlayerToClubDialog();
            } 
        });

        MenuButton closeMainGuiBtn = new MenuButton("");
        closeMainGuiBtn.setButtonImgSrc(Application.propertiesHandler.getValueFromProperty("CloseWindowLogo", "App"));
        closeMainGuiBtn.setBackground(Color.RED);

        MenuButton hideMainGuiBtn = new MenuButton("");
        hideMainGuiBtn.setButtonImgSrc(Application.propertiesHandler.getValueFromProperty("HideWindowLogo", "App"));
        hideMainGuiBtn.setBackground(Color.BLACK);

        hideMainGuiBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                setState(ICONIFIED);
            }
        });

        closeMainGuiBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
            }
        });

        panel.add(openAddClubDialogBtn);
        panel.add(openAddPlayerToClubDialogBtn, "gapleft 10");

        //Special buttons cause epic

        //panel.add(hideMainGuiBtn, "gapleft 700");
        //panel.add(closeMainGuiBtn, "gapleft 30");

        add(panel);
    }

    public void createMainGuiMenuWithClub()
    {
        openAddPlayerToClubDialogBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                showAddPlayerToClubDialog();
            } 
        });
    }

    public void showAddClubDialog()
    {
        if (addClubDialog == null)
        {
            addClubDialog = new AddClubDialog();
        }
        addClubDialog.setVisible(true);
    }

    public void showAddPlayerToClubDialog()
    {
        if (addPlayerToClubDialog == null)
        {
            addPlayerToClubDialog = new AddPlayerToClubDialog();
        }
        addPlayerToClubDialog.setVisible(true);
    }
}
