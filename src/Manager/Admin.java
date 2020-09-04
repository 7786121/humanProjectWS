package Manager;

import java.util.Scanner;

import Interface.BoardINFImpl;

public class Admin {
	private Scanner in = new Scanner(System.in);
	private BoardINFImpl bii = new BoardINFImpl();

	public Admin() {

		int selNum = -1;
		while (true) {
			menu();
			System.out.println("관리자 메뉴");
			selNum = in.nextInt();
			in.nextLine();

			switch (selNum) {
			case 1:
				list();
				break;
			case 2:
				write();
				break;
			default:
			}
		}
	}

	public void menu() {
		System.out.println("1. 글 목록 보기");
		System.out.println("2. 게시물 작성");
	}
	public void list() {
		bii.list();
	}
	public void write() {
		bii.write();
	}
}
