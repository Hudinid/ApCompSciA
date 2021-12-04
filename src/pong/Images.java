package pong;

import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Image;
import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Images {
	public Images() {
		BufferedImage paddle = null;
		try {
			paddle = ImageIO.read(new File("Paddle.png"));
		}
		catch (IOException e) {
			System.out.println(e);
		}
		BufferedImage ball = null;
		try {
			ball = ImageIO.read(new File("Ball.png"));
		}
		catch (IOException e){
			System.out.println(e);
		}
		
		BufferedImage knightPaddle = null;
		try { 
			knightPaddle = ImageIO.read(new File("KnightPaddle.png"));
		}
		catch (IOException e) {
			System.out.println(e);
		}
		
		BufferedImage wizardPaddle = null;
		try {
			wizardPaddle = ImageIO.read(new File("WizardPaddle.png"));
		}
		catch (IOException e) {
			System.out.println(e);
		}
		
		BufferedImage roguePaddle = null;
		try {
			roguePaddle = ImageIO.read(new File("roguePaddle.png"));
		}
		catch (IOException e) {
			System.out.println(e);
		}
		
	}
}
