/**
 * 
 */

/**
 * @author Jacob Drinkwater
 *2/18/2020
 *This class will deal with movements for Game also I forgot to capitalize the class name and now im horrifed to go back and change
 *everything
 */
public class movement {
	private String direction;		//direction of movement
	private int roomID;				//id of the room moving to
	private String requiredObject;	//the object needed to proceed
	
	/*
	 * @Params none
	 * default constructor needs string and int 
	 */
	public movement(String direction, int roomID)
	{
		this.direction = direction;
		this.roomID = roomID;
		requiredObject = "";
	}
	/*
	 * Overloads the original constructor string for direction,int for id, and String for the required object
	 */
	public movement(String direction, int roomID,String requiredObject)
	{
		this.direction = direction;
		this.roomID = roomID;
		this.requiredObject = requiredObject;
	}
	public String getDirection()	//gets the direction of movement and makes it uppercase
	{
		return direction.toUpperCase();	
	}
	public String getRequiredObject()
	{
		return requiredObject;
	}
	
	public boolean needsObjects()
	{
		if(!requiredObject.equals(""))
		{
			return true;
		}
		else
			return false;
	}
	public int getRoomID()			//returns the room id 
	{
		return roomID;
	}
	//returns direction the room id and items needed to proceed. if there is no item needed leave it out
	public String toString()
	{
		if(requiredObject.equals(""))
		{
			return "Direction: " + direction+"|" + " RoomID: " + roomID;
		}
		else
			return "Direction: " + direction+"|" + " RoomID: " + roomID + "\n" + "Items needed to proceed: " + requiredObject; 
	}

}
