<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
    <link  rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-bs4.js"></script>
    <%@ include file="/include/nav.jsp"%>
<c:if test="${empty sessionScope.user}">
<script>
alert('인증안된 유저입니다.');
location.herf="/blog/user/login.jsp";
</script>
</c:if>
<!--================Contact Area =================-->
<section class="contact_area">
	<div class="container">

		<div class="row">

			<div class="col-lg-12">
			<!-- ========================start of form=================================== -->
				<form class="row contact_form" action="/blog/board?cmd=write" method="post" >
					<div class="col-md-12">
						<div class="form-group">
							<input type="text" class="form-control" id="title" name="title" placeholder="Title">
						</div>

					</div>
					<div class="col-md-12">
						<div class="form-group">
							<textarea class="form-control" name="content" id="summernote" ></textarea>
						</div>
					</div>
					<div class="col-md-12 text-right">
						<button type="submit" value="submit" class="btn submit_btn">Posting</button>
				<!-- ==========form에 있는 사용자가 입력한 모든 정보를 서블릿(서버)에 전달=========== -->
					</div>
				</form>
				<!-- =======================end of form================================== -->
			</div>
		</div>
	</div>
</section>
<!--================Contact Area =================-->
<br><br>
  <div id="summernote"></div>
    <script>
    $('#summernote').summernote({
    	placeholder: '글을 입력하세요',
        tabsize: 2,
        height: 200
      });
    $('.dropdown-toggle').dropdown()
    </script>
<%@ include file="/include/footer.jsp"%>