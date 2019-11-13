package com.cos.dao;

import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cos.model.Board;
import com.cos.utill.DBClose;

public class BoardDao {
	private Connection conn;// mysql 연결
	private PreparedStatement pstmt;// 쿼리문 작성 실행하기 위해 필요
	private ResultSet rs;
	
	
	public int update(Board board) {

		final String SQL = "UPDATE board SET title = ?, content = ? WHERE id = ?";
		conn = DBConn.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);

			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getId());
			
			int result = pstmt.executeUpdate(); // 변경된 튜플의 개수를 리턴
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}

		return -1;
	}
	
	public int delete(int id) {
		final String SQL = " DELETE FROM board WHERE id=?";
		conn = DBConn.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBClose.close(conn, pstmt);
			
		}
		return -1;
	}
	
	//인기리스트 가져오기
	public List<Board> findOrderByReadCountDesc() {
		final String SQL = "SELECT*FROM board ORDER BY readCount DESC limit 3";
		conn = DBConn.getConnection();
		
		try {
			List<Board> boards = new ArrayList<>();
			
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("id"));
				board.setUserId(rs.getInt("userId"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(rs.getTimestamp("createDate"));
				boards.add(board);
			
			}	return boards;
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
		
	}

	// 조회수
	public int increaseReadCount(int id) {	
		final String SQL = "UPDATE board SET readCount=readCount + 1 WHERE id=?";
		conn = DBConn.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
		
			int result = pstmt.executeUpdate();// 변경된 듀플개수 리턴

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt);
		}

		return -1;

	}

	
	public int save(Board board) {
		// 데이터베이스 연결
		final String SQL = "INSERT INTO board(userId, title, content, createDate) values(?,?,?,now())";

		conn = DBConn.getConnection();
		System.out.println(board.getUserId());
		System.out.println(board.getTitle());
		System.out.println(board.getContent()+" ");
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, board.getUserId());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent()+" ");

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

	
	
	// select * 은 데이터베이스에 있는걸 다 갖고올거임
	public List<Board> findAll(int page) {
		final String SQL = "SELECT * FROM board b , user u WHERE b.userId= u.id order by b.id desc limit ?,3";
		conn = DBConn.getConnection();
		
		try {
			List<Board> boards = new ArrayList<>();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, (page-1)*3);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content")+" ");
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
				board.getUser().setUsername(rs.getString("u.username"));
				board.getUser().setUserProfile(rs.getString("u.userProfile"));
				boards.add(board);
			}
			return boards;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}

	
	public List<Board> findAll(int page, String search) {
		
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * ");
		sb.append("from board b, user u ");
		sb.append("where b.userId = u.id and ");
		sb.append("(b.content like ? or b.title like ?) ");
		sb.append("order by b.id desc limit ?, 3 ");
		
		final String SQL = sb.toString();
		conn = DBConn.getConnection();                                                                                                                                                                                                                                                                                                                                                                                                                                                        
		
		try {
			List<Board> boards = new ArrayList<>();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, "%"+search+"%");
			pstmt.setString(2, "%"+search+"%");
			pstmt.setInt(3, (page-1)*3);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content")+" ");
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
				board.getUser().setUsername(rs.getString("u.username"));
				board.getUser().setUserProfile(rs.getString("u.userProfile"));
				boards.add(board);
			}
			return boards;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
		return null;
	}
	
	// 상세보기 SELECT*FROM board WHERE id
	public Board findById(int id) {
		final String SQL = "SELECT*FROM board b, user u  WHERE b.userId = u.id and b.id=?";
		conn = DBConn.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Board board = new Board();
				board.setId(rs.getInt("b.id"));
				board.setUserId(rs.getInt("b.userId"));
				board.setTitle(rs.getString("b.title"));
				board.setContent(rs.getString("b.content"));
				board.setReadCount(rs.getInt("b.readCount"));
				board.setCreateDate(rs.getTimestamp("b.createDate"));
				board.getUser().setUsername(rs.getString("u.username"));
				board.getUser().setUserProfile(rs.getString("u.userProfile"));
				return board;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, pstmt, rs);
		}
	return null;
}
	
	

	}
