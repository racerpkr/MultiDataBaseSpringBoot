package com.multidatabase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multidatabase.response.ResultResponseObject;
import com.multidatabase.service.GetData;

@RestController
public class CommonController {

	@Autowired GetData getData;
	
	@RequestMapping(value = "/get")
	public ResultResponseObject getDataFromBothDB(){
		
		return getData.getDataFromDb();	
	}
}
