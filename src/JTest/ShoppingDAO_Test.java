package JTest;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import DAO.ShoppingDAO;
import DAO.SnackDAO;
import DTO.ShoppingDTO;

public class ShoppingDAO_Test {
	SnackDAO sdao = null;
	ShoppingDAO sdao1 = null;
	Scanner in = new Scanner(System.in);

	@Before
	public void ShoppingDAO() {
		sdao1 = ShoppingDAO.getInstance();
		sdao = SnackDAO.getInstance();
	}

	@Test
	public void insert() {
		String selId = null;
		String selName = null;
		int selCnt = -1;
		ShoppingDTO sdto = new ShoppingDTO();
		System.out.println("���̵� �Է�");
		selId = in.nextLine();
		sdto.setSid(selId);
		System.out.println("������ ���� �Է�");
		selName = in.nextLine();
		sdto.setSname(selName);
		System.out.println("���� �Է�");
		selCnt = in.nextInt();
		in.nextLine();
		sdto.setCnt(selCnt);
		sdao1.insert(sdto);
		sdao.update(selName, selCnt);
		System.out.println("�Ϸ�");

	}
	@Test
	public void list() {
		ArrayList<ShoppingDTO> sList = sdao1.list();
		System.out.println("���");
		for(int i =0; i<sList.size(); i++) {
			ShoppingDTO tempDTO = new ShoppingDTO();
			tempDTO=sList.get(i);
			System.out.println(i+"�� ���� ���");
			System.out.println("���̵�" + tempDTO.getSid());
			System.out.println("���ڸ�" + tempDTO.getSname());
			System.out.println("����" + tempDTO.getCnt());
		}
	}
}
