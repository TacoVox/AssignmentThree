package gui;

import becker.robots.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class RobotWars  
{
	private JFrame gameFrame;
	private JPanel gamePanel;
	private JButton prButton;
	private City gameCity;
	private JPanel controlPanel;
	private JMenuBar menuBar;
	
	private final int size = 11;
	
	
	public RobotWars()
	{
		gameFrame = new JFrame("Robot Wars");
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createMenuBar();
		createMainPanel();
		createControlPanel();
		createCity(11, 11);
	}
	
	
	/**
	 *  A method to create the main game panel that will hold 
	 *  the players controls panel and the city view.
	 */
	private void createMainPanel()
	{
		gamePanel = new JPanel(null);
		gamePanel.setLayout(new BorderLayout());
		
		gameFrame.add(gamePanel);
		
		gameFrame.setContentPane(gamePanel);
		
		gameFrame.pack();
	}
	
	
	/**
	 *  A method to create the control panel and the 
	 *  buttons in it.
	 */
	private void createControlPanel()
	{
		JButton button;
		
		controlPanel = new JPanel(null);
		controlPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		button = new JButton("UP");
		button.setPreferredSize(new Dimension(100, 30));
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 1;
		c.gridy = 0;
		controlPanel.add(button, c);
		
		button = new JButton("DOWN");
		button.setPreferredSize(new Dimension(100, 30));
		c.anchor = GridBagConstraints.PAGE_END;
		c.gridx = 1;
		c.gridy = 2;
		controlPanel.add(button, c);
		
		button = new JButton("PICK");
		button.setPreferredSize(new Dimension(100, 30));
		c.anchor = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 1;
		controlPanel.add(button, c);
		
		button = new JButton("LEFT");
		button.setPreferredSize(new Dimension(100, 30));
		c.anchor = GridBagConstraints.LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		controlPanel.add(button, c);
		
		button = new JButton("RIGHT");
		button.setPreferredSize(new Dimension(100, 30));
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 2;
		c.gridy = 1;
		controlPanel.add(button, c);
		
		gamePanel.add(controlPanel, BorderLayout.SOUTH);
		
		gameFrame.pack();
		
	}
	
	
	/**
	 * 
	 */
	private void createMenuBar()
	{
		menuBar = new JMenuBar();
		
		JMenu actions = new JMenu("Actions");
		
		JMenuItem pause = new JMenuItem("Pause");
		actions.add(pause);
		JMenuItem resume = new JMenuItem("Resume");
		actions.add(resume);
		
		menuBar.add(actions);
		
		JMenu settings = new JMenu("Settings");
		
		JRadioButton easy = new JRadioButton("Easy");
		JRadioButton normal = new JRadioButton("Normal");
		JRadioButton hard = new JRadioButton("Hard");
		
		ButtonGroup group = new ButtonGroup();
		group.add(easy);
		group.add(normal);
		group.add(hard);
		
		settings.add(easy);
		settings.add(normal);
		settings.add(hard);
		
		menuBar.add(settings);	
		
		gameFrame.setJMenuBar(menuBar);
	}
	
	
	/** 
	 * A method to create the city and the city view.
	 * 
	 * @param str	The number of visible streets.
	 * @param ave	The number of visible avenues.
	 */
	private void createCity(int str, int ave)
	{
		City.showFrame(false);
		
		gameCity = new City(0, 0, size, size);
		// create robots here and threads (do not start!!!)
		
		RobotUIComponents uic = new RobotUIComponents(gameCity, 0, 0, size, size);
		CityView cv = uic.getCityView();
		
		gamePanel.add(cv, BorderLayout.NORTH);
		
		gameFrame.pack();
		
	}
	
	
	public void restart()
	{
		gameFrame.remove(gamePanel);
		
		createMainPanel();
		createControlPanel();
		createCity(size, size);
	}
	

	public static void main(String[] args) 
	{
		
		
		RobotWars game = new RobotWars();
		
		
		

	} // end main()

} // end RobotWars
