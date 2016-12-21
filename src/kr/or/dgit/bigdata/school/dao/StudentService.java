package kr.or.dgit.bigdata.school.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.fabric.xmlrpc.base.Data;

import kr.or.dgit.bigdata.school.dto.Student;
import kr.or.dgit.bigdata.school.util.ConnectionFactory;
import kr.or.dgit.bigdata.school.util.JdbcUtil;

public class StudentService implements InterfaceDao<Student> {
	private static final StudentService instance = new StudentService();
	
	
	public static StudentService getInstance() {
		return instance;
	}
	private StudentService() {}
	
	@Override
	public void insertItem(Student item) {
		String sql = "insert into student values(?,?,?,?)";
		PreparedStatement pstmt = null;
		Connection con = ConnectionFactory.getInstance();
		
		try {
			pstmt = con.prepareStatement(sql); //데이터베이스가 인식할수있는언어로바뀜
			
			pstmt.setInt(1, item.getStudId());
			pstmt.setString(2, item.getName());
			pstmt.setString(3, item.getEmail());
			pstmt.setTimestamp(4, new Timestamp(item.getDob().getTime()));//자바의 날짜형태를 ->시간으로바꿈
			pstmt.executeUpdate();
		} catch (SQLException e) {
			if(e.getErrorCode()==1062){
				JOptionPane.showMessageDialog(null, "중복된 학번입니다.");
			}else{
				e.printStackTrace();
			}
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
	}

	@Override
	public void deleteItem(int idx) {
		String sql = "delete from student where stud_id=?";
		PreparedStatement pstmt = null;
		Connection con = ConnectionFactory.getInstance();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (SQLException e) {
				e.printStackTrace();
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(con);
		}
		
	}

	@Override
	public void updateItem(Student item) {
		String sql = "update student set name = ?, email = ?,dob = ? where stud_id = ?";
		
/*		Connection con = ConnectionFactory.getInstance();
		PreparedStatement pstmt = null;
		try (){
위식을 아래로바꾸줌*/
		try (Connection con = ConnectionFactory.getInstance();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){ //finally 없애고
			pstmt.setString(1, item.getName());
			pstmt.setString(2, item.getEmail());
			pstmt.setTimestamp(3, new Timestamp(item.getDob().getTime()));
			pstmt.setInt(4, item.getStudId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Student selectByNo(int idx) {
		String sql = "select stud_id, name, email, dob from student where stud_id = ?";
		Connection con = ConnectionFactory.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null; //결과가져오기
		Student student = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()){
				student = getStudent(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
			JdbcUtil.close(rs, pstmt);
		}
		return student;
	}

	@Override
	public List<Student> selectByAll() {
		String sql = "select stud_id, name, email, dob from student";
		Connection con = ConnectionFactory.getInstance();
		PreparedStatement pstmt = null;
		ResultSet rs = null; //결과가져오기
		List<Student> list = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(!rs.next()){
				return Collections.emptyList();
			}
			list = new ArrayList<>();
			do{
				list.add(getStudent(rs));//selectbyno에서도 써야되기때무네 메소드만들어줌
			}while(rs.next());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(con);
			JdbcUtil.close(rs, pstmt);
		}
		return list;
	}
	private Student getStudent(ResultSet rs) throws SQLException { //resultset은 데클라레이션해줌
		int studId = rs.getInt("stud_id");
		String name = rs.getString("name");
		String email = rs.getString("email");
		Date dob = rs.getDate("dob");
		return new Student(studId, name, email, dob);
	}

}
