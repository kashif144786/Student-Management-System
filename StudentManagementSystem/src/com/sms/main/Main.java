package com.sms.main;

import com.sms.model.*;

import java.util.Scanner;

import com.sms.dao.*;
import com.sms.util.*;


public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("===== STUDENT MANAGEMENT SYSTEM =====");
		System.out.println("1. Add Student");
		System.out.println("2. View Students");
		System.out.println("3. Search Students");
		System.out.println("4. Delete Student");
		System.out.println("5. Update Student");
		System.out.println("6. Exit");
		
		
		StudentDAO dao = new StudentDAO();
		
		while(true)
		{
			System.out.println();
			System.out.print("Enter MENU Option : ");
			int choice = sc.nextInt();
			switch(choice)
			{
			case 1:
				sc.nextLine();
				System.out.print("Enter Students Name : ");
				String name = sc.nextLine();
				System.out.print("Enter Age : ");
				int age = sc.nextInt();
				sc.nextLine();
				System.out.print("Enter Course name : ");
				String course = sc.nextLine();
				System.out.print("Enter Email : ");
				String email = sc.nextLine();
				
				Student student = new Student(name, age, course, email);
				
				dao.addStudent(student);
				break;
				
			case 2:
				dao.viewStudents();
				break;
				
			case 3:
				System.out.print("Enter Student ID :");
				int searchId = sc.nextInt();
				dao.searchStudent(searchId);
				break;
				
			case 4:
				System.out.print("Enter ID to delete Student :");
				int deleteId = sc.nextInt();
				dao.deleteStudent(deleteId);
				break;
				
			case 5:
				System.out.print("Enter Student ID: ");
				int updateId = sc.nextInt();
				sc.nextLine();

				System.out.println("1. Update Name");
				System.out.println("2. Update Age");
				System.out.println("3. Update Course");
				System.out.println("4. Update Email");

				int choice1 = sc.nextInt();
				sc.nextLine();

				System.out.println("Enter new value:");
				String newValue = sc.nextLine();

				dao.updateStudent(updateId, choice1, newValue);
				break;
				
			case 6:
				System.out.println("EXITING...");
				return;
				
			default:
				System.out.println("INVALID CHOICE");
					
			}
		}
		
		
		
		
		
		

	}

}
