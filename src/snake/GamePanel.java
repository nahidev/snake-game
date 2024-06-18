package snake;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import javax.swing. *;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{
	
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
	static final int DELAY = 120;
    static final int SPEED_INCREMENT = 20;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int applesEaten;
	int appleX;
	int appleY;
	boolean goldenApple = false;
	String direction = "R";
	boolean running = false;
	Timer timer;
	Random random;
	Color color1 = new Color(9, 36, 0);
	Color color2 = new Color(27, 108, 0);
	
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}
	
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if(running) {	
			if(goldenApple) {
				g.setColor(Color.yellow);
			}else {
			g.setColor(Color.red);
			}
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
		
			for(int i = 0; i<bodyParts; i++) {
				if(i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}else {
					if(i % 2 == 0) {
						g.setColor(color1);;
					}else {
						g.setColor(color2);
					}
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
						}
					}
					g.setColor(Color.red);
					g.setFont(new Font("Agency FB", Font.BOLD, 30));
					FontMetrics metrics = getFontMetrics(g.getFont());
					g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());				
			}else {
					gameOver(g);
				}
			}
	
	public String getLevelMessage() {
		if(applesEaten >=50) {
			return "You have reached the Legendary Level";
		}else if(applesEaten >=21){
			return "You have reached the Hard Level. Congratulations!";
		}else if(applesEaten >=11) {
			return "You have reached the Medium Level. Not bad!";
		}else {
			return "You have reached the Easy Level. Try again!";
		}
	}
	
	public void newApple() {
		appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		appleY = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
		goldenApple = random.nextInt(10) == 0;	
	}
	
	public void move() {
		for(int i = bodyParts; i>0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		switch(direction) {
		case "U":
			y[0] = y[0] - UNIT_SIZE;
			break;
		case "D":
			y[0] = y[0] + UNIT_SIZE;
		break;
		case "L":
			x[0] = x[0] - UNIT_SIZE;
		break;
		case "R":
			x[0] = x[0] + UNIT_SIZE;	
		}
	}
	
	public void checkApple() {
		if((x[0] == appleX)&& (y[0] == appleY)) {
			if(goldenApple) {
				bodyParts += 2;
				applesEaten += 2;
			}else {
				bodyParts++;
				applesEaten++;
			}
			newApple();
			 if (applesEaten % 10 == 0) {
	                increaseSpeed();
	            }
		}	
	}
	
	public void increaseSpeed() {
		int newDelay = timer.getDelay() - SPEED_INCREMENT;
		if (newDelay > 0) {
	        	timer.setDelay(newDelay);
	        }
	    }
	
	public void checkCollisions() {
		for(int i = bodyParts; i>0;i--) {
			if((x[0] == x[i])&& (y[0] == y[i])) {
				running = false;
			}
		}
		if(x[0] <0) {
			running = false;
		}
		if(x[0] > SCREEN_WIDTH) {
			running = false;
		}
		if(y[0] <0) {
			running = false;
		}
		if(y[0] > SCREEN_HEIGHT) {
			running = false;
		}
		if(!running) {
			timer.stop();
		}
	}
	
	public void gameOver(Graphics g) {
		g.setColor(Color.red);
		g.setFont(new Font("Agency FB", Font.BOLD, 76));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Game Over", (SCREEN_WIDTH - metrics1.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2);
		g.setColor(Color.red);
		g.setFont(new Font("Agency FB", Font.BOLD, 40));
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics2.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
		String levelMessage = getLevelMessage();
		g.setFont(new Font("Agency FB", Font.BOLD, 30));
		if(applesEaten >=50) {
			g.setColor(new Color(255,215,0));
		}else {
			g.setColor(Color.white);
		}
		FontMetrics metricsLevel = getFontMetrics(g.getFont());
		g.drawString(levelMessage, (SCREEN_WIDTH - metricsLevel.stringWidth(levelMessage))/2, SCREEN_HEIGHT - 50);
	}
	
	@Override 
	public void actionPerformed(ActionEvent e) {
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
		
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(direction != "R") {
					direction = "L";
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(direction !="L") {
					direction = "R";
				}
				break;
			case KeyEvent.VK_UP:
				if(direction != "D") {
					direction = "U";
				}
				break;
			case KeyEvent.VK_DOWN:
				if(direction != "U") {
					direction = "D";
				}
				break;
			}	
		}
	}
}
