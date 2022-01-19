public class Mal extends Img {

	private int boardIndex = 0;

	public Mal(String _imgName, int posX, int posY, int width, int height) {
		super(_imgName, posX, posY, width, height);
		for (int i = 0; i < 29; i++) {
			if (BoardPanel.boardPosX[i] == posX	&& BoardPanel.boardPosY[i] == posY) {
				boardIndex = i;
				break;
			}
		}
	}

	public boolean isClick(int x, int y) {
		int xx = 0;
		int yy = 0;
		boolean swi = false;
		if (this.getLocation().x <= x + xx
				&& this.getLocation().x + this.getSize().width > x + xx) {
			if (this.getLocation().y <= y + yy
					&& this.getLocation().y + this.getSize().height > y + yy) {
				swi = true;
			}
		}
		return swi;
	}

	public void setLocation(int posX, int posY, int index) {
		super.setLocation(posX, posY);
		for (int i = 0; i < 29; i++) {
			if (BoardPanel.boardPosX[i] == posX && BoardPanel.boardPosY[i] == posY) {
				boardIndex = i;
				break;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (posX == BoardPanel.startPosX[index][i]) {
				if (posY == BoardPanel.startPosY[index][i]) {
					boardIndex = 0;
				}
			}
			if (posX == BoardPanel.endPosX[index][i]) {
				if (posY == BoardPanel.endPosY[index][i]) {
					boardIndex = -1;
				}
			}
		}
	}

	public int getBoardIndex() {
		return boardIndex;
	}
}
