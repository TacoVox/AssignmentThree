package icons;

import becker.robots.icons.Icon;

import java.awt.Graphics;
import java.awt.image.*;
import java.io.File;

public class RRIcon extends Icon
{
    public RRIcon()
    {
	super();
    }

    protected void paintIcon(Graphics g)
    { 
	g.drawImage(getRandImage(), 0, 0, null);
    }
    
    public BufferedImage getRandImage()
    {
	BufferedImage img = ImageIO.read(new File("red.gif"));
	
	return img;
    }
}