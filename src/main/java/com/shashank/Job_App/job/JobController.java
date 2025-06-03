package com.shashank.Job_App.job;

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

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/jobs")
public class JobController {
	@Autowired
	private JobService jobService;
	
	@GetMapping("")
	private ResponseEntity<?> getAll() {
		return jobService.findAll();
	}
	
	@GetMapping("/{id}")
	private ResponseEntity<?> findById(@PathVariable Long id) {
		return jobService.findById(id);
	}
	
	@PostMapping("")
	private ResponseEntity<?> addJob(@RequestBody Job job) {
		return jobService.add(job);
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<?> updateJobById(@PathVariable Long id, @RequestBody Job job){
		return jobService.updateJob(id,job);
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<?> deleteJobById(@PathVariable Long id){
		return jobService.deleteJobById(id);
	}
}
