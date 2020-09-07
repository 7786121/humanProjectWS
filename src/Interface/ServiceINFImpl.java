package Interface;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.ShoppingDAO;
import DAO.SnackDAO;
import DTO.ShoppingDTO;
import DTO.SnackDTO;

public class ServiceINFImpl implements Service {
	private SnackDAO sdao = SnackDAO.getInstance();
	private ShoppingDAO sdao1 = ShoppingDAO.getInstance();
	private Scanner in = new Scanner(System.in);

	@Override
	public void list() {
		ArrayList<SnackDTO> list = sdao.list();
		System.out.println("목록");
		for (int i = 0; i < list.size(); i++) {
			SnackDTO tempDTO = new SnackDTO();
			System.out.println("-----------");
			tempDTO = list.get(i);
			System.out.println(tempDTO.getNo());
			System.out.println(tempDTO.getName());
			System.out.println(tempDTO.getCompany());
			System.out.println(tempDTO.getPrice());
			System.out.println(tempDTO.getCnt());
		}
	}

	@Override
	public void buy() { //구매
		list();
		String selId = null;
		String selName = null;
		int selCnt = -1;
		ShoppingDTO sdto = new ShoppingDTO();
		System.out.println("아이디를 입력");
		selId = in.nextLine();
		sdto.setSid(selId);
		System.out.println("구매할 과자");
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

	@Override
	public void slist() { // 구매 목록
		ArrayList<ShoppingDTO> slist = sdao1.list();
		System.out.println("구매 목록");
		for (int i = 0; i < slist.size(); i++) {
			ShoppingDTO tempDTO = new ShoppingDTO();
			tempDTO = slist.get(i);
			System.out.println(i + "번 목록입니다.");
			System.out.println("아이디 : " + tempDTO.getSid());
			System.out.println("과자명 :" + tempDTO.getSname());
			System.out.println("수량 :" + tempDTO.getCnt());
		}
	}

	@Override
	public void zerolist() { //품절
		ArrayList<SnackDTO> slist = sdao.zero();
		System.out.println("품절");
		for (int i = 0; i < slist.size(); i++) {
			SnackDTO tempDTO = new SnackDTO();
			tempDTO = slist.get(i);
			System.out.println(i + "번 목록입니다.");
			System.out.println("번호" + tempDTO.getNo());
			System.out.println("과자명" + tempDTO.getName());
			System.out.println("제조사" + tempDTO.getCompany());
			System.out.println("가격" + tempDTO.getPrice());
			System.out.println("수량" + tempDTO.getCnt());
		}
	}

}
