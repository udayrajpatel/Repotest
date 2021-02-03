package in.co.rays.dto;

/**
 * DropdownList interface is implemented by DTOs those are used to create drop
 * down list on HTML pages
 * 
 * @author uday
 *
 */

public interface DropdownListDTO {

	/**
	 * Returns key of list element
	 * 
	 * @return key
	 */

	public String getKey();

	/**
	 * Returns display text of list element
	 * 
	 * @return value
	 */
	
	public String getValue();

}