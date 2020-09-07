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
		System.out.println("아이디 입력");
		selId = in.nextLine();
		sdto.setSid(selId);
		System.out.println("구매할 과자 입력");
		selName = in.nextLine();
		sdto.setSname(selName);
		System.out.println("수량 입력");
		selCnt = in.nextInt();
		in.nextLine();
		sdto.setCnt(selCnt);
		sdao1.insert(sdto);
		sdao.update(selName, selCnt);
		System.out.println("완료");

	}
	@Test
	public void list() {
		ArrayList<ShoppingDTO> sList = sdao1.list();
		System.out.println("목록");
		for(int i =0; i<sList.size(); i++) {
			ShoppingDTO tempDTO = new ShoppingDTO();
			tempDTO=sList.get(i);
			System.out.println(i+"번 구매 목록");
			System.out.println("아이디" + tempDTO.getSid());
			System.out.println("과자명" + tempDTO.getSname());
			System.out.println("수량" + tempDTO.getCnt());
		}
	}
}
