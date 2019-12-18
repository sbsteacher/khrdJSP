<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String msg = (String)request.getAttribute("msg");
%>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<link type="text/css" rel="stylesheet" href="res/css/common.css">
</head>
<body>
	<div id="container">
		<div id="formContainer">
			<% if(msg != null)  { %>
				<%=msg %>
			<% } %>
			<form id="frm" action="join" method="post" onsubmit="return chk()">
				<div>아이디: <input type="text" name="uid"></div>
				<div>비밀번호: <input type="password" name="upw""></div>
				<div>확인 비밀번호: <input type="password" name="reupw"></div>
				<div>성별:
					<label>여성 <input type="radio" name="usex" value="1" checked></label>
					<label>남성 <input type="radio" name="usex" value="2"></label>					
				</div>
				<div>이름: <input type="text" name="unm"></div>
				<div><input type="submit" value="Join"></div>
			</form>
			<a href="login">로그인창</a>
			
		</div>
	</div>
	<script>
		function chk() {
			if(frm.uid.value.length == 0) {
				alert('이름 안 적혀있소!!!')
				frm.uid.focus()
				return false
			} else if(frm.upw.value.length == 0) {
				alert('비밀번호 안 적혀있소!!!')
				frm.upw.focus()
				return false
			} else if(frm.upw.value != frm.reupw.value) {
				alert('비밀번호를 확인해주세요!!!')				
				return false
			} else if(frm.unm.value == '') {
				alert('이름을 작성해 주세요!!')
				frm.unm.focus()
				return false
			}
		}
	</script>
</body>
</html>







