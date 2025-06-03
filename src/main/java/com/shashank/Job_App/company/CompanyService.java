package com.shashank.Job_App.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public ResponseEntity<?> add(Company company) {
		try {			
			companyRepository.save(company);
			return new ResponseEntity<String>("Successfully created Company with this Id:"+company.getId(),HttpStatus.CREATED);
		}
		catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> findAll() {
		// TODO Auto-generated method stub
		List<Company> companyList = companyRepository.findAll();
		if(companyList!= null && companyList.size()!=0)
			return new ResponseEntity<List<Company>>(companyList,HttpStatus.OK);
		
		else
			return new ResponseEntity<String>("No Companies Found",HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<?> findById(Long id) {
		// TODO Auto-generated method stub
		try {
			Company company = companyRepository.findById(id).get();
			return new ResponseEntity<Company>(company, HttpStatus.OK);
			
		}
		catch(Exception e) {
			
			return new ResponseEntity<String>("No Company Found with this Id:"+id,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> updateCompany(Long id, Company company) {
		// TODO Auto-generated method stub
		try {
			Company oldCompany = companyRepository.findById(id).get();
			oldCompany.setName(company.getName());
			oldCompany.setDescription(company.getDescription());
			oldCompany.setJobs(company.getJobs());
			companyRepository.save(oldCompany);
			return new ResponseEntity<String>("Successfully Updated Company with Company ID:"+id,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>("Could not find Company with Company ID:"+id,HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> deleteCompanyById(Long id) {
		// TODO Auto-generated method stub
		try {
			companyRepository.deleteById(id);
			return new ResponseEntity<String>("Successfully Deleted Company with Company ID:"+id,HttpStatus.OK);
		}
		catch (Exception e) {
			return new ResponseEntity<String>("Could not find Company with Company ID:"+id,HttpStatus.NOT_FOUND);
		}
	}
}
