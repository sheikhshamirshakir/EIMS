package models.Fees;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import models.Fees.FeesHead;
import models.admission.Student;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


@Entity
@Table (name="student_collection_type")
public class StudentCollectionType extends Model{
	
	@Id
    public Long id;
	
	@Required
	@ManyToOne
	@Column(unique=true)
    @JoinColumn(name = "student_id", referencedColumnName = "sid")  
    public Student student;
	
	@Required
	@ManyToOne
    @JoinColumn(name = "fees_category_id", referencedColumnName = "id")  
    public Category feesCategory;
	
//	@ManyToOne
//    @JoinColumn(name = "discount_name", referencedColumnName = "id")  
//    public DiscountCategory discountCategory;
	
	public String discountName;
      
	public static Finder<Long, StudentCollectionType> find = new Finder(Long.class, StudentCollectionType.class);

    public static List<StudentCollectionType> all() {
        return find.all();
    }
    
    public static StudentCollectionType findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    public static void create(StudentCollectionType sct) {
    	sct.save();
    }
    
    public static void update(StudentCollectionType sct) {
    	sct.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }
    
    public static StudentCollectionType findByStudentId(Long sid) {
        return find.where().eq("student_id", sid).findUnique();
    }
    
    public static StudentCollectionType findBycategoryandStudent(Long id, Long sid) {
        return find.where().eq("student.sid", sid)
        		.eq("feesCategory.id", id)
        		.findUnique();
    }

//    public static Map<String,String> getsctsAsMap() {
//        LinkedHashMap<String,String> scts = new LinkedHashMap<String,String>();
//        for(StudentCollectionType sct: StudentCollectionType.find.orderBy("name").findList()) {
//        	scts.put(sct.id.toString(), sct.name);
//        }
//        
//        return scts;
//    }
    

}
