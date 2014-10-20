//Set package name
package listeners;

//Import necessary libraries
import java.awt.event.*;
import warrobots.*;
import robotwars.*;

//A listener class for performing pause and resume
public class PickUpListener implements ActionListener
{
    //Private Attribute hr
    private HumanRobot hr;

    //Constructor --> gets a object with the type of the main class
    public PickUpListener(RobotWars hr)
    {
	//Assign the rw to the private Attribute of our class
	this.hr = hr;
    }
    //Method which gets executed if the button/menu item is clicked
    public void actionPerformed(ActionEvent e)
    {
	//If the robot can pick up a thing
	if(hr.canPickThing())
	    {
		//Pick the thing up
		hr.pickThing();
	    }
    }
}