package SpaceInvaders;

import java.awt.Graphics2D;

public abstract class SpaceInvadersGameState {
    public abstract void draw(Graphics2D win);
    public abstract void update();

}