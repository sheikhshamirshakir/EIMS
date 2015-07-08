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

import models.admission.Faculty;
import models.admission.Student;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;


@Entity
@Table (name="book_book")
public class Book extends Model{
	
	@Id
    public Long id;
	
	@Required
	public String name;
	
	@Required
	public String bookDetails;
	
	@Required
	public String isbn;
	
	@Required
	public Double price;
	
    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    public Author author;
    
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public Category bookCategory;
    
    @ManyToOne
    @JoinColumn(name = "publisher_id", referencedColumnName = "id")
    public Publisher publisher;
    
	
   
    public static Finder<Long, Book> find = new Finder(Long.class, Book.class);

    public static List<Book> all() {
        return find.all();
    }
    
    public static Book findById(Long id) {
        return find.where().eq("id", id).findUnique();
    }

    
    public static void create(Book book) {
    	book.save();
    }
    
    public static void update(Book book) {
    	book.update();
    }
    
    public static void delete(Long id){
    	delete(id);
    }
   
    public static Long findLastId(){
		List<Book> books =  Book.find.where().orderBy("id Desc").findList();
		return books.get(0).id;
	}
    
    public static Map<String,String> getBooksAsMap() {
        LinkedHashMap<String,String> books = new LinkedHashMap<String,String>();
        for(Book book: Book.find.orderBy("name").findList()) {
        	books.put(book.id.toString(), book.name);
        }
     return books;
    }
    

}
