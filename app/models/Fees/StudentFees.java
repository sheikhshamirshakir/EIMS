package models.Fees;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.avaje.ebean.RawSql;
import com.avaje.ebean.RawSqlBuilder;

import models.admission.Student;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
@Entity
@Table (name="student_fees")
public class StudentFees extends Model{
	
	@Id
	public Long id;
	
	@Required
	@ManyToOne
	@JoinColumn(name = "student_id", referencedColumnName = "sid")
	public Student student;

	//@Required
	@ManyToOne
	@JoinColumn(name = "fees_head_id", referencedColumnName = "id")
	public FeesHead feesHead;

	@Required
	public Float amount;
	
	
    public Date fromDate;
    
    
    public Date endDate;
    
    public Double withoutDiscount;
    
    public Double withDiscount;
    
    @Column(name="paid_amount")
    public Double paidAmount;
    
    @Column(name="forward_amount")
    public Double forwardAmount;
    
	
	public static Finder<Long, StudentFees> find = new Finder(Long.class, StudentFees.class);

	public static List<StudentFees> all() {
		return find.all();
	}

	public static StudentFees findById(Long id) {
		return find.where().eq("id", id).findUnique();
	}


//	public static List<Float> findRatesOfCardsByStoreId(Long id) {
//		List<StudentFees> studentFees = StudentFees.find.where().eq("store.id", id).findList();
//		// ArrayList<Long> feesHead = new ArrayList<Long>(studentFees.size());
//		List<Float> rates = new ArrayList<Float>(studentFees.size());
//		for (int i = 0; i < studentFees.size(); i++) {
//			rates.add(i, studentFees.get(i).amount);
//		}
//
//		return rates;
//	}

	public static List<Long> findFeesHeadIdsByStudentId(Long id) {

		List<StudentFees> studentFees = StudentFees.find.where().eq("student.sid", id).findList();
		ArrayList<Long> fHIds = new ArrayList<Long>(studentFees.size());
		for (int i = 0; i < studentFees.size(); i++) {
			fHIds.add(studentFees.get(i).feesHead.id);
		}
		// System.out.print(feesHead);
		return fHIds;
	}

	public static StudentFees findByStudentAndFeesHead(Long studentId, Long feesHeadId) {
		StudentFees sf = find.where().eq("student.sid", studentId).eq("feesHead.id", feesHeadId).findUnique();
		return sf;
	}

	public static List<StudentFees> findByStudent(Long studentId) {
		List<StudentFees> sf = find.where().eq("student.sid", studentId).findList();

		return sf;
	}
	
	public static List<StudentFees> findByStudentAndDate(Long studentId, String from, String to) {
	  	 System.out.println("....................................>>>>>>>>>>>>>"+studentId);
    	 System.out.println("....................................>>>>>>>>>>>>>"+from);
    	 System.out.println("....................................>>>>>>>>>>>>>"+to);
		Date fromDate = new Date();
		Date toDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try{
		fromDate =  formatter.parse(from);
		toDate =  formatter.parse(to);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	  	 System.out.println("....................................>>>>>>>>>>>>>"+studentId);
    	 System.out.println("....................................>>>>>>>>>>>>>"+fromDate);
    	 System.out.println("....................................>>>>>>>>>>>>>"+toDate);
		List<StudentFees> sf = find.where()
				.eq("student.sid", studentId)
				.eq("fromDate", fromDate)
				.eq("endDate", toDate)
				.orderBy("id asc" )
				.findList();

		return sf;
	}


	public static List<FeesHead> findFeesHeadByStudent(Long studentId) {
		List<FeesHead> feesHeads = new ArrayList<FeesHead>();
		List<StudentFees> studentFees = find.where().eq("student.sid", studentId).findList();

		for (StudentFees sf : studentFees) {
			FeesHead fh = FeesHead.findById(sf.feesHead.id);
			feesHeads.add(fh);
		}
		return feesHeads;
	}

	public static List<StudentFees> findByFeesHead(Long feesHeadId) {
		List<StudentFees> sf = find.where().eq("feesHead.id", feesHeadId).findList();
		return sf;
	}


	public static boolean isFeesHeadIdMatched(Long studentId, Long feesHeadId) {
		List<Long> tempFeesHeadIds = findFeesHeadIdsByStudentId(studentId);
		return tempFeesHeadIds.contains(feesHeadId);
	}


	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static void deleteByStudent(Long id) {
		List<StudentFees> studentFees = StudentFees.find.where().eq("student.sid", id).findList();

		for (StudentFees sf : studentFees) {
			delete(sf.id);
		}
	}

	

	public static void deleteByStudentAndFeesHead(Long studentId, Long feesHeadId) {
		StudentFees sf = findByStudentAndFeesHead(studentId, feesHeadId);
		if (sf != null) {
			delete(sf.id);
		}
	}

	public static void deleteByStudentAndFeesHeadList(Long studentId, List<Long> feesHeadIds) {
		for (Long feesHeadId : feesHeadIds) {
			StudentFees sf = findByStudentAndFeesHead(studentId, feesHeadId);
			if (sf != null)
				delete(sf.id);
		}
	}

	public static Long create(StudentFees studentFees) {
		studentFees.save();
		return studentFees.id;
	}
	
	public static Long findLastId(){
		List<StudentFees> studentFees =  StudentFees.find.where().orderBy("id Desc").findList();
		return studentFees.get(0).id;
	}
	
	
	public static void update(StudentFees studentFees){
		studentFees.update();
	}
	
	
	public static Double fidLastForwardAmount(Long id ){
		
//		String sql ="select sf.forward_amount,sf.paid_amount"
//				+ " from student_fees sf ";
////				+ "where sf.student_id = "+id;
//	    RawSql rawSql = RawSqlBuilder.unparsed(sql)
//	    		.columnMapping("sf.forward_amount", "forwardAmount")
////	    		.columnMapping("sf.student_id", "student.sid")
//	    		.columnMapping("sf.paid_amount", "paidAmount")
////	    		.columnMapping("fees_head_id", "feesHead.id")
//	     		.create();
//	    
//	    
//	    List<StudentFees> studentFees =  find.setRawSql(rawSql).findList();
	   
		List<StudentFees> studentFees = StudentFees.findByStudent(id);
		List<Double> fas = new ArrayList<Double>();
		for(StudentFees sfs : studentFees){
			if(sfs.forwardAmount!=null)
			{
				System.out.println(".............................."+sfs.forwardAmount);
  			fas.add(sfs.forwardAmount);
			}
		}
		Double forwardAmount=0.0;
		for(Double num :fas)
		{
			forwardAmount+=num;
		}
	    return forwardAmount;
	}
	
	
	
	public static void doZeroForwardAmount(Long id ){
		
   
		List<StudentFees> studentFees = StudentFees.findByStudent(id);
//		List<Double> fas = new ArrayList<Double>();
		for(StudentFees sfs : studentFees){
			if(sfs.forwardAmount!=null)
			{
			    sfs.forwardAmount=0.0;
  			    StudentFees.update(sfs);
			}
		}
		
	  
	}
	
	
	
}
