package bubble.test.ex15;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

// class Player -> 메모리에 띄울 수 있는 애들. 게임에 존재할 수 있다. (추상메서드를 가질 수 없다. 따라서 무조건 구현해 줄 의무를 가짐)
@Setter
@Getter
public class Player extends JLabel implements Moveable {

	private BubbleFrame mContext;
	private List<Bubble> bubbleList;
	
	//플레이어 방향
	private PlayerDirection playerDirection;
	
	// 위치 상태
	private int x;
	private int y;
	
	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	// 벽에 충돌한 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	
	// 플레이어 속도 상태
	private final int SPEED = 4;
	private final int JUMPSPEED = 2; // up, down
	
	private ImageIcon playerR, playerL;

	public Player(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundPlayerService();
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
		bubbleList = new ArrayList<>();
	}

	private void initSetting() {
		x = 80;
		y = 535;
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		leftWallCrash = false;
		rightWallCrash = false;
		
		playerDirection = playerDirection.RIGHT;

		this.setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}
	
	private void initBackgroundPlayerService() {
		new Thread(new BackGroundPlayerService(this)).start();
	}

	@Override
	public void left() {
		playerDirection = playerDirection.LEFT;
		left=true;
		new Thread(()-> {
			while (left) {
				setIcon(playerL);
				x = x-SPEED;
				setLocation(x, y);	
				try {
					Thread.sleep(10); // 0.01초 간격
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	
	}

	@Override
	public void right() {
		playerDirection = playerDirection.RIGHT;
		right=true;
		new Thread(()-> {
			while (right) {
				setIcon(playerR);
				x = x+SPEED;
				setLocation(x, y);
				try {
					Thread.sleep(10); // 0.01초 간격
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	
	}

	@Override
	public void up() {
		up = true;
		new Thread(()->{
			for(int i=0; i<130/JUMPSPEED; i++) {
				y = y - JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	
			up = false;
			down();
		}).start();

	}

	@Override
	public void down() {
		down = true;
		new Thread(()->{
			while(down) {
				y = y + JUMPSPEED;
				setLocation(x, y);
				try {
					Thread.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			down = false;
			
		}).start();
	}
	
	@Override
	public void attack() {
		new Thread(()->{
			Bubble bubble = new Bubble(mContext);
			mContext.add(bubble);
			bubbleList.add(bubble);
			System.out.println("방울 시작");
			if(playerDirection == playerDirection.LEFT) {
				bubble.left();
				System.out.println("방울 왼쪽");
			}else {
				
				bubble.right();
				System.out.println("방울 오른쪽");
			}
			System.out.println("방울 종료");
		}).start();
	}

}
