package JTest;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DAO.BoardDAO;
import DTO.BoardDTO;

public class DAO_Test {
	private BoardDAO bdao = null;
	
	@Before
	public void BoardDAO () {
		bdao = BoardDAO.getInstance(); 
	}
	
	@Test
	public void insert() {
		BoardDTO bdto = new BoardDTO();
		bdto.setNo(1);
		bdto.setTitle("test");
		bdto.setContent("aaaa");
		bdto.setWriter("홍길동");
		bdto.setCnt(2);
		bdao.insert(bdto);
	}
	@Test
	public void delete() {
		bdao.delete(21);
	}
	@Test
	public void list() {
		ArrayList<BoardDTO> list = bdao.list();
		for(int i =0; i<list.size(); i++) {
			System.out.println("-----"+i+"번 게시글"+"-----");
			System.out.println(list.get(i).getNo());
			System.out.println(list.get(i).getTitle());
			System.out.println(list.get(i).getContent());
			System.out.println(list.get(i).getWriter());
			System.out.println(list.get(i).getCnt());
		}
	}
	@Test
	public void update() {
		bdao.update(24,"aaaaaaaaaa");
	}
}
