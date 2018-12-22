package com.fpt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.entity.Employee;
import com.fpt.exception.ResourceNotFoundException;
import com.fpt.repository.EmpRepository;

@RestController
public class Controller {
	@Autowired
	private EmpRepository empRepository;

	@GetMapping("/")
	public ResponseEntity<?> hello() {
		return ResponseEntity.ok("Hello World!");
	}

	@PostMapping("/addemp")
	public ResponseEntity<?> addEmp(@RequestBody Employee emp) {
		return ResponseEntity.ok(empRepository.saveAndFlush(emp));
	}

	@GetMapping("/viewall")
	public ResponseEntity<?> viewAll() {
		return ResponseEntity.ok(empRepository.findAll());
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmp(@PathVariable String id){
		if(!empRepository.existsById(id))
			return ResponseEntity.ok("ID not found!");
		return empRepository.findById(id).map(emp -> {
			empRepository.delete(emp);
			return ResponseEntity.ok("Delete successfully!");
		}).orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + " not found"));
	}
}
