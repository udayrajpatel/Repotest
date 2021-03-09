package in.co.rays.dto;
import java.util.Date;

/**
 * 
 * Timetable DTO encapsulates Timetable attributes
 * 
 * @author uday
 *
 */

public class TimetableDTO extends BaseDTO {

	/**
	 * 
	 * Default Serial Version ID
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Description of Timetable
	 */
	private String description;
	/**
	 * Exam Date of Timetable
	 */
	private Date examDate;
	/**
	 * Exam Time of Timetable;
	 */
	private String examTime;
	/**
	 * CourseId of Timetable
	 */
	private long courseId;
	/**
	 * CourseName of Timetable
	 */
	private String courseName;
	/**
	 * SubjectId of Timetable
	 */
	private long subjectId;
	/**
	 * Subject Name of Timetable
	 */
	private String subjectName;
	
	/**
	 * Semester of Timetable
	 */
	private String semester;
	
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public String getExamTime() {
		return examTime;
	}
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
	public String getKey() {
		
		return id + "";
	}
	public String getValue() {
		
		return subjectName;

	}

}
