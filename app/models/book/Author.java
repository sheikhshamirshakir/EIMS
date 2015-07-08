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

import models.admission.Degree;
import dummymodels.DummyEmployee;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


@Entity
@Table (name="book_author")
public class Author extends Model{
	
	@Id
    public Long id;
	
	@Required
	public String name;
	
	@Required
	public String authorDetails;
	
	@OneToMany(mappedBy = "author")
	public List<Book> books;
   
    public static Finder<Long, Author> find = new Finder(Long.class, Author.class);

    public static List<Author> all() {
        return find.all();
    }
    
    public static Author findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    
    public static void create(Author author) {
    	author.save();
    }
    
    public static void update(Author author) {
    	author.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }
   
    public static Map<String,String> getAuthorsAsMap() {
        LinkedHashMap<String,String> authors = new LinkedHashMap<String,String>();
        for(Author author: Author.find.orderBy("name").findList()) {
        	authors.put(author.id.toString(), author.name);
        }
     return authors;
    }
    

}
