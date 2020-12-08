package in.co.rays.dto;

// TODO: Auto-generated Javadoc
/**
 * Contains Role data.
 *
 * @author 
 */

public class RoleDTO extends BaseDTO implements DropdownList {

     /** Predefined Role constants. */
    public static final int ADMIN = 1;
    
    /** The Constant STUDENT. */
    public static final int STUDENT = 2;
    
    /** The Constant COLLEGE. */
    public static final int COLLEGE = 3;
    
    /** The Constant FACULTY. */
    public static final int FACULTY= 4;

    /** Role Name. */

    private String name;

    /** Role Description. */
    private String description;

    /**
     * accessor.
     *
     * @return the name
     */

    public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
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

    /* (non-Javadoc)
     * @see in.co.rays.dto.DropdownList#getKey()
     */
    public String getKey() {
        return id + "";
    }

    /* (non-Javadoc)
     * @see in.co.rays.dto.DropdownList#getValue()
     */
    public String getValue() {
        return name;
    }

}
