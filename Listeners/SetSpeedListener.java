//Import necessary libraries
import java.awt.event.*;

//A listener class for performing pause and resume
class SetSpeedListener implements ActionListener
{
    //Private attribute speed -> this saves the speed which will be overgiven to the robot
    private double speed;

    //Private attribute rr -> this saves a RandomRobot which will be used to set his speed
    private RandomRobot rr;

    //Private attribute rw -> this saves an object of type RobotWars -> allows us to acces the restart function

    //Constructor --> gets a object with the type of the main class
    public SetSpeedListener(RobotWars rw, RandomRobot rr, double speed)
    {
	//Assign the rr to the private attribute of our class
	this.rr = rr;
	
	//Assign the speed to the private attribute of this class
	this.speed = speed;
    }

    //Method which gets executed if the button/menu item is clicked
    public void actionPerformed(ActionEvent e)
    {
	//Restart the game
	rw.restart();

	//Set the speed of the robot
	rr.setSpeed(speed);

	//Start the move
	rw.gameOn();
    }
}