import static dit948.Random.*;

import becker.Robots.*;

public abstract class WarRobot extends RobotSE implements Runnable
{
    private City city;

    public WarRobot(City city)
    {
	this.city = city;
    }
    
    public void run()
    {
	//Override this function
    }
    
}
