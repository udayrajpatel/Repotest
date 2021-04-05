package in.co.rays.project0.form;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import in.co.rays.project0.dto.BaseDTO;
import in.co.rays.project0.dto.CollegeDTO;


/**
 * The Class CollegeForm.
 * 
 * @author uday
 *
 */
public class CollegeForm extends BaseForm{
	
	/** The name. */
	@NotEmpty
	private String name;
	
	/** The address. */
	@NotEmpty
	private String address;
	
	/** The city. */
	@NotEmpty
	private String city;
	
	/** The state. */
	@NotEmpty
	private String state;
	
	/** The mobile no. */
	@NotNull
    @Pattern(regexp = "\\d{10}")
	private String mobileNo;
	
	/** The message. */
	private String message;
	
	/**
	 * Gets the name.
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
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * Gets the mobile no.
	 *
	 * @return the mobile no
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	
	/**
	 * Sets the mobile no.
	 *
	 * @param mobileNo the new mobile no
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

    /* (non-Javadoc)
     * @see in.co.rays.project0.form.BaseForm#getDto()
     */
    @Override
    public BaseDTO getDto() {
        CollegeDTO dto = new CollegeDTO();
        dto.setId(id);
        dto.setName(name);
        dto.setAddress(address);
        dto.setCity(city);
        dto.setState(state);
        dto.setPhoneNo(mobileNo);
        dto.setCreatedDateTime(new Timestamp(new Date().getTime()));
        dto.setModifiedDateTime(new Timestamp(new Date().getTime()));
        return dto;
    }

    /**
     * Populate from from dto
     */
    @Override
    public void populate(BaseDTO bDto) {
        CollegeDTO dto = (CollegeDTO) bDto;
        id = dto.getId();
        name = dto.getName();
        address = dto.getAddress();
        city = dto.getCity();
        state = dto.getState();
        mobileNo = dto.getPhoneNo();
        
        if(dto.getCreatedDateTime()!=null)
        createdDateTime = dto.getCreatedDateTime().getTime();
        
        if(dto.getModifiedDateTime()!=null)
        modifiedDateTime = dto.getModifiedDateTime().getTime();
        
    }

}
