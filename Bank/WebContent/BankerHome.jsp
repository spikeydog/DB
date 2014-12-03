<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Credit Dauphine Banker Home</title>
<script>
</script>
</head>
<body>
<%@ page import="java.sql.Timestamp, 
				java.util.List, 
				java.util.LinkedList, 
				bank.bean.OwnedAccount, 
				bank.bean.Transaction,
				bank.bean.User" %>

<header>
	<jsp:include page="BankerHeader.jsp"/>
	<% 
		List<OwnedAccount> accounts = (List<OwnedAccount>) session.getAttribute("accounts");
		List<Transaction> trans = (List<Transaction>) session.getAttribute("trans");
	%>
</header>
<br>
<H2>Banker Home</H2>
<section>
	<H3>Pending Account Requests</H3>
	<form method="post" action="CustomerAccountDetails">
	<input name="customerID" type="hidden" />
	<input name="accountID" type="hidden"/>
	<input name=transID type="hidden"/>
	<table width="100%" frame="box">
		<tr>
			<td width="15%">
				Account
			</td>
			<td width="15%">
				Customer ID
			</td>
			<td width="15%">
				Type
			</td>
			<td width="15%">
				Current Balance
			</td>
		</tr>
<%
	StringBuilder scribe = new StringBuilder();
	if (null == accounts) {
		accounts = new LinkedList<OwnedAccount>();
	}
	
	 if (0 == accounts.size()) {
	 	scribe.append("<tr><td>There are no pending account requests</td></tr>");
	 } else {
		 out.write(accounts.size() + " accounts require your attention<br>");
		 int i = 0;
		 for (OwnedAccount account : accounts) {
			 scribe = new StringBuilder();
			 int num = account.getAccountNumber();
			 User customer = account.getUser();
			 scribe.append("<tr>");
			 scribe.append("<td><a href=\"CustomerByAccount?accountID=").append(num).append("\">");
			 scribe.append(num).append("</a></td>");
			 scribe.append("<td><a href=\"CustomerAccountDetails?customerID=").
			 append(customer.getUserID()).append("&accountID=").append(num).append("\">").
			 append(customer.getLastName()).append(", ").append(customer.getFirstName()).
			 append("</a></td>").append("<td>").append(account.getType()).append("</td>");
			 scribe.append("<td>").append(account.getBalance()).append("</td>");
			 scribe.append("</tr>");
			 out.write(scribe.toString());
			 if (++i == accounts.size()) {break;}
		 }
	 }
	 out.write(scribe.toString());
%>
	</table>
	<br>
<H3>Fraudulent Transactions</H3>
	<br>
		
		<table width="100%" frame="box">
			<tr>
				<td width="15%">
					Trans ID
				</td>
				<td width="15%">
					Account
				</td>
				<td width="15%">
					Issuer
				</td>
				<td width="15%">
					Amount
				</td>
			</tr>
	<%
		scribe = new StringBuilder();
		if (null!= trans && trans.size() > 0) {
			for (Transaction tx : trans) {
				scribe = new StringBuilder();
				scribe.append("<tr>");
				scribe.append("<td>").append(tx.getTransID()).append("</td>");
				scribe.append("<td><a href=\"CustomerByAccount?accountID=").append(tx.getAccountNumber());
				scribe.append("\">").append(tx.getAccountNumber()).append("</a></td>");
				scribe.append("<td>").append(tx.getIssuer()).append("</td>");
				scribe.append("<td>").append(tx.getAmount()).append("</td>");
				scribe.append("</tr>");
				out.write(scribe.toString());					
			}
		} else {
			out.write("<tr><td>There are no pending fraudulent transactions</td></tr>");
		}
	%>
		</table>
	</form>
</section>
</body>
</html>