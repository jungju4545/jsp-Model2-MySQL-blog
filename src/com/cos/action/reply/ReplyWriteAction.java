package com.cos.action.reply;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.ReplyDao;
import com.cos.model.Comment;
import com.cos.model.Reply;
import com.cos.utill.Script;
import com.google.gson.Gson;

public class ReplyWriteAction implements Action{
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("replyWriteAction");
	int userId  = Integer.parseInt(request.getParameter("userId"));
	int commentId  = Integer.parseInt(request.getParameter("commentId"));
	String content = request.getParameter("content");
	
	System.out.println("userId : "+userId);
	System.out.println("commentId : "+commentId);
	System.out.println("content : "+content);
	
	
	Reply replyForm = new Reply();
	replyForm.setCommentId(commentId);
	replyForm.setId(userId);
	replyForm.setContent(content);
	
	ReplyDao rdao = new ReplyDao();
	
	int  result = rdao.save(replyForm);
	
	if(result==1) {
		// Comment 테이블에 가장 마지막으로 만들어진 Comment 튜플이 필요
		replyForm = rdao.findByMaxId();
		replyForm.getResponseData().setStatusCode(1);
		replyForm.getResponseData().setStatus("ok");
		replyForm.getResponseData().setStatusMessage("Write was complited");
		
		//Gson>> Json parsing
		Gson gson = new Gson();
		String replyJson = gson.toJson(replyForm);
		System.out.println("1 : " +replyJson);
		// 데이터 응답
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(replyJson);
		out.flush();
	}else {
		Script.back(response);
	}
	
}
}

