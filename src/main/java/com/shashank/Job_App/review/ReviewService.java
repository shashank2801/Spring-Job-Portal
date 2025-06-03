package com.shashank.Job_App.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shashank.Job_App.company.Company;
import com.shashank.Job_App.company.CompanyRepository;
import com.shashank.Job_App.company.CompanyService;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public ResponseEntity<?> addReview(Long companyId,Review review) {
		try {
			Company company = companyRepository.findById(companyId).get();
			if(company != null) {
				review.setCompany(company);
				reviewRepository.save(review);
				return new ResponseEntity<String>("Successfully added review for the Company with Id: "+companyId,HttpStatus.CREATED);
			}
			else {
				throw new Exception("Company not found with Company Id: "+companyId);
			}
			
//			reviewRepository.save();
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> findAllByCompany(Long companyId) {
		// TODO Auto-generated method stub
		List<Review> companyReviews = reviewRepository.findByCompanyId(companyId);
		if(companyReviews!= null && companyReviews.size()!=0)
			return new ResponseEntity<List<Review>>(companyReviews,HttpStatus.OK);
		
		else
			return new ResponseEntity<String>("No Reviews found for Company with Id: "+companyId,HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> findById(Long companyId,Long reviewId) {
		// TODO Auto-generated method stub
		try {
			Review review = reviewRepository.findByCompanyIdAndId(companyId,reviewId);
			if(review != null)
				return new ResponseEntity<Review>(review, HttpStatus.OK);
			else
				throw new Exception("No review found with Id: "+reviewId+" for company with Id: "+companyId);
			
		}
		catch(Exception e) {
			
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> updateReview(Long companyId,Long reviewId, Review review) {
		// TODO Auto-generated method stub
		try {
			Review oldReview = reviewRepository.findByCompanyIdAndId(companyId, reviewId);
			if(oldReview!=null) {
				oldReview.setDescription(review.getDescription());
				oldReview.setRating(review.getRating());
				oldReview.setTitle(review.getTitle());
				reviewRepository.save(oldReview);
				return new ResponseEntity<String>("Successfully Updated Review with Review Id:"+reviewId+" for company with Id: "+companyId,HttpStatus.OK);
			}
			else {
				throw new Exception("No review found with Id:"+reviewId+" for company with Id:"+companyId);
			}
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<?> deleteReview(Long companyId,Long reviewId) {
		// TODO Auto-generated method stub
		try {
			Review oldReview = reviewRepository.findByCompanyIdAndId(companyId, reviewId);
			if(oldReview!=null) {
				reviewRepository.delete(oldReview);
				return new ResponseEntity<String>("Successfully Deleted Review with Id: "+reviewId+" for company with Company ID: "+companyId,HttpStatus.OK);
			}
			else {
				throw new Exception("No review found with Id: "+reviewId+" for company with Id: "+companyId);
			}

		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
}
