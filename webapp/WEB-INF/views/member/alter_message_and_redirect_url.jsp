<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원관리</title>
<script type="text/javascript">
<!--
	function onLoad() {
		alert('${message}');
		var frm = document.getElementById("search");
		frm.method="POST";
		frm.action="<c:url value='${url}' />";
		frm.submit();
	}
//-->
</script>
</head>
<body onload="javascript:onLoad()">
	<form:form commandName="search">
		<form:hidden path="searchName" />
		<form:hidden path="searchMemberType" />
	</form:form>
</body>
</html>