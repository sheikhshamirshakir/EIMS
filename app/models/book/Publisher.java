package models.book;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import dummymodels.DummyEmployee;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


@Entity
@Table (name="book_publisher")
public class Publisher extends Model{
	
	@Id
    public Long id;
	
	@Required
	public String name;
	
	@Required
	public String publisherDetails;
	
	@OneToMany(mappedBy = "publisher")
	public List<Book> books;
	   
    public static Finder<Long, Publisher> find = new Finder(Long.class, Publisher.class);

    public static List<Publisher> all() {
        return find.all();
    }
    
    public static Publisher findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    
    public static void create(Publisher publisher) {
    	publisher.save();
    }
    
    public static void update(Publisher publisher) {
    	publisher.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }
   
    public static Map<String,String> getPublishersAsMap() {
        LinkedHashMap<String,String> publishers = new LinkedHashMap<String,String>();
        for(Publisher publisher: Publisher.find.orderBy("name").findList()) {
        	publishers.put(publisher.id.toString(), publisher.name);
        }
     return publishers;
    }
    

}
