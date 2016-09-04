package peter.snake.game.frontend;

public class SnakeBlock {
	int x;
	int y;
	Direction dir;
	SnakeBlock next;
//	SnakeBlock prev;
	
	public SnakeBlock(int x, int y, Direction dir, SnakeBlock next) {
		this.x = x;
		this.y = y;
		this.dir = dir;
//		this.prev = prev;
		this.next = next;
	}
	
	public void setData(int x, int y, Direction dir) {
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	public boolean collide(SnakeBlock sb) {
		if(this.x == sb.x && this.y == sb.y) {
			return true;
		}	
		return false;
	}
}
