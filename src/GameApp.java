

/**
 * 
 */

/**
 * @author Jacob Drinkwater
 * game app that reads in rooms from a text file
 *	2/9/20
 */
import java.io.*;
import java.util.*;
public class GameApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IndexException {
		
		Scanner nameInput = new Scanner(System.in);
		System.out.println("Enter the game you want to play: ");
		String fileName = nameInput.nextLine();
		
		File rooms = new File(fileName+".txt");			//reads in the file
		File objects = new File(fileName+"objects.txt");
		String roomName,
			   description;
		int roomID;
		int roomIndex = 0;
		Room roomArr[]=new Room[30];		//can have a total of 30 rooms
		
		ArrayList<GameObject> listOfObjects = new ArrayList<GameObject>();
		try(Scanner s = new Scanner(rooms))
		{
			
			while(s.hasNext())		//while the file still has lines to read
			{
				
				roomID = Integer.parseInt(s.nextLine());	//sets room id to first line
				//System.out.println(roomID);
				roomName = s.nextLine();
				//System.out.println(roomName);
				boolean done = false;
				String temp = "";
				description = "";
				while(!done)		//while next line != ----- add it to description
				{
					temp = s.nextLine();
					if(temp.equals("-----"))
						done = true;
					
					else
					
						description += temp +"\n";		
					}
				//System.out.println(description);
				done = false;
				temp = "";
				ArrayList <movement> movements = new ArrayList<movement>();
				while(!done)					//while not done look for empty line if its not "" add it to the arraylist movement
				{
					temp = s.nextLine();
						if(temp.equals(""))
							done = true;
						else
						{
							if(temp.contains("/"))
							{
								String[] splitTemp = temp.split("\t");
								String[] requiredItem = splitTemp[1].split("/");
								//System.out.println(requiredItem[1]);
								int roomid = Integer.parseInt(requiredItem[0]);
								
								
								movement m1 = new movement(splitTemp[0],roomid,requiredItem[1]);
								movements.add(m1);
								//System.out.println("<><><><if hit><><><><><>");
							}
							else
							{
								String[] splitTemp = temp.split("\t");
								int roomid = Integer.parseInt(splitTemp[1]);
								movement m1 = new movement(splitTemp[0],roomid);
								movements.add(m1);
								//System.out.println("<><><><else hit><><><><><>");
							}
							
						}		
						
				}
				Room room = new Room(roomID,roomName,description,movements);
				roomArr[roomIndex] = room;
				roomIndex++;
				
			}
			
		}
		
		catch(IOException e)
		{
			System.out.println(e.getMessage());
		}
		try(Scanner o = new Scanner(objects))
		{
			
			String objName,
				   itemDesc ="" ;
			int itemRoomNum;
			
			while(o.hasNext())		//while the file still has lines to read
			{
				objName = o.nextLine();		//obj name first line
				itemDesc =o.nextLine();		//item desc 2nd line
				itemRoomNum = Integer.parseInt(o.nextLine());	//initial room num final line for object 
				GameObject obj = new GameObject(objName,itemDesc,itemRoomNum);
				
				//************   Jacob, look here   **********************
				//put out a statement to screen to indicate an object is being added to a room
				System.out.println("Adding... "+obj + " to room " + itemRoomNum);
				roomArr[itemRoomNum-1].addObject(obj);     //  add the object to the appropriate room.    If room 1, then subscript 0   thus  room minus 1
			}
			 // System.out.println("*******Number of objects in room 1********");

			

			  //System.out.println(roomArr[0].getObjectCount());

          
			  			/*
			  			 * 
			  			 
                         GameObject test = roomArr[0].getObject(0);

                         System.out.println("got object from room 1.  It is: " + test);

                         System.out.println("\nnow going to remove it from room 1");

                        roomArr[0].removeObject(test);

                         System.out.println("Room 1 data is now:  " + roomArr[0]);
                         
                        roomArr[1].addObject(test);
                         
                         System.out.println("***Room 2 with new object:***"+"\n"+"\n"+roomArr[1]);
                        
                         
                         System.out.println("\n"+"***Testing object count***");
                         System.out.println(roomArr[0].getObjectCount());
                         System.out.println("\n"+"***Contains object test***");
                         System.out.println(roomArr[0].containsObject(test));
              */
	
		
		
		}
                       
            
            
            catch(IOException|ArrayIndexOutOfBoundsException e)	//Adding the use of  | in catch for java 8
            {
            	System.out.println(e.getMessage());
            }
		
            
		
		//Go through all the rooms and print them all.
		System.out.println("************ Do the rooms have the right items in them...   ***************");
		for (int i = 0; i < roomIndex; i++)
		{
			System.out.println(roomArr[i]);
			System.out.println("__________________\n");
		}
		System.out.println("*******Objects in room 1********");
		System.out.println(roomArr[0].getObjectCount());
		
		
		/*
		 * Command testing
		 */
		
		boolean done = false;
		int currentRoom = 0;	// the room that the player is standing in
		while(!done)
		{
			
			System.out.println(roomArr[currentRoom].getName());	//name of the current room
			System.out.println(roomArr[currentRoom].getDescription());//description of current room;
			
			Scanner input = new Scanner(System.in);	//make a new scanner to get info in
			System.out.println("Enter a command: ");
			String userInput = input.nextLine().toUpperCase();	//make all userinputs uppcase
			switch (userInput) 
			{
			case "QUIT":
				System.out.println("Closing the program...");
				done = true;
				System.exit(0);	//quits the program
			break;
			case ("HELP"):
				System.out.println("Available commands are: Quit,Help,Inventory,Look,Take,Drop");
			break;
			case("INVENTORY"):
				System.out.println(listOfObjects);
			break;
			case("LOOK"):
				System.out.println(roomArr[currentRoom].showObjectsInRoom());
				System.out.println("Available Movements: " +roomArr[currentRoom].getMovements());
			break;
			case("TAKE"):
				Scanner item = new Scanner(System.in);
				System.out.println("Type the item you want to grab: ");
				String itemInput = item.nextLine();
				try {
				listOfObjects.add(roomArr[currentRoom].getObject(itemInput));
				roomArr[currentRoom].removeObject(roomArr[currentRoom].getObject(itemInput));//in the current room remove the object thats in the room that matches the given item name
				System.out.println("Item taken");
				}
				catch(RemoveException e)
				{
					System.out.println(e.getMessage());
				}
			break;
			case("DROP"):
				Scanner itemDrop = new Scanner(System.in);
				System.out.println("Type the item you want to drop: ");
				itemInput = itemDrop.nextLine();
				for(int i = 0; i < listOfObjects.size();i++)
				{
					if (itemInput.equals(listOfObjects.get(i).getName()))	//if the input equals the current index of list objects get the name
					{
						roomArr[currentRoom].addObject(listOfObjects.get(i));	//add the object to the current room from your listofobjects in that index
						listOfObjects.remove(i);	//remove it from the inventory
						System.out.println("Item dropped");
					}
					
				}
			break;
			default:
				for(int i = 0; i < roomArr[currentRoom].getMovements().size();i++)
				{
					/*
					 * the follow line of code could make you blind proceed with caution
					 * it gets the current room's movements gets the element of the current loop iteration, gets the direction and sees if it is equal to the user input
					 */
					if(roomArr[currentRoom].getMovements().get(i).getDirection().equals(userInput)) 
					{
						if(roomArr[currentRoom].getMovements().get(i).needsObjects())
						{
							System.out.println("Hit required object");
							for(int index = 0; index < listOfObjects.size();index++)
							{
								if(listOfObjects.get(index).toString().contains((roomArr[currentRoom].getMovements().get(i).getRequiredObject())))
								{
									System.out.println(currentRoom); 
									movement tempMove = roomArr[currentRoom].getMovements().get(i);
									System.out.println(tempMove);
									currentRoom= tempMove.getRoomID() -1;
									System.out.println(currentRoom);
								}
								
							}
						}
						else {
							System.out.println(currentRoom);
							movement tempMove = roomArr[currentRoom].getMovements().get(i);
							System.out.println(tempMove);
							currentRoom= tempMove.getRoomID() -1;
							System.out.println(currentRoom);
						
					}
					}
				}
				
			}
		}
		
		
	
	}
}

