/**
 * 
 */

/**
 * @author Jacob Drinkwater
 * game app that reads in rooms from a text file
 *	2/9/20
 */
import java.io.*;
import java.lang.reflect.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

import org.json.simple.JSONArray; 
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException; 
import javax.swing.JFileChooser;
public class GameApp {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IndexException, IOException, ParseException, InterruptedException {
		
		Scanner nameInput = new Scanner(System.in);
		System.out.println("Enter the game you want to play: ");
		String fileName = nameInput.nextLine();
		File rooms = new File(fileName+".txt");			//reads in the file
		File objects = new File(fileName+"objects.txt");
		File startup = new File(fileName+"data.dat");
		File analytics = new File(fileName+"analytics.dat");
		String roomName,
			   description;
		int roomID;
		int roomIndex = 0;
		Room roomArr[]=new Room[30];		//can have a total of 30 rooms
		int gamesPlayed = 0;
		String lastGamePlayed = "";
		String date = "";
		String andate = "";
		double timePlayed = 0;
		String gameName ="";
		String angameName ="";
		Duration timeElapsed;
		/*
		 * Binary file writing and reading
		 */
		try(DataInputStream input = new DataInputStream(new FileInputStream(startup)))
		{
			gamesPlayed = input.readInt();
			for (int i = 0; i<15; i++)
				lastGamePlayed += input.readChar();
			
			for (int i = 0; i<15; i++)
				date += input.readChar();
			System.out.println("Number of games played:"+gamesPlayed+" Last Game Played:"+lastGamePlayed+" Last Played:"+date);
			
		}
		//if there is no file make one and write data to it
		catch(FileNotFoundException e)
		{
			System.out.println("No file found... Creating file");
			Thread.sleep(250);
			try(DataOutputStream output = new DataOutputStream(new FileOutputStream(startup)))
			{
				output.writeInt(gamesPlayed);	//write the games played
				output.writeChars(String.format("%15s",fileName));	//writing the last game played
				output.writeChars(String.format("%15s",java.time.LocalDate.now().toString()));	//writing the current date
			}
		}
		
		Instant start = Instant.now();
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
				//System.out.println("Adding... "+obj + " to room " + itemRoomNum);
				roomArr[itemRoomNum-1].addObject(obj);     //  add the object to the appropriate room.    If room 1, then subscript 0   thus  room minus 1
			}
			 
	
		
		
		}
                       
            
            
            catch(IOException|ArrayIndexOutOfBoundsException e)	//Adding the use of  | in catch for java 8
            {
            	System.out.println(e.getMessage());
            }
		
            
		
		
		
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
				try(DataOutputStream output = new DataOutputStream(new FileOutputStream(startup)))
				{
					output.writeInt(gamesPlayed +1);	//write the games played
					output.writeChars(String.format("%15s",fileName));	//writing the last game played
					output.writeChars(String.format("%15s",java.time.LocalDate.now().toString()));	//writing the current date
				
					
				}
				try(RandomAccessFile io = new RandomAccessFile(analytics,"rw"))
				{
				Instant endTime = Instant.now();
				timeElapsed = Duration.between(start,endTime);
				timePlayed += timeElapsed.getSeconds()/60;
				angameName = fileName;
				io.seek(io.length());
				io.writeChars(String.format("%15s",angameName));
				io.writeChars(String.format("%15s", java.time.LocalDate.now().toString()));
				io.writeDouble(timePlayed);
				}
				System.out.println("Closing the program...");
				done = true;
				System.exit(0);	//quits the program
			break;
			case ("HELP"):
				System.out.println("Available commands are: Quit,Help,Inventory,Look,Take,Drop,Save, and Load");
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
			case("SAVE"):
				JSONObject jo = new JSONObject();
				
				BufferedWriter bw = new BufferedWriter(new FileWriter(new File("Save.json")));
				jo.put("CurrentRoom", currentRoom);
				
				//for items in inventory
				JSONArray ja = new JSONArray();
				Map m;
				for(GameObject gameobj:listOfObjects)
				{
					m = new LinkedHashMap(3);
					m.put("objName", gameobj.getName());
					m.put("description", gameobj.getDescription());
					m.put("roomNumber",gameobj.getInitialLocation());
					//add the map to json array
					ja.add(m);
				}
				jo.put("Inventory", ja);
				
				bw.write(jo.toJSONString()+"\n");
				bw.close();
				System.out.println("Current room and inventory saved");
				break;
		
			
			/*
			 * Problem below line 269
			 */
			case("LOAD"):
				File saveLoad = new File("Save.json");	//read in a new file
				BufferedReader br = new BufferedReader(new FileReader(saveLoad));	//make a new buffered reader
				String line = "";			//initialize the string
				while((line = br.readLine())!=null)
				{
				Object obj = new JSONParser().parse(line);	//make the line a json object
				jo = (JSONObject) obj;
				
				long readRoom = (long) jo.get("CurrentRoom");
				currentRoom = (int) readRoom;
				Iterator<Map.Entry> itr1;
				JSONArray jaIn = (JSONArray) jo.get("Inventory");		
				jaIn = (JSONArray) jo.get("Inventory");	//type cast to JSONArray
				Iterator itr2 = jaIn.iterator();
				while (itr2.hasNext())
				{
					itr1 = ((Map) itr2.next()).entrySet().iterator();
					Map.Entry pair = itr1.next();
					
					long objRoom = (long)pair.getValue();
					int objRoomInt =(int)objRoom;
					pair = itr1.next();
					String desc = (String) pair.getValue();
					pair = itr1.next();
					String name = (String)pair.getValue();
					//System.out.println(objRoom+desc+name);
					try {
						listOfObjects.add(roomArr[objRoomInt-1].getObject(name));
						roomArr[objRoomInt-1].removeObject(roomArr[objRoomInt-1].getObject(name));//in the current room remove the object thats in the room that matches the given item name
						}
						catch(RemoveException e)
						{
							System.out.println(e.getMessage());
						}	
					}
				
			
				
				
				}
			case("ANALYTICS"):
				try(RandomAccessFile io = new RandomAccessFile(analytics,"rw"))
				{
					int positon = 0;
					angameName = "";	//resetting the game name
					andate="";	//resetting the date string
					Boolean anDone = false;
					System.out.println("Number of records in file: "+ io.length()/68); //each record is 68 bytes divide by length of total file size
					
					for(int i = 0; i<15; i++)
						angameName += io.readChar();	//append game name for each char 
					for(int i = 0; i<15; i++)
						andate += io.readChar();	//append date for each char (set length of 15)
					timePlayed = io.readDouble();	//read in the  double
					System.out.println("\nGame Played: "+angameName+"\nDate Last Played: "+andate+"\nTotal time played: "+timePlayed);
					io.seek(0);	//return back to 0
					System.out.println("Available Commands:Last,Next,Previous,Return");
					Scanner anInput = new Scanner(System.in);	//make a new scanner to get info in
					while(!anDone)
					{
					System.out.println("Enter a command: ");
					String userAnInput = input.nextLine().toUpperCase();	//make all userinputs uppcase
					switch(userAnInput) {
					case("NEXT"):
						andate="";	//reset date string
						angameName = "";	//reset game name
						positon += 68;	// set positon to next record
						if(positon < io.length() && positon > 0 )
						{	
							io.seek(positon);	//seek to next record
							for(int i = 0; i<15; i++)
								angameName += io.readChar();
							
							for(int i = 0; i<15; i++)
								andate += io.readChar();
						
							timePlayed = io.readDouble();
							System.out.println("\nGame PLayed: "+angameName+"\nDate Last Played: "+andate+"\nTotal time played: "+timePlayed);
						}
						else
							System.out.println("No more records available.");
						
					break;
					case("PREVIOUS"):
						andate="";
						angameName = "";
						positon -= 68;
						if(positon < io.length() && positon > 0 )
						{	
							
							io.seek(positon);
							for(int i = 0; i<15; i++)
								angameName += io.readChar();
							
							for(int i = 0; i<15; i++)
								andate += io.readChar();
							
							timePlayed = io.readDouble();
							System.out.println("\nGame PLayed: "+angameName+"\nDate Last Played: "+andate+"\nTotal time played: "+timePlayed);
						}
						else
							System.out.println("At the first record");
					break;
					case("LAST"):
						andate="";
						angameName = "";
						positon = (int) (io.length()-68);
					io.seek(positon);
					for(int i = 0; i<15; i++)
						angameName += io.readChar();
					
					for(int i = 0; i<15; i++)
						andate += io.readChar();
					
					timePlayed = io.readDouble();
					System.out.println("\nGame PLayed: "+angameName+"\nDate Last Played: "+andate+"\nTotal time played: "+timePlayed);
					break;
					case("RETURN"):
						anDone= true;
					}
					}
				}
			catch(EOFException e)
			{
				System.out.println("******HIT EOF********");
				e.getStackTrace();
			}
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
							//System.out.println("Hit required object");
							for(int index = 0; index < listOfObjects.size();index++)
							{
								if(listOfObjects.get(index).toString().contains((roomArr[currentRoom].getMovements().get(i).getRequiredObject())))
								{
									//System.out.println(currentRoom); 
									movement tempMove = roomArr[currentRoom].getMovements().get(i);
									//System.out.println(tempMove);
									currentRoom= tempMove.getRoomID() -1;
									//System.out.println(currentRoom);
								}
								
							}
						}
						else {
							//System.out.println(currentRoom);
							movement tempMove = roomArr[currentRoom].getMovements().get(i);
							//System.out.println(tempMove);
							currentRoom= tempMove.getRoomID() -1;
							//System.out.println(currentRoom);
						
					}
					}
				}
				
			}
		}
		
		
	
	}
}