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
		//Call the super constructer
		super(city, street, avenue, dir);
		//Assign currentDir to dir
		currentDir = dir;
		//Assign this.rw = rw
		this.rw = rw;
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
			pickThing();                 //robot pick up thing
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
			if(currentDir != getDirection()) // If currentDir is not the same direction of 
			{								 //the getDirection, then go through the other if statements
				if(currentDir == Direction.NORTH) //If cerrentDir is the same as Direction.NORTH
					goNorth();					  //Then go north
				else if(currentDir == Direction.EAST) //If cerrentDir is the same as Direction.EAST
						goEast();				  //Then go east
				else if(currentDir == Direction.SOUTH) //If cerrentDir is the same as Direction.SOUTH
						goSouth();				  //Then go south
						
				else 							
					goWest();					//Else go west
			}
			else 
				move();							//Otherwise the robot shall move in the current direction
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
		if(canPickThing()) //If the robot is on the intersetcion of a thing it can pickThing
		{
			pickThing(); //Robot pickThing
			int choice = JOptionPane.showConfirmDialog(null, "You win! \n Restartgame?",  //creating a int choice for the JOptionPane and give it a text
														"Game Over", JOptionPane.YES_NO_OPTION); // and a YES_NO_OPTION 
					if(choice == JOptionPane.YES_OPTION) //If press YES button
						rw.restart();					 //then restart
					else
						System.out.println("Do it later"); 
						//rw.quit(); //If NO button pressed it quits
		}
	}
	
}
