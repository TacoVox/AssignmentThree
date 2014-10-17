//Import Becker's robot library
import becker.robots.*;

//Import Cezar's random library
import static dit948.Random.*;

//A class which creates objects which are "robot that move randomly around the map"
class RandomRobot extends WarRobot
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

    //Method which starts a random move around the city
    public void randMove()
    {
	int turns;

	while(true)
	{
		turns = randomInt(4);
		
		switch(turns)
		{
		case 0:
		    break;
		case 1:
		    turnLeft();
		    break;
		case 2:
		    turnAround();
		    break;
		case 3:
		    turnRight();
		    break;
		default:
		    System.err.println("A terrible error has happened - please contact the godlike developer team!");
		    break;
		}//End Switch
		
		while(!frontIsClear())
		{
		    turnLeft();
		}
		
		move();
	}//End while

    }//End randMove

}//EndClass