package com.sms.ui;
import javax.swing.*;

public class LoginFrame extends JFrame {

    public LoginFrame() {

        setTitle("Student Management System");
        setSize(500, 350);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");
        JButton exitButton = new JButton("Exit");

        usernameLabel.setBounds(50, 50, 100, 30);
        usernameField.setBounds(150, 50, 150, 30);

        passwordLabel.setBounds(50, 100, 100, 30);
        passwordField.setBounds(150, 100, 150, 30);

        loginButton.setBounds(90, 170, 90, 30);
        exitButton.setBounds(200, 170, 90, 30);

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(exitButton);

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        loginButton.addActionListener(e -> {

            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if(username.equals("admin") && password.equals("1234")) {
                dispose();
                new DashboardFrame();
            }

        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}