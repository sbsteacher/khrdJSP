<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
			<c:if test="${msg != null}">
				<div class="warning">${msg}</div>
			</c:if>
			<form id="frm" action="login" method="post" onsubmit="return chk()">
				<div>아이디 : <input type="text" name="uid"></div>
				<div>비밀번호 : <input type="password" name="upw"></div>
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