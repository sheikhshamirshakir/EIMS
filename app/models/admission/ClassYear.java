package models.admission;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import play.db.ebean.Model;
  
/*
 * 
 * AUTHOR: SHAON   
 * CREATE DATE: 10th MARCH 2015
 * 
 */

@Entity
@Table (name="ClassYear")
public class ClassYear extends Model {
	
	@Id
    public Long id;
	
	public String name;

	public static Finder<Long,ClassYear> find =  new Finder(Long.class, ClassYear.class);
	
	public static List<ClassYear> all(){
		return find.all();
	}
	
	public static void create(ClassYear classYear){
		classYear.save();
	}
	
	public static void update(ClassYear classYear){
		classYear.update();
	}
	
	public static void delete(Long id){
		delete(id);
	}

	public static ClassYear findById(Long id2) {
		
		ClassYear classYear = find.byId(id2);
			
		return classYear;
	}
	
	 
	    
	
}
