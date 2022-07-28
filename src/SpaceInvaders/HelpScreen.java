package SpaceInvaders;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.text.AttributeSet.FontAttribute;

import utilities.GDV5;

public class HelpScreen extends SpaceInvadersGameState {
	private InvaderState invaderState;

	public final static Color LIGHT_GREY = new Color(204,204,204);
	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
	public static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	public static final Color VERY_LIGHT_GREEN = new Color(102, 255, 102);
	public HelpScreen(InvaderState state) {
		this.invaderState = state;
	}
	
	public void update() {
		if(GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) {
			invaderState.setState(new MenuGameState(invaderState));
		}
		
	}
	
	public void draw(Graphics2D win) {
		win.setColor(VERY_LIGHT_BLUE);
		win.setFont(new Font("Courier New", Font.BOLD, 18));
		win.drawString("Space Invaders is a shooter is a fixed shooter in which ", 20, 40);
		win.drawString("the player moves a laser cannon horizontally across ", 20, 80);
		win.drawString("the bottom of the screen", 20, 100);
		win.setFont(new Font("Courier New", Font.BOLD, 18));
		win.drawString("Every ten aliens that are killed, you will gain a power up\r\n", 20, 120);
		win.drawString("there will be 10,000 aliens", 20, 140);
		win.drawString("the scoreboard will reflect the number of aliens that are destroyed", 20, 160);
		win.setColor(VERY_LIGHT_RED);
		win.drawString("Hit escape to go back to the menu screen", 20, 180);
//		win.setFont(new Font("Courier New", Font.BOLD, 24));
//		win.drawString("1. Single Player ", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/3, 180);
	}
	
}
