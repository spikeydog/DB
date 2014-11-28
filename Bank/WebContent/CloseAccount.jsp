<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.util.List, java.util.LinkedList, bank.bean.Account, 
	bank.util.AccountType"%>
<%
	List<Account> accounts = (List<Account>) session.getAttribute("accounts");
%>
<header>
	<jsp:include page="CustomerHeader.jsp"/>
	<H2>Close Account</H2>
</header>
<form action="CloseAccount" method="post">
	<select>
		<%
			StringBuilder scribe = new StringBuilder();
			
			for (Account account : accounts) {
				int number = account.getAccountNumber();
				scribe.append("<option value=\"").append(number)
				.append("\">").append(number).append("</option>");
			}
			out.write(scribe.toString());
		%>
	</select>
</form>

</body>
</html>