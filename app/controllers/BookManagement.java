/**Created By Sheikh Shamir Shakir Shomoy Solution
 * Shomoy Solution
 * On 15-Mar-2015
 ***/
package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import be.objectify.deadbolt.java.actions.Dynamic;
import models.admission.Student;
import models.book.Book;
import play.data.Form;
import play.mvc.Action;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import services.deadbolt.DeadboltHandler;
import utils.AppConstants;
import views.html.book.*;

public class BookManagement extends Controller{

	static Form<Book> bookForm = Form.form(Book.class);
	
//	@Dynamic(value = "Entry Employee Book", handler = DeadboltHandler.class)
	 public static Result create() {
	        return ok(create.render(bookForm));
	    }
	 
//	@Dynamic(value = "Entry Employee Book", handler = DeadboltHandler.class)
	 public static Result save() {
		 Form<Book> filledForm = bookForm.bindFromRequest();
		 if (filledForm.hasErrors()) {
				return badRequest(create.render(filledForm));

			} else {
		 
		 MultipartFormData body = request().body().asMultipartFormData();
   		 FilePart book_image = body.getFile("bookImage");
 
		 Book book = filledForm.get();
	     Book.create(book);
	     
	     String bID = Book.findLastId().toString();
		 		 
	     	String bName=book.name.replaceAll("\\s+","");	
			String image_name = bName+bID+"_image.png";
		    String contentType = book_image.getContentType(); 
		    File file_type = book_image.getFile();
		    				    
		    try {
	            FileUtils.copyFile(file_type, new File("public/photos/book", image_name));
//		    	FileUtils.copyFile(file_type, new File("/assets/photos/book", image_name));
		    	
		    } catch (IOException ioe) {
	            System.out.println("Problem operating on filesystem");
	        }	
	     
	   	 flash("success", AppConstants.SUCCESS_MESSAGE);
	     //return ok("");
	   	return redirect(controllers.routes.BookManagement.list());
	    }
	 }
//	@Dynamic(value = "List of Employee Book", handler = DeadboltHandler.class)
	 public static Result list(){
	    	List<Book> books = Book.all();
	     	return ok(list.render(books));
	    }

	 
	 public static Result show(Long id) {
			Book book = Book.findById(id);
					
		  	if (book == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
//				return ok("");
				return redirect(controllers.routes.BookManagement.list());
			} else
			{	
			
		  String bN = book.name;
		  String bId = book.id.toString();
		  String image_name = bN.replaceAll("\\s+","")+bId+"_image.png";

		  	
				return ok(show.render(book,image_name));
		
			}
		  	}
	 
	 public static Result edit(Long id) {
		 Book book = Book.findById(id);
			
		  	if (book == null) {
				flash("error", AppConstants.ERROR_MESSAGE_ID_NOT_FOUND);
				//return ok("");
				return redirect(controllers.routes.BookManagement.show(id));
			}else
				return ok(edit.render(bookForm.fill(book)));
		}
	 
	 
	 public static Result update(){
			Form<Book> filledForm = bookForm.bindFromRequest();
			if (filledForm.hasErrors()) {
				return badRequest(edit.render(filledForm));
			} else {
			
			Book book = filledForm.get();
			Book.update(book);
			return ok(list.render(Book.all()));
			}
			
		}
	 
	 public static Result delete(Long id){
		 Book.delete(id);
		 flash("success", AppConstants.SUCCESSFUL_DELETE_MESSAGE);
		 return ok(list.render(Book.all()));
	 }
	 
	 

}


