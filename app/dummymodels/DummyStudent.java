package dummymodels;

import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import models.admission.Category;


public class DummyStudent {
	
	public Long id;
	
	public String studentName;	
	public String gender;
	public Date dateOfBirth;
	public String placeOfBirth; 
	
    public String nationality;
    public String presentAddress;
    public String permanentAddress;
    public String religion;
    public String bloodGroup;
	
    public String maritalStatus;  
	public String mobile;
    public String email; 
    
	public String parentName;
	public String parentPhone;
	public String parentEmail;
	
	public String departmentId;
    public String degreeId;
	public String classId;
	public String sectionId;

    public String division;  
    public String district;
    public Double atleastCredit;
	
}
