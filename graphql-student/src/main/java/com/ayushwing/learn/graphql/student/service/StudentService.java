package com.ayushwing.learn.graphql.student.service;

import com.ayushwing.learn.graphql.student.model.Student;

public interface StudentService {

	public Student getStudentByName(String name);
	
	public Student createStudent(Student student);
}
