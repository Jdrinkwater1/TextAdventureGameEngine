/**
 * 
 */
import java.awt.*;
import javax.swing.*;
/**
 * @author Jacob Drinkwater
 *
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
	
	private JLabel gameLabel;
					
	public GameGUI3()
	{
		JFrame window = new JFrame("TextAdventure");
		window.setSize(500,700);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() { 			
			public void run(){				
				new GameGUI3();
			}}				
		);

	}

}
