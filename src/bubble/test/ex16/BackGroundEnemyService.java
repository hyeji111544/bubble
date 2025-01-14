package bubble.test.ex16;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

public class BackGroundEnemyService implements Runnable {
	
	private BufferedImage image;
	private Enemy enemy;

	
	public BackGroundEnemyService(Enemy enemy) {
		this.enemy = enemy;
		try {
			image = ImageIO.read(new File("image/backgroundMapservice.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void run() {
		while(enemy.getState()==0) {
	
			Color leftColor = new Color(image.getRGB(enemy.getX() - 10, enemy.getY()+ 25));
			Color rightColor = new Color(image.getRGB(enemy.getX() + 50 + 15, enemy.getY()+25));
			int bottomColor = image.getRGB(enemy.getX() + 25, enemy.getY()+50 + 5);
					
			//바닥 충돌 확인
			if(bottomColor != -1) {
				//System.out.println("바닥에 충돌함");
				enemy.setDown(false);
			}else {
				if(!enemy.isUp() && !enemy.isDown()) {
					System.out.println("down");
					enemy.down();
				}
			}
			
			if(leftColor.getRed() == 255 && leftColor.getGreen()==0 && leftColor.getBlue()==0) {
				//System.out.println("왼쪽 벽에 충돌함");
				enemy.setLeft(false);
				if(!enemy.isRight()) {
					enemy.right();
				}
			}else if (rightColor.getRed() == 255 && rightColor.getGreen()==0 && rightColor.getBlue()==0) {
				//System.out.println("오른쪽 벽에 충돌함");
				enemy.setRight(false);
				if(!enemy.isLeft()) {
					enemy.left();
				}
			}
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

}
