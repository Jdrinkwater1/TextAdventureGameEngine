/**
 * 
 */
/**
 * @author Jacob Drinkwater
 *
 */
public class GameObject {
	private String objName,
				   description;
	private int objRoomNum;
	
	
	//default constructor  sets up object name, description and room number
	public GameObject(String name,String itemDesc,int RoomNumber)
	{
		objName= name;
		description = itemDesc;
		objRoomNum = RoomNumber;
	}
	
	//returns the object name 
	public String getName()
	{
		return objName;
	}
	
	//returns String description
	public String getDescription()
	{
		return description;
	}
	//returns int initial object location
	public int getInitialLocation()
	{
		return objRoomNum;
	}
	//returns string the name of object description and room number.
	public String toString()
	{
		return "Name: "+objName+" Description: " +description+"Locaton: "+objRoomNum;
	}
	

}
