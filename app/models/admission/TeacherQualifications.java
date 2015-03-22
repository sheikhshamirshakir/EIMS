package models.admission;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
@Table (name="teachers_qualifications")
public class TeacherQualifications extends Model {

	@Id
	public Long id;
	
	public String SSCSession;
	public String SSCResult;
	public String SSCSchool;
	
	public String HSCSession;
	public String HSCResult;
	public String HSCCollage;
	
	public String HonsSession;
	public String HonsResult;
	public String HonsUniv;
	
	public String MsSession;
	public String MsResult;
	public String MsUniv;
	
	public String PHDYear;
	public String PHDTopics;
    public String PHDUniv;
    
    public Long employeeId;
    
    
public static Finder<Long,TeacherQualifications> find =  new Finder(Long.class, TeacherQualifications.class);
	
	public static List<TeacherQualifications> all(){
		return find.all();
	}
	
	public static void create(TeacherQualifications teacherQualifications){
		teacherQualifications.save();
	}
	
	public static void update(TeacherQualifications teacherQualifications){
		teacherQualifications.update();
	}
	
	public static void delete(Long id){
		delete(id);
	}

	public static TeacherQualifications findById(Long id2) {
		
		TeacherQualifications teacherQualifications = find.byId(id2);
			
		return teacherQualifications;
	}
	
	
}
