package dummymodels;

import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import models.admission.Category;


public class DummyStudent {
	
	public Long id;
	
	public String studentName;
	
	public Date dateOfBirth;
	
	public boolean gender;
		
    
	public String departmentId;
  
	
	public String degreeId;
	
	public String classId;
	  
	
	public String sectionId;


	//parent information
	public String parentName;
	
	public String parentPhone;
	
	public String parentEmail;
	
	public String placeOfBirth; 
    public String maritalStatus;  
    public String citizen;
    public String presentAddress;  
    public String division;  
    public String district;
    public String permanentAddress;
	public String mobile;
    public String email; 
	
}
