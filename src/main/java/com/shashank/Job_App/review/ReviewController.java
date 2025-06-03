package com.shashank.Job_App.review;

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
@RequestMapping("/companies/{companyId}")
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@GetMapping("/reviews")
	private ResponseEntity<?> getAll(@PathVariable Long companyId) {
		return reviewService.findAllByCompany(companyId);
	}
	
	@GetMapping("/reviews/{reviewId}")
	private ResponseEntity<?> findById(@PathVariable Long companyId,@PathVariable Long reviewId) {
		return reviewService.findById(companyId,reviewId);
	}
	
	@PostMapping("/reviews")
	private ResponseEntity<?> addReview(@PathVariable Long companyId,@RequestBody Review review) {
		return reviewService.addReview(companyId,review);
	}
//	
	@PutMapping("/reviews/{reviewId}")
	private ResponseEntity<?> updateReviewById(@PathVariable Long companyId, @PathVariable Long reviewId,@RequestBody Review review){
		return reviewService.updateReview(companyId,reviewId,review);
	}
	
	@DeleteMapping("/reviews/{reviewId}")
	private ResponseEntity<?> deleteCompanyById(@PathVariable Long companyId, @PathVariable Long reviewId){
		return reviewService.deleteReview(companyId,reviewId);
	}
}
