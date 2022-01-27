package snake;
import java.awt.Rectangle;
import utilities.GDV5;
import java.util.*;
import java.awt.Color;

public class Food extends Rectangle {
	public static final Color VERY_LIGHT_RED = new Color(255, 102, 102);
	
	public Food(int startX, int startY, int size) {
		super(startX, startY, size, size);
	}
}
