<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Credit Dauphine</title>
<style>
	wide {
		width:160;
	}
</style>
</head>
<body>
<%@ page import="java.sql.Timestamp, 
				bank.bean.Terms,
				bank.bean.User" %>
<% Terms terms = (Terms) session.getAttribute("terms"); %>
<header>
	<jsp:include page="BankerHeader.jsp"/>
	<H2>Update Terms for Account <%= null==terms? "???" : terms.getAccountNumber() %></H2>
</header>
<form action="SetTerms" method="post">
<input type="hidden" name="accountID" value="<%= null==terms? "???" : terms.getAccountNumber() %>"/>
	<table>
		<tr>
			<td style="wide">
				<label for="min">Minimum Balance:</label>
			</td>
			<td>
			<input id="min" name="min" 
						value="<%= terms==null? "" : terms.getMinBalance()%>"/>
			</td>
		</tr>
		<tr>
			<td style="wide">
				<label for="min">Maximum Balance:</label>
			</td>
			<td>
			<input id="rate" name="max" 
						value="<%= terms==null? "" : terms.getMaxBalance() %>"/>
			</td>
		</tr>
		<tr>
			<td style="wide">
				<label for="rate">Interest Rate:</label>
			</td>
			<td>
			<input id="rate" name="rate" 
						value="<%= terms==null? "" : terms.getInterestRate() %>"/>
			</td>
		</tr>
		<tr>
			<td style="wide">
				<label for="period">Period:</label>
			</td>
			<td>
			<input id="period" name="period" 
						value="<%= terms==null? "" : terms.getPeriod() %>"/>
			</td>
		</tr>
		<tr>
			<td style="wide">
				<label for="min">Monthly fees:</label>
			</td>
			<td>
			<input id="fees" name="fees" 
						value="<%= terms==null? "" : terms.getFees() %>"/>
			</td>
		</tr>
	</table>
	<input type="submit" value="Update Terms"/>
</form>
</body>
</html>