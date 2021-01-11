package Gui;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGui extends JFrame 
{
    private static final long serialVersionUID = 1745017706827567279L;

    private MenuButton openAddClubDialogBtn = new MenuButton("Add Club");
    private MenuButton openAddPlayerToClubDialogBtn = new MenuButton("Add Player to Club");

    private AddClubDialog addClubDialog;
    private AddPlayerToClubDialog addPlayerToClubDialog;

    private String guiTitle;
    private ImageIcon img = new ImageIcon("Img/1920px-FIFA_logo_without_slogan.svg.png");

    private JPanel panel = new JPanel();

    public MainGui(String guiTitle)
    {
        this.guiTitle = guiTitle;

        createMainGuiMenu();
        setIconImage(img.getImage());
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);
    }

    public String getGuiTitle()
    {
        return this.guiTitle;
    }

    public void setGuiTitle(String guiTitle)
    {
        this.guiTitle = guiTitle;
    }

    public void createMainGuiMenu()
    {
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

        this.panel.setBackground(Color.BLACK);
        this.panel.add(this.openAddClubDialogBtn);
        this.panel.add(this.openAddPlayerToClubDialogBtn);

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
