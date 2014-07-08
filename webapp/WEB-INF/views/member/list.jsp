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
	function addUser() {
		var frm = document.getElementById("search");
		frm.method="GET";
		frm.action="<c:url value='/add' />";
		frm.submit();
	}
	
	function openModifyPopup(id) {
		window.open("<c:url value='/modify/' />"+id+"?searchFunc=userSearch", "userModifyWindow", "width='50px' height='50px' toolbar=no menubar=no");
	}
	
	function userSearch() {
		var frm = document.getElementById("search");
		frm.submit();
	}
	
	function goPage(page) {
		var frm = document.getElementById("userPaging");
		var currentPage = document.getElementsByName("currentPage")[0];
		currentPage.value=page;
		frm.submit();
	}
//-->
</script>
</head>
<body>
	<div id="searchDiv">
		<form:form commandName="search" method="post">
			<form:label path="searchName">이름</form:label>
			<form:input path="searchName" />
			<form:label path="searchMemberType">회원종류</form:label>
			<form:select path="searchMemberType">
<c:forEach var="memberType" items="${memberTypes}" varStatus="loop">
				<form:option value="${memberType}" lavel="${memberType}" />
</c:forEach>			
			</form:select>
			<button onclick="javascript:userSearch();">검색</button>
			<button onclick="javascript:addUser();">추가</button>
		</form:form>
	</div>
	<table>
		<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>회원종류</th>
			</tr>
		</thead>
<c:forEach var="user" items="${users}" varStatus="loop">
		<tr>
			<td>${user.id}</td>
			<td><a href="javascript:openModifyPopup('${user.id}')">${user.name}</a></td>
			<td>${user.memberType}</td>
		</tr>
</c:forEach>
	</table>
	<div class="paging">
		<form:form commandName="userPaging">
			<input type="hidden" name="currentPage" value="${pagingInfo.currentPage}" />
			<input type="hidden" name="countPerPage" value="${pagingInfo.countPerPage}" />
			<input type="hidden" name="maxPage" value="${pagingInfo.maxPage}" />
			<input type="hidden" name="searchName" value="${search.searchName}" />
			<input type="hidden" name="searchMemberType" value="${search.searchMemberType}" />
		</form:form>
		<c:if test="${pagingInfo.currentPage > 1}">
		<button onclick="javascript:goPage(1);">&lt;&lt;</button>
		<button onclick="javascript:goPage(${pagingInfo.currentPage}-1);">&lt;</button>
		</c:if>
		<c:if test="${pagingInfo.currentPage < pagingInfo.maxPage}">
		<button onclick="javascript:goPage(${pagingInfo.currentPage}+1);">&gt;</button>
		<button onclick="javascript:goPage(${pagingInfo.maxPage});">&gt;&gt;</button>
		</c:if>
	</div>
</body>
</html>