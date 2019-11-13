<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="reply-btn">
		<button onClick="commentDelete(${comment.id})" class="btn-reply text-uppercase">삭제</button>
		<button onClick="replyListShow(${comment.id})" class="btn-reply text-uppercase">보기</button>
		<button onClick="replyForm(${comment.id})" class="btn-reply text-uppercase">쓰기</button>
	</div>



	<script>
	function commentDelete(comment_id){
		$.ajax({
			method : "POST",
		    url : "/blog/api/comment?cmd=delete",
		    data : comment_id+"",
		    contentType:"text/plain; charset=UTF-8",
		    success: function(result){
		    	console.log(r);
		    	// 해당 엘레멘트를 찾아 삭제하면 됨
		    	if()
		    	if(result==="ok"){    				    	
		    	$("#comment-id-"+comment_id).remove();
		    	}},
		    	
		    error:function(xhr){
		    	console.log(xhr.status);
		    }
		    
		});
	}
   </script>
</body>

</html>