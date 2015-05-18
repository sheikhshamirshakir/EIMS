package models.Fees;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	
}
