package snake.GameStates;

import java.awt.Graphics2D;

public abstract class SnakeGameState {
    public abstract void draw(Graphics2D win);
    public abstract void update();

}