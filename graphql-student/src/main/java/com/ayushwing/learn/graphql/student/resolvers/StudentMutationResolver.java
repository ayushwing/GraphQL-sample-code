package com.ayushwing.learn.graphql.student.resolvers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ayushwing.learn.graphql.student.model.Student;
import com.ayushwing.learn.graphql.student.service.StudentService;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

@Component
public class StudentMutationResolver implements GraphQLMutationResolver {

	@Autowired
	private StudentService studentService;
	
	public Student createStudent(Student student) {
		return studentService.createStudent(student);
	}
}
