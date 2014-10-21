//Assign package name
package warrobots;

//Import Becker's robot library
import becker.robots.*;

//Import Becker's icon library
import becker.robots.icons.*;

//Import Javas graphic library
import java.awt.*;

//Import Cezar's random library
import static dit948.Random.*;

import icons.RRIcon;

//A class which creates objects which are "a robot which moves randomly around the map"
public class RandomRobot extends WarRobot
{
    //Constructor
    public RandomRobot(City city, int street, int avenue, Direction dir)
    {
	//Call the super constructor
	super(city, street, avenue, dir);
    }//End RandomRobot

    //Method which is called when the thread starts
    public void run()
    {
	//Start the random move around the city
	randMove();
    }//End run()

    //This method sets an icon which is selected randomly out of a set of icons
    public void setRandIcon()
    {
	setIcon(new RRIcon(50));	
    }

    //Method which starts a random move around the city
    public void randMove()
    {
	//Integer which will get assigned later on with random numbers
	int turns;
	
	//A infinite loop
	while(true)
	    {
		//Assign an integer between 0 and 3 to the turns
		turns = randomInt(4);

		//A switch depending on the turns
		switch(turns)
		{
		    
		    //In case turns is a 0
		    case 0:
			//Do nothing - just break
			break;
		    //In case turns is a 1
		    case 1:
			//Turn one time left and then break
			turnLeft();
			break;
		    //In case turns is a 2
		    case 2:
			//Turn one time around - then break
			turnAround();
			break;
		    //In case turns is a 3
		    case 3:
			//Turn one time right - then break
			turnRight();
			break;
		    //If something completly different is in turns
		    default:
			//Print an error string on the error output stream and break
			System.err.println("A terrible error has happened - please contact the godlike developer team!");
			break;
	        }//End Switch

		//If the front of the robot isn't clear (a wall is there)
		while(!frontIsClear())
		{
		    //Turn left
		    turnLeft();
		}
		
		//Move one step forward
		move();
	    }//End while

    }//End randMove

}//EndClass
