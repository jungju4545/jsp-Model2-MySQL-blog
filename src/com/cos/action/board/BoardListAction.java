package com.cos.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.eclipse.jdt.internal.compiler.ast.ForeachStatement;

import com.cos.action.Action;
import com.cos.dao.BoardDao;
import com.cos.model.Board;
import com.cos.utill.Utils;


public class BoardListAction implements Action{
	private final static String TAG  = "BoardListAction";
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	if(request.getParameter("page")==null) return;
	int page = Integer.parseInt(request.getParameter("page"));
	
	if(page<=0 ) {
		page=1;
	}
	
	BoardDao bDao = new BoardDao();
	List<Board> boards = null;
	List<Board> hotBoards = bDao.findOrderByReadCountDesc();// 인기리스트
	
	//search 와 목록을 분기
	if(request.getParameter("search")==null || request.getParameter("search").equals("")){
		boards = bDao.findAll(page);
		request.setAttribute("search", null);
		
	}else {
		String search = request.getParameter("search");
		boards = bDao.findAll(page, search);
		request.setAttribute("search", search);
	}
	
	Utils.setPreviewImg(boards);
	Utils.setPreviewContent(boards);
	Utils.setPreviewContent(hotBoards);
	request.setAttribute("boards", boards);
	request.setAttribute("hotBoards", hotBoards);
	
	RequestDispatcher dis = request.getRequestDispatcher("board/list.jsp");
	dis.forward(request, response);
}
}
