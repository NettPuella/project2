package banking6;

//쓰레드로 정의할 AutoSaver 클래스(Tread를 상속받음)
public class AutoSaver extends Thread{
	
	//AccountManager타입의 멤버변수 생성
	AccountManager accMgr;

	//생성자를 통해 AccountManager타입의 멤버변수를 초기화
	public AutoSaver(AccountManager accMgr) {
		this.accMgr = accMgr;
	}
	/*
	run()메서드는 쓰레드의 main()메서드에 해당한다
	Tread 클래스의 run()메서드는 오버라이딩한 것으로 직접 호추랗면 안되고
	start()를 통해 간접적으로 호출해야한다.
	만약 직접 호출하면 단순히 실행만되고 쓰레드가 생성되지 않는다.
	*/
	@Override
	public void run() {
		try {
			while(true) {
				//5초마다 저장
				accMgr.autoSaveTxt();
				sleep(5000);
			}
		} 
		catch (InterruptedException e) {

		}
		
	}
	
	
}



























