public class Data {
	public static int reultOfYut[][] = new int[2][3];//윷 결과를 저장하는 변수
	public static int nowReultOfYut;	//현재 윷 결과 값
	public static int choiceMalIndex;	//선택한 말의 인덱스
	public static int choiceResultIndex; //선택한 결과의 인덱스
	
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
