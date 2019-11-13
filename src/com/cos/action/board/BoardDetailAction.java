package com.cos.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.action.comment.CommentDao;
import com.cos.dao.BoardDao;
import com.cos.model.Board;
import com.cos.model.Comment;
import com.cos.utill.Script;
import com.cos.utill.Utils;

public class BoardDetailAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if (request.getParameter("id") == null || request.getParameter("id").equals(""))
			return;

		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		BoardDao dao = new BoardDao();
		Board board = dao.findById(id);
		CommentDao commentdao = new CommentDao();

		List<Comment> comments = commentdao.findByBoardId(id);

		if (board != null) {
			// 조회수 증가 - 쿠키를 저장해서 쿠키가 있을 때 새로고침 방지
			int result = dao.increaseReadCount(id);// 정상 1, 비정상 -1
			

			if (result == 1) {

				// 유튜브 파싱
				Utils.setPreviewYoutube(board);
				// board request에 담고 dispatcher이동
				request.setAttribute("board", board);
				request.setAttribute("comments", comments);
				RequestDispatcher dis = request.getRequestDispatcher("board/detail.jsp");
				dis.forward(request, response);
			} else {
				Script.back(response);
			}
		} else {
			Script.back(response);
		}
	}
}
