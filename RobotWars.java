// import galleries
import becker.robots.*;

import javax.swing.*;

import java.awt.*;

import WarRobots.*;
import Listeners.*;



public class RobotWars  
{
	private JFrame gameFrame;		// main frame for the game
	private JPanel gamePanel;		// main panel to hold buttons and city view
	private JButton prButton;		// pause/resume button
	private City gameCity;			// the city
	private JPanel controlPanel;	// panel for the control buttons
	private JMenuBar menuBar;		// the menu bar
	
	private int size = 11;
	private double currentSpeed = 2.0;
	
	/**
	 *  A constructor-creates a new frame with a menu bar,
	 *  controls and a city view.
	 */
	public RobotWars()
	{
		gameFrame = new JFrame("Robot Wars");
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createMenuBar();
		createMainPanel();
		createControlPanel();
		createCity(size, size, currentSpeed);
	}
	
	
	/**
	 *  A method to create the main game panel that will hold 
	 *  the players controls panel and the city view.
	 */
	private void createMainPanel()
	{
		gamePanel = new JPanel(null);
		gamePanel.setLayout(new BorderLayout());
		
		gameFrame.setContentPane(gamePanel);
		
		gameFrame.pack();
	}
	
	
	/**
	 *  A method to create the control panel and the 
	 *  buttons in it. Listeners are assigned to buttons. 
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
	 *  A method that creates the menu bar and menus.
	 *  In each menu there are menu items or radio buttons.
	 *  Action listeners are also assigned to the different 
	 *  components.
	 */
	private void createMenuBar()
	{
		menuBar = new JMenuBar();
		
		JMenu actions = new JMenu("Actions");
		
		JMenuItem pause = new JMenuItem("Pause");
		pause.addActionListener(new PauseResumeListener(this));
		actions.add(pause);
		JMenuItem resume = new JMenuItem("Resume");
		resume.addActionListener(new PauseResumeListener(this));
		actions.add(resume);
		
		menuBar.add(actions);
		
		JMenu settings = new JMenu("Settings");
		
		JRadioButton easy = new JRadioButton("Easy");
		easy.addActionListener(new SetSpeedListener(this, 0.5));
		JRadioButton normal = new JRadioButton("Normal");
		normal.addActionListener(new SetSpeedListener(this, 1.0));
		normal.setSelected(true);
		JRadioButton hard = new JRadioButton("Hard");
		hard.addActionListener(new SetSpeedListener(this, 2.0));
		
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
		City.showFrame(false);
		
		gameCity = new City(0, 0, size, size);
		// create robots here and threads (do not start!!!)
		
		RobotUIComponents uic = new RobotUIComponents(gameCity, 0, 0, size, size);
		CityView cv = uic.getCityView();
		
		gamePanel.add(cv, BorderLayout.NORTH);
		
		gameFrame.pack();
		
		prButton = uic.getStartStopButton();
		
		createWalls();
		
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
	 * 
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
