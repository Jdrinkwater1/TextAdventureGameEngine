import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Color;
import javax.swing.JList;

public class GameGUI2 {

	JFrame frmGame;
	private JTextField txtInsertItemName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameGUI2 window = new GameGUI2();
					window.frmGame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameGUI2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGame = new JFrame();
		frmGame.setTitle("Game");
		frmGame.setBounds(100, 100, 450, 300);
		frmGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblGame = new JLabel("Game");
		frmGame.getContentPane().add(lblGame, BorderLayout.NORTH);
		
		JTextArea txtrInstertGameText = new JTextArea();
		txtrInstertGameText.setForeground(Color.WHITE);
		txtrInstertGameText.setBackground(Color.BLACK);
		txtrInstertGameText.setText("Insert Game Text");
		frmGame.getContentPane().add(txtrInstertGameText, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		frmGame.getContentPane().add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{53, 0};
		gbl_panel.rowHeights = new int[]{23, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JButton helpButton = new JButton("Help");
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_helpButton = new GridBagConstraints();
		gbc_helpButton.insets = new Insets(0, 0, 5, 0);
		gbc_helpButton.gridx = 0;
		gbc_helpButton.gridy = 0;
		panel.add(helpButton, gbc_helpButton);
		
		JList list = new JList();
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel.add(list, gbc_list);
		
		JButton lookButton = new JButton("Look");
		GridBagConstraints gbc_lookButton = new GridBagConstraints();
		gbc_lookButton.gridx = 0;
		gbc_lookButton.gridy = 2;
		panel.add(lookButton, gbc_lookButton);
		
		JPanel panel_1 = new JPanel();
		frmGame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton takeButton = new JButton("Take");
		panel_1.add(takeButton);
		
		JButton dropButton = new JButton("Drop");
		panel_1.add(dropButton);
		
		txtInsertItemName = new JTextField();
		txtInsertItemName.setText("Insert Item Name");
		panel_1.add(txtInsertItemName);
		txtInsertItemName.setColumns(15);
		
		JPanel panel_2 = new JPanel();
		frmGame.getContentPane().add(panel_2, BorderLayout.EAST);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JButton northButton = new JButton("North");
		GridBagConstraints gbc_northButton = new GridBagConstraints();
		gbc_northButton.insets = new Insets(0, 0, 5, 0);
		gbc_northButton.gridx = 0;
		gbc_northButton.gridy = 0;
		panel_2.add(northButton, gbc_northButton);
		
		JButton eastButton = new JButton("East");
		GridBagConstraints gbc_eastButton = new GridBagConstraints();
		gbc_eastButton.insets = new Insets(0, 0, 5, 0);
		gbc_eastButton.gridx = 0;
		gbc_eastButton.gridy = 1;
		panel_2.add(eastButton, gbc_eastButton);
		
		JButton southButton = new JButton("South");
		GridBagConstraints gbc_southButton = new GridBagConstraints();
		gbc_southButton.insets = new Insets(0, 0, 5, 0);
		gbc_southButton.gridx = 0;
		gbc_southButton.gridy = 2;
		panel_2.add(southButton, gbc_southButton);
		
		JButton westButton = new JButton("West");
		GridBagConstraints gbc_westButton = new GridBagConstraints();
		gbc_westButton.insets = new Insets(0, 0, 5, 0);
		gbc_westButton.gridx = 0;
		gbc_westButton.gridy = 3;
		panel_2.add(westButton, gbc_westButton);
		
		JButton inButton = new JButton("In");
		GridBagConstraints gbc_inButton = new GridBagConstraints();
		gbc_inButton.gridx = 0;
		gbc_inButton.gridy = 4;
		panel_2.add(inButton, gbc_inButton);
	}
	public void actionPerformed(ActionEvent x) 
	{
		
	}
	
}
