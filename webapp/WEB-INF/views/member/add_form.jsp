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
	function goList() {
		var frm = document.getElementById("user");
		frm.action="<c:url value='/list' />";
		frm.submit();	
	}
//-->
</script>
</head>
<body>
	<form:form commandName="user">
		<form:hidden path="id"/>
		<table>
			<tr>
				<th>이름</th>
				<td><form:input path="name" /><form:errors path="name" />${errName}</td>
			</tr>
			<tr>
				<th>생일</th>
				<td><form:input path="birthDate" /><form:errors path="birthDate" />${errBirthDate}</td>
			</tr>
			<tr>
				<th>회원종류</th>
				<td>
					<form:select path="memberType">
<c:forEach var="memberType" items="${memberTypes}" varStatus="loop">
						<form:option value="${memberType}" lavel="${memberType}" />
</c:forEach>			
					</form:select><form:errors path="memberType" />${errMemberType}
				</td>
			</tr>
			<tr>
				<td calspan="2">
					<input type="submit" value="추가">
					<button onclick="javascript:goList();">목록으로</button>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>