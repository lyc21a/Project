package pangPang;

import java.awt.*;
import javax.swing.*;

class pangMain {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Pang pang");				//â �̸�
		frame.setPreferredSize(new Dimension(1024,768));				//â ũ��
		Container contentPane = frame.getContentPane();

		ImagePanel imagePanel = new ImagePanel();
		imagePanel.addMouseListener(new MouseHandler(imagePanel));
		contentPane.add(imagePanel, BorderLayout.CENTER);
		
		JPanel controlPanel = new JPanel();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
