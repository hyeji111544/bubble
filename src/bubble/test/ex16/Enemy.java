package bubble.test.ex16;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

// class Player -> 메모리에 띄울 수 있는 애들. 게임에 존재할 수 있다. (추상메서드를 가질 수 없다. 따라서 무조건 구현해 줄 의무를 가짐)
@Setter
@Getter
public class Enemy extends JLabel implements Moveable {

	private BubbleFrame mContext;
	//플레이어 방향
	private EnemyDirection enemyDirection;
	
	// 위치 상태
	private int x;
	private int y;
	
	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private int state; // 0(살아있다), 1(죽었다)
	
	
	// 플레이어 속도 상태
	private final int SPEED = 3;
	private final int JUMPSPEED = 1; // up, down
	
	private ImageIcon enemyR, enemyL;

	public Enemy(BubbleFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSetting();
		initBackgroundEnemyService();
		right();
	}

	private void initObject() {
		enemyR = new ImageIcon("image/enemyR.png");
		enemyL = new ImageIcon("image/enemyL.png");
	}

	private void initSetting() {
		x = 480;
		y = 178;
		
		left = false;
		right = false;
		up = false;
		down = false;
		
		state = 0;
		
		enemyDirection = EnemyDirection.RIGHT;

		this.setIcon(enemyR);
		setSize(50, 50);
		setLocation(x, y);
	}
	
	private void initBackgroundEnemyService() {
		new Thread(new BackGroundEnemyService(this)).start();
	}

	@Override
	public void left() {
		enemyDirection = EnemyDirection.LEFT;
		left=true;
		new Thread(()-> {
			while (left) {
				setIcon(enemyL);
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
		enemyDirection = EnemyDirection.LEFT;
		right=true;
		new Thread(()-> {
			while (right) {
				setIcon(enemyR);
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

}
