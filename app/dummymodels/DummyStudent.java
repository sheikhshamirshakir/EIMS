package dummymodels;

import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import play.data.format.Formats;
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
    
//	public String parentName;
//	public String parentPhone;
//	public String parentEmail;
    
    public String fatherName;            
	public String fatherMobile;
   	public String fatherEmail;
	public String fathersLastEduQual;
	
	
	public String fatherProfession;
	
	
	public String motherName; 
	public String motherMobile; 
	public String mothersLastEduQual;
    public String numberOfChild;
	
    public String guardianName;            
  	public String guardianMobile;
  	
  	
  	public String guardianProfession;
  	
  	
  	public String guarRelation;
 	
  	
  	public String guardianEmail;
  	public String guarEducation; 
    
    public String levelOfEducation1;
    public String degree1;
    public String majorOrGroup1;
    public String instituteName1;
    public String board1;
    public String year1;
    public String result1;
    
    public String levelOfEducation2;
    public String degree2;
    public String majorOrGroup2;
    public String instituteName2;
    public String board2;
    public String year2;
    public String result2;
    
    public String levelOfEducation3;
    public String degree3;
    public String majorOrGroup3;
    public String instituteName3;
    public String board3;
    public String year3;
    public String result3;
    
    
    
    
    
    
    
    
    public String levelOfEducation4;
    public String degree4;
    public String majorOrGroup4;
    public String instituteName4;
    public String board4;
    public String year4;
    public String result4;
    
    
    
    
    
    public String isParentGuardian;
    public String facultyId;
	public String departmentId;
    public String degreeId;
	public String classId;
	public String sectionId;

    public Double atleastCredit;
    
    
    public String username;
    public String password;
    public String role;
	
}
