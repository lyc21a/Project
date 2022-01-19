import javax.swing.JOptionPane;

public class PlayGame extends Thread {
	// 게임진행 스위치
	public static boolean switchOfPlayGame; // 게임이 진행중인지 판단
	public static boolean switchOfThrowYutTurn;// 윷을 던지는 턴이 진행중인지 판단
	public static boolean switchOfMoveMalTurn; // 말을 이동하는 턴이 진행중인지 판단
	public static boolean switchOfPlayRun; // 플레이가 진행중인지 판단
	public static boolean[] switchOfBoardClik = new boolean[29]; // 클릭되는 판을 결정
	public static boolean[][] switchOfEndBoardClick = new boolean[2][3];

	// 플레이 차례
	public static final int USER1 = 0;
	public static final int USER2 = 1;
	public static int playUser;

	// 버튼클릭 여부
	public static boolean switchOfThrowBtn; // 던지기 버튼이 눌렸는지 판단
	public static boolean switchOfMoveClick; // 최종이동햇는지 판단
	public static boolean switchOfOneMore;	// 한번 더 하는지 판단

	public PlayGame() {
		Swi swi = new Swi();
		swi.start();
	}

	public void run() {
		switchOfPlayGame = true;
		// 플레이어1과 플레이어2를 번갈아가면서 실행한다.
		while (switchOfPlayGame) {
			userStart(USER1);
			if (!switchOfPlayGame)
				break;
			userStart(USER2);
		}
	}

	public void userStart(int user) {
		switchOfPlayRun = true;

		playUser = user;
		JOptionPane.showMessageDialog(BoardPanel.pan, (playUser + 1) + "P의 차례 입니다.");

		while (switchOfPlayRun) {
			// 윷 던지기 턴이 시작.
			throwYut();
			if (!switchOfPlayRun) {
				break;
			}
			// 말 이동 턴이 시작.
			moveMal();
		}
	}

	public void throwYut() {
		switchOfThrowYutTurn = true;
		switchOfMoveMalTurn = false;

		BoardPanel.textPane.setText("던지기 버튼을 클릭하세요.");
		while (switchOfThrowYutTurn) {

			// 던지기 버튼을누를 때까지 대기
			while (!switchOfThrowBtn) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			switchOfThrowBtn = false;

			int resultIndex = -1;
			for (int i = 0; i < 3; i++) {
				if (Data.reultOfYut[playUser][i] == 0) {
					resultIndex = i;
					break;
				}
			}

			// 던진 결과가 낙이 아닌 경우 결과를 배열에 저장한다.
			if (Data.nowReultOfYut != 0) {
				// 현재결과를 결과배열에 입력한다.
				if (resultIndex != -1) {
					Data.reultOfYut[playUser][resultIndex] = Data.nowReultOfYut;
					if (Data.nowReultOfYut != 4 && Data.nowReultOfYut != 5) {
						break;
					}
				} else {
					break;
				}
			} else {
				BoardPanel.textPane.setText("낙 입니다.");
				switchOfPlayRun = false; // 플레이턴을 종료한다.
				break;
			}
		}
		switchOfThrowYutTurn = false;
	}

	public void moveMal() {
		switchOfMoveMalTurn = true;
		switchOfThrowYutTurn = false;

		while (switchOfMoveMalTurn) {

			while (!switchOfMoveClick) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			switchOfMoveClick = false;

			//상대방의 말을 잡지 않은 경우에, 결과배열이 모두 비어있는지 확인 
			if (!switchOfOneMore) {
				int count = 0;
				for (int i = 0; i < 3; i++) {
					if (Data.reultOfYut[PlayGame.playUser][i] == 0) {
						count++;
					}
				}
				if (count == 3) {
					switchOfMoveMalTurn = false;
					switchOfPlayRun = false;
					break;
				}
			}
			else{
				switchOfMoveMalTurn = false;
				switchOfOneMore = false;
			}
		}
	}
}
