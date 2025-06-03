package com.shashank.Job_App.job;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
	public ResponseEntity<?> add(Job job) {
		try {			
			jobRepository.save(job);
			return new ResponseEntity<String>("Successfully created Jon with this Id:"+job.getId(),HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> findAll() {
		// TODO Auto-generated method stub
		List<Job> jobList = jobRepository.findAll();
		if(jobList!= null && jobList.size()!=0)
			return new ResponseEntity<List<Job>>(jobList,HttpStatus.OK);
		
		else
			return new ResponseEntity<String>("No Jobs Found",HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> findById(Long id) {
		// TODO Auto-generated method stub
		try {
			Job job = jobRepository.findById(id).get();
			return new ResponseEntity<Job>(job, HttpStatus.OK);
			
		}
		catch(Exception e) {
			
			return new ResponseEntity<String>("No Job Found with this Id:"+id,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> updateJob(Long id, Job job) {
		// TODO Auto-generated method stub
		try {
			Job oldJob = jobRepository.findById(id).get();
			oldJob.setTitle(job.getTitle());
			oldJob.setDescription(job.getDescription());
			oldJob.setMinSalary(job.getMinSalary());
			oldJob.setMaxSalary(job.getMaxSalary());
			oldJob.setLocation(job.getLocation());
			jobRepository.save(oldJob);
			return new ResponseEntity<String>("Successfully Updated Job with Job ID:"+id,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>("Could not find Job with Job ID:"+id,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> deleteJobById(Long id) {
		// TODO Auto-generated method stub
		try {
			Job oldJob = jobRepository.findById(id).get();
			jobRepository.delete(oldJob);
			return new ResponseEntity<String>("Successfully Deleted Job with Job ID:"+id,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>("Could not find Job with Job ID:"+id,HttpStatus.NOT_FOUND);
		}
	}
}
