package banking5;

import java.io.Serializable;
import java.util.Scanner;

//저장
public abstract class Account implements Serializable{
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
		
		//잔고보다 출금 요청액이 많을 경우
		if(accMoney < money) {
			System.out.println("잔고가 부족합니다 어떻게할까요? : ");
			System.out.println("YES. 금액전체 출금처리 NO. 출금요청취소");
			AccountManager.scan.nextLine();
			String keyInput = AccountManager.scan.nextLine();
			
			//조건식 - YES를 눌렀을 경우 (equalsIgnoreCase 는 ==) : 금액전체를 출금해라
			if(keyInput.equalsIgnoreCase("YES")) {
				//금액전체를 출금해라
				accMoney = 0;
				System.out.println("금액전체 출금되었습니다.");
			}
			//조건식 - NO를 눌렀을 경우
			else if(keyInput.equalsIgnoreCase("NO")){
				System.out.println("출금요청이 취소되었습니다.");
				return false;
			}
			// YES, NO를 제외한 메뉴를 입력한 경우 - 예외처리
			else {
				System.out.println("메뉴를 정확하게 입력해주세요");
			}
		}	
		//잔고보다 출금 요청액이 적을 경우 => 정상적인 출금
		else {
			//잔고에서 출금
			accMoney -=  money;
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		//멤버변수의 hash값을 얻어온다
		int accCode = this.accountNumber.hashCode();
		return accCode;
	}
	
	@Override
	public boolean equals(Object obj) {
		//매개변수로 전달된 객체(=obj)를 다운캐스팅한다.
		Account ac = (Account)obj;
		//accountNumber를 비교해 동일한 객체가 발결되면 true를 반환(set에 저장x)
		if(ac.accountNumber.equals(this.accountNumber)) {
			return true;
		}
		//동일한 객체가 발견되지 않으면 false를 반환(set에 저장 o)
		else {
			return false;
		}
	}
	
}



























