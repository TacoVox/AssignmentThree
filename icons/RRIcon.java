
package icons;

import becker.robots.icons.Icon;

import java.awt.Graphics;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

import static dit948.Random.*;

public class RRIcon extends Icon
{
    private BufferedImage img;
    
    public RRIcon()
    {
	super();
	img = getRandImage();
    }

    protected void paintIcon(Graphics g)
    { 
	g.drawImage(img, 0, 0, null);
    }
    
    public BufferedImage getRandImage()
    {
	BufferedImage img = null;
	
	int rand = randomInt(3);
	
	String[] paths = new String[3];

	paths[0] = "./icons/data/red.gif";
	paths[1] = "./icons/data/blue.gif";
	paths[2] = "./icons/data/orange.gif";
	
	try
        {
	    img = ImageIO.read(new File(paths[rand]));
	}
	catch (IOException e)
	{
	    System.err.println("Could not load icon!");
	    System.err.println(e);    
	}
    
	return img;
    }
}