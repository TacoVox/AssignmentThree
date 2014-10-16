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
    }

    //Method which is called when the thread starts
    public void run()
    {
	//Start the random move around the city
	randMove();
    }

    //Method which starts a random move around the city
    public void randMove()
    {
	int turns = randomInt(4);
	
	switch(turns):
	{
	    case 0:
		
	}
    }
}
