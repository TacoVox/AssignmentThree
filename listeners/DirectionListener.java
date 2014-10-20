//Set package name
package listeners;

//Import necessary libraries
import java.awt.event.*;
import becker.robots.*;

import warrobots.*;
import robotwars.*;

//A listener class for performing pause and resume
public class DirectionListener implements ActionListener
{
    //Private Attribute hr
    private HumanRobot hr;
    
    //Private attribute for direction
    Direction dir;

    //Constructor --> gets a object with the type of the main class
    public DirectionListener(RobotWars hr, Direction dir)
    {
	//Assign the rw to the private Attribute of our class
	this.hr = hr;

	//Assign DIR to the private att of our class
	this.dir = dir;
    }
    //Method which gets executed if the button/menu item is clicked
    public void actionPerformed(ActionEvent e)
    {
	//Call the changeDirection function of the HumandRobot that changes his direction 
	hr.changeDirection(dir);
    }
}