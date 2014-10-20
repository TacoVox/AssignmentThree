//Assign package name
package warrobots;

//Import Becker's robot library
import becker.robots.*;

//A abstract class which allows us to save the implements Runnable later
public abstract class WarRobot extends RobotSE implements Runnable
{
    //Constructor which calls the super constructor
    public WarRobot(City city, int street, int avenue, Direction dir)
    {
	//Call the super constr.
	super(city, street, avenue, dir);
    }

    //The runnable method
    public void run()
    {
	//Override this function
    }
}