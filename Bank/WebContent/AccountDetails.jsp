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
<%@ page import="java.util.List, java.util.LinkedList, bank.bean.Account, 
	bank.bean.Transaction, bank.util.AccountType"%>
<%
	Account account = (Account) session.getAttribute("account");
	List<Transaction> trans = (List<Transaction>) session.getAttribute("trans");
%>
<H1>Credit Dauphine</H1>
<header>
	<jsp:include page="CustomerHeader.jsp"/>
</header>

<H2>Account Activity - <%= account==null? "Empty" : account.getAccountNumber() %></H2>

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
		StringBuilder scribe = new StringBuilder();
		if (null == trans) {
			trans = new LinkedList<Transaction>();
		}
		
		 if (0 == trans.size()) {
		 	scribe.append("<tr><td>There are no matching transactions</td></tr>");
		 } else {
			 for (Transaction tx : trans) {
				 scribe.append("<tr>");
				 scribe.append("<td>").append(tx.getDate()).append("</td>");
				 scribe.append("<td>").append(tx.getIssuer()).append("</td>");
				 scribe.append("<td>").append(tx.getAmount()).append("</td>");
				 scribe.append("<td><input name=\"index\" type=\"checkbox\" disabled=\"");
				 scribe.append(tx.isFraud()).append("\" checked=\"");
				 scribe.append(tx.isFraud()).append("\"/></td>");
				 scribe.append("<td>").append(tx.isReversed()).append("</td>");
				 scribe.append("</tr>");
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
	switch (account.getType()) {
	case CHECKING: flag = 1; value = "Request Checks"; break;
	case CREDIT:	flag = 2; value = "Request Credit Card"; break;
	case SAVINGS:	flag = 3; value = "Request New Terms"; break;
	case LOAN: flag = 4; value = "Make payment"; break;
	default: value = "";
	}
	if (0 < flag) {
		out.write("<input id='" + flag + "' type='submit' onclick='setAction(event)' value='" + value + "'/>");
	}
%>
</form>

<p>This page displays a list of each of the transactions for this account and provides links for each of the actions they can perform.
</body>
</html>