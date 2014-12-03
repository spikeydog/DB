<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Credit Dauphine</title>
<script>
	function setAction(event) {
		document.getElementById("actionID").value = event.target.id;
	}
</script>
</head>
<body>
<%@ page import="java.util.List, java.util.LinkedList, bank.bean.Account, 
	bank.bean.Transaction, bank.bean.Terms, bank.util.AccountType"%>
<%
	Account account = (Account) session.getAttribute("account");
	Terms terms = (Terms) session.getAttribute("terms");
	List<Transaction> trans = (List<Transaction>) session.getAttribute("trans");
%>
<header>
	<jsp:include page="CustomerHeader.jsp"/>
	<H2>Account Details - <%= account==null? "Empty" : account.getAccountNumber() %></H2>
</header>
<table>
<tr>
		<td width="160">	
			Description: 
		</td>
			
		<td>
			<%= account!=null? account.getDescription() : "Pending approval" %>
		</td>
	</tr>
	<tr>
		<td width="160">	
			Current balance: 
		</td>
			
		<td>
			<%= account!=null? account.getBalance() : "Pending approval" %>
		</td>
	</tr>
	<tr>
		<td width="160">	
			Minimum balance: 
		</td>
			
		<td>
			<%= terms!=null? terms.getMinBalance() : "Pending approval" %>
		</td>
	</tr>
	<tr>
		<td width="160">	
			Maximum balance: 
		</td>
			
		<td>
			<%= terms!=null? terms.getMaxBalance() : "Pending approval" %>
		</td>
	</tr>
	<tr>
		<td width="160">	
			Interest rate:  
		</td>
			
		<td>
			<%= terms!=null? terms.getInterestRate() : "Pending approval" %>
		</td>
	</tr>
	<tr>
		<td width="160">	
			Period:  
		</td>
			
		<td>
			<%= terms!=null? terms.getPeriod() : "Pending approval" %>
		</td>
	</tr>
	<tr>
		<td width="160">	
			Monthly fee:
		</td>
			
		<td>
			<%= terms!=null? terms.getFees() : "Pending approval" %>
		</td>
	</tr>
	
</table>
<br>
<form action="MarkFraud">
<table width="1130" frame="box">
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
			 int i = 0;
			 for (Transaction tx : trans) {
				 scribe.append("<tr>");
				 scribe.append("<td>").append(tx.getDate()).append("</td>");
				 scribe.append("<td>").append(tx.getIssuer()).append("</td>");
				 scribe.append("<td>").append(tx.getAmount()).append("</td>");
				 scribe.append("<td><input name=\"index\" type=\"checkbox\"");
				 scribe.append("value=\"").append(i++).append("\"");
				 scribe.append(tx.isFraud()? "disabled=\"true\"" : "");
				 scribe.append(tx.isFraud()? "checked=\"true\"" : "");
				 scribe.append("onclick=\"submit();\"/></td>");
				 scribe.append((tx.isFraud() && !tx.isReversed())? "<td>Pending</td>" : "");
				 scribe.append("</tr>");
			 }
		 }
		 out.write(scribe.toString());
	%>
</table>
</form>
<H2>Account Actions</H2>
<form id="actionForm" method="post" action="">
<input name="actionID" id="actionID" type="hidden">
<%
	int flag = 0;
	String value = "None";
	String action = null;
	switch (account.getType()) {
	case CHECKING: flag = 1; value = "Request Checks"; 
		action = "alert('Checks will be mailed shortly and should arrive within 5-10 business days')";
		break;
		
	case CREDIT:	flag = 2; value = "Request Credit Card";
		action = "alert('Your new card should within 5-10 business days\nIf you possess your previous card, please destroy it')";
		break;
	case SAVINGS:	flag = 3; value = "Request New Terms"; 
		action = "alert('Your banker will review your account')";
		break;
	case LOAN: flag = 4; value = "Make payment";
		action = "window.location.assign('Transfer.jsp?target=" 
			+ account.getAccountNumber() + "')";
		break;
	default: value = "";
	}
	
	if (account.isFrozen()) {
		action = "alert('We are sorry, but your request cannot be fulfilled at this time')";
	}
	
	if (0 < flag) {
		out.write("<input id='" + flag + "' type=\"button\" onclick=\"" 
			+ action + "\" value=\"" + value + "\" />");
	}
%>
	<br>
	<input type="button" value="Close Account" onclick="window.location.assign('CloseAccount.jsp')"/>
</form>
</body>
</html>