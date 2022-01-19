public class SelectPan {
	private int nowPanIndex;
	public SelectPan(int index) {
		nowPanIndex = index;
	}

	public int select(SelectPan prePan, boolean shortRoad, int count) {
		if (count == 0) {
			return nowPanIndex;
		}
		
		switch (nowPanIndex) {
		case 0:
			if (count == -1)
				return -1; // °ñ
			else {
				if (nowPanIndex != prePan.getIndex())
					return -1;
				else
					return new SelectPan(1).select(this, false, count - 1);
			}
		case 1:
			if (count == -1)
				return 0;
			else
				return new SelectPan(2).select(this, false, count - 1);
		case 2:
			if (count == -1)
				return 1;
			else
				return new SelectPan(3).select(this, false, count - 1);
		case 3:
			if (count == -1)
				return 1;
			else
				return new SelectPan(4).select(this, false, count - 1);
		case 4:
			if (count == -1)
				return 3;
			else
				return new SelectPan(5).select(this, false, count - 1);
		case 5:
			if (count == -1)
				return 4;
			else {
				if (nowPanIndex != prePan.getIndex())
					return new SelectPan(6).select(this, false, count - 1);
				else 
					return new SelectPan(20).select(this, false, count - 1);				
			}
		case 6:
			if (count == -1)
				return 5;
			else
				return new SelectPan(7).select(this, false, count - 1);
		case 7:
			if (count == -1)
				return 6;
			else
				return new SelectPan(8).select(this, false, count - 1);
		case 8:
			if (count == -1)
				return 7;
			else
				return new SelectPan(9).select(this, false, count - 1);
		case 9:
			if (count == -1)
				return 8;
			else
				return new SelectPan(10).select(this, false, count - 1);
		case 10:
			if (count == -1)
				return 9;
			else {
				if (nowPanIndex != prePan.getIndex())
					return new SelectPan(11).select(this, false, count - 1);
				else 
					return new SelectPan(25).select(this, false, count - 1);				
			}
		case 11:
			if (count == -1)
				return 10;
			else
				return new SelectPan(12).select(this, false, count - 1);
		case 12:
			if (count == -1)
				return 11;
			else
				return new SelectPan(13).select(this, false, count - 1);
		case 13:
			if (count == -1)
				return 12;
			else
				return new SelectPan(14).select(this, false, count - 1);
		case 14:
			if (count == -1)
				return 13;
			else
				return new SelectPan(15).select(this, false, count - 1);
		case 15:
			if (count == -1)
				return 14;
			else
				return new SelectPan(16).select(this, false, count - 1);
		case 16:
			if (count == -1)
				return 15;
			else
				return new SelectPan(17).select(this, false, count - 1);
		case 17:
			if (count == -1)
				return 16;
			else
				return new SelectPan(18).select(this, false, count - 1);
		case 18:
			if (count == -1)
				return 17;
			else
				return new SelectPan(19).select(this, false, count - 1);
		case 19:
			if (count == -1)
				return 18;
			else
				return -1;
		case 20:
			if (count == -1)
				return 5;
			else
				return new SelectPan(21).select(this, false, count - 1);
		case 21:
			if (count == -1)
				return 20;
			else
				return new SelectPan(22).select(this, false, count - 1);
		case 22:
			if (count == -1)
				return 21;
			else {
				if (nowPanIndex != prePan.getIndex()) {
					if (prePan.getIndex() == 21)
						return new SelectPan(23).select(this, false, count - 1);
					else
						return new SelectPan(27).select(this, false, count - 1);
				} 
				else
					return new SelectPan(27).select(this, false, count - 1);
			}
		case 23:
			if (count == -1)
				return 22;
			else
				return new SelectPan(24).select(this, false, count - 1);
		case 24:
			if (count == -1)
				return 23;
			else
				return new SelectPan(15).select(this, false, count - 1);
		case 25:
			if (count == -1)
				return 10;
			else
				return new SelectPan(26).select(this, false, count - 1);
		case 26:
			if (count == -1)
				return 25;
			else
				return new SelectPan(22).select(this, false, count - 1);
		case 27:
			if (count == -1)
				return 22;
			else
				return new SelectPan(28).select(this, false, count - 1);
		case 28:
			if (count == -1)
				return 27;
			else
				return -1;
		default:
			return -1;
		}

	}

	public int getIndex() {
		return nowPanIndex;
	}
}
