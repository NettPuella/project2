package banking6;

public class NormalAccount extends Account {
	
	//멤버변수: 이자율
	int interRate;

	
	//생성자: 상속받은 변수와 확장한 변수 (이자비율의정보) 초기화
	public NormalAccount(String id, String name, int money, int rate) {
		super(id, name, money);
		this.interRate = rate;
	}
	
	//Account 클래스에서 오버라이딩한 메서드 
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		
		System.out.println("기본이자: " + interRate + "%");
		System.out.println("----------------");
	}
	@Override
	public boolean plusAccMoney(int money) {
		/*
		이자계산은 입금시에만(plusAccMoney) 잔고를 대상으로 계산. 
		[이자계산방식]
			일반계좌 : 잔고 + (잔고 * 기본이자) + 입금액 
		*/
		// Account 클래스의 잔고(get-가져오기)
		int accMoney = getAccMoney();
		
		//이자계산
		double calMoney = accMoney + (accMoney * (interRate / 100.0)) + money;
		
		//Account 클래스의 잔고에 이자계산(set-변경된 값을 넣기)
		setAccMoney((int)calMoney);
		
		return true;
	}
	@Override
	public String toString() {
		return String.format("[보통예금계좌]" + super.toString() + ", 기본이자: %d%n", interRate);
	}
}






















