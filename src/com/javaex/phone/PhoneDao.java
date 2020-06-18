package com.javaex.phone;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhoneDao {

	// 필드
	private String drivaer = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe", id = "phonedb", pw = "phonedb";
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null; // Select문에서 사용

	// 생성자
	public PhoneDao() {

	}
	// 메소드

	// 일반메소드
	private void getConnect() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(drivaer);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw); // 주소,아이디,비밀번호 conn에 넣기

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) { // null이 아니면 클로즈 다른문에서도 같이 사용해도 문제없음
				rs.close();
			}

			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// ********** 등록하기 **********
	public void PersonInsert(PersonVo vo) {
		getConnect(); // 메모리 가져오기
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "INSERT INTO person VALUES (seq_person_id.NEXTVAL, ? , ? , ?)"; // 쿼리문 문자열 만들기 , ?는 일단 비워둔다.
			pstmt = conn.prepareStatement(query);// 쿼리로 만들기

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getCompany());
			pstmt.executeUpdate(); // 실행문 executeUpdate()했을때 내보내기 **중요**

			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}

	// ********** 수정하기 **********
	public void PersonUpdate(PersonVo vo) {
		getConnect();
		try {
			String query = " Update person ";
			query += "		 set 	name=?, ";
			query += "		 	 	hp=?, ";
			query += " 				company=? ";
			query += " 		 where 	person_id=? ";

			pstmt = conn.prepareStatement(query);// 쿼리로 만들기

			pstmt.setString(1, vo.getName()); // 첫번째 ? 는 이문열
			pstmt.setString(2, vo.getHp()); // 두번째 ?는 경북 영양
			pstmt.setString(3, vo.getCompany());
			pstmt.setInt(4, vo.getPerson_id());
			pstmt.executeUpdate(); // 실행문 executeUpdate()했을때 내보내기 **중요**

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}

	// ********** 수정하기 **********
	public void PersonDelete(PersonVo vo) {
		try {
			getConnect();
			// 3. SQL문 준비 / 바인딩 / 실행
			String query = " delete from person ";
			query += "		 where person_id=? ";

			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, vo.getPerson_id());

			pstmt.executeUpdate();
			// 4.결과처리

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
	}

	// ********** 리스트 **********
	public List<PersonVo> getPhoneList() {
		// 리스트 준비
		List<PersonVo> phonelist = new ArrayList<PersonVo>();

		try {
			// connect정보 얻어오기
			getConnect();

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "select 	p.person_id,";
			query += "				p.name,";
			query += "				p.hp,";
			query += "				p.company";
			query += "		from	person p";

			pstmt = conn.prepareStatement(query);

			rs = pstmt.executeQuery();
			// 4.결과처리

			while (rs.next()) {
				int personid = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				// 리스트에 담기
				PersonVo personVo = new PersonVo(personid, name, hp, company);
				phonelist.add(personVo); // 데이터를 리스트에 담아두기
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		// 자원정리
		close();

		return phonelist; // 리스트에 담겨있는 데이터를 모두 리턴
	}

	public List<PersonVo> getPersonList(String search) {
		getConnect();
		List<PersonVo> phonelist = new ArrayList<PersonVo>();

		try {
			String query = "select 	p.person_id,";
			query += "				p.name,";
			query += "				p.hp,";
			query += "				p.company";
			query += "		from	person p";
			query += "		where 	name like ?";
			query += "		or		hp	like	?";
			query += "		or		company	like	?";

			pstmt = conn.prepareStatement(query);
			
			search = "%" + search + "%";
			
			pstmt.setString(1, search);
			pstmt.setString(2, search);
			pstmt.setString(3, search);
			
			rs = pstmt.executeQuery();
			// 4.결과처리
			while (rs.next()) {
				int personId = rs.getInt("person_id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String company = rs.getString("company");

				PersonVo personVo = new PersonVo(personId, name, hp, company);
				phonelist.add(personVo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();

		return phonelist;
	}

}
