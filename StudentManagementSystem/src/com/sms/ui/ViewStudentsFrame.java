package com.sms.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import com.sms.dao.StudentDAO;
import com.sms.model.Student;

public class ViewStudentsFrame extends JFrame {

    public ViewStudentsFrame() {

        setTitle("View Students");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        String[] columnNames = {"ID", "Name", "Age", "Course", "Email"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 640, 250);

        JButton backButton = new JButton("Back");
        backButton.setBounds(280, 300, 100, 30);

        add(scrollPane);
        add(backButton);

        StudentDAO dao = new StudentDAO();
        ArrayList<Student> studentList = dao.getAllStudents();

        for(Student student : studentList) {

            Object[] row = {
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getCourse(),
                student.getEmail()
            };

            model.addRow(row);
        }

        backButton.addActionListener(e -> {
            dispose();
            new DashboardFrame();
        });

        setVisible(true);
    }
}