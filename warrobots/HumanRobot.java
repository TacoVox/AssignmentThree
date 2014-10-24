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
	//Private fields
	private Direction currentDir; 
	private RobotWars rw;
	private RandomRobot rr;
	private boolean wantToPick = false;
	private double gameSpeed;
	
	//Constructer
	public HumanRobot(City city, int street, int avenue, Direction dir, RobotWars rw, RandomRobot rr, double speed)
	{
		//Call the super constructer
		super(city, street, avenue, dir);
		//Assign currentDir to dir
		currentDir = dir;
		//Assign this.rw to rw
		this.rw = rw;
		//Assign this.rr to rr
		this.rr = rr;
		//Assign this.gameSpeed to speed
		this.gameSpeed = speed;
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
			wantToPick = true; //pick up thing
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
						if(currentDir == Direction.NORTH) //If the current direction is north
							goNorth();					  //then go north
						else if(currentDir == Direction.EAST) //If the current direction is east
								goEast();					  //then go east
						else if(currentDir == Direction.SOUTH)//If the current direction is south
								goSouth();  				  // then go south
								
						else 
							goWest();		//If the current direction is west, then go west
					}
					else 
						move();				//After that move
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
		if(canPickThing())     //If the robot can pick up thing
		{					   //
			super.pickThing(); //then pick thing
			try				//After this is runned the existing program shall close
			{
				rr.breakRobot("nice game"); //Break randomRobot
			} catch (Exception e)		//creat a catch expression
			{
			}
			int choice = JOptionPane.showConfirmDialog(null, "You win! \n Restart game?", 				 //Creating a JOptionPane for to say if you want to play
																"Game Over", JOptionPane.YES_NO_OPTION); //again or quit
					if(choice == JOptionPane.YES_OPTION)	//If press yes, restart game
						rw.restart(gameSpeed);
					else
						rw.quit();	//If press no quit
		}
	}
	
	
	@Override //Override the breakRobot method
	public void breakRobot(String msg) 
	{
	    rw.pause();	//Pause the game
	    try			//After this is runned exit the existing program
	    {
	    	super.breakRobot(msg); //Calling super
	    } catch (Exception e)  //creat a catch exeption
	    {
	    }
		
	    int choice = JOptionPane.showConfirmDialog(null, "You lose! \n Restart game?", //Creating JOptionPane if you lose u can choose yes or 
	    															"Game Over", JOptionPane.YES_NO_OPTION); //no if you want to restart
	    if(choice == JOptionPane.YES_OPTION) //If Yes, restart
	    {
	    	rw.restart(gameSpeed); //set the new gamespeed to the next game
	    }
	    else
	    	rw.quit(); //If No, quit
		
	}
}
