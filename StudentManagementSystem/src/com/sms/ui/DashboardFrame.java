package com.sms.ui;

import javax.swing.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {

        setTitle("Student Dashboard");
        setSize(400, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//============ADD STUDENT================================================
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(e -> {
            dispose();
            new AddStudentFrame();
        });
        
//=============VIEW STUDENT===============================================
        JButton viewButton = new JButton("View Students");
        viewButton.addActionListener(e -> {
            dispose();
            new ViewStudentsFrame();
        });

//==============UPDATE STUDENT===========================================
        JButton updateButton = new JButton("Update Student");
        updateButton.addActionListener(e -> {

            dispose();
            new UpdateStudentFrame();

        });
        
//==================DELETE STUDENT========================================
        JButton deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(e -> {

            dispose();
            new DeleteStudentFrame();

        });
        
//====================SEARCH STUDENT======================================
        JButton searchButton = new JButton("Search Student");
        searchButton.addActionListener(e -> {
            dispose();
            new SearchStudentFrame();
        });
        
        JButton logoutButton = new JButton("Logout");

        addButton.setBounds(100, 40, 180, 30);
        viewButton.setBounds(100, 90, 180, 30);
        updateButton.setBounds(100, 140, 180, 30);
        deleteButton.setBounds(100, 190, 180, 30);
        searchButton.setBounds(100, 240, 180, 30);
        logoutButton.setBounds(100, 290, 180, 30);

        add(addButton);
        add(viewButton);
        add(updateButton);
        add(deleteButton);
        add(searchButton);
        add(logoutButton);

        logoutButton.addActionListener(e -> {
            dispose();
            new LoginFrame();
        });

        setVisible(true);
    }
}