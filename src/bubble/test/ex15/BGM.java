package bubble.test.ex15;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class BGM {
	public BGM() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("sound/bgm.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(ais);
			
			// 소리 설정
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			
			// 볼륨 조정
			gainControl.setValue(-30.0f);
			
			 // 클립 반복 재생 (옵션)
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            
            // 클립 재생 시작
            clip.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
