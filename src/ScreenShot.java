import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ScreenShot extends JButton implements ActionListener {

	SubmarineMain game;
	private int photoCount = 0;

	public ScreenShot(SubmarineMain game) {
		this.game = game;
		setIcon(new ImageIcon(".\\image\\photo-camera.png"));
		setLocation(850, 550);
		setSize(64, 64);
		setContentAreaFilled(false);
		setBorder(null);
		addActionListener(this);
		setFocusable(false);
	}

	public void pressed() {
		try {
			saveScreenShot(game, ".\\screenshot\\" + (++photoCount) + ".png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BufferedImage getScreenShot(Component component) {
		BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		component.paint(image.getGraphics());
		return image;
	}

	public static void saveScreenShot(Component component, String filename) throws IOException {
		BufferedImage image = getScreenShot(component);
		File outputFile = new File(filename);
		File parentDir = outputFile.getParentFile();
		if (parentDir != null && !parentDir.exists()) {
			if (!parentDir.mkdirs()) {
				throw new IOException("error creating directories");
			}
		}
		outputFile.getParentFile().mkdirs();
		ImageIO.write(image, "png", outputFile);
	}

	public void actionPerformed(ActionEvent e) {
		pressed();
	}
}
