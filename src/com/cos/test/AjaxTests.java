package com.cos.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.model.Comment;
import com.google.gson.Gson;

@WebServlet("/test")
public class AjaxTests extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int count = 1;

	public AjaxTests() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain; charset=UTF-8");// MIME타입 정해져있다.

		// 요청 데이터 처리
		BufferedReader in = request.getReader();
		String msg = in.readLine();
		System.out.println("요청 데이터 : " + msg);

		Gson gson = new Gson();
		Comment reply = gson.fromJson(msg, Comment.class);
		System.out.println("id : " + reply.getId());
		System.out.println("boardId : " + reply.getBoardId());
		System.out.println("userId : " + reply.getUserId());
		System.out.println("content : " + reply.getContent());
		System.out.println("createDate : " + reply.getCreateDate());

		// 응답 데이터 처리
		String jsonDate = "{\"name\" : \"손흥민\", \"sal\" : 100}";
		PrintWriter out = response.getWriter();
		out.println(jsonDate);
		out.flush();// 버퍼 비워줌
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}
}
