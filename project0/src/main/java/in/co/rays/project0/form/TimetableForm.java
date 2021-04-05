package in.co.rays.project0.form;
import java.sql.Timestamp;
import java.util.Date;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;
import in.co.rays.project0.dto.BaseDTO;
import in.co.rays.project0.dto.TimetableDTO;
import in.co.rays.project0.util.Util;

/**
 * The Class TimetableForm.
 * 
 * @author uday
 *
 */
public class TimetableForm extends BaseForm{
	
	/** The course name. */
	private String courseName;

	/** The subject name. */
	private String subjectName;
	
	/** The semester. */
	@NotEmpty
	private String semester;
	
	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * Sets the course name.
	 *
	 * @param courseName the new course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * Gets the subject name.
	 *
	 * @return the subject name
	 */
	public String getSubjectName() {
		return subjectName;
	}
	
	/**
	 * Sets the subject name.
	 *
	 * @param subjectName the new subject name
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	/**
	 * Gets the semester.
	 *
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}
	
	/**
	 * Sets the semester.
	 *
	 * @param semester the new semester
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	/**
	 * Gets the course id.
	 *
	 * @return the course id
	 */
	public long getCourseId() {
		return courseId;
	}
	
	/**
	 * Sets the course id.
	 *
	 * @param courseId the new course id
	 */
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	
	/**
	 * Gets the subject id.
	 *
	 * @return the subject id
	 */
	public long getSubjectId() {
		return subjectId;
	}
	
	/**
	 * Sets the subject id.
	 *
	 * @param subjectId the new subject id
	 */
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	
	/**
	 * Gets the exam date.
	 *
	 * @return the exam date
	 */
	public String getExamDate() {
		return examDate;
	}
	
	/**
	 * Sets the exam date.
	 *
	 * @param examDate the new exam date
	 */
	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
	
	/**
	 * Gets the exam time.
	 *
	 * @return the exam time
	 */
	public String getExamTime() {
		return examTime;
	}
	
	/**
	 * Sets the exam time.
	 *
	 * @param examTime the new exam time
	 */
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	
	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/** The course id. */
	@Min(value=1,message="{Min.form.subjectId}")
    private long courseId;
	
	/** The subject id. */
	@Min(value=1,message="{Min.form.subjectId}")
    private long subjectId;
	
	/** The exam date. */
	@NotEmpty
	private String examDate;
	
	/** The exam time. */
	@NotEmpty
	private String examTime;
	
	/** The description. */
	@NotEmpty
	private String description;
	
	/* (non-Javadoc)
	 * @see in.co.rays.project0.form.BaseForm#getDto()
	 */
	@Override
	public BaseDTO getDto() {
		TimetableDTO dto=new TimetableDTO();
		dto.setId(id);
		dto.setCourseId(courseId);
        dto.setCourseName(courseName);
        dto.setSubjectId(subjectId);
        dto.setSubjectName(subjectName);
		   if(examDate!=""){
			   dto.setExamDate(Util.getDate(examDate));
		      }
		dto.setSemester(semester);
		dto.setDescription(description);
		dto.setExamTime(examTime);
		dto.setCreatedBy(createdBy);
	    dto.setModifiedBy(modifiedBy);
	    dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
	    dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
		return dto;
	}



/* (non-Javadoc)
 * @see in.co.rays.project0.form.BaseForm#populate(in.co.rays.project0.dto.BaseDTO)
 */
@Override
public void populate(BaseDTO bDto) {

	TimetableDTO dto=(TimetableDTO) bDto;
	id=dto.getId();
	courseId=dto.getCourseId();
	courseName=dto.getCourseName();
	subjectId=dto.getSubjectId();
	subjectName=dto.getSubjectName();
	examTime=dto.getExamTime();
	examDate=Util.getDate(dto.getExamDate()); 
	semester=dto.getSemester();
	description=dto.getDescription();
	createdBy=dto.getCreatedBy();
   	modifiedBy=dto.getModifiedBy();
	  if (dto.getCreatedDateTime() != null) {
          createdDateTime = dto.getCreatedDateTime().getTime();
      }
      if (dto.getModifiedDateTime() != null) {
          modifiedDateTime = dto.getModifiedDateTime().getTime();
      }
	
}

}
