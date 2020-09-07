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
		System.out.println("���");
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
	public void buy() { //����
		list();
		String selId = null;
		String selName = null;
		int selCnt = -1;
		ShoppingDTO sdto = new ShoppingDTO();
		System.out.println("���̵� �Է�");
		selId = in.nextLine();
		sdto.setSid(selId);
		System.out.println("������ ����");
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

	@Override
	public void slist() { // ���� ���
		ArrayList<ShoppingDTO> slist = sdao1.list();
		System.out.println("���� ���");
		for (int i = 0; i < slist.size(); i++) {
			ShoppingDTO tempDTO = new ShoppingDTO();
			tempDTO = slist.get(i);
			System.out.println(i + "�� ����Դϴ�.");
			System.out.println("���̵� : " + tempDTO.getSid());
			System.out.println("���ڸ� :" + tempDTO.getSname());
			System.out.println("���� :" + tempDTO.getCnt());
		}
	}

	@Override
	public void zerolist() { //ǰ��
		ArrayList<SnackDTO> slist = sdao.zero();
		System.out.println("ǰ��");
		for (int i = 0; i < slist.size(); i++) {
			SnackDTO tempDTO = new SnackDTO();
			tempDTO = slist.get(i);
			System.out.println(i + "�� ����Դϴ�.");
			System.out.println("��ȣ" + tempDTO.getNo());
			System.out.println("���ڸ�" + tempDTO.getName());
			System.out.println("������" + tempDTO.getCompany());
			System.out.println("����" + tempDTO.getPrice());
			System.out.println("����" + tempDTO.getCnt());
		}
	}

}
