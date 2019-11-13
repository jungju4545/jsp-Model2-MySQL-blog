package com.cos.action.reply;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.action.Action;
import com.cos.action.comment.CommentFactory;


@WebServlet("/api/reply")
public class ReplyRestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReplyRestController() {
        super();
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");//url을 UTF-8로 인코딩한다는 뜻
		String cmd = request.getParameter("cmd");//url에 있는 cmd가 파라미터임
		if (cmd == null || cmd.equals("")) {
			return;
		}
		Action action = CommentFactory.getAction(cmd);
		if (action != null) {
			action.execute(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response); // 위에 doGet에 입력되었던 값의 객체
	}

}
