import javax.swing.JOptionPane;

public class PlayGame extends Thread {
	// �������� ����ġ
	public static boolean switchOfPlayGame; // ������ ���������� �Ǵ�
	public static boolean switchOfThrowYutTurn;// ���� ������ ���� ���������� �Ǵ�
	public static boolean switchOfMoveMalTurn; // ���� �̵��ϴ� ���� ���������� �Ǵ�
	public static boolean switchOfPlayRun; // �÷��̰� ���������� �Ǵ�
	public static boolean[] switchOfBoardClik = new boolean[29]; // Ŭ���Ǵ� ���� ����
	public static boolean[][] switchOfEndBoardClick = new boolean[2][3];

	// �÷��� ����
	public static final int USER1 = 0;
	public static final int USER2 = 1;
	public static int playUser;

	// ��ưŬ�� ����
	public static boolean switchOfThrowBtn; // ������ ��ư�� ���ȴ��� �Ǵ�
	public static boolean switchOfMoveClick; // �����̵��޴��� �Ǵ�
	public static boolean switchOfOneMore;	// �ѹ� �� �ϴ��� �Ǵ�

	public PlayGame() {
		Swi swi = new Swi();
		swi.start();
	}

	public void run() {
		switchOfPlayGame = true;
		// �÷��̾�1�� �÷��̾�2�� �����ư��鼭 �����Ѵ�.
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
		JOptionPane.showMessageDialog(BoardPanel.pan, (playUser + 1) + "P�� ���� �Դϴ�.");

		while (switchOfPlayRun) {
			// �� ������ ���� ����.
			throwYut();
			if (!switchOfPlayRun) {
				break;
			}
			// �� �̵� ���� ����.
			moveMal();
		}
	}

	public void throwYut() {
		switchOfThrowYutTurn = true;
		switchOfMoveMalTurn = false;

		BoardPanel.textPane.setText("������ ��ư�� Ŭ���ϼ���.");
		while (switchOfThrowYutTurn) {

			// ������ ��ư������ ������ ���
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

			// ���� ����� ���� �ƴ� ��� ����� �迭�� �����Ѵ�.
			if (Data.nowReultOfYut != 0) {
				// �������� ����迭�� �Է��Ѵ�.
				if (resultIndex != -1) {
					Data.reultOfYut[playUser][resultIndex] = Data.nowReultOfYut;
					if (Data.nowReultOfYut != 4 && Data.nowReultOfYut != 5) {
						break;
					}
				} else {
					break;
				}
			} else {
				BoardPanel.textPane.setText("�� �Դϴ�.");
				switchOfPlayRun = false; // �÷������� �����Ѵ�.
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

			//������ ���� ���� ���� ��쿡, ����迭�� ��� ����ִ��� Ȯ�� 
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
