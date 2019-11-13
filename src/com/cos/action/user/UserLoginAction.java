package com.cos.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.utill.SHA256;
import com.cos.utill.Script;

public class UserLoginAction implements Action{
	private static final String TAG = "UserLoginAction : ";
@Override
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	//
	String username = request.getParameter("username");
	String rawPassword = request.getParameter("password");
	String rememberMe = request.getParameter("rememberMe");
	String password = SHA256.getEncypt(rawPassword, "cos");
	
	System.out.println(TAG+"username : "+username);
	System.out.println(TAG+"password : "+password);
	System.out.println(TAG+"rememberMe : "+rememberMe);
	
	UserDao dao = new UserDao();
	int result = dao.findByUsernameAndPassword(username,password);
	
	if(result==1) {
		//쿠키 저장
		if(rememberMe !=null) {
			System.out.println(TAG+"쿠키 : 저장");
			Cookie c = new Cookie("username",username);
			c.setMaxAge(6000);//100mim
			response.addCookie(c);// 사용자에게 쿠키 전달
			}
		else {
			System.out.println(TAG+"쿠키삭제");
			Cookie c = new Cookie("username",null);
			c.setMaxAge(0);
			response.addCookie(c);
		}
		
		// 세션 등록
		HttpSession session = request.getSession();
		
		//User 객채 만들기
	    User user=dao.findByUsername(username);  
	    
	    session.setAttribute("user", user);
	    
	    
		// 로그인 하면 logout으로 바껴야 하는데 안된다.
//		session.setAttribute("username", username);
		response.sendRedirect("/blog/index.jsp");
	}else{
		Script.back(response);
	}
	
}
}
