package com.kyk.app.console;

import java.util.List;
import java.util.Scanner;

import com.kyk.app.entity.Notice;
import com.kyk.app.service.NoticeService;

public class NoticeConsole {
	private NoticeService service;
	
	public NoticeConsole() {
		service = new NoticeService();
	}

	public void printNoticeList() {
		List<Notice> list = service.getList();
		
		System.out.println("──────────────────────────────");
		System.out.printf("<공지사항> 총 %d게시글\n", 12);
		System.out.println("──────────────────────────────");
		
		for(Notice n : list) {
			System.out.printf("%d. %s / %s / %s\n", 
								n.getId(),n.getTitle(), n.getWriterId(), n.getRegDate());
			
		} 	
		System.out.println("──────────────────────────────");
		System.out.printf("            %d/%d 1/2 pages\n", 1, 2);
		

	}

	public int inputNocitceMenu() {
		Scanner s = new Scanner(System.in);
		
		System.out.print("<1.상세조회 / 2.이전 / 3.다음 / 4.글쓰기 / 5.종료>\n");
		String menu_input = s.nextLine();
		int menu = Integer.parseInt(menu_input);
		
		return menu;
		
	}

}
