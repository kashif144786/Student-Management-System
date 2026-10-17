package com.sms.ui;

import javax.swing.*;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

public class SearchStudentFrame extends JFrame {

    public SearchStudentFrame() {

        setTitle("Search Student");
        setSize(450, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField();

        JButton searchButton = new JButton("Search");

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();

        JLabel courseLabel = new JLabel("Course:");
        JTextField courseField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JButton backButton = new JButton("Back");

        idLabel.setBounds(40, 30, 100, 30);
        idField.setBounds(140, 30, 100, 30);
        searchButton.setBounds(260, 30, 100, 30);

        nameLabel.setBounds(40, 90, 100, 30);
        nameField.setBounds(140, 90, 180, 30);

        ageLabel.setBounds(40, 140, 100, 30);
        ageField.setBounds(140, 140, 180, 30);

        courseLabel.setBounds(40, 190, 100, 30);
        courseField.setBounds(140, 190, 180, 30);

        emailLabel.setBounds(40, 240, 100, 30);
        emailField.setBounds(140, 240, 180, 30);

        backButton.setBounds(170, 300, 100, 30);

        add(idLabel);
        add(idField);
        add(searchButton);

        add(nameLabel);
        add(nameField);

        add(ageLabel);
        add(ageField);

        add(courseLabel);
        add(courseField);

        add(emailLabel);
        add(emailField);

        add(backButton);

        searchButton.addActionListener(e -> {

            int id = Integer.parseInt(idField.getText());

            StudentDAO dao = new StudentDAO();

            Student student = dao.getStudentById(id);

            if(student != null) {

                nameField.setText(student.getName());

                ageField.setText(
                        String.valueOf(student.getAge()));

                courseField.setText(
                        student.getCourse());

                emailField.setText(
                        student.getEmail());

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Student Not Found");

            }

        });

        backButton.addActionListener(e -> {

            dispose();
            new DashboardFrame();

        });

        setVisible(true);
    }
}