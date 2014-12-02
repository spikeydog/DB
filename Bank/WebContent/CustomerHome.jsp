<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Credit Dauphine Customer Home</title>
<script>
	function foo(value) {
		document.getElementById("accountID").value= value;
	}
</script>
</head>
<body>
<%@ page import="java.sql.Timestamp, 
				java.util.List, 
				java.util.LinkedList, 
				bank.bean.Account, 
				bank.util.AccountType" %>

<header>
	<jsp:include page="CustomerHeader.jsp"/>
	<% List<Account> accounts = (List<Account>) session.getAttribute("accounts"); %>
</header>
<br>
<H2>Customer Home</H2>
<section>
	<H3>Accounts Overview</H3>
	<form method="post" id="accountsForm" action="AccountActivity">
	<input name="accountID" id="accountID" type="hidden" />
	<table width="100%" frame="box">
		<tr>
			<td width="30%">
				Account
			</td>
			<td width="30%">
				Type
			</td>
			<td width="30%">
				Current Balance
			</td>
		</tr>
	<%
		StringBuilder scribe = new StringBuilder();
		int i = 0;
		if (null == accounts) {
			accounts = new LinkedList<Account>();
		}
		if (0 == accounts.size()) {
		 	scribe.append("<tr><td>You currently have no accounts</td></tr>");
		 } else {
			 for (Account account : accounts) {
				 scribe.append("<tr><td><a method=\"post\" href=\"AccountDetails?index=").append(i++)
				 .append("\">").append(account.getAccountNumber()).append("</a></td></tr>");
			 }
		 }
		 out.write(scribe.toString());
	%>
	<tr><td><a href="RequestNewAccount.jsp">Create a new account</a></td></tr>
	</table>
	</form>
</section>
</body>
</html>