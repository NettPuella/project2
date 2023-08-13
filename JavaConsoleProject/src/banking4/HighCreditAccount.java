package banking4;

//보통예금계좌를 의미 ->  Account 의 자식클래스이다.
public class HighCreditAccount extends Account{
		//멤버변수: 이자율
		int interRate;
		//멤버변수 신용등급
		String creditGrade;
		
		//생성자: 상속받은 변수와 확장한 변수 (이자비율의정보, 신용등급) 초기화
		public HighCreditAccount(String id, String name, int money, int rate, String credit) {
			super(id, name, money);
			this.interRate = rate;
			//신용등급
			this.creditGrade = credit;
		}
		//Account 클래스에서 오버라이딩한 메서드 
		@Override
		public void showAccInfo() {
			super.showAccInfo();
			
			System.out.println("기본이자: " + interRate + "%");
			System.out.println("신용등급(A,B,C등급):" + creditGrade );
			System.out.println("----------------");
		}
		@Override
		public boolean plusAccMoney(int money) {
			/*
			-이자계산은 입금시에만(plusAccMoney) 잔고를 대상으로 계산. 
			-고객의 신용등급을 A, B, C로 나누고 계좌개설시 이 정보를 등록한다.
			-A,B,C 등급별로 각각 기본이율에 7%, 4%, 2%의 이율을 추가로 제공한다.
			※ 이자율 지정을 위한 인테페이스형 상수를 정의
			[이자계산방식]
			신용계좌 : 잔고 + (잔고 * 기본이자) + (잔고 * 추가이자) + 입금액
			*/
			// Account 클래스의 잔고(get-가져오기)
			int accMoney = getAccMoney();
			
			//이자계산
			double calMoney = 0;
			
			//각각의 신용등급에 따라 이자계산을 한다.
			//신용등급이 A일 경우
			if(creditGrade.equalsIgnoreCase("A")) {
				calMoney = accMoney + (accMoney * (interRate / 100.0)) + (accMoney * (ICustomDefine.A / 100.0)) + money;
			}
			//신용등급이 B일 경우
			else if(creditGrade.equalsIgnoreCase("B")){
				calMoney = accMoney + (accMoney * (interRate / 100.0)) + (accMoney * (ICustomDefine.B / 100.0)) + money;
			}
			//신용등급이 C일경우
			else if (creditGrade.equalsIgnoreCase("C")) {
				calMoney = accMoney + (accMoney * (interRate / 100.0)) + (accMoney * (ICustomDefine.C /100.0)) + money;
			}
			
			//Account 클래스의 잔고에 이자계산(set-변경된 값을 넣기)
			setAccMoney((int)calMoney);
			
			return true;
		}
		
}



