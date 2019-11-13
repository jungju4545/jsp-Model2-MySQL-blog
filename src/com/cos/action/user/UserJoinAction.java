package com.cos.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.dao.UserDao;
import com.cos.model.User;
import com.cos.utill.SHA256;
import com.cos.utill.Script;

public class UserJoinAction implements Action {
	private static final String TAG = "UserjoinAction : ";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1 목적 form 태그에 있는 name값을 받아서 DB에 넣고 나서 페이지에 이동
		// null값 처리하기 , 휴효성 검사
		System.out.println(request);
		String username = request.getParameter("username");
		String rawPassword = request.getParameter("password");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String password = SHA256.getEncypt(rawPassword, "cos");

		System.out.println(TAG + "username :" + username);
		System.out.println(TAG + "password :" + password);
		System.out.println(TAG + "email :" + email);
		System.out.println(TAG + "adddress :" + address);

		// 2 데이터에 insert

		User user = new User();
		user.setUsername(username);
		user.setPassword(password); // 암호화 해야된다.
		user.setEmail(email);
		user.setAddress(address);// 주소추가

		// DAo 연결
		UserDao dao = new UserDao();
		int result = dao.save(user);

		if (result == 1) {
			response.sendRedirect("/blog/user/login.jsp");

		} else {
			Script.back(response);
		}
		System.out.println(TAG + "username" + username);
		System.out.println(TAG + "username" + username);

	}
}
