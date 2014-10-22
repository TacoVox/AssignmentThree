
package icons;

import becker.robots.icons.Icon;

import java.awt.Graphics;
import java.awt.image.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.io.IOException;

public class HRIcon extends Icon
{
    private BufferedImage img;
    
    public HRIcon()
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
	
	try
        {
	    img = ImageIO.read(new File("./icons/data/pacman.gif"));
	}
	catch (IOException e)
	{
	    System.err.println("Could not load icon!");
	    System.err.println(e);    
	}
    
	return img;
    }
}