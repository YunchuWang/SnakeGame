package peter.snake.game.frontend;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;


class Painting extends JPanel implements Runnable,KeyListener {
	int x = 80,y = 80,velX = 20, velY = 20;
	Direction direction = Direction.RIGHT;
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 747;
	Snake mySnake;
	SnakeBlock ranBlock;
	Random random;
	Boolean gameOver;
	public Painting() {
		setOpaque(true);
        setBackground(Color.BLUE); 
        addKeyListener(this);
        random = new Random();
        gameOver = false;
        mySnake = new Snake(x, y , direction);
        ranBlock = new SnakeBlock(20 * random.nextInt(64), 20 * random.nextInt(37),direction,null);
        mySnake.addHead(new SnakeBlock(x + 20, y, direction,null));

	}
	public void paint(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		g.fillRect(mySnake.get(0).x,mySnake.get(0).y,20,20); 
		g.setColor(Color.BLACK);
		g.drawRect(mySnake.get(0).x,mySnake.get(0).y,20,20);
		
		for(int i = 1; i < mySnake.getSize();i++) {
			g.setColor(Color.RED);
			g.fillRect(mySnake.get(i).x,mySnake.get(i).y,20,20); 
			g.setColor(Color.BLACK);
			g.drawRect(mySnake.get(i).x,mySnake.get(i).y,20,20); 
		}
		g.setColor(Color.RED);
		g.fillRect(ranBlock.x,ranBlock.y,20,20);
		g.setColor(Color.BLACK);
		g.drawRect(ranBlock.x,ranBlock.y,20,20);  
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			mySnake.update();
			if(mySnake.collide(ranBlock)) {
				generateNewRan();
			} else {
				if(!updateData()) break;
			}
			if(gameOver) break;
            repaint();
            checkSelfColl();
            try {
                Thread.sleep(150);
            } catch (Exception ex) {}
        }
	}
	
	public boolean updateData() {
		if (mySnake.head.dir == Direction.LEFT) {
			if(mySnake.head.x < 0) return false;
			mySnake.head.x-= velX;
		} else if (mySnake.head.dir == Direction.RIGHT) {
			if(mySnake.head.x >= 1269) return false;
			mySnake.head.x+= velX;
		} else if (mySnake.head.dir == Direction.UP) {
			if(mySnake.head.y < 0) return false;
			mySnake.head.y -= velY;
		} else {
			if(mySnake.head.y >= HEIGHT - 1) return false;
			mySnake.head.y += velY;
		}
		return true;
	}
	
	public void checkSelfColl() {
		for(int i = 1; i < mySnake.getSize();i++) {
			if(mySnake.get(i).collide(mySnake.head)) {
				gameOver = true;
				System.out.println(gameOver + " " + i);
				break;
			}
		}
	}
	
	public void generateNewRan() {
		ranBlock.dir = mySnake.head.dir;
		mySnake.addHead(ranBlock);
		int ranX;
		int ranY;
		while(true) {
			ranX = 20 * random.nextInt(64);
			ranY = 20 * random.nextInt(38);
			Boolean flag = false;
			for(int i = 0; i < mySnake.getSize();i++) {
				if(ranX == mySnake.get(i).x && ranY == mySnake.get(i).y) {
					flag = true;
					break;
				}
			}
			if(!flag) break;
		}
		ranBlock = new SnakeBlock(ranX,ranY,direction,null);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
//	    int d;
	    if (keyCode == KeyEvent.VK_LEFT && mySnake.head.dir != Direction.RIGHT)
	    	mySnake.setDirection(Direction.LEFT);
	    else if (keyCode == KeyEvent.VK_RIGHT && mySnake.head.dir != Direction.LEFT)
	    	mySnake.setDirection(Direction.RIGHT);
	    else if (keyCode == KeyEvent.VK_UP && mySnake.head.dir != Direction.DOWN)
	    	mySnake.setDirection(Direction.UP);
	    else if (keyCode == KeyEvent.VK_DOWN && mySnake.head.dir != Direction.UP)
	    	mySnake.setDirection(Direction.DOWN);
//	    System.out.println("hello");
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	} 
	
}

class Display {
	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Painting test = new Painting();
		test.setFocusable(true);
		test.requestFocusInWindow();
		new Thread(test).start();
		
		window.getContentPane().add(test);
		window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		window.setUndecorated(true);
	    window.setVisible(true);
//	    System.out.println(test.getHeight());
	}
}
