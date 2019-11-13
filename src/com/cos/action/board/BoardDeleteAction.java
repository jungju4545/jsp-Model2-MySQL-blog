package com.cos.action.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.BoardDao;
import com.cos.model.Board;
import com.cos.utill.Script;

public class BoardDeleteAction implements Action{
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	if(request.getParameter("cmd")==null)return;
	int id = Integer.parseInt(request.getParameter("id"));
	BoardDao deldao = new BoardDao();
	int result = deldao.delete(id);
	if(result == 1) {
		response.sendRedirect("/blog/index.jsp");
	}else {
		Script.back(response);
	}
}
}
