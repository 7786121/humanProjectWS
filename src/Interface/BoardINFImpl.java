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
		System.out.println("현재 작성된 글 목록");
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
		System.out.println("글쓰기 라고 쓰면 게시판 작성 시작합니다.");
		String a = in.nextLine();
		if (a.equals("글쓰기")) {
			BoardDTO b = new BoardDTO();
			System.out.println("제목을 입력하세요");
			b.setTitle(in.nextLine());
			boarddao.insert(b);
			System.out.println("내용을 입력하세요");
			b.setContent(in.nextLine());
			boarddao.insert(b);
			System.out.println("작성자명을 입력하세요");
			b.setWriter(in.nextLine());
			boarddao.insert(b);
			boarddao.insert(boarddto);
			System.out.println("작성 완료");
		}else {
			System.out.println("잘못입력되었습니다.");
		}
	}

}
