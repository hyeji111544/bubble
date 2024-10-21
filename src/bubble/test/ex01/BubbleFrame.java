package bubble.test.ex01;

import javax.swing.JFrame;

// 1. 윈도우 창이 되었음
// 2. 윈도우 창은 내부에 패널을 하나 가지고 있다.
public class BubbleFrame extends JFrame {
	
	public BubbleFrame() {
		setSize(1000, 640);
		setVisible(true);
		// help 의 Eclipse Marketplace 에서 Window builder 플러그인 설치하면 패널안에 마음대로 그릴 수 있으나 플러그인 찾기 실패함.
	}

	public static void main(String[] args) {
		new BubbleFrame();

	}

}
