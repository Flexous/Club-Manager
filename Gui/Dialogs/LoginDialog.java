package Gui.Dialogs;

import java.awt.Color;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Backend.Application;
import Backend.ClubManagerFunctions;
import Gui.MenuButton;
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
    }

    public void initLoginDialog()
    {
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(ClubManagerFunctions.getContrastColor(getPanel().getBackground()));
        getPanel().add(usernameLabel, "gapleft 50, wrap");
        
        JTextField usernameField = new JTextField(20);
        getPanel().add(usernameField, "gapleft 50, wrap");

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(ClubManagerFunctions.getContrastColor(getPanel().getBackground()));
        getPanel().add(passwordLabel, "gapleft 50, wrap");
        
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setEchoChar('*');
        getPanel().add(passwordField, "gapleft 50, wrap");

        MenuButton loginButton = new MenuButton("Anmelden");
        loginButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) 
            {
                if (usernameField.getText().isEmpty())
                {
                    usernameField.setBackground(Color.RED);
                    return;
                }

                if (passwordField.getPassword().toString().isEmpty())
                {
                    passwordField.setBackground(Color.RED);
                    return;
                }
                
                if (!Application.loginManager.userExists(usernameField.getText()))
                {
                    usernameField.setBackground(Color.RED);
                    return;
                }

                if (!Application.loginManager.passwordCorrect(passwordField.getPassword().toString()))
                {
                    passwordField.setBackground(Color.RED);
                    return;
                }

                User user = new User(usernameField.getText());
                Application.setCurrentUser(user);
                setVisible(false);
                Application.initMainGui();
            }
        });
        getPanel().add(loginButton, "gapleft 50, gaptop 50, wrap");
        
        MenuButton openCreateUserMenuBtn = new MenuButton("Benutzer erstellen");
        openCreateUserMenuBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                getPanel().removeAll();

                JLabel usernameLabel = new JLabel("Username:");
                usernameLabel.setForeground(ClubManagerFunctions.getContrastColor(getPanel().getBackground()));
                getPanel().add(usernameLabel, "gapleft 50, wrap");
                
                JTextField usernameField = new JTextField(20);
                getPanel().add(usernameField, "gapleft 50, wrap");
        
                JLabel passwordLabel = new JLabel("Password:");
                passwordLabel.setForeground(ClubManagerFunctions.getContrastColor(getPanel().getBackground()));
                getPanel().add(passwordLabel, "gapleft 50, gaptop 20, wrap");

                JPasswordField passwordField = new JPasswordField(20);
                passwordField.setEchoChar('*');
                getPanel().add(passwordField, "gapleft 50, wrap");

                JLabel repeatPasswordLabel = new JLabel("Repeat Password:");
                repeatPasswordLabel.setForeground(ClubManagerFunctions.getContrastColor(getPanel().getBackground()));
                getPanel().add(repeatPasswordLabel, "gapleft 50, gaptop 20, wrap");
                
                JPasswordField repeatPasswordField = new JPasswordField(20);
                repeatPasswordField.setEchoChar('*');
                getPanel().add(repeatPasswordField, "gapleft 50, wrap");

                MenuButton createUserBtn = new MenuButton("Registrieren");
                createUserBtn.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        boolean inputIsValid = true;

                        if (usernameField.getText().isEmpty())
                        {
                            usernameField.setBackground(Color.RED);
                            inputIsValid = false;
                        }
                        if (passwordField.getPassword().toString().isEmpty())
                        {
                            passwordField.setBackground(Color.RED);
                            inputIsValid = false;
                        }
                        if (repeatPasswordField.getPassword().toString().isEmpty())
                        {
                            repeatPasswordField.setBackground(Color.RED);
                            inputIsValid = false;
                        }

                        if (!passwordField.getPassword().toString().equals(repeatPasswordField.getPassword().toString()))
                        {
                            repeatPasswordField.setBackground(Color.RED);
                            inputIsValid = false;
                        }

                        if (inputIsValid)
                        {
                            if (Application.registrationManager.
                            checkUserRegistration(usernameField.getText(), passwordField.getPassword().toString()))
                            {
                                setVisible(false);
                                User user = new User(usernameField.getText());
                                Application.setCurrentUser(user);
                                Application.initMainGui();
                            }
                        }
                    }
                });
                getPanel().add(createUserBtn, "gapleft 50, gaptop 50, wrap");

                revalidate();
                repaint();
            }
        });
        getPanel().add(openCreateUserMenuBtn, "gapleft 50, gaptop 50, wrap");

        add(getPanel());
        setVisible(true);
    }
}
