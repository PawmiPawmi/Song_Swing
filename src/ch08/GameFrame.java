package ch08;

import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
	
	BufferedImage backgroundImage;
	BufferedImage player1;
	BufferedImage enemy1;
	ImagePanel imagePanel;
	int playerX=150;
	int playerY=300;
	int enemyX=250;
	int enemyY=420;
	boolean flag=true;
	boolean flag1=true;
	
	public GameFrame() {
		initData();
		setInitLayout();
		addEventListener();
		}
	
	private class ImagePanel extends JPanel implements Runnable {
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, 600, 600, null);
			g.drawImage(enemy1, enemyX, enemyY, 50, 50, null);
			
			if(flag1==true) {
				g.drawImage(player1, playerX, playerY, 50, 50, null);
			}
		
			
			//Todo 플레이어, 적군 그림 그려야 함
			//쓰레드를 활용할 예정
		}

		@Override
		public void run() {
			boolean direction=true;
			//true: 왼쪽 
			//false: 오른쪽
			// TODO Auto-generated method stub
			while(flag){
				System.out.println("진행중");
				if(direction==true) {
					enemyX -= 5;
					if(enemyX==50) {
						direction=false;
					}
				} else {
					enemyX += 5;
					if(enemyX==530) {
						direction=true;
					}
				}
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				repaint();
			}
		}}
	
	
	private void initData() {
		setTitle("Thread 활용한 미니 예제");
		setSize(600,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		try {
			// 예외가 발생할 수 있는 코드를 작성하는 영역
			backgroundImage = ImageIO.read(new File("img/backgroundMap.png"));
			player1 = ImageIO.read(new File("img/playerL.png"));
			enemy1 = ImageIO.read(new File("img/enemyL.png"));
		} catch(IOException e) {
			e.printStackTrace();
		} imagePanel=new ImagePanel();
		// 다른 작업자에게 일을 위임시킨다.
		Thread thread = new Thread(imagePanel);
		thread.start();
		}
		
	private void setInitLayout() {
		//setLayout(null); // 배치 관리자를 좌표 기반
		//setResizable(false); // 프레임 크기 조절 불가 설정
		setVisible(true);
		
		add(imagePanel);
	}
	private void addEventListener() {
		// 이벤트 리스너 등록 방법 2가지 중
		this.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				System.out.println("여기가 동작 합니다.");
				int code=e.getKeyCode();
				if(code==KeyEvent.VK_UP) {
					playerY-=10;
				} else if (code==KeyEvent.VK_DOWN) {
					playerY+=10;
				} else if (code==KeyEvent.VK_LEFT) {
					playerX-=10;
				} else if (code==KeyEvent.VK_RIGHT) {
					playerX+=10;
				} else if (code==KeyEvent.VK_SPACE) {
					// 1. 스페이스를 눌렀을 때, 적군을 멈출 수 있게 코드 수정
					flag=false;
				}
				// 2. player가 적군과 만났다면, player 그림을 없애주세요.
				if((Math.abs(playerX-enemyX)<=50)&&(Math.abs(playerY-enemyY)<=50)) {
					flag1=false;
				}
				repaint();
			} // end of keyPressed
		});
		
	}
	
	
}
