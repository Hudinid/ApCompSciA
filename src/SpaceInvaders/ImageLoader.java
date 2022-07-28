package SpaceInvaders;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
 
public class ImageLoader {
	
	public ImageLoader() {
		
	}
	
	public BufferedImage loader(String filePath) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(filePath));
		}
		catch(IOException e) {
			System.err.println(e);
		}
		
		return img;
	}
}
