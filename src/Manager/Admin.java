package Manager;

import java.util.Scanner;

import Interface.ServiceINFImpl;

public class Admin {
	private Scanner in = new Scanner(System.in);
	private ServiceINFImpl serv = new ServiceINFImpl();

	public Admin() {

		while (true) {
			menu();
			int selNum = -1;
			System.out.println("������ �޴�");
			selNum = in.nextInt();
			in.nextLine();

			switch (selNum) {
			case 1:
				list();
				break;
			case 2:
				buy();
				break;
			case 3 :
				slist();
				break;
			case 4 :
				zero();
				break;
			default:
			}
		}
	}

	public void menu() {
		System.out.println("1. ���� ���");
		System.out.println("2. ���� ����");
		System.out.println("3. ���� ���");
		System.out.println("4. ǰ��");
	}
	public void list() {
	serv.list();
	}
	public void buy() {
		serv.buy();
	}
	public void slist() {	
	serv.slist();
	}
	public void zero() {
		serv.zerolist();
	}
}
