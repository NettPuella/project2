package banking1;
//출력 및 기능
import java.util.Scanner;

public class BankingSystemMain {
	
	//키보드 입력
	public static Scanner scan = new Scanner(System.in);
	//인스턴스(객체) 배열 생성-계좌정보 최대 50(차후 변경됨)
	static Account[] accountArr = new Account[50];
	//개설된 계좌 카운트 변수
	static int accCount = 0;
	
	//메뉴출력
	public static void showMenu() {
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설");
		System.out.println("2.입	금");
		System.out.println("3.출	금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.프로그램종료");
		System.out.print("선택:");
	}
	// 계좌개설을 위한 함수
	public static void makeAccount() {
		System.out.print("계좌번호: ");
		String accID = scan.nextLine(); //next()으로 대체해도됨
		System.out.print("고객이름: ");
		String cusName = scan.nextLine(); //next()으로 대체해도됨
		System.out.print("잔 고: ");
		int accMoney = scan.nextInt();
		
		//신규정보를 통한 객체생성
		Account addAcc = new Account(accID, cusName, accMoney);
		
		//객체배열에 저장후 카운트 변수 1 증가 (후위증가)
		accountArr[accCount++] = addAcc;
		
//		//(계좌를 개설할때 마다)반복할때마다 객체배열의 자리만큼 반복하라
//		for (int i = 0; i < accountArr.length; i++) {
//			//객체배열에 빈값을 확인하고 내가 입력한 값들을 넣어라
//			if(accountArr[i] == null) {
//				accountArr[i] = new Account(accID, cusName, accMoney);
//			}
//		}
		System.out.println("계좌개설이완료되었습니다.\n");
	}
	
	// 입    금
	public static void depositMoney() {
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		
		System.out.print("계좌번호: ");
		String accID = scan.nextLine();
		System.out.print("입금액: ");
		int money = scan.nextInt();
		
		//개설된 계좌만큼 반복
		for(int i=0 ; i<accCount ; i++) {
			//조건식 = 입력한 계좌번호와 개설된 계좌번호가 같다면
			if(accID.compareTo(accountArr[i].getAccountNumber())==0) {
				//입금처리
				accountArr[i].plusAccMoney(money); 
			}
		}
		System.out.println("입금이 완료되었습니다\n");
	}
	
	// 출    금
	public static void withdrawMoney() {
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		
		System.out.print("계좌번호: ");
		String accID = scan.nextLine();
		System.out.print("출금액: ");
		int money = scan.nextInt();
		
		//개설된 계좌만큼 반복
		for(int i=0 ; i<accCount ; i++) {
			//조건식 = 입력한 계좌번호와 개설된 계좌번호가 같다면
			if(accID.compareTo(accountArr[i].getAccountNumber())==0) {
				//출금처리
				accountArr[i].minusAccMoney(money); 
			}
		}
		System.out.println("출금이 완료되었습니다\n");
	}
	
	// 전체계좌정보출력
	public static void showAccInfo() {
		//개설된 계좌만큼 반복해라
		for(int i=0 ; i<accCount ; i++) {
			//저장된 객체 배열의 계좌 정보출력
			accountArr[i].showAccInfo();
		}
		System.out.println("---------------");
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}

	//전체함수 출력
	public static void main(String[]ars) {
		boolean run = true;
		
		//사용자가 종료할떄까지 프로그램은 실행이 유지 => 무한루프
		while(run) {
			showMenu();
			
			//내가 입력하는 것
			int keyInput = scan.nextInt();
			//버퍼 날림
			scan.nextLine();
			
			//조건식 - MAKE(1) 선택했을때 (== 같다, =대입) => 계좌개설 함수 실행
			if(keyInput == ICustomDefine.MAKE) {
				//계좌개설 함수 실행
				System.out.println("***신규계좌개설***");
				makeAccount();
				
			}
			//조건식 - DEPOSIT(2)을 선택했을때 => 입금 함수 실행
			else if (keyInput == ICustomDefine.DEPOSIT) {
				System.out.println("***입   금***");
				//입금 함수 실행
				depositMoney();	
			}
			//조건식 - WITHDRAW(3)을 선택했을때 => 출금 함수 실행
			else if (keyInput == ICustomDefine.WITHDRAW) {
				System.out.println("***출  금***");
				//출금 함수 실행
				withdrawMoney();
			}
			//조건식 - INQUIRE(4)을 선택했을때 =>계좌정보출력
			else if (keyInput == ICustomDefine.INQUIRE) {
				System.out.println("***계좌정보출력***");
				//계좌정보출력
				showAccInfo();
			}
			//조건식 - EXIT(5)을 선택했을때 =>프로그램종료
			else if (keyInput == ICustomDefine.EXIT) {
				//프로그램종료
				run = false;
			}
		}
		System.out.println("프로그램종료");
	}
}
















