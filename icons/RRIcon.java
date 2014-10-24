//Add to package icons
package icons;

//Import Becker's icons
import becker.robots.icons.Icon;

//Import necessary Java libraries
import java.awt.Graphics;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

//Import Cezars Random librarie
import static dit948.Random.*;

//Class to draw an enemy icon
public class RRIcon extends Icon
{
    //Private field to safe the image
    private BufferedImage img;
    
    //Constructor
    public RRIcon()
    {
	super();

	//Get a random image - safe it in img
	img = getRandImage();
    }//End constructor
    
    //Method to draw the image
    protected void paintIcon(Graphics g)
    { 
	g.drawImage(img, 0, 0, null);
    }//End paintIcon
    
    //Method to load a random enemy Image from a file
    public BufferedImage getRandImage()
    {
	BufferedImage img = null;
	
	//Get a random integer
	int rand = randomInt(3);
	
	//Array for different enemy pictures
	String[] paths = new String[3];

	paths[0] = "./icons/data/red.gif";
	paths[1] = "./icons/data/blue.gif";
	paths[2] = "./icons/data/orange.gif";
	
	//Try to load one of those pictures into our img
	try
        {
	    img = ImageIO.read(new File(paths[rand]));
	}//End try

	//Catch the exception
	catch (IOException e)
	{
	    System.err.println("Could not load icon!");
	    System.err.println(e);    
	}//End catch
    
	return img;
    }//End getRandImage

}//End class