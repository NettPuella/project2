package banking2;
//기능
import java.util.Scanner;

public class AccountManager {
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
		
		//계좌개설 메뉴선택 계좌선택메뉴 출력
		System.out.println("------계좌선택-------");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print("선택: ");
		
		int keyInput = scan.nextInt();
		scan.nextLine();
		
		System.out.print("계좌번호: ");
		String accID = scan.nextLine(); //next()으로 대체해도됨
		System.out.print("고객이름: ");
		String cusName = scan.nextLine(); //next()으로 대체해도됨
		System.out.print("잔 고: ");
		int accMoney = scan.nextInt();
		
		//신규정보를 통한 객체생성
		Account addAcc = null;
		
		//1.보통계좌, 2.신용신뢰계좌 계좌선택 메뉴 선택시 
		
		//조건식 - 1.보통계좌 메뉴 선택시
		if(keyInput == 1) {
			//기본이자 %(정수형태로입력): 출력 => sysout
			System.out.println("기본이자 %(정수형태로입력):");
			
			//기본이자 입력 => scanner
			int interest = scan.nextInt();
			
			addAcc = new NormalAccount(accID, cusName, accMoney, interest);
		}
		//조건식 - 2.신용계좌 메뉴 선택시
		else if (keyInput == 2) {
			//기본이자 %(정수형태로입력): 출력
			System.out.print("기본이자 %(정수형태로입력):");
			
			//기본이자 입력 = scanner
			int interest = scan.nextInt();
			
			scan.nextLine();
			
			//신용등급(A,B,C등급): 출력 ->sysout
			System.out.print("신용등급(A,B,C등급):");
			
			//신용등급 입력 -> scanner
			String credit = scan.nextLine();
			
			addAcc = new HighCreditAccount(accID, cusName, accMoney, interest, credit);
		}
		
		//객체배열에 저장후 카운트 변수 1 증가 (후위증가)
		accountArr[accCount++] = addAcc;
		
//			//(계좌를 개설할때 마다)반복할때마다 객체배열의 자리만큼 반복하라
//			for (int i = 0; i < accountArr.length; i++) {
//				//객체배열에 빈값을 확인하고 내가 입력한 값들을 넣어라
//				if(accountArr[i] == null) {
//					accountArr[i] = new Account(accID, cusName, accMoney);
//				}
//			}
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
}
