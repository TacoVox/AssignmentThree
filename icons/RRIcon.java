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
    
    public Image getRandImage()
    {
	Image img = Toolkit.getDefaultToolkit().createImage("./red.ico");
	
	return img;
    }
}