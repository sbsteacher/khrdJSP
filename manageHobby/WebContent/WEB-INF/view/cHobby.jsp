<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<style>
	#left {
		width: 200px;
	}
</style>    
<div id="sectionContainer">
	<div id="left">
		<table>
			<tr>
				<th>no</th>
				<th>이름</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="item" items="${hobbyList}">
				<tr>
					<td>${item.i_hobby}</td>
					<td>${item.hnm }</td>
					<td><button onclick="doDel(${item.i_hobby})">삭제</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="right">
		<c:if test="${msg != null }">
			<div class="warning">${msg }</div>
		</c:if>
		<form id="frm" action="cHobby" method="post" onsubmit="return chk()">
			<input type="hidden" name="i_hobby">
			<div>취미명 : <input type="text" name="hnm"></div>
			<div><input type="submit" value="등록"></div>
		</form>
	</div>
</div>
<script>
	function doDel(i_hobby) {
		frm.i_hobby.value = i_hobby
		frm.submit()
	}

	function chk() {
		if(frm.hnm.value.length == 0) {
			alert('취미명을 작성해 주세요')
			frm.hnm.focus()
			return false
		}
	}
</script>






