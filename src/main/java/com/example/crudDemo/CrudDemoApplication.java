package com.example.crudDemo;

import ch.qos.logback.classic.pattern.LineSeparatorConverter;
import com.example.crudDemo.dao.StudentDAO;
import com.example.crudDemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner->{
			//createStudent(studentDAO);
			createMultipleStudent(studentDAO);
			//readStudent(studentDAO);
			//queryForStudent(studentDAO);
			//queryForStudentByLastName(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			//deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students");
		int nuumRowsDeleted=studentDAO.deleteAll();
		System.out.println("Rows deleted = "+nuumRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId=3;
		System.out.println("Deleting an studnet :"+studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		//Retrieve student based on the id
		int studentId=1;
		System.out.println("GEtting stduent with id "+studentId);
		Student student=studentDAO.findById(studentId);
		//change first name to  "Scoo"
		System.out.println("updating student");
		student.setFirstName("Anna");
		//update the student
		studentDAO.update(student);

		//display the student
		System.out.println("Updated studetn .."+student);
	}

	private void queryForStudentByLastName(StudentDAO studentDAO) {
		//get a list of students
		List<Student> theStudents=studentDAO.findByFirstName("Ankit");
		//display tha list of students
		for (Student student:theStudents){
			System.out.println(student);
		}
	}

	private void queryForStudent(StudentDAO studentDAO) {

		//get a list pf student
		List<Student> theStudents=studentDAO.findAll();

		//display that list of studemt
		for (Student theStudent: theStudents ){
			System.out.println(theStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		//Create a  student object
		System.out.println("Creating new student object........");
		Student tempStudent =new Student("Ankit","Bisen","ankitb@gmail.com");
		
		//Save the student 
		System.out.println("Saving the students.....");
		studentDAO.save(tempStudent);
		
		//display id of the saved  student

		System.out.println("Saved stsudent generated id......"+tempStudent.getId());
		//retrieve student based on the id : primary key
		System.out.println("Retriev9ng the Stduent with id :"+tempStudent.getId());
		Student myStudent=studentDAO.findById(tempStudent.getId());
		
		//display the studemt

		System.out.println("Student found....."+myStudent);
		
	}

	private void createMultipleStudent(StudentDAO studentDAO) {
		//Create Multiple Stduent
		System.out.println("Creating new student object........");
		Student tempStudent1 =new Student("John","Bisen","johnb@gmail.com");
		Student tempStudent2 =new Student("mary","Bisen","marryb@gmail.com");
		Student tempStudent3 =new Student("Jack","Bisen","jackb@gmail.com");


		//Save those stduents
		System.out.println("Saving the students.....");
		studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
         studentDAO.save(tempStudent3);

	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object........");
		Student tempStudent =new Student("Ankit","Bisen","ankitb@gmail.com");

		System.out.println("Saving the students.....");
		studentDAO.save(tempStudent);


		System.out.println("Saved stsudent generated id......"+tempStudent.getId());
	}
	
	

}
