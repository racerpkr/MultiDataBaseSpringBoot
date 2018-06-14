package com.multidatabase.postgres.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.multidatabase.postgres.entity.Users;

public interface IUsers extends JpaRepository<Users, Long>{

	@Query("select u from Users u where u.name LIKE :name ")
	public List<Users> getUsersByNameLike(String name);
	
	
}
