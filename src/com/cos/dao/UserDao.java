package com.cos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.cos.model.User;
import com.cos.utill.DBClose;

public class UserDao {
	private Connection conn;// mysql 연결
	private PreparedStatement pstmt;// 쿼리문 작성 실행하기 위해 필요
	private ResultSet rs;
	
	public int proFile(User user) {
		final String SQL = "UPDATE user SET userProfile=? WHERE username= ?";

		conn = DBConn.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserProfile());
			pstmt.setString(2, user.getUsername());
			int result = pstmt.executeUpdate();// 변경된 듀플개수 리턴
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
		
	}
	
	public User findByUsername(String username) {
		
		final String SQL = "SELECT*FROM user WHERE username=?";
		
		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, username);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
				user.setUserProfile(rs.getString("userProfile"));
				user.setAddress(rs.getString("address"));
				user.setCreateDate(rs.getTimestamp("createDate"));
				
				return user;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBClose.close(conn, pstmt,rs);
		}
		
		return null;
	}
	
	// 업뎃
	public int update(User user) {
		// 데이터베이스 연결
		final String SQL = "UPDATE user SET password =?, address =?, WHERE id= ?";

		conn = DBConn.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
		
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getAddress());
			pstmt.setInt(3, user.getId());
			int result = pstmt.executeUpdate();// 변경된 듀플개수 리턴
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}
	
	public int update(String userProfile, int id) {
		// 데이터베이스 연결
		final String SQL = "UPDATE user SET userProfile =? WHERE id= ?";

		conn = DBConn.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userProfile);
			pstmt.setInt(2, id);
			int result = pstmt.executeUpdate();// 변경된 듀플개수 리턴
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}
		return -1;
	}

	public int save(User user) {
		// 데이터베이스 연결
		final String SQL = "INSERT INTO user(username, password, email ,address,createDate) values(?,?,?,?,now())";

		conn = DBConn.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getAddress());

			int result = pstmt.executeUpdate();// 변경된 듀플개수 리턴
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}

		return -1;

	}

//로그인 인증 성공 :1,  DB오류 :-1, 미인증 : 0
	public int findByUsernameAndPassword(String username, String password) {
		final String SQL = "SELECT count(id) FROM user WHERE username=? AND password=?";

		conn = DBConn.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int result = rs.getInt(1);
				return result;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return -1;
	}
}
