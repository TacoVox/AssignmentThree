public class RobotWars
{
    import becker.robots.*;

    private WarRobot[2] robots;
    private GUI ui;
    private GameLogic logic;
    private City gamecity;

    public static void main(String[] args)
    {
	//Constructor for a new City
	gamecity = new City();
	
	//Constructor for Random- and HumanRobot - assigned to an Arrayindex
	robots[0] = new RandomRobot(gamecity);
	robots[1] = new HumanRobot(gamecity);

	//Logic
	logic = new GameLogic(robots);

	//GUI
	ui = new GUI(robots, gamecity, logic);

    }
}