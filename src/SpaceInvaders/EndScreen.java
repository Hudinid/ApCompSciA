package SpaceInvaders;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.text.AttributeSet.FontAttribute;

import utilities.GDV5;

public class EndScreen extends SpaceInvadersGameState {
	private InvaderState invaderState;

	public final static Color LIGHT_GREY = new Color(204,204,204);
	public static final Color VERY_LIGHT_BLUE = new Color(51, 204, 255);
	public static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	public static final Color VERY_LIGHT_GREEN = new Color(102, 255, 102);
	public EndScreen(InvaderState state) {
		this.invaderState = state;
	}
	
	public void update() {
		if(GDV5.KeysPressed[KeyEvent.VK_ESCAPE]) {
			invaderState.setState(new MenuGameState(invaderState));
		}
		
	}
	
	public void draw(Graphics2D win) {
		win.setColor(VERY_LIGHT_BLUE);
		win.setFont(new Font("Courier New", Font.BOLD, 32));
		win.drawString("Hit Escape to Go Back", 20, 40);
		win.setFont(new Font("Courier New", Font.BOLD, 28));
		win.drawString("You killed " + Ship.aliensKilled + " aliens.", 20, 100);
		win.setColor(VERY_LIGHT_RED);
//		win.setFont(new Font("Courier New", Font.BOLD, 24));
//		win.drawString("1. Single Player ", GDV5.getMaxWindowX()/2 - GDV5.getMaxWindowX()/3, 180);
	}
	
}
