package com.ayushwing.learn.graphql.student.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ayushwing.learn.graphql.student.model.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	Map<String, Student> inMemoryCache = new HashMap<>();

	@Override
	public Student getStudentByName(String name) {
		return inMemoryCache.get(name);
	}

	@Override
	public Student createStudent(Student student) {
		inMemoryCache.put(student.getName(), student);
		return student;
	}

}
