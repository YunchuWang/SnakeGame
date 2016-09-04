package peter.snake.game.frontend;

public class Snake {
	SnakeBlock head;
	int size = 1;
	public int getSize() {
		return size;
	}

	public void setDirection(Direction dir) {
		head.dir = dir;
	}
	public Snake(int x, int y, Direction dir) {
		head = new SnakeBlock(x,y,dir,null);
	}
	
	public void addHead(SnakeBlock Head) {
		Head.next = head;
//		Head.prev = null;
//		head.prev = Head;
//		head.next = null;
		this.head = Head;
		this.size++;
	}
	
	public SnakeBlock get(int index) {
		if(index >= size) return null;
		SnakeBlock temp = head;
		for(int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp;
	}
	
	public SnakeBlock getLast() {
		SnakeBlock temp = head;
		while(temp.next != null) {
			temp = temp.next;
		}
		return temp;
	}
	public void update() {
		SnakeBlock temp;
		for(int i = getSize() - 1; i >= 1; i--) {
			temp = get(i);
			temp.setData(get(i - 1).x, get(i - 1).y, get(i - 1).dir);
		}
	}
	public boolean collide(SnakeBlock sb) {
		if (head.x == sb.x && (head.y + 20) == sb.y && head.dir == Direction.DOWN) {
			return true;
		} else if (head.x + 20 == sb.x && head.y == sb.y && head.dir == Direction.RIGHT) {
			return true;
		} else if (head.x - 20 == sb.x && head.y == sb.y && head.dir == Direction.LEFT) {
			return true;
		} else if (head.x == sb.x && (head.y - 20) == sb.y && head.dir == Direction.UP) {
			return true;
		}
			
		return false;
	}
	
}
