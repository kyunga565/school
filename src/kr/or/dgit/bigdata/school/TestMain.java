package kr.or.dgit.bigdata.school;

import java.sql.Connection;
import java.util.Date;

import kr.or.dgit.bigdata.school.dao.StudentService;
import kr.or.dgit.bigdata.school.dto.Student;
import kr.or.dgit.bigdata.school.util.ConnectionFactory;

public class TestMain {
	public static void main(String[] args) {
/*		Connection con = ConnectionFactory.getInstance();
		System.out.println(con); 1.�ڳؼ��׽�Ʈ*/
		
		Student item = new Student(4,"ȫ�α�","hdk@test.co.kr",new Date());//Date�� �缱�̶߸�->�ٸ����������¾Ⱦ��ϼ�������
/*		StudentService.getInstance().insertItem(item); 2.insert�׽�Ʈ */
		
		item.setName("������");
/*		StudentService.getInstance().updateItem(item); 3.update�׽�Ʈ */
		
		StudentService.getInstance().deleteItem(4);
		
		for(Student s : StudentService.getInstance().selectByAll()){
			System.out.println(s);
		}
		
		System.out.println(StudentService.getInstance().selectByNo(1));
	}
}
