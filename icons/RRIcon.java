package icons;

import becker.robots.icons.Icon;

import java.awt.Graphics;
import java.awt.Image;

public class RRIcon extends Icon
{
    public RRIcon(double relativeSize)
    {
	super(relativeSize);
    }

    protected void paintIcon(Graphics g)
    { 
	g.drawImage(getRandImage(), 0, 0, this);
    }
    
    public BufferedImage getRandImage()
    {
	BufferedImage img = ImageIO.read(new File(red.ico));
	
	return img;
    }
}