<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Credit Dauphine - Transfer Funds</title>
</head>
<body>
<jsp:include page="CustomerHeader.jsp"/>
<section>
<form name="transferForm" action="Transfer" submit="return validate()">
	<fieldset><legend>Select Source and Destination Accounts</legend>
	<table>
		<tr>
			<td>
				<label for="source">Source Account</label>
			</td>
			<td>
				<input id="source" name="sourceAccount" required="true" size="size()" maxlength="size()" type="text"/>
			</td>
		</tr>
		<tr>
			<td>
				<label for="dest">Destination Account</label>
			</td>
			<td>
				<input id="dest" name="dest" required="true" size="size()" maxlength="size()" type="text"/>
			</td>
		</tr>
		<tr>
			<td>
				<label for="amount">Amount</label>
			</td>
			<td>
				<input id="amount" name="amount" required="true" size="size()" maxlength="size()" type="text"/>
			</td>
			<td>
				<label hidden="true" id="warning" color="#FF0000">Passwords do not match!</label>
			</td>
		</tr>
		</table>
	</fieldset>
</section>
</body>
</html>