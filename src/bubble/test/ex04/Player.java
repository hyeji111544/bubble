package bubble.test.ex04;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

// class Player -> 메모리에 띄울 수 있는 애들. 게임에 존재할 수 있다. (추상메서드를 가질 수 없다. 따라서 무조건 구현해 줄 의무를 가짐)
@Setter
@Getter
public class Player extends JLabel implements Moveable {

	
	// 위치 상태
	private int x;
	private int y;
	
	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	
	private ImageIcon playerR, playerL;

	public Player() {
		initObject();
		initSetting();
	}

	private void initObject() {
		playerR = new ImageIcon("image/playerR.png");
		playerL = new ImageIcon("image/playerL.png");
	}

	private void initSetting() {
		x = 55;
		y = 535;
		
		left = false;
		right = false;
		up = false;
		down = false;

		this.setIcon(playerR);
		setSize(50, 50);
		setLocation(x, y);
	}

	@Override
	public void left() {
		System.out.println("L");
		left=true;
		new Thread(()-> {
			while (left) {
				setIcon(playerL);
				x = x-10;
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
		System.out.println('R');
		right=true;
		new Thread(()-> {
			while (right) {
				setIcon(playerR);
				x = x+10;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void down() {
		// TODO Auto-generated method stub

	}

}
