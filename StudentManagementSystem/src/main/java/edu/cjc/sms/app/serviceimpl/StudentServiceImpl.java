package edu.cjc.sms.app.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cjc.sms.app.model.Student;
import edu.cjc.sms.app.repositary.StudentRepository;
import edu.cjc.sms.app.servicei.StudentServiceI;

@Service
public class StudentServiceImpl implements StudentServiceI{

	@Autowired
	StudentRepository sr;

	@Override
	public List<Student> addStudent(Student s) {
		sr.save(s);
		return sr.findAll();
	}

	@Override
	public List<Student> getAllStudents() {
		return sr.findAll();
	}

	@Override
	public List<Student> getBatchesStudent(String batchNumber) {
		
		return sr.findAllByBatchNumber(batchNumber);
	}

	
}
