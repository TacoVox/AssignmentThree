//Add to package icons
package icons;

//Import Becker's Icon
import becker.robots.icons.Icon;

//Import necessary Java libraries
import java.awt.Graphics;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

//Class which is responsible for drawing an Pacman icon
public class HRIcon extends Icon
{
    //Private field img to safe the loaded picture
    private BufferedImage img;
    
    //Constructor
    public HRIcon()
    {
	//Call the super constructor
	super();
	
	//getRandImage - safe it in the private field
	img = getRandImage();
    }//End constructor

    //Function to paint the icon
    protected void paintIcon(Graphics g)
    { 
	g.drawImage(img, 0, 0, null);
    }//End paintIcon
    
    //Function to get the image from a file and return it
    public BufferedImage getRandImage()
    {
	BufferedImage img = null;
	
	//Try to get an image from file and safe it in img
	try
        {
	    img = ImageIO.read(new File("./icons/data/pacman.gif"));
	}//End try

	//Print the exceptions
	catch (IOException e)
	{
	    System.err.println("Could not load icon!");
	    System.err.println(e);    
	}//End catch
    
	return img;
    }//End getRandImage

}//End class