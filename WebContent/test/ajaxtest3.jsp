<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>fetch API 사용하기-promise</h1>
	<button onClick="getNum1()">데이터 가져오기</button>
	<script>
	let num1 = 0;
	let num2 = 0;
	let sum=0;

	function getNum1(){
		fetch("http://localhost:8000/blog/test2", {
			method : "POST", // javascript에서 {}는 object를 의미합니다
			body : "안녕" // json, String , object, number 다 날릴 수 있음
		}).then(function(data) {
			return data.text(); //inject한다. 뭘? return 되는 data를;
			//return data.json();
		}).then(function(data){
			num1 = data; // 100
			getNum2();
		}); 
	}
	
	function getNum2(){
		fetch("http://localhost:8000/blog/test3", {
			method : "POST" // javascript에서 {}는 object를 의미합니다
		}).then(function(data) {
			return data.text(); //inject한다. 뭘? return 되는 data를;
			//return data.json();
		}).then(function(data){
			num2 = data;
			console.log(data); // data.text에서 넘어 온 값
			sum = Number(num1)+Number(num2);
			console.log("sum : "+sum);
		}); 
	}
	</script>
</body>
</html>