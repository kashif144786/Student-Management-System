package com.sms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sms.model.Student;
import com.sms.util.DBConnection;

public class StudentDAO {
	public void addStudent(Student student) {
		Connection con = DBConnection.getConnection();
		String query = "INSERT INTO Students(name, age, course, email) VALUES(?,?,?,?)";
		try
		{
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, student.getName());
			ps.setInt(2, student.getAge());
			ps.setString(3, student.getCourse());
			ps.setString(4, student.getEmail());
			ps.executeUpdate();
			
			System.out.println("Student added successfully.");
			
		} catch(Exception e) {
			System.out.println(e);
		}
						
	}
	
	//=================================================================
	
	//For Console OUTPUT
	public void viewStudents() {
		Connection con = DBConnection.getConnection();
		String query = "SELECT * FROM Students";
		
		try
		{
			if(con == null) {
			    System.out.println("Database connection failed");
			    return;
			}
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println(
					    "ID: " + rs.getInt("id") +
					    ", Name: " + rs.getString("name") +
					    ", Age: " + rs.getInt("age") +
					    ", Course: " + rs.getString("course") +
					    ", Email: " + rs.getString("email")
					);
			}
			con.close();
		} catch(Exception e)
		
		{
			System.out.println(e);
		}
	}
	
	//For GUI OUTPUT
	public ArrayList<Student> getAllStudents() {
	    
	    ArrayList<Student> studentList = new ArrayList<>();

	    Connection con = DBConnection.getConnection();
	    String query = "SELECT * FROM Students";

	    try {
	        PreparedStatement ps = con.prepareStatement(query);
	        ResultSet rs = ps.executeQuery();

	        while(rs.next()) {

	            Student student = new Student(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getInt("age"),
	                rs.getString("course"),
	                rs.getString("email")
	            );

	            studentList.add(student);
	        }

	        rs.close();
	        ps.close();
	        con.close();

	    } catch(Exception e) {
	        System.out.println(e);
	    }

	    return studentList;
	}
	
	//==================================================================
	
	//For Console OUTPUT
	public void searchStudent(int id) {
		Connection con = DBConnection.getConnection();
		String query = "SELECT * FROM Students WHERE id = ?";
		
		try
		{
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				System.out.println(
					    "ID: " + rs.getInt("id") +
					    ", Name: " + rs.getString("name") +
					    ", Age: " + rs.getInt("age") +
					    ", Course: " + rs.getString("course") +
					    ", Email: " + rs.getString("email")
					);
			}
			rs.close();
			ps.close();
			con.close();
			
		} catch(Exception e)
		
		{
			System.out.println(e);
		}
	} 
	
	//For GUI OUTPUT
	public Student getStudentById(int id) {

	    Connection con = DBConnection.getConnection();
	    String query = "SELECT * FROM Students WHERE id = ?";

	    try {

	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setInt(1, id);

	        ResultSet rs = ps.executeQuery();

	        if(rs.next()) {

	            return new Student(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getInt("age"),
	                rs.getString("course"),
	                rs.getString("email")
	            );
	        }

	    } catch(Exception e) {
	        System.out.println(e);
	    }

	    return null;
	}
	
	//==================================================================
	
	public boolean deleteStudent(int id) {

	    Connection con = DBConnection.getConnection();
	    String query = "DELETE FROM Students WHERE id = ?";

	    try {

	        PreparedStatement ps = con.prepareStatement(query);

	        ps.setInt(1, id);

	        int rows = ps.executeUpdate();

	        if(rows > 0) {

	            ps.close();
	            con.close();

	            return true;
	        }
	        else {

	            ps.close();
	            con.close();

	            return false;
	        }

	    } catch(Exception e) {

	        System.out.println(e);
	    }

	    return false;
	}
	
//=======================================================================
	
	public void updateStudent(int id, int choice, String newValue) {
		Connection con = DBConnection.getConnection();
		String query = "SELECT * FROM Students WHERE id = ? ";
		
		try
		{
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				switch(choice)
				{
				case 1:
					String updateName = "UPDATE Students SET name = ? WHERE id = ?";
					PreparedStatement ps1 = con.prepareStatement(updateName);
					ps1.setString(1, newValue);
					ps1.setInt(2, id);
					ps1.executeUpdate();
					System.out.println("Data Updated.");
					ps1.close();
					con.close();
					break;
					
				case 2:
					String updateAge = "UPDATE Students SET age = ? WHERE id = ?";
					PreparedStatement ps2 = con.prepareStatement(updateAge);
					ps2.setInt(1, Integer.parseInt(newValue));
					ps2.setInt(2, id);
					ps2.executeUpdate();
					System.out.println("Data Updated.");
					ps2.close();
					con.close();
					break;
					
				case 3:
					String updateCourse = "UPDATE Students SET course = ? WHERE id = ?";
					PreparedStatement ps3 = con.prepareStatement(updateCourse);
					ps3.setString(1, newValue);
					ps3.setInt(2, id);
					ps3.executeUpdate();
					System.out.println("Data Updated.");
					ps3.close();
					con.close();
					break;
					
				case 4:
					String updateEmail = "UPDATE Students SET email = ? WHERE id = ?";
					PreparedStatement ps4 = con.prepareStatement(updateEmail);
					ps4.setString(1, newValue);
					ps4.setInt(2, id);
					ps4.executeUpdate();
					System.out.println("Data Updated.");
					ps4.close();
					con.close();
					break;
					
				default:
					System.out.println("Invalid Choice");
					break;
					
				}
			} else {
				System.out.println("ID does not exists.");
			}
			rs.close();
			
			
		} catch (Exception e)
		
		{
			System.out.println(e);
		}
	}
	//============================================================================
	//Delete this if frontend does not worked
	public boolean updateStudent(Student student) {
		Connection con = DBConnection.getConnection();
		String query = "UPDATE Students SET name = ?, age = ?, course = ?, email = ? WHERE id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, student.getName());
			ps.setInt(2, student.getAge());
			ps.setString(3, student.getCourse());
			ps.setString(4, student.getEmail());
			ps.setInt(5, student.getId());

			int rows = ps.executeUpdate();

			ps.close();
			con.close();

			return rows > 0;

		} catch(Exception e) {
			System.out.println(e);
		}

		return false;
	}

}
