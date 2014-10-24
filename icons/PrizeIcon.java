//Add to package icons
package icons;

//Import necessary Java libraries
import java.awt.Graphics;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//Import Becker's icons
import becker.robots.icons.*;

//Class to get a fancy icon for the prize
public class PrizeIcon extends Icon
{
    //Private field to safe the image in
    private BufferedImage img;
    
    //Constructor
    public PrizeIcon()
    {      
	super();

	//Try to get an con from the path
	try 
	{
	    img = ImageIO.read(new File("./icons/data/prize.gif"));
	}//End try
	
	//Catch the exception
	catch (IOException e) 
	{
	    System.err.println("Error while loading the picture!");
	}//End catch
	
	//Set icon size
        setSize(0.4);
    }//End Constructor
	
    //Function to draw the icon
    public void paintIcon(Graphics g)
    {
	g.drawImage(img, 0, 0, null);
    }//End drawIcon
	
}//End class