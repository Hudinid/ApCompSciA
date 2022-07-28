package SpaceInvaders;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.text.AttributeSet.FontAttribute;

import utilities.GDV5;

public class MenuGameState extends SpaceInvadersGameState {
	private InvaderState invaderState;

	public final static Color LIGHT_GREY = new Color(204,204,204);
	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
	public static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	public static final Color VERY_LIGHT_GREEN = new Color(102, 255, 102);
	public MenuGameState(InvaderState state) {
		this.invaderState = state;
	}
	
	public void update() {
		if(GDV5.KeysPressed[KeyEvent.VK_1]) {
			invaderState.setState(new SpaceInvaders(invaderState));
		}
		if(GDV5.KeysPressed[KeyEvent.VK_2]) {
			invaderState.setState(new HelpScreen(invaderState));
		}
	}
	
	public void draw(Graphics2D win) {
		win.setColor(VERY_LIGHT_BLUE);
		win.setFont(new Font("Courier New", Font.BOLD, 40));
		win.drawString("Welcome to", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/4 + 70, 220);
		win.setFont(new Font("Courier New", Font.BOLD, 70));
		win.drawString("SPACE INVADERS!", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/4 - 100, 300);
		win.setFont(new Font("Courier New", Font.BOLD, 28));
		win.setColor(Color.WHITE);
		win.drawString("By Brian Hu, Erin Song, and Ellie Jiang", GDV5.getMaxWindowX() / 2 - GDV5.getMaxWindowX()/6 - 200, 370);
		win.setColor(VERY_LIGHT_RED);
		win.setFont(new Font("Courier New", Font.BOLD, 24));
		win.drawString("1. Single Player ", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/3 + 140, 450);
		win.drawString("2. Help Screen ", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/3 + 150, 500);
	}
	
}
