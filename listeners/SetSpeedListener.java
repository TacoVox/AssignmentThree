//Set package name
package listeners;

//Import necessary libraries
import java.awt.event.*;
import robotwars.*;

//A listener class for performing pause and resume
public class SetSpeedListener implements ActionListener
{
    //Private attribute speed -> this saves the speed which will be overgiven to the robot
    private double speed;
    //Private attribute rw -> this saves an object of type RobotWars -> allows us to acces the restart function
    private RobotWars rw;
    //Constructor --> gets a object with the type of the main class
    public SetSpeedListener(RobotWars rw, double speed)
    {
	//Assign the speed to the private attribute of this class
	this.speed = speed;
	//Assign the rw to the private attribute of this class
	this.rw = rw;
    }
    //Method which gets executed if the button/menu item is clicked
    public void actionPerformed(ActionEvent e)
    {
	//Set a new speed
	rw.setSpeed(speed);
	//Restart the game set the speed
	rw.restart();
	//Start the move
	rw.gameOn();
    }
}