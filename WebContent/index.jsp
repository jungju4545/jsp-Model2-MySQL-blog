<%@page import="javax.tools.DocumentationTool.Location"%>

<%@page import="com.cos.dao.DBConn"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script>
	location.href = "/blog/board?cmd=list&page=1";//list.jsp에서 바꾼다. 데이터가 안넘어가니깐
</script>	
</body>

</html>
