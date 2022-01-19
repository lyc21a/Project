package pangPang;

import java.awt.event.*;

class MouseHandler implements MouseListener{
	ImagePanel _target;
	public MouseHandler(ImagePanel target)
	{
		_target = target;
	}
		public void mouseEntered(MouseEvent e) {

		}
		public void mouseExited(MouseEvent e) {

		}
		public void mousePressed(MouseEvent e) {
	
		}
		public void mouseClicked(MouseEvent e) {
			_target.clicked(e.getX(), e.getY());
		}
		public void mouseReleased(MouseEvent e) {

		}
	}