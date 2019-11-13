package com.cos.action.comment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.utill.Script;

public class CommentDeleteAction implements Action{
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	BufferedReader in = request.getReader();
	String temp =in.readLine();
	int commentId = Integer.parseInt(temp);
	
	System.out.println("commentId :"+commentId);
	
	CommentDao commentDao = new CommentDao();
	int result  = commentDao.delete(commentId);
	
	if(result==1) {
		PrintWriter out = response.getWriter();
		out.print("ok");
		out.flush();
	}else {
		Script.back(response);
	}
}
}
