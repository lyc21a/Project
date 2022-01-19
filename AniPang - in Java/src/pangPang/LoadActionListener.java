package pangPang;

import java.awt.event.*;
import javax.swing.*;

public class LoadActionListener implements ActionListener {
	JTextField text;
	ImagePanel imagePanel;
	LoadActionListener(JTextField text, ImagePanel imagePanel){
		this.text = text;
		this.imagePanel = imagePanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		imagePanel.repaint();
	}
}
