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
<%@ page import="java.util.List, java.util.LinkedList" %>

<header>
	<jsp:include page="CustomerHeader.jsp"/>
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
		List<String> accounts;
		StringBuilder scribe = new StringBuilder();
		int accountID1 = 1234567;
		int accountID2 = 6546854;
		if (null != session.getAttribute("Accounts")) {
			accounts = (List<String>) session.getAttribute("Accounts");
		} else {
			accounts = new LinkedList<String>();
			accounts.add("<tr><td><input type='submit' onclick='foo(this.value)' value='" + accountID1 + "'><td>Checking<td>2,242.09");
			accounts.add("<tr><td><input type='submit' onclick='foo(this.value)' value='" + accountID2 + "'><td>Savings<td>10,900.00");
		}
		
		 if (0 == accounts.size()) {
		 	scribe.append("<tr><td>You currently have no accounts</td></tr>");
		 } else {
			 for (String account : accounts) {
				 scribe.append("<tr><td>" + account + "</td></tr>");
			 }
		 }
		 out.write(scribe.toString());
	%>
	<tr><td><a href="">Create a new account</a></td></tr>
	</table>
	</form>
</section>
</body>
<p>This page will contain a list of user accounts with a summary of information for each one: account number, type, and balance.
<p>Each account contains a link that goes to the details page for that account
<p>links a customer to the account creation page
</html>