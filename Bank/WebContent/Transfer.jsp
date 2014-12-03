<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Credit Dauphine</title>
</head>

<body>
<%@ page import="java.util.List, java.util.LinkedList, bank.bean.Account, 
	bank.util.AccountType"%>
<%
	List<Account> accounts = (List<Account>) session.getAttribute("accounts");
	String target = request.getParameter("target");
	String defaultTarget = null==target? "" : " default=\"" + target + "\" "; 
%>
<header>
	<jsp:include page="CustomerHeader.jsp"/>
	<H2>Transfer Funds</H2>
</header>
<section>
<form name="transferForm" method="post" action="Transfer">
	<fieldset><legend>Select Source and Destination Accounts</legend>
	<table>
		<tr>
			<td>
				<label for="source">Source Account</label>
			</td>
			<td>
				<select id="source" name="source">
					<%
						StringBuilder scribe = new StringBuilder();
			
						for (Account account : accounts) {
							if (!account.isFrozen()) {
								int number = account.getAccountNumber();
								scribe.append("<option value=\"").append(number)
								.append("\">").append(number).append("</option>");
							}
						}
						out.write(scribe.toString());
					%>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label for="dest">Destination Account</label>
			</td>
			<td>
				<select id="target" name="target" <%= defaultTarget %> >
					<%
						scribe = new StringBuilder();
			
						for (Account account : accounts) {
							if (!account.isFrozen()) {
								int number = account.getAccountNumber();
								scribe.append("<option value=\"").append(number)
								.append("\">").append(number).append("</option>");
							}
						}
						out.write(scribe.toString());
					%>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<label for="amount">Amount</label>
			</td>
			<td>
				<input 	id="amount" 
						name="amount" 
						required="true" 
						size="size()" 
						maxlength="size()"
						type="text"/>
			</td>
			<td>
				<label style="" id="warning" color="#FF0000">Enter a positive value!</label>
			</td>
		</tr>
			<td>
				<input type="submit" value="Transfer Funds"/>
			</td>
		<tr>
		</table>
	</fieldset>
</form>
</section>
</body>
</html>