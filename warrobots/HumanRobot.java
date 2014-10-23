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
	private RandomRobot rr;
	private boolean wantToPick = false;
	
	//Constructer
	public HumanRobot(City city, int street, int avenue, Direction dir, RobotWars rw, RandomRobot rr)
	{
		//Call the super constructer
		super(city, street, avenue, dir);
		//Assign currentDir to dir
		currentDir = dir;
		//Assign this.rw = rw
		this.rw = rw;
		
		this.rr = rr;
	}
	
	//Creating an ActionEvent 
	public void getButton(ActionEvent e){
		
		if(e.getActionCommand()=="down") //If you press down button
		{
			currentDir = Direction.SOUTH; //The direction of the robot is south
		} else if (e.getActionCommand()=="up") //If you press up button
		{
			currentDir = Direction.NORTH; //The direction of the robot is north
		} else if (e.getActionCommand()=="left") //If you press left button
		{
			currentDir = Direction.WEST; //The direction of the robot is west
		} else if (e.getActionCommand()=="right") //If you press right button
		{
			currentDir = Direction.EAST; //The direction of the robot is east
		}
		else if (e.getActionCommand()=="pick") //If you press pick button
		{
			wantToPick = true;              
		}
	}
	
	//The robot will run when the thread starts
	public void run()
	{
		//Infinite while-loop to check for the Direction of the robot
				while(true)
				{
					
					if (rr.getIntersection() == this.getIntersection()) // if the randrobot is on the same
						breakRobot("AAAAAAAAAAA");						//intersection as HumanRobot, robot break
					
					if (wantToPick == true) //If player has pressed pick button
						pickThing();		//then pick up thing
					
					if(currentDir != getDirection()) //If the robots direction is not the same as
					{								 //the getDirection is true, then do thing below
						if(currentDir == Direction.NORTH) //if
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
	//Creating a pickThing method
	public void pickThing()
	{
		if(canPickThing())
		{
			super.pickThing();
			try
			{
				rr.breakRobot("nice game");
			} catch (Exception e)
			{
			}
			int choice = JOptionPane.showConfirmDialog(null, "You win! \n Restart game?", "Game Over", JOptionPane.YES_NO_OPTION);
					if(choice == JOptionPane.YES_OPTION)
						rw.restart();
					else
						rw.quit();
		}
	}
	
	
	@Override
	public void breakRobot(String msg)
	{
	    rw.pause();
	    try
	    {
		super.breakRobot(msg);
	    } catch (Exception e)
	    {
	    }
		
	    int choice = JOptionPane.showConfirmDialog(null, "You lose! \n Restart game?", "Game Over", JOptionPane.YES_NO_OPTION);
	    if(choice == JOptionPane.YES_OPTION)
	    {
		choice = 8;
		rw.restart();
	    }
	    else
		rw.quit();
		
	}
}
