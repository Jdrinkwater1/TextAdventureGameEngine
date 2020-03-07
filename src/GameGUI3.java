/**
 * 
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
/**
 * @author Jacob Drinkwater
 *3/7/20
 *GUI for the gameapp not functional yet
 */
public class GameGUI3 {

	private JButton helpButton,
					lookButton,
					northButton,
					eastButton,
					southButton,
					westButton,
					inButton,
					takeButton,
					dropButton;
					
	private JTextArea gameText;
	private JTextField inputText;
	private JList inventoryList;
	private JLabel gameLabel;
					
	public GameGUI3()
	{
		JFrame window = new JFrame("TextAdventure");	//set the window title to text adventure maybe make it dynamic later
		window.setSize(700,500);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagConstraints gbc = new GridBagConstraints();
		/*
		 * North Panel Setup
		 */
		gameLabel = new JLabel("Adventure Game!");
		window.add(gameLabel,BorderLayout.NORTH);
		/*
		 * Center Panel Setup
		 */
		gameText = new JTextArea();
		gameText.setForeground(Color.WHITE);	//make the text area background black
		gameText.setBackground(Color.BLACK);	//make the text white
		gameText.setText("Game output goes here");	//set the text for now
		window.add(gameText,BorderLayout.CENTER);
		
		//added a change for git
		/*
		 * South Panel Setup
		 */
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());	//makes south panel flow layout
		takeButton = new JButton("Take");
		dropButton = new JButton("Drop");
		inputText = new JTextField("Enter item name:");	//make the text field filled with enter item name
		southPanel.add(takeButton);
		southPanel.add(dropButton);
		southPanel.add(inputText);
		inputText.setColumns(15);	//make the input box 15 columns wide
		window.add(southPanel,BorderLayout.SOUTH);
		
		/*
		 * West Panel Setup
		 */
		JPanel westPanel = new JPanel();
		GridBagLayout westLayout = new GridBagLayout();
		westLayout.columnWidths = new int[]{100, 0};	//make the columns 100 px wide
		westLayout.rowHeights = new int[]{23, 0, 0, 0};	//make each row 23 high
		westLayout.rowWeights = new double[]{0.0, 1.0};//set the spacing between rows for each component
		westPanel.setLayout(westLayout);
	//set up the help button
		helpButton = new JButton("Help");
		gbc.insets = new Insets(0,0,5,0);//pad the bottom 5 px
		gbc.gridx = 0;
		gbc.gridy = 0;
		westPanel.add(helpButton,gbc);
	//setup the inventory list
		inventoryList = new JList();
		gbc.fill = GridBagConstraints.BOTH;	//make the component fill in both directions X and Y
		gbc.gridy = 1;//set y to 1
		westPanel.add(inventoryList,gbc);
	//setup the look button
		lookButton = new JButton("Look");
		gbc.fill = GridBagConstraints.NONE;	//reset fill back to 0
		gbc.gridy = 2;						//sets the position to y 2
		westPanel.add(lookButton,gbc);
	//add west panel to window
		window.add(westPanel,BorderLayout.WEST);	//add it to the west layout
		
		/*
		 * East Panel Setup
		 */
		JPanel eastPanel = new JPanel();
		eastPanel.setLayout(new GridBagLayout());
		northButton = new JButton("North");
		eastButton = new JButton("East");
		southButton = new JButton("South");
		westButton = new JButton("West");
		inButton = new JButton("In");
	//setup north button
		gbc.gridx=0;	//reset grid axis
		gbc.gridy=0;
		eastPanel.add(northButton,gbc);
	//setup east button
		gbc.gridy=1;
		eastPanel.add(eastButton,gbc);
	//setup south button
		gbc.gridy=2;
		eastPanel.add(southButton,gbc);
	//setup west button
		gbc.gridy=3;
		eastPanel.add(westButton,gbc);
	//setup in button
		gbc.gridy=4;
		eastPanel.add(inButton,gbc);
	//add it to the window
		window.add(eastPanel,BorderLayout.EAST);
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() { 			
			public void run(){				
				new GameGUI3();
			}}				
		);

	}

}
