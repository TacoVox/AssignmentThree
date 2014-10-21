//Importing package warrobots
package warrobots;

// comment
//Importing becker.jar
import becker.robots.*;

//Creating the class HumanRobot that shall be controlled by the user with the GUI
public class HumanRobot extends WarRobot
{
	private Direction currentDir;
	
	//Constructer
	public HumanRobot(City city, int street, int avenue, Direction dir)
	{
		//call the super constructer
		super(city, street, avenue, dir);
		
		currentDir = dir;
	}
	
	//The robot will run when the thread starts
	public void run()
	{
		//The robot will move at one direction all the time
		go();
	}
	//A move method to make the robot change direction
	private void go()
	{
		//Infinite while-loop to check for the Direction of the robot
		while(true)
		{
			if(currentDir != getDirection()) // if currentdir is not
			{
				if(currentDir == Direction.NORTH)
					goNorth();
				else if(currentDir == Direction.EAST)
						goEast();
				else if(currentDir == Direction.SOUTH)
						goSouth();
						
				else 
					goWest();
			}
			else 
				move();
		}
	}
	
	public void setDirection(Direction dir)
	{
		currentDir = dir;
	}
	
	private void goNorth()
	{
		if(isFacingNorth())	
			System.err.println("already facing north");
		else if(isFacingEast())
			turnLeft();
		else if(isFacingSouth())
			turnAround();
		else 
			turnRight();
	}
	private void goEast()
	{
		if(isFacingEast())
			System.err.println("already facing east");
		else if(isFacingSouth())
			turnLeft();
		else if(isFacingWest())
			turnAround();
		else
			turnRight();
	}
	private void goSouth()
	{
		if(isFacingSouth())
			System.err.println("already facing south");
		else if(isFacingWest)
			turnLeft();
		else if(isFacingNorth)
			turnAround();
		else 
			turnRight();
	}
	private void goWest()
	{
		if(isFacingWest())
			System.err.println("already facing west");
		else if(isFacingNorth())
			turnLeft();
		else if(isFacingEast())
			turnAround();
		else
			turnRight();
	}
}
