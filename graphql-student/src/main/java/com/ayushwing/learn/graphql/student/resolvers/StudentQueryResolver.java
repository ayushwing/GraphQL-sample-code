package com.ayushwing.learn.graphql.student.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayushwing.learn.graphql.student.model.Student;
import com.ayushwing.learn.graphql.student.service.StudentService;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class StudentQueryResolver implements GraphQLQueryResolver {

	@Autowired
	private StudentService studentService;
	
	public Student getStudentByName(String name) {
		return studentService.getStudentByName(name);
	}
}
