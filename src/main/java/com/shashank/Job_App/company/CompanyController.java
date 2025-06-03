package com.shashank.Job_App.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shashank.Job_App.job.Job;
import com.shashank.Job_App.job.JobService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/companies")
public class CompanyController {
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("")
	private ResponseEntity<?> getAll() {
		return companyService.findAll();
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<?> findById(@PathVariable Long id) {
		return companyService.findById(id);
	}
	
	@PostMapping("")
	private ResponseEntity<?> addJob(@RequestBody Company company) {
		return companyService.add(company);
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<?> updateCompanyById(@PathVariable Long id, @RequestBody Company company){
		return companyService.updateCompany(id,company);
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<?> deleteCompanyById(@PathVariable Long id){
		return companyService.deleteCompanyById(id);
	}
}
