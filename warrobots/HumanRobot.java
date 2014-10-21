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
		while(true)
		{
			if(currentDir != getDirection())
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
		//if(isFacingNorth() == true)		
	}
	private void goEast()
	{
		
	}
	private void goSouth()
	{
		turnLeft(2);
	}
	private void goWest()
	{
		
	}
}
