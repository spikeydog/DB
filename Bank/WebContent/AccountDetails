<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function setAction(event) {
		document.getElementById("actionID").value = event.target.id;
	}
</script>
</head>
<body>
<%@ page import="java.util.List, java.util.LinkedList" %>
<H1>Credit Dauphine</H1>
<header>
	<jsp:include page="CustomerHeader.jsp"/>
</header>

<H2>Account Activity - <%= session.getAttribute("AccountID") == null? "123456789" : session.getAttribute("AccountID") %></H2>

<table width="100%" frame="box">
		<tr>
			<td width="20%">
				Transaction Time
			</td>
			<td width="35%">
				Issued By
			</td>
			<td width="15%">
				Transaction Amount
			</td>
			<td width="15%">
				Mark as Fraud
			</td>
			<td width="15%">
				Status
			</td>
		</tr>
	<%
		int accountType = 3;
		List<String> transactions;
		StringBuilder scribe = new StringBuilder();
		if (null != session.getAttribute("Accounts")) {
			transactions = (List<String>) session.getAttribute("Accounts");
		} else {
			transactions = new LinkedList<String>();
			transactions.add("<tr><td>2014-10-10 12:02:09 p.m." +
				"<td>HARRIS TEETER #23092" + 
				"<td>- $12.22" + 
				"<td><input id='" + "txid" + "' value='" + "id" + "' name='fraud' type='checkbox'" + 
				"onchange='foo(event)'/>");
			transactions.add("<tr><td>2014-10-10 6:09:49 p.m." +
					"<td>NCSECU #92 ATM" + 
					"<td>- $40.00" + 
					"<td><input id='" + "txid" + "' value='" + "id" + "' name='fraud' type='checkbox'" + 
					"onchange='foo(event)'/>");
		}
		
		 if (0 == transactions.size()) {
		 	scribe.append("<tr><td>There are no matching transactions</td></tr>");
		 } else {
			 for (String transaction : transactions) {
				 scribe.append("<tr><td>" + transaction + "</td></tr>");
			 }
		 }
		 out.write(scribe.toString());
	%>
</table>
<H2>Account Actions</H2>
<form id="actionForm" method="post" action="AccountAction">
<input name="actionID" id="actionID" type="hidden">
<%
	int flag = 0;
	String value = "None";
	switch (accountType) {
	case 1: flag = 1; value = "Request Checks"; break;
	case 2:	flag = 2; value = "Request Credit Card"; break;
	case 3:	flag = 3; value = "Request New Terms"; break;
	case 4: flag = 4; value = "Make payment"; break;
	default: value = "";
	}
	if (0 < flag) {
		out.write("<input id='" + flag + "' type='submit' onclick='setAction(event)' value='" + value + "'/>");
	}
%>
<br>
<input id="5" value="Transfer Funds" type="submit" onclick="setAction(event)"/><br>
<input id="6" value="Close Account" type="submit" onclick="setAction(event)"/><br>
</form>

<p>This page displays a list of each of the transactions for this account and provides links for each of the actions they can perform.
</body>
</html>