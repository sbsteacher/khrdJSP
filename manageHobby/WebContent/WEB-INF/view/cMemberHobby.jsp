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
				<th>이름</th>
				<th>취미</th>
				<th>삭제</th>
			</tr>
			<c:forEach var="item" items="${mhList}">
				<tr>
					<td>${item.unm }</td>
					<td>${item.hnm }</td>
					<td><button onclick="del(${item.i_member}, ${item.i_hobby })">삭제</button></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="right">
			<c:if test="${msg != null }">
				<div class="warning">${msg }</div>
			</c:if>
			
			<form action="cMemberHobby" method="post" id="delFrm">
				<input type="hidden" name="typ" value="2">
				<input type="hidden" name="i_member">
				<input type="hidden" name="i_hobby">
			</form>
			
			<form action="cMemberHobby" method="post" id="frm" onsubmit="return chk()">
				<input type="hidden" name="typ" value="1">
				<div>
					멤버 : 
					<select name="i_member" onchange="memberChange(this.value)">
						<option value="0">--선택--</option>
						<c:forEach var="item" items="${memberList}">
							<option value="${item.i_member }" ${item.i_member == i_member ? 'selected' : ''}>${item.unm }</option>
						</c:forEach>						
					</select>
				</div>
				<div>
					취미 : 
					<select name="i_hobby">		
						<option value="0">--선택--</option>	
					</select>
				</div>
				<div>
					<input type="submit" value="등록">
				</div>
			</form>
	</div>
</div>
<script>
	function del(i_member, i_hobby) {
		delFrm.i_member.value = i_member
		delFrm.i_hobby.value = i_hobby
		delFrm.submit()
	}

	function memberChange(v) {
		axios.get('mhJson?i_member=' + v).then(function(result) {
			
			var data = result.data
			
			var html = '<option value="0">--선택--</option>'
			data.forEach(function(item) {
				html += '<option value="' + item.i_hobby + '">' + item.hnm + '</option>'
				//html += `<option value="${item.i_hobby}">${item.hnm}</option>`
			})
			frm.i_hobby.innerHTML = html
			
		}).catch(function(error) {
			console.log(error)
		})
	}
	
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