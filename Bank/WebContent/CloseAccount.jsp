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
	bank.bean.Terms, bank.util.AccountType"%>
<%
	Account account = (Account) session.getAttribute("account");
	Terms terms = (Terms) session.getAttribute("terms");
%>
<header>
	<jsp:include page="CustomerHeader.jsp"/>
	<H2>Close Account <%= account.getAccountNumber() %></H2>
	<H3>Are you sure you want to close this account?</H3>
</header>
Current balance: <%= account.getBalance() %>
<br>
Minimum balance: <%= terms.getMinBalance() %>
<br>
Maximum balance: <%= terms.getMaxBalance() %>
<br>
Interest rate: <%= terms.getInterestRate() %>
<br>
Monthly fee: <%= terms.getFees() %>
<br>
Period: <%= terms.getPeriod() %>
<br>

<form action="CloseAccount" method="post">
	<input 	type="submit" 
			value="Close Account">
	</input>
	<input 	type="button" 
			value="Cancel" 
			onclick="window.location.assign('AccountDetails.jsp')">
	</input>;
</form>

</body>
</html>