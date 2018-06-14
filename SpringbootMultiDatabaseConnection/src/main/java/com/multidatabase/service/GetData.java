package com.multidatabase.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multidatabase.mysql.entity.StudentDetails;
import com.multidatabase.mysql.repository.IStudentDetails;
import com.multidatabase.postgres.entity.Users;
import com.multidatabase.postgres.repository.IUsers;
import com.multidatabase.response.ResultResponseObject;

@Service
public class GetData {

	@Autowired 
	IStudentDetails iStudentDetails;
	
	@Autowired 
	IUsers iUsers;
	
	public ResultResponseObject getDataFromDb(){
		ResultResponseObject result = new ResultResponseObject();
		
		String message = "";
		String response = "Success";
		List<Object> object = new ArrayList<>();
		List<StudentDetails> studentList = iStudentDetails.getStudentDetailsByAge(10);
		//List<Users> usersList = iUsers.getUsersByNameLike("msdf");
		List<Users> usersList = iUsers.findAll();
		if(studentList.isEmpty()){
			response = "Failure";
			message = "student List Empty ";
		}
		if(usersList.isEmpty()){
			response = "Failure";
			message +="User List Empty";
		}
		
		object.add(usersList);
		object.add(studentList);
		result.setResult(object);
		
		return result;
		
	}
	
}
