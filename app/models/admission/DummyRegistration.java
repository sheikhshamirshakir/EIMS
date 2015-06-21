package models.admission;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import models.Permission;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;


@Entity
@Table (name="dummy_registration")
public class DummyRegistration extends Model{
	
	@Id
	@Column(name="id")
	public int id;
		
	@Column(name="admission_id")
	public String admissionId;
	

	@Column(name="applicant_name")
	public String name;
	
	@Column(name="fathers_name")
	public String fathersName;
	
	@Column(name="mothers_name")
	public String mothersName;
		
	@Column(name="dob")
	public Date dateOfBirth;
	
	

	 private static Finder<Integer, DummyRegistration> find = new Finder(Integer.class, DummyRegistration.class);
		
		public static DummyRegistration getStudent(DummyRegistration dummyRegistration) {
			return find.where()
				.eq("ltrim(admission_id)", dummyRegistration.admissionId.trim())
				.eq("trim(applicant_name)", dummyRegistration.name.trim())
				.eq("trim(fathers_name)", dummyRegistration.fathersName.trim())
				.eq("trim(mothers_name)", dummyRegistration.mothersName.trim())
				.eq("dob", dummyRegistration.dateOfBirth)				
				.findUnique();
			 
		}
	
}
