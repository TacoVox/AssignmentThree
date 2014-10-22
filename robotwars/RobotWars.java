//
package robotwars;

// import galleries
import becker.robots.*;

import javax.swing.*;

import java.awt.*;

import warrobots.*;
import listeners.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import icons.*;



public class RobotWars implements ActionListener
{
	private JFrame gameFrame;		// main frame for the game
	private JPanel gamePanel;		// main panel to hold buttons and city view
	private JButton prButton;		// pause/resume button
	private City gameCity;			// the city
	private JPanel controlPanel;	// panel for the control buttons
	private JMenuBar menuBar;		// the menu bar
	
	private int size = 11;			// size of city (number of streets and avenues)
	private double currentSpeed = 1.0;	// starting speed of random robot
	private boolean paused = false;		// boolean for pause/resume methods
	
	private JButton buttonUp, buttonDown, buttonLeft, buttonRight, buttonPick;
	
	private HumanRobot mark;
	private Thread markThread;
	
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
		
		controlPanel = new JPanel(null);	// create a panel for controls
		controlPanel.setLayout(new GridBagLayout());	// set layout manager
		GridBagConstraints c = new GridBagConstraints();	// variable for constraints on the position of buttons
		
		buttonUp = new JButton("UP");		// button to move up
		buttonUp.setPreferredSize(new Dimension(100, 30));	// set size of button
		buttonUp.addActionListener(this);
		buttonUp.setActionCommand("up");
		c.gridx = 1;		// position of button on x-axis
		c.gridy = 0;		// position of button on y-axis
		controlPanel.add(buttonUp, c);	// add button to control panel
		
		buttonDown = new JButton("DOWN");		// button to move down
		buttonDown.setPreferredSize(new Dimension(100, 30));
		buttonDown.addActionListener(this);
		buttonDown.setActionCommand("down");
		c.gridx = 1;
		c.gridy = 2;
		controlPanel.add(buttonDown, c);
		
		buttonPick = new JButton("PICK");		// button to pick things
		buttonPick.setPreferredSize(new Dimension(100, 30));
		buttonPick.addActionListener(this);
		buttonPick.setActionCommand("pick");
		c.gridx = 1;
		c.gridy = 1;
		controlPanel.add(buttonPick, c);
		
		buttonLeft = new JButton("LEFT");		// button to move left
		buttonLeft.setPreferredSize(new Dimension(100, 30));
		buttonLeft.addActionListener(this);
		buttonLeft.setActionCommand("left");
		c.gridx = 0;
		c.gridy = 1;
		controlPanel.add(buttonLeft, c);
		
		buttonRight = new JButton("RIGHT");		// button to move right
		buttonRight.setPreferredSize(new Dimension(100, 30));
		buttonRight.addActionListener(this);
		buttonRight.setActionCommand("right");
		c.gridx = 2;
		c.gridy = 1;
		controlPanel.add(buttonRight, c);
		
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
//		actions.setMnemonicKey(KeyEvent.VK_A);		// set action key for keyboard (ALT + A)
		
		JMenuItem pause = new JMenuItem("Pause");		// menu item pause
		pause.addActionListener(new PauseListener(this));		// add action listener to pause
//		pause.setMnemonic(KeyEvent.VK_P);		// set action key for keyboard (ALT + P)
		actions.add(pause);			// add pause to actions menu
		
		JMenuItem resume = new JMenuItem("Resume");		// menu item resume
		resume.addActionListener(new ResumeListener(this));		// add listener
//		resume.setMnemonic(KeyEvent.VK_R);		// set (ALT + R)
		actions.add(resume);		// add to actions menu
		
		menuBar.add(actions);		// add actions menu to menu bar
		
		JMenu settings = new JMenu("Settings");		// settings menu
//		settings.setMnemonic(KeyEvent.VK_S);		// set (ALT + S)
		
		JRadioButton easy = new JRadioButton("Easy");		// radio button for level easy
		easy.addActionListener(new SetSpeedListener(this, 0.5));		// add action listener
//		easy.setMnemonic(KeyEvent.VK_E);		// set (ALT + E)
		
		JRadioButton normal = new JRadioButton("Normal");		// radio button for normal level
		normal.addActionListener(new SetSpeedListener(this, 1.0));		// add listener
//		normal.setMnemonic(KeyEvent.VK_N);		// set (ALT + N)
		normal.setSelected(true);			// normal is the selected button by default
		
		JRadioButton hard = new JRadioButton("Hard");		// radio button for hard level
		hard.addActionListener(new SetSpeedListener(this, 2.0));	// add listener
//		hard.setMnemonic(KeyEvent.VK_H);		// set (ALT + H)
		
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
		karel.setIcon(new RRIcon());
		
		mark = new HumanRobot(gameCity, 8, 8, Direction.NORTH, this);
		mark.setIcon(new HRIcon());
			
		Thread karelThread = new Thread(karel);
		karelThread.start();
		
		Thread markThread = new Thread(mark);
		markThread.start();
		
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
	 *  A method to pause the game.
	 */
	public void pause()
	{
		if ( paused == false )
		{
		prButton.doClick();
		paused = true;
		}
	}
	
	
	/**
	 *  A method to resume the game.
	 */
	public void resume()
	{
		if ( paused == true )
		{
			prButton.doClick();
			paused = false;
		}
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
	
	
	
	/**
	 *  A method to put a thing in the city.
	 */
	public void placeThing()
	{
		Thing thing = new Thing(gameCity, 5, 5);
		thing.setIcon(new PrizeIcon());		
	}

	
	
	public void actionPerformed(ActionEvent e)
	{
		mark.getButton(e);  
	}
	
	
	public static void main(String[] args) 
	{
		
		RobotWars game = new RobotWars();
		game.gameOn();
		
	} // end main()

	
} // end RobotWars
