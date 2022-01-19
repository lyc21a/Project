import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Img extends JLabel {

	private String urlhead = "res/";
	
	ImageIcon imgIcon;

	public Img(String _imgName, int posX, int posY, int width, int height) {
		imgIcon = new ImageIcon(urlhead+_imgName);
		this.setIcon(imgIcon);
		this.setBounds(posX, posY, width, height);
		this.setVisible(true);
	}

	public void setImgSize(int _width, int _height) {
		this.setSize(_width, _height);
	}

	public void setImgName(String name) {
		imgIcon = new ImageIcon(urlhead+name);
		this.setIcon(imgIcon);
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(imgIcon.getImage(), 0, 0, this.getWidth(),
				this.getHeight(), 0, 0, imgIcon.getIconWidth(),
				imgIcon.getIconHeight(), this);
	}

	/** TODO : drawImg */
	public void drawImg(JPanel view1) {
		view1.add(this);
	}
	public void drawImg(JFrame frame) {
		frame.add(this);
	}
}
