package dummymodels;

import java.util.List;

import javax.persistence.Id;

import play.data.validation.Constraints.Required;

public class DummyTeacherCourse {

	@Id
	public Long id;

    
    public Long teacherId;

    
    public List<String> courseId;
}
