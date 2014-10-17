//Import necessary libraries
import java.awt.event.*;

//A listener class for performing pause and resume
class PauseResumeListener implements ActionListener
{
    //Private attribute speed -> this saves the speed which will be overgiven to the robot
    private double speed;

    //Private attribute rr -> this saves a RandomRobot which will be used to set his speed
    private RandomRobot rr;

    //Constructor --> gets a object with the type of the main class
    public PauseResumeListener(RandomRobot rr, double speed)
    {
	//Assign the rr to the private attribute of our class
	this.rr = rr;
	
	//Assign the speed to the private attribute of this class
	this.speed = speed;
    }

    //Method which gets executed if the button/menu item is clicked
    public void actionPerformed(ActionEvent e)
    {
	//Set the speed of the robot
	rr.setSpeed(speed);
    }
}