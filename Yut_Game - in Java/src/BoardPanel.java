import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.Font;

public class BoardPanel extends JPanel implements MouseListener {
	public static int[] boardPosX = new int[29];
	public static int[] boardPosY = new int[29];
	public static int[][] startPosX = new int[2][3];
	public static int[][] startPosY = new int[2][3];
	public static int[][] endPosX = new int[2][3];
	public static int[][] endPosY = new int[2][3];
	public static final int WIDTH = 50;
	public static final int HEIGHT = 50;
	
	private Img boardLabel;
	private Img endBoard;
	private Img[] yutStick = new Img[4];

	public static JPanel pan;
	public static JButton[] yutResultButton = new JButton[3];
	public static Img selectBoard = new Img("under_arrow.gif", 690, 320, 50, 50);
	public static Mal[][] mal = new Mal[2][3];
	public static JTextPane textPane;
	public static JButton throwBtn;

	public BoardPanel() {
		pan = this;
		addMouseListener(this);
		initPosition();
		initImg();
	}

	private void initPosition() {
		setPox(0, 520, 520);
		setPox(1, 520, 420);
		setPox(2, 520, 320);
		setPox(3, 520, 220);
		setPox(4, 520, 120);
		setPox(5, 520, 20);
		setPox(6, 420, 20);
		setPox(7, 320, 20);
		setPox(8, 220, 20);
		setPox(9, 120, 20);
		setPox(10, 20, 20);
		setPox(11, 20, 120);
		setPox(12, 20, 220);
		setPox(13, 20, 320);
		setPox(14, 20, 420);
		setPox(15, 20, 520);
		setPox(16, 120, 520);
		setPox(17, 220, 520);
		setPox(18, 320, 520);
		setPox(19, 420, 520);
		setPox(20, 437, 103);
		setPox(21, 354, 186);
		setPox(22, 270, 270);
		setPox(23, 186, 354);
		setPox(24, 103, 437);
		setPox(25, 103, 103);
		setPox(26, 186, 186);
		setPox(27, 354, 354);
		setPox(28, 437, 437);
	}

	private void setPox(int index, int x, int y) {
		boardPosX[index] = x;
		boardPosY[index] = y;
	}

	private void initImg() {
		setBounds(0, 0, 780, 690);
		setLayout(null);
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				startPosX[i][j] = 610 + 50 * j;
				startPosY[i][j] = 20 + 50 * i;
				endPosX[i][j] = 610 + 50 * j;
				endPosY[i][j] = 160 + 50 * i;

				if (i == 0)
					mal[i][j] = new Mal("black_mal.png", startPosX[i][j],
							startPosY[i][j], 50, 50);
				else if (i == 1)
					mal[i][j] = new Mal("white_mal.png", startPosX[i][j],
							startPosY[i][j], 50, 50);

				mal[i][j].drawImg(this);
			}
		}
		
			selectBoard.drawImg(this);
		
		yutResultButton[0] = new JButton("");
		yutResultButton[0].setBounds(610, 420, 50, 50);
		add(yutResultButton[0]);
		yutResultButton[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Data.choiceResultIndex = 0;
			}
		});
		yutResultButton[1] = new JButton("");
		yutResultButton[1].setBounds(660, 420, 50, 50);
		add(yutResultButton[1]);
		yutResultButton[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Data.choiceResultIndex = 1;
			}
		});
		yutResultButton[2] = new JButton("");
		yutResultButton[2].setBounds(710, 420, 50, 50);
		add(yutResultButton[2]);
		yutResultButton[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Data.choiceResultIndex = 2;
			}
		});

		for (int i = 0; i < 4; i++) {
			yutStick[i] = new Img("front.gif", 590 + 45 * i, 480, 50, 100);
			yutStick[i].drawImg(this);
		}

		endBoard = new Img("finish.gif", 590, 140, 190, 140);
		endBoard.drawImg(this);
		boardLabel = new Img("map.gif", 0, 0, 590, 590);
		boardLabel.drawImg(this);

		throwBtn = new JButton("�� ������");
		throwBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���� ������.
				Data.nowReultOfYut = yutThrow();
				PlayGame.switchOfThrowBtn = true;
			}
		});
		throwBtn.setBounds(0, 590, 790, 100);
		add(throwBtn);

		textPane = new JTextPane();
		textPane.setFont(new Font("����", Font.ITALIC, 20));
		textPane.setForeground(Color.YELLOW);
		textPane.setBackground(Color.RED);
		textPane.setBounds(590, 280, 190, 130);
		add(textPane);

	}

	// ���� ������ �Լ�.
	// -1:����, 0:��, 1:��, 2:��, 3:��, 4:��, 5:��
	public int yutThrow() {
		int[] yutStickValue = new int[4];
		int value = 0;

		// �� ���븦 ��� �ո����� �ʱ�ȭ ��Ų��.
		for (int i = 0; i < 4; i++)
			yutStickValue[i] = 1;

		// 0~99���� ���ڸ� �� �������� �ҷ����� 95 96 97 98 99 �� ������ ������ ó���Ѵ�.
		int nak = (int) (Math.random() * 100);
		if (nak < 95) {

			// ���� ������. �� 4���� ��� ������. 0 : �޸�(�����Ѹ�), 1:�ո�(�ձٸ�)
			for (int i = 0; i < 4; i++) {
				yutStickValue[i] = (int) (Math.random() * 2);

				// ù��° �����밡 �޸��̸� ���� ������� �ٲ��.
				if (yutStickValue[0] == 0) {
					yutStickValue[0] = -1;
				}
			}

			int countYut = 0; // �޸��� ������� ī���� �Ѵ�.
			for (int i = 0; i < 4; i++) {
				if (yutStickValue[i] <= 0) {
					countYut++;
				}
			}

			if (countYut == 0) {
				value = 5;
				BoardPanel.textPane.setText("�� �Դϴ�. �� �� �� ��������.");
			}

			if (countYut == 4) {
				value = 4;
				BoardPanel.textPane.setText("�� �Դϴ�. �� �� �� ��������.");
			}

			if (countYut == 3) {
				value = 3;
				BoardPanel.textPane.setText("�� �Դϴ�.");
			}

			if (countYut == 2) {
				value = 2;
				BoardPanel.textPane.setText("�� �Դϴ�.");
			}

			if (countYut == 1) {
				// ó�� ���밡 �޸��̸� ����
				if (yutStickValue[0] == -1) {
					value = -1;
					BoardPanel.textPane.setText("���� �Դϴ�.");
				} else {
					value = 1;
					BoardPanel.textPane.setText("�� �Դϴ�.");
				}
			}

			// �� �׸� ���
			for (int i = 0; i < 4; i++) {
				if (yutStickValue[i] == -1) {
					yutStick[i].setImgName("return.gif");
				} else if (yutStickValue[i] == 0) {
					yutStick[i].setImgName("back.gif");
				} else if (yutStickValue[i] == 1) {
					yutStick[i].setImgName("front.gif");
				}
			}
		} else {
			// ���ϰ�쿡�� ���� �ش��ϴ� ������ 0�� ��ȯ�Ѵ�.
			value = 0;
			for (int i = 0; i < 4; i++) {
				yutStick[i].setImgName("");
			}
		}
		this.repaint();
		return value;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (PlayGame.switchOfMoveMalTurn) {
			for (int i = 0; i < 29; i++) {
				if (PlayGame.switchOfBoardClik[i]) {
					if (e.getX() >= boardPosX[i]
							&& e.getX() < boardPosX[i] + WIDTH) {
						if (e.getY() >= boardPosY[i]
								&& e.getY() < boardPosY[i] + HEIGHT) {

							int startX = mal[PlayGame.playUser][Data.choiceMalIndex]
									.getX();
							int startY = mal[PlayGame.playUser][Data.choiceMalIndex]
									.getY();
							int endX = boardPosX[i];
							int endY = boardPosY[i];

							// ���� ���
							for (int malIndex = 0; malIndex < 3; malIndex++) {
								if (mal[PlayGame.playUser][malIndex].getX() == startX) {
									if (mal[PlayGame.playUser][malIndex].getY() == startY) {
										mal[PlayGame.playUser][malIndex]
												.setLocation(endX, endY, PlayGame.playUser);
									}
								}
							}

							// ���� ������ �� �� ��
							int playIndex = 0;
							if (PlayGame.playUser == PlayGame.USER1)
								playIndex = PlayGame.USER2;
							else
								playIndex = PlayGame.USER1;

							for (int malIndex = 0; malIndex < 3; malIndex++) {
								if (mal[playIndex][malIndex].getX() == endX) {
									if (mal[playIndex][malIndex].getY() == endY) {
										// ���� ó�� ��ġ�� �̵���Ų��.
										mal[playIndex][malIndex].setLocation(BoardPanel.startPosX[playIndex][malIndex], BoardPanel.startPosY[playIndex][malIndex],playIndex);
										// �� �� �� �����⸦ �� �� �ְ� �����.
										PlayGame.switchOfOneMore = true;
									}
								}
							}

							Data.reultOfYut[PlayGame.playUser][Data.choiceResultIndex] = 0;
							PlayGame.switchOfMoveClick = true;
							break;
						}
					}
				}
			}

			for (int i = 0; i < 3; i++) {	//�� ��ġ ����
				if (PlayGame.switchOfEndBoardClick[PlayGame.playUser][i]) {
					if (e.getX() >= endPosX[PlayGame.playUser][i]
							&& e.getX() < endPosX[PlayGame.playUser][i] + WIDTH) {
						if (e.getY() >= endPosY[PlayGame.playUser][i]
								&& e.getY() < endPosY[PlayGame.playUser][i] + HEIGHT) {

							int startX = mal[PlayGame.playUser][Data.choiceMalIndex].getX();
							int startY = mal[PlayGame.playUser][Data.choiceMalIndex].getY();

							for (int malIndex = 0; malIndex < 3; malIndex++) {
								if (mal[PlayGame.playUser][malIndex].getX() == startX) {
									if (mal[PlayGame.playUser][malIndex].getY() == startY) {
										mal[PlayGame.playUser][malIndex].setLocation(BoardPanel.endPosX[PlayGame.playUser][malIndex], BoardPanel.endPosY[PlayGame.playUser][malIndex],PlayGame.playUser);
									}
								}
							}									
							
							Data.reultOfYut[PlayGame.playUser][Data.choiceResultIndex] = 0;
							PlayGame.switchOfMoveClick = true;
						}
					}
				}
			}

			for (int i = 0; i < 3; i++) {
				if (mal[PlayGame.playUser][i].isClick(e.getX(), e.getY())) {
					Data.choiceMalIndex = i;
					break;
				}
			}
		}
		Data.choiceResultIndex = -1;
	}
}
