package pangPang;

import java.awt.*;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class ImagePanel extends JPanel {
	Image[] image[]= new Image[6][6];
	Toolkit toolkit = getToolkit();			//toolkit 객체를 리턴하는 메소드
	int num;
	
	public ImagePanel() 
	{
		for(int i=0;i<6; i++) {
			for(int j=0;j<6;j++) {
			image[i][j] = toolkit.getImage("c:\\" +num+ ".jpg");
			num = (int)(Math.random()*6);
			}
		}
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		for(int row=0;row<6;++row)
			for(int col=0; col<6 ; ++col)
				g.drawImage(image[row][col], row*120, col*120, 120, 120, this);

		if(_isSelected) {
			int x = _selectedCol * 120;
			int y = _selectedRow * 120;
			Graphics2D g2 = (Graphics2D)g;
			g2.setStroke(new BasicStroke(5,BasicStroke.CAP_BUTT,0));
			g2.drawRect(x,y,120,120);
		}
	}
	
	boolean _isSelected = false;
	int _selectedRow, _selectedCol;
	
	public void clicked(int x, int y) {
		int row = x/120;
		int col = y/120;
		
		if(row > 5 || col > 5) return;
		
		if(!_isSelected) {
			_selectedRow = row;
			_selectedCol = col;
			_isSelected = true;
		}
		
	else{
		Image temp = image[_selectedRow][_selectedCol];
		image[_selectedRow][_selectedCol] = image[row][col];
		image[row][col] = temp;
		_isSelected = false;
	}
		repaint();
	}
}