package bubble.test.ex06;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

// 메인 스레드 바쁨 - 키보드 이벤트 처리해야됨
// 백그라운드에서 플레이어 관찰
public class BackGroundPlayerService implements Runnable {
	
	private BufferedImage image;
	private Player player;
	
	public BackGroundPlayerService(Player player) {
		this.player = player;
		try {
			image = ImageIO.read(new File("image/backgroundMapservice.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void run() {
		while(true) {
			// 플레이어의 위치에 따른 색상 확인
			Color leftColor = new Color(image.getRGB(player.getX() - 10, player.getY()+ 25));
			Color rightColor = new Color(image.getRGB(player.getX() + 50 + 15, player.getY()+25));
			//System.out.println("leftColor"+leftColor);
			//System.out.println("rightColor"+rightColor);
			
			if(leftColor.getRed() == 255 && leftColor.getGreen()==0 && leftColor.getBlue()==0) {
				System.out.println("왼쪽 벽에 충돌함");
			}else if (rightColor.getRed() == 255 && rightColor.getGreen()==0 && rightColor.getBlue()==0) {
				System.out.println("오른쪽 벽에 충돌함");
			}
			
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

}
