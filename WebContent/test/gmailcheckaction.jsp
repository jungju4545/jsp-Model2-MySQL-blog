<%@page import="java.io.PrintWriter"%>
<%@page import="com.cos.utill.SHA256"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// code 값 받기
String code = request.getParameter("code");

//DB에서 id값으로 email과 salt 가져오기


// return code 값이랑 send code값이랑 비교해서 동일하면
boolean rightCode = SHA256.getEncypt("s01097537272@gamil.com", "cos").equals(code)? true : false;

PrintWriter script = response.getWriter();
if(rightCode == true){
	script.println("<script>");
	script.println("alert('이메일 인증에 성공하였습니다.')");
	script.println("location.href='login.jsp'");
	script.println("</script>");
} else{
	script.println("<script>");
	script.println("alert('이메일 인증을 실패하였습니다.')");
	script.println("location.href='error.jsp'");
	script.println("</script>");
}






// 인증완료 로그인 페이지로 이동


// 미인증이면 에러페이지로 이동
%>