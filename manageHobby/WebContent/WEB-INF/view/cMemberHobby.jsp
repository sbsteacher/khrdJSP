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
	
	</div>
	<div id="right">
			<c:if test="${msg != null }">
				<div class="warning">${msg }</div>
			</c:if>
			<form action="cMemberHobby" method="post" id="frm" onsubmit="return chk()">
				<input type="hidden2" name="typ" value="1">
				<div>
					멤버 : 
					<select name="i_member">
						<option value="0">--선택--</option>
						<c:forEach var="item" items="${memberList}">
							<option value="${item.i_member }">${item.unm }</option>
						</c:forEach>						
					</select>
				</div>
				<div>
					취미 : 
					<select name="i_hobby">
						<option value="0">--선택--</option>
						<c:forEach var="item" items="${hobbyList}">
							<option value="${item.i_hobby }">${item.hnm }</option>
						</c:forEach>						
					</select>
				</div>
				<div>
					<input type="submit" value="등록">
				</div>
			</form>
	</div>
</div>
<script>
	function chk() {
		if(frm.i_member.value == '0') {
			alert('멤버를 선택해 주세요')
			return false
		} else if(frm.i_hobby.value == '0') {
			alert('취미를 선택해 주세요')
			return false
		}
	}
</script>