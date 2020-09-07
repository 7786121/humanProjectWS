package JTest;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DAO.SnackDAO;
import DTO.SnackDTO;

public class SnackDAO_Test {
	private SnackDAO sdao = null;
	
	@Before
	public void SnackDAO () {
		sdao = SnackDAO.getInstance(); 
	}
	
	@Test
	public void insert() {
		SnackDTO sdto = new SnackDTO();
		sdto.setNo(1);
		sdto.setName("test");
		sdto.setCompany("test");
		sdto.setPrice(500);
		sdto.setCnt(10);
		sdao.insert(sdto);
	}
	@Test
	public void delete() {
		sdao.delete(30);
	}
	@Test
	public void list() {
		ArrayList<SnackDTO> list = sdao.list();
		for(int i =0; i<list.size(); i++) {
			System.out.println("-----"+i+"번 목록"+"-----");
			System.out.println(list.get(i).getNo());
			System.out.println(list.get(i).getName());
			System.out.println(list.get(i).getCompany());
			System.out.println(list.get(i).getPrice());
			System.out.println(list.get(i).getCnt());
		}
	}
	@Test
	public void update() {
		sdao.update("새우과자",5000);
	}
	@Test
	public void zero() {
		ArrayList<SnackDTO> sList = sdao.zero();
		System.out.println("품절");
		for(int i =0; i<sList.size(); i++) {
			SnackDTO tempDTO = new SnackDTO();
			tempDTO=sList.get(i);			
			System.out.println(tempDTO.getNo());
			System.out.println(tempDTO.getName());
			System.out.println(tempDTO.getCompany());
			System.out.println(tempDTO.getPrice());
			System.out.println(tempDTO.getCnt());
		}
	}
}
