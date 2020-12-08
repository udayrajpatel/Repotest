package in.co.rays.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.ParseException;

// TODO: Auto-generated Javadoc
/**
 * This class validates input data
 *  
 * @author Shivam Tiwari
 */
 
public class DataValidator {

	   

	/**
	 * Checks if is name.
	 *
	 * @param name the name
	 * @return true, if is name
	 */
	public static boolean isName(String name) { 

		String namereg = "^[^-\\s][\\p{L} .']+$";
		
		if (isNotNull(name) && name.matches(namereg)) {

			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Check Password Length.
	 *
	 * @param val the val
	 * @return true, if is password length
	 */
	public static boolean isPasswordLength(String val) {

		if (isNotNull(val) && val.length() >= 8 && val.length() <= 12) {
		
			
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if is valid age.
	 *
	 * @param val the val
	 * @return true, if is valid age
	 */
	public static boolean isValidAge(String val)
	{
		
		boolean pass = false;
		if (isDate(val)) {
			Date cdate = new Date();
			try {
				SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
				Date userdate = format.parse(val);
				int age = cdate.getYear()-userdate.getYear();
				System.out.println("final age  "+age);
				if(age>=18){
					pass=true;
				}
			} catch (java.text.ParseException e) {
				
			}
		}
		
		return pass;
	}

/**
 * Checks if is password.
 *
 * @param pass the pass
 * @return true, if is password
 */
public static boolean isPassword(String pass) { // my method created
        
	
		String passreg = "((?=.*\\d)(?=.*[a-z])(?=.*[@#$%]).{8,20})";
		
		if (isNotNull(pass) && pass.matches(passreg) ) {
			System.out.println("true");
			return true;
		}

		else {
			return false;
		}
	}

/**
 * Checks if is roll no.
 *
 * @param roll the roll
 * @return true, if is roll no
 */
public static boolean isRollNo(String val) 
{

	String rollno = "[0-9]+[a-zA-Z_]+[0-9]+";

	if (isNotNull(val)) 
	{
	  try 
		 {
		   return val.matches(rollno);
		 }
	 catch (NumberFormatException e) 
		 {
	    	return false;
		 }

	} 
	else 
		{
	      return false;
		}
}


/*public static boolean isRollNo(String roll) { 
	
	String rollreg = "[a-zA-Z]{2}[0-9]{4}";
	

	if (DataValidator.isNotNull(roll)) {

		boolean check = roll.matches(rollreg);
		System.out.println(check);
		return check;
	}

	else {

		return false;
	}
}
*/
/**
 * Checks if is phone no.
 *
 * @param val the val
 * @return true, if is phone no
 */
public static boolean isPhoneNo(String val) {

	String phonereg = "^[6-9][0-9]{9}$";
//	String phonereg = "^[6-9]{10}$";

	if (isNotNull(val)) {
		try {
			return val.matches(phonereg);
		} catch (NumberFormatException e) {
			return false;
		}

	} else {
		return false;
	}
}


/**
 * Checks if is phone length.
 *
 * @param val the val
 * @return true, if is phone length
 */
public static boolean isPhoneLength(String val) {

	if (isNotNull(val) && val.length() == 10) {
		return true;
	} else {
		return false;
	}
}


	/**
	 * Checks if value is Null.
	 *
	 * @param val the val
	 * @return true, if is null
	 */
    public static boolean isNull(String val) {
        if (val == null || val.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if value is NOT Null.
     *
     * @param val the val
     * @return true, if is not null
     */
    public static boolean isNotNull(String val) {
        return !isNull(val);
    }

    /**
     * Checks if value is an Integer.
     *
     * @param val the val
     * @return true, if is integer
     */

    public static boolean isInteger(String val) {

        if (isNotNull(val)) {
            try {
                int i = Integer.parseInt(val);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Checks if value is Long.
     *
     * @param val the val
     * @return true, if is long
     */
    public static boolean isLong(String val) {
        if (isNotNull(val)) {
            try {
                long i = Long.parseLong(val);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Checks if value is valid Email ID.
     *
     * @param val the val
     * @return true, if is email
     */
    public static boolean isEmail(String val) {

        String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (isNotNull(val)) {
            try {
                return val.matches(emailreg);
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }
    }

    /**
     * Checks if value is Date.
     *
     * @param val the val
     * @return true, if is date
     */
    public static boolean isDate(String val) {

        Date d = null;
        if (isNotNull(val)) {
            d = DataUtility.getDate(val);
        }
        return d != null;
    }

    /**
     * Test above methods.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        System.out.println("Not Null 2" + isNotNull("ABC"));
        System.out.println("Not Null 3" + isNotNull(null));
        System.out.println("Not Null 4" + isNull("123"));

        System.out.println("Is Int " + isInteger(null));
        System.out.println("Is Int " + isInteger("ABC1"));
        System.out.println("Is Int " + isInteger("123"));
        System.out.println("Is Int " + isNotNull("123"));
    }
}
