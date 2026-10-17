package com.sms.ui;

import javax.swing.*;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

public class AddStudentFrame extends JFrame {

    public AddStudentFrame() {

        setTitle("Add Student");
        setSize(400, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();

        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();

        JLabel courseLabel = new JLabel("Course:");
        JTextField courseField = new JTextField();

        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JButton saveButton = new JButton("Save");
        JButton backButton = new JButton("Back");

        nameLabel.setBounds(50, 90, 100, 30);
        nameField.setBounds(150, 90, 150, 30);

        ageLabel.setBounds(50, 140, 100, 30);
        ageField.setBounds(150, 140, 150, 30);

        courseLabel.setBounds(50, 190, 100, 30);
        courseField.setBounds(150, 190, 150, 30);

        emailLabel.setBounds(50, 240, 100, 30);
        emailField.setBounds(150, 240, 150, 30);

        saveButton.setBounds(90, 300, 90, 30);
        backButton.setBounds(200, 300, 90, 30);

        add(nameLabel);
        add(nameField);
        add(ageLabel);
        add(ageField);
        add(courseLabel);
        add(courseField);
        add(emailLabel);
        add(emailField);
        add(saveButton);
        add(backButton);

        saveButton.addActionListener(e -> {

            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String course = courseField.getText();
            String email = emailField.getText();

            Student student = new Student(name, age, course, email);

            StudentDAO dao = new StudentDAO();
            dao.addStudent(student);

            JOptionPane.showMessageDialog(this, "Student Added Successfully");

        });

        backButton.addActionListener(e -> {
            dispose();
            new DashboardFrame();
        });

        setVisible(true);
    }
}