<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	test
	<hr>
	<div id="demo">1</div>
	<button type="button" onclick="loadDoc()">Change Count</button>
	<script>
		var reply = {
			id: null,
			boardId: 1,
			userId: 3,
			content: 'input 태그에 적힌 값을 들고온다.',
			createDate:null
		};
		
		console.log(reply);
		var replyString= JSON.stringify(reply);
		console.log(replyString);

		function loadDoc() {
			var xhttp = new XMLHttpRequest();
			// 콜백함수 xhttp.send()된 뒤 요청받은 서버에서 응답이 정상으로 처리되면 콜백
			xhttp.onreadystatechange = function() {
				if (this.readyState == 4 && this.status == 200) {

					console.log(this.responseText);
					var jsonData = JSON.parse(this.responseText);//자바스크립트 오브젝트로 변경
					document.querySelector("#demo").innerHTML = jsonData.name
							+ " " + jsonData.sal; // 서버에서 돌려준 데이터
				}
			};
			xhttp.open("POST", "http://localhost:8000/blog/test", true); // 리턴성공하면 위 함수가 실행 아니면 안됨
			xhttp.setRequestHeader("content-type","application/json");
			xhttp.send(replyString);
		}
	</script>

</body>
</html>