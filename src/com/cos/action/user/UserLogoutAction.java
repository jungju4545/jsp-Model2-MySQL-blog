package com.cos.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;

public class UserLogoutAction implements Action{
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//세션만료
	HttpSession session= request.getSession();
	session.invalidate();//세션 무료화
	
	// list로 이동
	// 주의할 점 : 데이터 안들고 있다. 데이터가 필요한 것들은 서블릿을 타고 가야함
    response.sendRedirect("/blog/index.jsp");
}
}
