package ex1;

import com.kyk.app.console.NoticeConsole;

public class Program2 {

	public static void main(String[] args) {
		NoticeConsole console = new NoticeConsole();
		console.printNoticeList();
		int menu = console.inputNocitceMenu();
		
		EXIT: 
		while(true) {
			switch(menu) {
			case 1: // 상세조회
				break;
			case 2: // 이전
				break;
			case 3: // 다음
				break;
			case 4: // 글쓰기
				break;
			case 5: // 종료
				System.out.println("bye~");
				break EXIT;
			default:
				System.out.println("1~5까지만 입력가능합니다.");
				break;
			}
		}

	}

}
