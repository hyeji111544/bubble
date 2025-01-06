package bubble.test.ex14;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bubble extends JLabel implements Moveable{

	//의존성 콤포지션
	private BubbleFrame mContext;
	private Player player;
	private Enemy enemy;
	private BackGroundBubbleService backGroundBubbleService;
	
	// 위치 상태
	private int x;
	private int y;
	
	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	
	// 적군을 맞춘 상태
	private int state; // 0(물방울), 1(적을 가둔 물방울)
	
	private ImageIcon bubble; // 물방울
	private ImageIcon bubbled; // 적을 가둔 물방울
	private ImageIcon bomb; // 물방울이 터진거
	
	public Bubble(BubbleFrame mContext) {
		this.mContext = mContext;
		this.player = mContext.getPlayer();
		this.enemy = mContext.getEnemy();
		initObject();
		initSetting();
	}
	
	private void initObject() {
		bubble = new ImageIcon("image/bubble.png");
		bubbled = new ImageIcon("image/bubbled.png");
		bomb = new ImageIcon("image/bomb.png");
		
		backGroundBubbleService = new BackGroundBubbleService(this);
	}
	
	private void initSetting() {
		left = false;
		right = false;
		up = false;
		
		x = player.getX();
		y = player.getY();
		
		setIcon(bubble);
		setSize(50, 50);
		state = 0;
	}
	

	@Override
	public void left() {
		left = true;
		for(int i=0; i<400; i++) {
			x--;
			setLocation(x, y);
			
			if(backGroundBubbleService.leftWall()) {
				left = false;
				break;
			}
			
			if((Math.abs(x-enemy.getX()) < 10) &&
					(Math.abs(y-enemy.getY()) > 0 && Math.abs(y-enemy.getY())<50)) {
				System.out.println("적군 충돌");
				if(enemy.getState() == 0) {
					attack();
					break;
				}
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}

	@Override
	public void right() {
		right = true;
		for(int i=0; i<400; i++) {
			x++;
			setLocation(x, y);
			
			if(backGroundBubbleService.rightWall()) {
				right = false;
				break;
			}
			
			if((Math.abs(x-enemy.getX()) < 10) &&
					(Math.abs(y-enemy.getY()) > 0 && Math.abs(y-enemy.getY())<50)) {
				System.out.println("적군 충돌");
				if(enemy.getState() == 0) {
					attack();
					break;
				}
			}
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		up();
	}
	
	@Override
	public void attack() {
		state = 1;
		enemy.setState(1);
		setIcon(bubbled);
		mContext.remove(enemy); //메모리에서 사라진다
		mContext.repaint();
	}

	@Override
	public void up() {
		while(true) {
			y--;
			setLocation(x, y);
			
			if(backGroundBubbleService.topWall()) {
				break;
			}
			
			try {

				if(state==0) { // 기본
					Thread.sleep(1);
				}else { // 공격 성공 물방울
					Thread.sleep(10);
				}
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if(state == 0) clearBubble(); // 천장에 버블이 도착하고 나서 3초 후에 메모리에서 소멸
		
	}
	
	private void clearBubble() {
		try {
			Thread.sleep(3000);
			setIcon(bomb);
			Thread.sleep(500);
			mContext.remove(this); // BubbleFrame 의 메모리에서 사라짐
			mContext.repaint();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
