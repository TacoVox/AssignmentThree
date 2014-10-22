package icons;

import java.awt.Graphics;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import becker.robots.icons.*;


public class PrizeIcon extends Icon
{
	
	BufferedImage img;
	
	public PrizeIcon()
	{      
		super();

		try 
		{
			img = ImageIO.read(new File("./icons/data/prize.gif"));
	    	}
		catch (IOException e) 
	    	{
			System.err.println("Error while loading the picture!");
	    	}
		setSize(0.4);
	}
	

	
	@Override
	public void paintIcon(Graphics g)
	{
		g.drawImage(img, 0, 0, null);
	}
	
}