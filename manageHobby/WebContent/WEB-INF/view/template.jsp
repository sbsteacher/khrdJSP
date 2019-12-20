<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("loginUser") == null) {
		response.sendRedirect("login");
		return;
	}
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link type="text/css" rel="stylesheet" href="res/css/common.css">
</head>
<body>
	<form id="logoutForm" action="home" method="post">		
	</form>
	<div id="tempContainer">
		<header>
			<nav>
				<ul>
					<li><a href="home">Home</a></li>
					<li><a href="cHobby">취미등록</a></li>
					<li><a href="cMemberHobby">멤버 취미 등록</a>
					<li><a href="#" onclick="goLogout()">로그아웃</a></li>
					<li>${loginUser.unm}님 환영합니다</li>
				</ul>
			</nav>
		</header>
		<section>
			<jsp:include page="${view}.jsp"></jsp:include>
		</section>	
		<footer>
			저희 회사는 10년 전통의 어쩌고 저쩌고 전화번호 이메일 주소 회사 주소
		</footer>
	</div>
	<script>
		function goLogout() {			
			logoutForm.submit()
		}
	</script>
</body>
</html>