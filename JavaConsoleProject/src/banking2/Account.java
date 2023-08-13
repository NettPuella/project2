package banking2;
//저장
public class Account {
	//멤버변수 - 계좌번호(String형), 이름(String형), 잔액(int형) 
	private String accountNumber;
	private String customName;
	private int accMoney;
	
	//생성자
	public Account(String id, String name, int money) {
		this.accountNumber = id;
		this.customName = name;
		this.accMoney = money;
	}
	//멤버메소드 : 전체계좌정보출력
	public void showAccInfo() {
		System.out.println("-----------");
		System.out.println("계좌번호: " + accountNumber);
		System.out.println("고객이름: " + customName);
		System.out.println("잔 고: " + accMoney);
	}
	
	
	//getter(가져옴) / setter(변경) 정의
	public String getAccountNumber() {
		return accountNumber; 
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public int getAccMoney() {
		return accMoney;
	}

	public void setAccMoney(int accMoney) {
		this.accMoney = accMoney;
	}
	
	//입금처리
	public boolean plusAccMoney(int money) {
		accMoney +=  money;
		return true;
	}
	
	//출금처리
	public boolean minusAccMoney(int money) {
		accMoney -=  money;
		return true;
	}
}



























