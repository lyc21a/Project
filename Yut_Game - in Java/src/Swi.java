public class Swi extends Thread {

	public void run() {
		while (true) {
			// 윷 결과 버튼의 텍스트와 활성화
			for (int i = 0; i < 3; i++) {
				BoardPanel.yutResultButton[i].setText("" + Data.reultOfYut[PlayGame.playUser][i]);
				if (PlayGame.switchOfMoveMalTurn) {
					if (Data.reultOfYut[PlayGame.playUser][i] == 0)
						BoardPanel.yutResultButton[i].setEnabled(false);
					else
						BoardPanel.yutResultButton[i].setEnabled(true);
				}
				else
					BoardPanel.yutResultButton[i].setEnabled(false);
			}
			
			if(Data.choiceResultIndex != -1){
				int index = BoardPanel.mal[PlayGame.playUser][Data.choiceMalIndex].getBoardIndex();
				int result = Data.reultOfYut[PlayGame.playUser][Data.choiceResultIndex];
				SelectPan pan = new SelectPan(index);
				int s = pan.select(pan, true, result);
				int l = pan.select(pan, false, result);
				if(s != -1)
					BoardPanel.selectBoard.setLocation(BoardPanel.boardPosX[s], BoardPanel.boardPosY[s]);
				else{
					int x = BoardPanel.endPosX[PlayGame.playUser][Data.choiceMalIndex];
					int y = BoardPanel.endPosY[PlayGame.playUser][Data.choiceMalIndex];
					BoardPanel.selectBoard.setLocation(x,y);
				}
				
				for(int i=0;i<29;i++){
					PlayGame.switchOfBoardClik[i] = false;
				}
				for(int i=0;i<2;i++){
					for(int j = 0 ; j<3 ; j++){
						PlayGame.switchOfEndBoardClick[i][j] = false;
					}
				}
				
				if(s>=0)
					PlayGame.switchOfBoardClik[s] = true;
				else
					PlayGame.switchOfEndBoardClick[PlayGame.playUser][Data.choiceMalIndex] = true;
				if(l>=0)
					PlayGame.switchOfBoardClik[l] = true;
				else
					PlayGame.switchOfEndBoardClick[PlayGame.playUser][Data.choiceMalIndex] = true;
			}
			else{
				BoardPanel.selectBoard.setLocation(620, 320);
				for(int i=0;i<29;i++){
					PlayGame.switchOfBoardClik[i] = false;
				}
				for(int i=0;i<2;i++){
					for(int j = 0 ; j<3 ; j++){
						PlayGame.switchOfEndBoardClick[i][j] = false;
					}
				}
			}
		}
	}
}