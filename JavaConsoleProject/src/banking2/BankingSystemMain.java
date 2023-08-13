package banking2;
//출력
import java.util.Scanner;

public class BankingSystemMain {
	
	/*
	원래 기능구현한 함수들 여기 있었음 AccountManager.java로 이동 
	*/
	
	//전체함수 출력 (출력을 위한 함수)
	public static void main(String[]ars) {
		boolean run = true;
		//인스턴스 생성
		AccountManager accMgr = new AccountManager();
		
		
		//사용자가 종료할때까지 프로그램은 실행이 유지 => 무한루프
		while(run) {
			accMgr.showMenu();
			
			//내가 입력하는 것
			int keyInput = accMgr.scan.nextInt();
			//버퍼 날림
			accMgr.scan.nextLine();
			
			//조건식 - MAKE(1) 선택했을때 (== 같다, =대입) => 계좌개설 함수 실행
			if(keyInput == ICustomDefine.MAKE) {
				//계좌개설 함수 실행
				System.out.println("***신규계좌개설***");
				accMgr.makeAccount();
				
			}
			//조건식 - DEPOSIT(2)을 선택했을때 => 입금 함수 실행
			else if (keyInput == ICustomDefine.DEPOSIT) {
				System.out.println("***입   금***");
				//입금 함수 실행
				accMgr.depositMoney();	
			}
			//조건식 - WITHDRAW(3)을 선택했을때 => 출금 함수 실행
			else if (keyInput == ICustomDefine.WITHDRAW) {
				System.out.println("***출  금***");
				//출금 함수 실행
				accMgr.withdrawMoney();
			}
			//조건식 - INQUIRE(4)을 선택했을때 =>계좌정보출력
			else if (keyInput == ICustomDefine.INQUIRE) {
				System.out.println("***계좌정보출력***");
				//계좌정보출력
				accMgr.showAccInfo();
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
















