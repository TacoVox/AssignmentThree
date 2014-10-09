public class RobotWars
{
    //Import the robots library from Becker
    import becker.robots.*;

    //Private fields of the RobotWars class
    //Array to for saving the robots in
    private WarRobot[2] robots;
    //Assign empty GUI
    private GUI ui;
    //Assign empty City
    private City gamecity;
    //Assign empty Threads
    Thread RRThread;
    Thread HRThread;

    //Main method to start the program
    //All variables get defined here. Threads will be started
    public static void main(String[] args)
    {
	//Constructor for a new City
	gamecity = new City();
	
	//Constructor for Random- and HumanRobot - assigned to an Arrayindex
	robots[0] = new RandomRobot(gamecity, this);
	robots[1] = new HumanRobot(gamecity, this);

	//Start new game / get new positions for the robots
	newGame();

	//Create GUI
	ui = new GUI(gamecity);

	//Assign Objects to Thread. Run them.
	RRThread = new Thread(robots[0]);
	HRThread = new Thread(robots[1]);

	//Start both threads
	RRThread.start();
	HRThread.start();
    }

    public void newGame()
    {
	robots[0].newPosition();
	robots[1].newPosition();
    }
}