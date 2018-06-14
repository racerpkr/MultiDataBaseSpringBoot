package com.multidatabase.mysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.multidatabase.mysql.entity.StudentDetails;

/**
 * 
 * @author praveenkumar
 *
 */
@Repository
public interface IStudentDetails extends JpaRepository<StudentDetails, Long>{
	
	@Query("Select s from StudentDetails s where s.age=?1")
	public List<StudentDetails> getStudentDetailsByAge(int age);

}
