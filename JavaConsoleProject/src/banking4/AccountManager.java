package banking4;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
//기능
import java.util.Scanner;

public class AccountManager {
	//키보드 입력
	public static Scanner scan = new Scanner(System.in);
	
	////////////////////////////////////////////////////////////
	//인스턴스(객체) 배열 생성-계좌정보 최대 50(차후 변경됨)
//	static Account[] accountArr = new Account[50];
	//개설된 계좌 카운트 변수
	static int accCount = 0;
	///////////////////////////////////////////////////////////
	
	// 인스턴스 배열 생성을 HashSet<E> 컬렉션으로 변경
	static HashSet<Account> accountArr = new HashSet<Account>();
	
	//메뉴출력
	public static void showMenu() {
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설");
		System.out.println("2.입	금");
		System.out.println("3.출	금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.계좌정보삭제");
		System.out.println("6.프로그램종료");
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
//		accountArr[accCount++] = addAcc;
		
		//중복된 계좌 판단 => 내가 입력한 계좌정보와 VS 기존에 저장된 계좌정보
		//중복된 계좌 판단 => 컬렉션을 boolean값으로 비교가능
		/*
		새로운 요소를 add() 메서드로 추가하려고 할 때 이미 동일한 요소가 
		존재한다면 추가되지 않고 false를 반환합니다. 그렇지 않은 경우에는 
		추가되고 true를 반환합니다. 
		
		accountArr.add(addAcc) == false;->중복이 된다
		accountArr.add(addAcc) != true;->중복이 안된다
		*/
		boolean isOverlap = accountArr.add(addAcc);//true, false 대신 넣음
		
		// 중복계좌 발견 - 덮어쓸까 VS 기존 정보를 유지
		if(isOverlap == false) {
			scan.nextLine();
			System.out.println("중복계좌발견되었습니다.덮어쓸까요?");
			System.out.println("YES.업데이트 NO.유지)");
			String input = scan.nextLine();
			//YES를 눌렀을떄
			if(input.equalsIgnoreCase("YES")) {
				//기존정보를 삭제
				accountArr.remove(addAcc);
				//새로 입력된 정보를 덮어쓰기
				accountArr.add(addAcc);
				System.out.println("새로운 정보로 업데이트 되었습니다.");
			}
			//NO를 눌렀을때
			else {
				//기존의 정보를 유지한다. 즉 새로운 정보는 무시된다.
				System.out.println("기존 계좌로 유지하겠습니다.");
			}
		}
		//중복계좌 없음 - 계좌개설
		else {
			System.out.println("계좌개설이완료되었습니다.\n");
		}
		
	}
	
	// 입    금
	public static void depositMoney() {
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		
		System.out.print("계좌번호: ");
		String accID = scan.nextLine();
		
		try {
			System.out.print("입금액: ");
			int money = scan.nextInt();
			
			//조건식: 음수를 입금할 수 없다. => 내가 입력한 금액이 0보다 작으면
			if(money < 0) {
				System.out.println("음수를 입금할 수 없습니다.");
				return; //종료를 의미함
			}
			/* 입금액은 500원 단위로 가능하다. => 내가 입력한 금액이 500원으로 
			나눴을때 나머지가 0으로 떨어지지 않으면 */
			if(money%500 != 0) {
				System.out.println("입금액은 500원 단위로 가능합니다.");
				return;
			}
			
			//개설된 계좌만큼 반복
//			for(int i=0 ; i<accCount ; i++) {
//				//조건식 = 입력한 계좌번호와 개설된 계좌번호가 같다면
//				if(accID.compareTo(accountArr[i].getAccountNumber())==0) {
//					//입금처리
//					accountArr[i].plusAccMoney(money); 
//				}
//			}
			/*
	         Iterator<데이터타입> iterator명 = 컬렉션.iterator();
	         while(iterator명.hasNext()) {
	            iterator.next();
	         }
	         */
			//이터레이터 객체 생성
			Iterator<Account> itr = accountArr.iterator();
			
			//입금결과 확인용 변수
			boolean isDmoney = false;
			
			//반환할 객체가 있는지 확인
			while(itr.hasNext()) {
				/*
				이터레이터는  next()메서드를 통해 객체를 출력 
				*/
				Account ac = itr.next(); //어카운트 계좌정보 출력
				//조건식 = 입력한 계좌번호와 개설된 계좌번호가 같다면
				if(accID.compareTo(ac.getAccountNumber())==0) {
					//개설된 계좌에 입금처리
					ac.plusAccMoney(money); 
					isDmoney = true;
				}
			}
			if(isDmoney == false) {
				System.out.println("입금이 취소되었습니다.\n");
			}
			else {
				System.out.println("입금이 완료되었습니다\n");
			}
		}
		catch (InputMismatchException e) {
			//숫자말고 문자로 입력할때 예외처리를 해라 -InputMismatchException
			System.out.println("입금시 문자를 입력할 수 없습니다.");
			scan.nextLine();
		}	
	}
	
	// 출    금
	public static void withdrawMoney() {
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
		
		System.out.print("계좌번호: ");
		String accID = scan.nextLine();
		
		try {
			System.out.print("출금액: ");
			int money = scan.nextInt();
			
			//조건식: 음수를 입금할 수 없다. => 내가 입력한 금액이 0보다 작으면
			if(money < 0) {
				System.out.println("음수를 출금할 수 없습니다.");
				return; //종료를 의미함
			}
			/* 출금액은 1000원 단위로 가능하다. => 내가 출력한 금액이 1000원으로 
			나눴을때 나머지가 0으로 떨어지지 않으면 */
			if(money%1000 != 0) {
				System.out.println("출금액은 1000원 단위로 가능합니다.");
				return;
			}
			
			//개설된 계좌만큼 반복
//			for(int i=0 ; i<accCount ; i++) {
//				//조건식 = 입력한 계좌번호와 개설된 계좌번호가 같다면
//				if(accID.compareTo(accountArr[i].getAccountNumber())==0) {
//					//출금처리
//					accountArr[i].minusAccMoney(money); 
//				}
//			}
			//이터레이터 객체 생성
			Iterator<Account> itr = accountArr.iterator();
			
			//출금결과 확인용 변수
			boolean isDmoney = false;
			
			//반환할 객체가 있는지 확인
			while(itr.hasNext()) {
				/*
				이터레이터는  next()메서드를 통해 객체를 출력 
				*/
				Account ac = itr.next(); //어카운트 계좌정보 출력
				//조건식 = 입력한 계좌번호와 개설된 계좌번호가 같다면
				if(accID.compareTo(ac.getAccountNumber())==0) {
					//개설된 계좌에 입금처리
					ac.minusAccMoney(money);
					isDmoney = true;
				}
			}
			if(isDmoney == false) {
				System.out.println("출금이 취소되었습니다.\n");
			}
			else {
				System.out.println("출금이 완료되었습니다\n");
			}
		} 
		catch (InputMismatchException e) {
			///숫자말고 문자로 입력할때 예외처리를 해라 -InputMismatchException
			System.out.println("출금시 문자를 입력할 수 없습니다.");
			scan.nextLine();
		}
	}
	
	// 전체계좌정보출력
	public static void showAccInfo() {
		//개설된 계좌만큼 반복해라
//		for(int i=0 ; i<accCount ; i++) {
//			//저장된 객체 배열의 계좌 정보출력
//			accountArr[i].showAccInfo();
//		}
		//이터레이터 객체 생성
		Iterator<Account> itr = accountArr.iterator();
		
		
		while(itr.hasNext()) {
			Account ac = itr.next();
			
			//저장된 객체 배열의 계좌 정보출력
			ac.showAccInfo();
		}
		
		System.out.println("---------------");
		System.out.println("전체계좌정보 출력이 완료되었습니다.");
	}
	
	//계좌정보 삭제(함수)
	public static void deleteAccInfo() {
		System.out.println("삭제할 계좌번호: ");
		String accID = scan.nextLine();
		
		//이터레이터 생성
		Iterator<Account> itr = accountArr.iterator();
		
		//계좌 확인용 변수
		boolean isDel = false;
		
		while(itr.hasNext()) {
			Account ac = itr.next();
			
			//입력한 계좌 번호와 기존 계좌번호가 같을때 삭제
			if(accID.compareTo(ac.getAccountNumber())==0) {
				//개설된 계좌 삭제
				accountArr.remove(ac);
				isDel = true;
				break;
			}		
		}
		if(isDel == false) {
			System.out.println("존재하지 않는 계좌번호입니다.");
		}
		else {
			System.out.println("계좌정보가 삭제되었습니다.");
		}		
	}
}










































