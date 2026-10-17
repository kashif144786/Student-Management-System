package com.sms.model;

public class Student {
	private int id;
	private String name;
	private int age;
	private String course;
	private String email;
	
	public Student(String name, int age, String course, String email ) {
		//this.id = id;
		this.name = name;
		this.age = age;
		this.course = course;
		this.email = email;
	}
	
	public Student(int id, String name, int age, String course, String email) {
	    this.id = id;
	    this.name = name;
	    this.age = age;
	    this.course = course;
	    this.email = email;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setAge(int age) {
		if(age <=0 )
		{
			System.out.println("Please Enter a valid age!!");	
		} 
		else if(age > 0 && age <=12 )
		{
			System.out.println("Age should be 13 or above for Registration.");	
		}
		else	
		{
			this.age=age;
		}
		
	}
	
	public int getAge() {
		return age;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	public String getCourse(){
		return course;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}

}
