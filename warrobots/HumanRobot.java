//Importing package warrobots
package warrobots;

// comment
//Importing becker.jar
import becker.robots.*;
import robotwars.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

//Creating the class HumanRobot that shall be controlled by the user with the GUI
public class HumanRobot extends WarRobot
{
	private Direction currentDir;
	private RobotWars rw;
	
	//Constructer
	public HumanRobot(City city, int street, int avenue, Direction dir, RobotWars rw)
	{
		//call the super constructer
		super(city, street, avenue, dir);
		
		currentDir = dir;
		
		this.rw = rw;
	}
	
	
	public void getButton(ActionEvent e){
		
		if(e.getActionCommand()=="down")
		{
			currentDir = Direction.SOUTH;
		} else if (e.getActionCommand()=="up")
		{
			currentDir = Direction.NORTH;
		} else if (e.getActionCommand()=="left")
		{
			currentDir = Direction.WEST;
		} else if (e.getActionCommand()=="right")
		{
			currentDir = Direction.EAST;
		}
		else if (e.getActionCommand()=="pick")
		{
			pickThing();
		}
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
	//A setDirection method to set the direction of the robot
	public void setDirection(Direction dir)
	{
		//Assigning currentDir to dir
		currentDir = dir;
	}
	//
	private void goNorth() //Go north method to tell the robot how to turn
	{
		//if statement for the direction of the robot
		if(isFacingNorth())	//If facing north, continue
			System.err.println("already facing north"); //An error message if robot facing north
		else if(isFacingEast()) //If the robot is facing east
			turnLeft(); //Turn one time left
		else if(isFacingSouth()) //If facing south
			turnAround(); //Turn around (two left turns)
		else 
			turnRight(); // If facing west turn right
	}
	private void goEast() //Go east method to tell the robot how to turn
	{
		//if statement for the direction of the robot
		if(isFacingEast()) //If facing east, continue
			System.err.println("already facing east"); //An error message if robot facing east
		else if(isFacingSouth()) //If facing south
			turnLeft(); //Turn one time left
		else if(isFacingWest()) //If facing west
			turnAround(); //Turn around (two left turns)
		else
			turnRight(); //If facing north turn right
	}
	private void goSouth() //Go south method to tell the robot how to turn
	{
		//if statement for the direction of the robot
		if(isFacingSouth()) //If facing south, continue
			System.err.println("already facing south"); //An error message if robot facing south
		else if(isFacingWest()) //If facing west
			turnLeft(); //Turn one time left
		else if(isFacingNorth()) //If facing north
			turnAround(); //Turn around (two left turns)
		else 
			turnRight(); //If facing east turn right 
	}
	private void goWest() //Go west method to tell the robot how to turn
	{
		//if statement for the direction of the robot
		if(isFacingWest()) //If facing west, continue
			System.err.println("already facing west"); //An error message if robot facing west
		else if(isFacingNorth()) //If facing north
			turnLeft(); //Turn left one time
		else if(isFacingEast()) //If facing east
			turnAround(); //Turn around (two left turns)
		else
			turnRight(); //If facing south turn right
	}
	
	public void pickThing()
	{
		if(canPickThing())
		{
			pickThing();
			int choice = JOptionPane.showConfirmDialog(null, "You win! \n Restartgame?", "Game Over", JOptionPane.YES_NO_OPTION);
					if(choice == JOptionPane.YES_OPTION)
						rw.restart();
					else
						System.out.println("Do it later");
						//rw.quit();
		}
	}
	
}
