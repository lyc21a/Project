public class Data {
	public static int reultOfYut[][] = new int[2][3];//�� ����� �����ϴ� ����
	public static int nowReultOfYut;	//���� �� ��� ��
	public static int choiceMalIndex;	//������ ���� �ε���
	public static int choiceResultIndex; //������ ����� �ε���
	
	public static void dataInit(){
		nowReultOfYut = 0;
		choiceMalIndex=0;
		choiceResultIndex= -1;
		for(int i=0;i<3;i++){
			reultOfYut[0][i] = 0;
			reultOfYut[1][i] = 0;
		}
	}

}
