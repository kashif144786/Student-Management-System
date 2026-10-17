package com.sms.ui;

import javax.swing.*;

import com.sms.dao.StudentDAO;

public class DeleteStudentFrame extends JFrame {

    public DeleteStudentFrame() {

        setTitle("Delete Student");
        setSize(400, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField();

        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back");

        idLabel.setBounds(50, 50, 100, 30);
        idField.setBounds(150, 50, 150, 30);

        deleteButton.setBounds(70, 130, 100, 30);
        backButton.setBounds(200, 130, 100, 30);

        add(idLabel);
        add(idField);

        add(deleteButton);
        add(backButton);

        deleteButton.addActionListener(e -> {

            int id = Integer.parseInt(idField.getText());

            StudentDAO dao = new StudentDAO();

           boolean deleted = dao.deleteStudent(id);

            if(deleted) {

                JOptionPane.showMessageDialog(
                        this,
                        "Student Deleted Successfully");

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Student ID Not Found");
            }

        });

        backButton.addActionListener(e -> {

            dispose();
            new DashboardFrame();

        });

        setVisible(true);
    }
}