/**
 * 
 */

/**
 * @author Jacob Drinkwater
 *2/18/20
 *this sets up a room with the given characteristics  name description movements objects etc.
 */
import java.util.*;

public class Room {
	private String roomName,	//name of the room
				   description;	//description of the room
	private ArrayList<GameObject> objects;//array list of objects in a room
	private ArrayList<movement> movements;//array list of possible movents and room ids that you can move to
	private int roomID;	//the current rooms id


	public Room(int id,String rn,String de,ArrayList<movement>mv)	//sets a room to the id, room name, description,momvents

	{
	
		roomName=rn;
		movements = mv;
		description = de;
		roomID = id;
		objects= new ArrayList<GameObject>();
	}
	
	public String getName()		//gets the room name
	{
		return roomName;
	}
	
	public String getDescription() //gets the rooms description
	{
		return description;
	}
	public ArrayList<movement> getMovements()	//returns the list of possible movements
	{
		return movements;
	}
	public int getID()	//gets the room Id
	{
		return roomID;
	}
	/*Adds an object
	 * @params GameObject 
	 */
	public void addObject(GameObject Object)	
	{
		objects.add(Object);
	}
	/*
	 * @params GameObject
	 * removes an object from a room
	 */
	public void removeObject(GameObject o) throws RemoveException	//remove an object from a room
	{
		if (objects.contains(o)  )
		{
			objects.remove(o);
		}
		else
			throw new RemoveException();
	}
	/*
	 * @params GameObject 
	 * returns if the room has an object
	 */
	public boolean containsObject(GameObject o)	//checks to see if the room has the object
	{
		return objects.contains(o);
	}
	
	public int getObjectCount()	//gets the ammount of objects in a room
	{
		return objects.size();
	}
	/*
	 * picks up  an object
	 * returns a game object
	 */
	public GameObject getObject(int index) throws IndexException	
	{
		if(objects.size()>0)
		{
			return objects.get(index);
		}
		else
			throw new ArrayIndexOutOfBoundsException();
	}
	/*
	 * picks up an object with given string after looping through the object array
	 * returns a gameobject
	 */
	public GameObject getObject(String obj) throws RemoveException
	{
		for(int i = 0; i < objects.size();i++)
		{
			if (obj.equals(objects.get(i).getName()))
			{
				return objects.get(i);
			}
		
		} 
		throw new RemoveException();
		
	}
	/*
	 * displays just the objects in a room.
	 */
	public String showObjectsInRoom()
	{
		return "Objects in Room: " + objects;
	}
	//prints out the room name,description,movements,objects, and id
	public String toString()
	{
		return "Room: " + roomName+" id: " +roomID+" Description: " + description+" legal movements: "+movements + "Objects in room: " + objects;
	}

}
