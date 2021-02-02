package Gui;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Backend.Application;
import Objects.User;

public class LoginDialog extends ClubManagerDialog 
{
    private static final long serialVersionUID = 3605841499671255064L;

    public LoginDialog()
    {
        super();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() 
        {
            public void windowClosed(WindowEvent e)
            {
                System.exit(0);
            }
        });

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        getPanel().add(usernameLabel, "gapleft 50, wrap");
        
        JTextField usernameField = new JTextField(20);
        getPanel().add(usernameField, "gapleft 50, wrap");

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        getPanel().add(passwordLabel, "gapleft 50, wrap");
        
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setEchoChar('*');
        getPanel().add(passwordField, "gapleft 50, wrap");

        MenuButton createClubBtn = new MenuButton("User erstellen");
        createClubBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                User user = new User(usernameField.getText());
                Application.currentUser = user;
                setVisible(false);
                Application.initMainGui();
            }
        });
        getPanel().add(createClubBtn, "gapleft 50, gaptop 150, wrap");

        add(getPanel());
        setVisible(true);
    }
}
