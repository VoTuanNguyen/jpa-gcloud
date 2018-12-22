package com.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fpt.entity.Employee;

public interface EmpRepository extends JpaRepository<Employee, String>{
	
}
