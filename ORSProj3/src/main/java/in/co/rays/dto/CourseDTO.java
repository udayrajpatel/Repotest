package in.co.rays.dto;
public class CourseDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Name of course
	 */
	private String name;

	/**
	 * Duration of Course
	 */
	private String duration;
	/**
	 * Description of project
	 */
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKey() {
		
		return id + "";
	}

	public String getValue() {
		
		return name;
	}

}
