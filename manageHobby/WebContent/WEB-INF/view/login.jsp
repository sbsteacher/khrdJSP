<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	String msg = (String)request.getAttribute("msg");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link type="text/css" rel="stylesheet" href="res/css/common.css">
</head>
<body>
	<div id="container">
		<div id="formContainer">			
			<% if(msg != null) { %>
				<div class="warning"><%=msg %></div>
			<% } %>
			<form id="frm" action="login" method="post" onsubmit="return chk()">
				<div>아이디 : <input type="text" name="uid" value="mic"></div>
				<div>비밀번호 : <input type="password" name="upw" value="1212"></div>
				<div><input type="submit" value="Login"></div>
			</form>
			<a href="join">회원가입</a>
			
		</div>
	</div>
	<script>
		function chk() {
			if(frm.uid.value.length == 0) {
				alert('아이디를 작성해 주세요')
				frm.uid.focus()
				return false
			} else if(frm.upw.value.length == 0) {
				alert('비밀번호를 작성해 주세요')
				frm.upw.focus()
				return false
			}
		}
	</script>
</body>
</html>