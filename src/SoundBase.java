import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.DataLine;
import javax.swing.*;

public class SoundBase implements Runnable {
	private static final int BUFFER_SIZE = 1024;
	private String fileToPlay = null;
	// 定義執行緒中通訊用的變數
	private boolean threadExit = false;
	private boolean stopped = true;
	private boolean paused = false;
	private boolean playing = false;
	// 用於執行緒的同步管理
	public Object synch = new Object();
	private Thread playerThread = null;

	public SoundBase(String filename) {
		fileToPlay = filename;
	}

	public SoundBase() {
		fileToPlay = "default.wav";
	}

	public void run() {
		while (!threadExit) {
			waitforSignal();
			if (!stopped)
				playMusic();
		}
	}

	public void endThread() {
		threadExit = true;
		synchronized (synch) {
			synch.notifyAll();
		}
		try {
			Thread.sleep(500);
		} catch (Exception ex) {
		}
	}

	public void waitforSignal() {
		try {
			synchronized (synch) {
				synch.wait();
			}
		} catch (Exception ex) {
		}
	}

	public void play() {
		if ((!stopped) || (paused))
			return;
		if (playerThread == null) {
			playerThread = new Thread(this);
			playerThread.start();
			try {
				Thread.sleep(500);
			} catch (Exception ex) {
			}
		}
		synchronized (synch) {
			stopped = false;
			paused = false;
			synch.notifyAll();
		}
	}

	/*
	 * public void setFileToPlay(String fname) { fileToPlay = fname; }
	 * 
	 * public void playFile(String fname) { setFileToPlay(fname); play(); }
	 */

	public void playMusic() {
		byte[] audioData = new byte[BUFFER_SIZE];
		AudioInputStream ais = null;
		SourceDataLine line = null;
		AudioFormat baseFormat = null;
		try {
			ais = AudioSystem.getAudioInputStream(new File(fileToPlay));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "打開檔失敗！");
		}
		if (ais != null) {
			baseFormat = ais.getFormat(); // 獲取檔案格式
			line = getLine(baseFormat); // 從檔中獲取資料
			if (line == null) { // 如果不是可解碼類型，測試能否獲取外部解碼器
				AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(),
						16, baseFormat.getChannels(), baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
				ais = AudioSystem.getAudioInputStream(decodedFormat, ais);
				line = getLine(decodedFormat);
			}
		}
		if (line == null) {
			JOptionPane.showMessageDialog(null, "不認識此格式！");
			return; // 不能播放此檔
		}
		playing = true;
		line.start(); // 準備播放檔
		int inBytes = 0;
		while ((inBytes != -1) && (!stopped) && (!threadExit)) {
			try {
				inBytes = ais.read(audioData, 0, BUFFER_SIZE);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "無法讀取檔中的內容！");
			}
			try {
				if (inBytes > 0)
					line.write(audioData, 0, inBytes);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "無法輸出解碼資料到音訊裝置！");
			}
			if (paused)
				waitforSignal();
		}
		line.drain();
		line.stop();
		line.close();
		playing = false;
		stopped = true;
		paused = false;
	}

	public void stop() {
		paused = false;
		stopped = true;
		waitForPlayToStop();
	}

	public void waitForPlayToStop() {
		while (playing)
			try {
				Thread.sleep(500);
				synchronized (synch) {
					synch.notifyAll();
				}
			} catch (Exception ex) {
			}
	}

	public void pause() {
		if (stopped)
			return;
		synchronized (synch) {
			paused = true;
			synch.notifyAll();
		}
	}

	public void resume() {
		if (stopped)
			return;
		synchronized (synch) {
			paused = false;
			synch.notifyAll();
		}
	}

	private SourceDataLine getLine(AudioFormat audioFormat) {
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		try {
			res = (SourceDataLine) AudioSystem.getLine(info);
			res.open(audioFormat);
		} catch (Exception e) {
			res = null;
		}
		return res;
	}

}
