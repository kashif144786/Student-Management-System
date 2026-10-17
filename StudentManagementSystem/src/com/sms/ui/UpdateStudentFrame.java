package com.sms.ui;

import javax.swing.*;
import com.sms.dao.StudentDAO;

public class UpdateStudentFrame extends JFrame {

    public UpdateStudentFrame() {

        setTitle("Update Student");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel idLabel = new JLabel("Student ID:");
        JTextField idField = new JTextField();

        JLabel valueLabel = new JLabel("New Value:");
        JTextField valueField = new JTextField();

        JRadioButton nameRadio = new JRadioButton("Name");
        JRadioButton ageRadio = new JRadioButton("Age");
        JRadioButton courseRadio = new JRadioButton("Course");
        JRadioButton emailRadio = new JRadioButton("Email");

        JButton updateButton = new JButton("Update");
        JButton backButton = new JButton("Back");

        ButtonGroup group = new ButtonGroup();

        group.add(nameRadio);
        group.add(ageRadio);
        group.add(courseRadio);
        group.add(emailRadio);

        idLabel.setBounds(50, 40, 100, 30);
        idField.setBounds(170, 40, 150, 30);

        nameRadio.setBounds(50, 100, 100, 30);
        ageRadio.setBounds(170, 100, 100, 30);

        courseRadio.setBounds(50, 150, 100, 30);
        emailRadio.setBounds(170, 150, 100, 30);

        valueLabel.setBounds(50, 220, 100, 30);
        valueField.setBounds(170, 220, 150, 30);

        updateButton.setBounds(80, 300, 100, 30);
        backButton.setBounds(220, 300, 100, 30);

        add(idLabel);
        add(idField);

        add(nameRadio);
        add(ageRadio);
        add(courseRadio);
        add(emailRadio);

        add(valueLabel);
        add(valueField);

        add(updateButton);
        add(backButton);
        
        updateButton.addActionListener(e -> {

            int id = Integer.parseInt(idField.getText());

            String newValue = valueField.getText();

            int choice = 0;

            if(nameRadio.isSelected()) {
                choice = 1;
            }
            else if(ageRadio.isSelected()) {
                choice = 2;
            }
            else if(courseRadio.isSelected()) {
                choice = 3;
            }
            else if(emailRadio.isSelected()) {
                choice = 4;
            }

            if(choice == 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please select a field to update");

                return;
            }

            StudentDAO dao = new StudentDAO();

            dao.updateStudent(id, choice, newValue);

            JOptionPane.showMessageDialog(
                    this,
                    "Student Updated Successfully");
        });
        
        backButton.addActionListener(e -> {

            dispose();
            new DashboardFrame();

        });

        setVisible(true);
    }

}