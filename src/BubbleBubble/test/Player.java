package BubbleBubble.test;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import BubbleBubble.test.ex01.Moveable;

public class Player extends JLabel implements Moveable {

	private int x;
	private int y;
	private ImageIcon playerR, playerL;

	// 움직임의 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	// 플레이어 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2;
	
	// setter 메서드
	public void setLeft(boolean left) {
		this.left=left;
	}
	public void setRight(boolean right) {
		this.right=right;
	}
	public void setUp(boolean up) {
		this.up=up;
	}

	public Player() {
		initData();
		setInitLayout();

	}

	private void initData() {
		playerR = new ImageIcon("img/playerR.png");
		playerL = new ImageIcon("img/playerL.png");

		// 처음 실행 시 초기 값 셋팅
		x = 55;
		y = 535;
		// 플레이어가 가만히 멈춘 상태
		left=false;
		right=false;
		up=false;
		down=false;
		
		setIcon(playerR);
		this.setSize(50, 50);
		setLocation(x, y);
	}

	private void setInitLayout() {

	}

	public void left() {
		left=true;
		setIcon(playerL);
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(left) {
					x = x - SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} // end of while
			} // end of run
			
		}).start(); // end of Thread
		
	}

	public void right() {
		right=true;
		setIcon(playerR);
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				while(right) {
					x = x + SPEED;
					setLocation(x, y);
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} // end of while
			} // end of run
		}).start(); // end of Rannable

	} // end of right

	public void up() {
		System.out.println("점프");
		up=true;
		setIcon(playerR);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				for(int i=0; i<130/JUMPSPEED; i++) {
					y=y-JUMPSPEED;
					setLocation(x,y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} // end of for
				
				up=false;
				down();
				
				} // end of run
		}).start();
		//객체의 상태값을 잘 조절해야 한다.
	}

	@Override
	public void down() {
		System.out.println("다운");
		down=true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=0; i<130/JUMPSPEED; i++) {
					y=y+JUMPSPEED;
					setLocation(x,y);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}).start();
		// 상태 처리
		down = false;

	}

}
