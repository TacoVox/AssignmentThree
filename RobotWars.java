

// import galleries
import becker.robots.*;

import javax.swing.*;

import java.awt.*;

import warrobots.*;
import listeners.*;



public class RobotWars  
{
	private JFrame gameFrame;		// main frame for the game
	private JPanel gamePanel;		// main panel to hold buttons and city view
	private JButton prButton;		// pause/resume button
	private City gameCity;			// the city
	private JPanel controlPanel;	// panel for the control buttons
	private JMenuBar menuBar;		// the menu bar
	
	private int size = 11;			// size of city (number of streets and avenues)
	private double currentSpeed = 1.0;	// starting speed of random robot
	
	/**
	 *  A constructor-creates a new frame with a menu bar,
	 *  controls and a city view.
	 */
	public RobotWars()
	{
		gameFrame = new JFrame("Robot Wars");	// create frame
		gameFrame.setVisible(true);				// set visible
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	// set closing option
		createMenuBar();		// create and add menu bar
		createMainPanel();		// create and add main panel
		createControlPanel();	// create and add control panel
		createCity(size, size, currentSpeed);		// create and add city
	}
	
	
	/**
	 *  A method to create the main game panel that will hold 
	 *  the players controls panel and the city view.
	 */
	private void createMainPanel()
	{
		gamePanel = new JPanel(null);		// create panel
		gamePanel.setLayout(new BorderLayout());		// set layout manager
			
		gameFrame.setContentPane(gamePanel);	// add panel to frame
		gameFrame.pack();
	}
	
	
	/**
	 *  A method to create the control panel and the 
	 *  buttons in it. Listeners are assigned to buttons. 
	 */
	private void createControlPanel()
	{
		JButton button;		// declare variable for buttons
		
		controlPanel = new JPanel(null);	// create a panel for controls
		controlPanel.setLayout(new GridBagLayout());	// set layout manager
		GridBagConstraints c = new GridBagConstraints();	// variable for constraints on the position of buttons
		
		button = new JButton("UP");		// button to move up
		button.setPreferredSize(new Dimension(100, 30));	// set size of button
		c.gridx = 1;		// position of button on x-axis
		c.gridy = 0;		// position of button on y-axis
		controlPanel.add(button, c);	// add button to control panel
		
		button = new JButton("DOWN");		// button to move down
		button.setPreferredSize(new Dimension(100, 30));
		c.gridx = 1;
		c.gridy = 2;
		controlPanel.add(button, c);
		
		button = new JButton("PICK");		// button to pick things
		button.setPreferredSize(new Dimension(100, 30));
		c.gridx = 1;
		c.gridy = 1;
		controlPanel.add(button, c);
		
		button = new JButton("LEFT");		// button to move left
		button.setPreferredSize(new Dimension(100, 30));
		c.gridx = 0;
		c.gridy = 1;
		controlPanel.add(button, c);
		
		button = new JButton("RIGHT");		// button to move right
		button.setPreferredSize(new Dimension(100, 30));
		c.gridx = 2;
		c.gridy = 1;
		controlPanel.add(button, c);
		
		gamePanel.add(controlPanel, BorderLayout.SOUTH);		// add control panel to main panel at the bottom
		
		gameFrame.pack();
		
	}
	
	
	/**
	 *  A method that creates the menu bar and menus.
	 *  In each menu there are menu items or radio buttons.
	 *  Action listeners are also assigned to the different 
	 *  components.
	 */
	private void createMenuBar()
	{
		menuBar = new JMenuBar();		// a menu bar
		
		JMenu actions = new JMenu("Actions");		// actions menu
		
		JMenuItem pause = new JMenuItem("Pause");		// menu item pause
		pause.addActionListener(new PauseResumeListener(this));		// add action listener to pause
		actions.add(pause);			// add pause to actions menu
		JMenuItem resume = new JMenuItem("Resume");		// menu item resume
		resume.addActionListener(new PauseResumeListener(this));		// add listener
		actions.add(resume);		// add to actions menu
		
		menuBar.add(actions);		// add actions menu to menu bar
		
		JMenu settings = new JMenu("Settings");		// settings menu
		
		JRadioButton easy = new JRadioButton("Easy");		// radio button for level easy
		easy.addActionListener(new SetSpeedListener(this, 0.5));		// add action listener
		JRadioButton normal = new JRadioButton("Normal");		// radio button for normal level
		normal.addActionListener(new SetSpeedListener(this, 1.0));		// add listener
		normal.setSelected(true);			// normal is the selected button by default
		JRadioButton hard = new JRadioButton("Hard");		// radio button for hard level
		hard.addActionListener(new SetSpeedListener(this, 2.0));	// add listener
		
		ButtonGroup group = new ButtonGroup();		// button group for radio buttons
		group.add(easy);		// add radio
		group.add(normal);		// buttons 
		group.add(hard);		// to button group
		
		settings.add(easy);			// add radio 
		settings.add(normal);		// buttons to 
		settings.add(hard);			// settings menu
		
		menuBar.add(settings);		// add settings menu to menu bar
		
		gameFrame.setJMenuBar(menuBar);		// add menu bar to frame
	}
	
	
	/**
	 *  A method to create the walls in the city.
	 */
	private void createWalls()
	{
		for (int i=0; i<size; i++)		
		{
			new Wall(gameCity, 0, i, Direction.NORTH);
			new Wall(gameCity, size-1, i, Direction.SOUTH);
			new Wall(gameCity, i, 0, Direction.WEST);
			new Wall(gameCity, i, size-1, Direction.EAST);
		}
	}
	
	
	/** 
	 * A method to create the city and the city view.
	 * 
	 * @param str	The number of visible streets.
	 * @param ave	The number of visible avenues.
	 */
	private void createCity(int str, int ave, double speed)
	{
		City.showFrame(false);		// set city frame to be unvisible
		
		gameCity = new City(0, 0, size, size);		// create a city
		
		RobotUIComponents uic = new RobotUIComponents(gameCity, 0, 0, size, size);
		
		CityView cv = uic.getCityView();		// get a view of the city
		
		gamePanel.add(cv, BorderLayout.NORTH);		// add city view to main panel in top position
		
		gameFrame.pack();
		
		prButton = uic.getStartStopButton();		// get a start/stop button
		
		createWalls();		// create walls
		
		RandomRobot karel = new RandomRobot(gameCity, 2, 2, Direction.EAST);
		
		karel.setSpeed(speed);
		
		Thread karelThread = new Thread(karel);
		karelThread.start();
		
	}
	
	
	/**
	 *  A method that restarts the game. 
	 *  First the main panel is removed and then 
	 *  new main panel, control panel and city is created.
	 */
	public void restart()
	{
		gameFrame.remove(gamePanel);
		
		createMainPanel();
		createControlPanel();
		createMenuBar();
		createCity(size, size, currentSpeed);
	}
	
	
	/**
	 *  A method for pausing and resuming the game.
	 */
	public void pauseResume()
	{
		prButton.doClick();
	}
	
	
	/**
	 *  A method to start the game with
	 *  the robots moving.
	 */
	public void gameOn()
	{
		prButton.doClick();
	}

	
	/**
	 *  A method to set the speed of the random robot.
	 */
	public void setSpeed(double speed)
	{
		currentSpeed = speed;
	}
	
	
	public static void main(String[] args) 
	{
		
		RobotWars game = new RobotWars();
		game.gameOn();
		
	} // end main()

	
} // end RobotWars
