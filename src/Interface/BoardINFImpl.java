package Interface;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.BoardDAO;
import DTO.BoardDTO;

public class BoardINFImpl implements BoardINF {
	private BoardDAO boarddao = BoardDAO.getInstance();
	private BoardDTO boarddto = new BoardDTO();
	private Scanner in = new Scanner(System.in);

	@Override
	public void list() {
		ArrayList<BoardDTO> list = boarddao.list();
		System.out.println("���� �ۼ��� �� ���");
		for (int i = 0; i < list.size(); i++) {
			BoardDTO tempDTO = new BoardDTO();
			System.out.println("-----------");
			tempDTO = list.get(i);
			System.out.println(tempDTO.getNo());
			System.out.println(tempDTO.getTitle());
			System.out.println(tempDTO.getContent());
			System.out.println(tempDTO.getWriter());
			System.out.println(tempDTO.getCnt());
		}
	}

	@Override
	public void write() {
		System.out.println("�۾��� ��� ���� �Խ��� �ۼ� �����մϴ�.");
		String a = in.nextLine();
		if (a.equals("�۾���")) {
			BoardDTO b = new BoardDTO();
			System.out.println("������ �Է��ϼ���");
			b.setTitle(in.nextLine());
			boarddao.insert(b);
			System.out.println("������ �Է��ϼ���");
			b.setContent(in.nextLine());
			boarddao.insert(b);
			System.out.println("�ۼ��ڸ��� �Է��ϼ���");
			b.setWriter(in.nextLine());
			boarddao.insert(b);
			boarddao.insert(boarddto);
			System.out.println("�ۼ� �Ϸ�");
		}else {
			System.out.println("�߸��ԷµǾ����ϴ�.");
		}
	}

}
