<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원 등록</title>
<script type="text/javascript">
<!--
function userModify() {
	var frm = document.getElementById("user");
	frm.action="<c:url value='/modify' />";
	frm.submit();	
}
function userDelete() {
	var frm = document.getElementById("user");
	frm.action="<c:url value='/delete' />";
	frm.submit();	
}
function popupClose() {
	window.close()
}
//-->
</script>
</head>
<body>
	<form:form commandName="user">
		<input type="hidden" name="searchFunc" id="searchFunc" value="${searchFunc}"/>
		<form:hidden path="id"/>
		<table>
			<tr>
				<th>이름</th>
				<td><form:input path="name" />${errName}</td>
			</tr>
			<tr>
				<th>생일</th>
				<td><form:input path="birthDate" />${errBirthDate}</td>
			</tr>
			<tr>
				<th>회원종류</th>
				<td>
					<form:select path="memberType">
<c:forEach var="memberType" items="${memberTypes}" varStatus="loop">
						<form:option value="${memberType}" lavel="${memberType}" />
</c:forEach>			
					</form:select>${errMemberType}
				</td>
			</tr>
			<tr>
				<td calspan="2">
					<button onclick="javascript:userModify();">수정</button>
					<button onclick="javascript:userDelete();">삭제</button>
					<button onclick="javascript:popupClose();">닫기</button>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>