package kr.or.dgit.bigdata.school;

import java.sql.Connection;
import java.util.Date;

import kr.or.dgit.bigdata.school.dao.StudentService;
import kr.or.dgit.bigdata.school.dto.Student;
import kr.or.dgit.bigdata.school.util.ConnectionFactory;

public class TestMain {
	public static void main(String[] args) {
/*		Connection con = ConnectionFactory.getInstance();
		System.out.println(con); 1.코넥션테스트*/
		
		Student item = new Student(4,"홍두깨","hdk@test.co.kr",new Date());//Date에 사선이뜨면->다른버전에서는안쓰일수도있음
/*		StudentService.getInstance().insertItem(item); 2.insert테스트 */
		
		item.setName("전지현");
/*		StudentService.getInstance().updateItem(item); 3.update테스트 */
		
		StudentService.getInstance().deleteItem(4);
		
		for(Student s : StudentService.getInstance().selectByAll()){
			System.out.println(s);
		}
		
		System.out.println(StudentService.getInstance().selectByNo(1));
	}
}
