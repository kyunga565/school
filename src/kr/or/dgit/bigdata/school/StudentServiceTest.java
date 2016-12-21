package kr.or.dgit.bigdata.school;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import kr.or.dgit.bigdata.school.dao.StudentService;
import kr.or.dgit.bigdata.school.dto.Student;

public class StudentServiceTest {
	private static StudentService studentService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		studentService = StudentService.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		studentService = null;
	}

	@Test
	public void testSelectByAll() {
		List<Student> list = studentService.selectByAll();
		Assert.assertNotEquals(0, list.size()); //����� 0�̾ƴ϶�� �����̶���
		for (Student s : list){
			System.out.println(s);
		}
	}
	@Test
	public void testSelectByNo(){
		Student selectedItem = studentService.selectByNo(1);
		Assert.assertNotNull(selectedItem);
		System.out.println(selectedItem);
	}
	@Test
	public void testDeleteItem(){ //�߰����Ȱ��� �߰���������� �߰��Ļ������
		int BeforeSize = studentService.selectByAll().size();
		
		studentService.deleteItem(3);
		
		int AfterSize = studentService.selectByAll().size();
		
		Assert.assertNotEquals(BeforeSize, AfterSize);
	}
}
