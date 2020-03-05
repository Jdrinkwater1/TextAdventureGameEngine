/**
 * 
 */

/**
 * @author Jacob Drinkwater
 *remove exception
 */
public class RemoveException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getMessage()
	{
		return "Item does not exist.";
	}

}
