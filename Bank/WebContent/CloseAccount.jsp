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
	
	<% if (account.getBalance() > 0) {
		out.write("<H3>Are you sure you want to close this account?</H3><br>");
		out.write("Your remaining balance will be refunded by cashier's check");
	} else if (account.getBalance() < 0) {
		out.write("You cannot close an account with negative balance.");	
	}
	%>
</header>
<table>
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
<form action="CloseAccount" method="post">
	
	<%
		if (account.getBalance() > 0) {
			out.write("<input type=\"submit\" value=\"Close Account\"></input>");
		}
	%>
	<input 	type="button" 
			value="Cancel" 
			onclick="window.location.assign('AccountDetails.jsp')">
	</input>
</form>

</body>
</html>