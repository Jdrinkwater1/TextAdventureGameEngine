
public class IndexException extends Exception {
	private static final long serialVersionUID = 1L;

	public String getMessage()
	{
		return "Index out of bounds";
	}
}
