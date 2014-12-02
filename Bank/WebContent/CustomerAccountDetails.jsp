<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	.wide {
		width:65;
	}
</style>
</head>
<body>
<%@ page import="java.sql.Timestamp, 
				java.util.List, 
				java.util.LinkedList,
				bank.bean.Customer, 
				bank.bean.Account,
				bank.bean.OwnedAccount, 
				bank.bean.Transaction,
				bank.bean.User" %>
</body>
<header>
	<jsp:include page="BankerHeader.jsp"/>
	<% 
		Customer customer = (Customer) session.getAttribute("customer");
		List<OwnedAccount> accounts = (List<OwnedAccount>) session.getAttribute("accounts");
		List<Transaction> trans = (List<Transaction>) session.getAttribute("trans");
	%>
	<H2>Customer Account Details Page</H2>
</header>
<table>
<tr>
	<td>
		<H3>Customer Profile</H3>
	</td>
	<td>
		<H3>Customer Account Summary</H3>
	</td>
</tr>
<tr>
<td>
	<table frame="1pt">
		<tr>
			<td>
				<label style="wide">First Name:</label>
			</td>
			<td>
				<label><%= customer.getFirstName() %></label>
			</td>
		</tr>
		<tr>
			<td>
				<label style="wide">Last Name:</label>
			</td>
			<td>
				<label><%= customer.getLastName() %></label>
			</td>
		</tr>
		<tr>
			<td>
				<label style="wide">Customer SSN:</label>
			</td>
		
			<td>
				<label><%= customer.getSsn() %></label>
			</td>
		</tr>
		<tr>
			<td>
				<label style="wide">Dauphine Risk Rating: </label>
			</td>
		
			<td>
				<label><%= customer.getRiskRating() %></label>
			</td>
			<td>
				<form>
					<input name="risk" type="text" size="5">
					<input id="udpate" name="update" 
					value="Update Risk Rating" type="submit"/>
				</form>
			</td>
		</tr>
		<tr>
				<td>
					<label width="65" for="email">E-mail Address: </label>
				</td>
				<td>
					<label id="email" 
							name="email" 
							required="true" 
							size="size()*2" 
							maxlength="return size()*2" 
							type="email"><%= customer.getEmailAddress() %></label>
				</td>
			</tr>
			<tr>
				<td>
					<label for="phone">Telephone Number: </label>
				</td>
				<td>
					<label id="phone" name="phone" required="true" 
							type="tel"/><%= customer.getTelephone() %></label>
				</td>
			</tr>
		<tr>
			<td>
				<label for="address1">Address Line 1: </label>
			</td>
			<td>
				<label id="address1" name="address1" 
							size="return size()*2" 
							maxlength="return size()*2" 
							type="text"/><%= customer.getAddress1() %></label>
			</td>
		</tr>
		<tr>
			<td>
				<label for="address2">Address Line 2: </label>
			</td>
			<td>
				<label id="address2" name="address2" 
						size="return size()*2" 
						maxlength="size()*2" 
						type="text"><%= customer.getAddress2() == null? "" : customer.getAddress2() %></label>
			</td>
		</tr>
		<tr>
			<td>
				<label for="city">City:</label>
			</td>
			<td>
				<label id="city" name="city" required="true" 
						size="return size()*2" maxlength="return size()*2" 
						type="text"><%= customer.getCity() %></label>
			</td>
		</tr>
		<tr>
			<td>
				<label for="state">State Code: </label>
			</td>
			<td>
				<label id="state" name="state" 
						required="true" size="2" 
						maxlength="2" type="text"><%= customer.getState() %></label>
			</td>
		</tr>
		<tr>
			<td>
				<label for="zip">Zip Code: </label>
			</td>
			<td>
				<label id="zip" name="zip" 
						required="true" size="5" 
						maxlength="5" 
						type="text"><%= customer.getZipCode() %></label>
			</td>
		</tr>
	</table>
</td>
<td valign="top">
<table style="vertical-align:top">
	<tr>
		<td width="100">
			<label>Account</label>
		</td>
		<td width="70">
			<label>Type</label>
		</td>
		<td width="100">
			<label>Balance</label>
		</td>
		<td width="50">
			<label>Frozen</label>
		</td>
	</tr>
	<%
		StringBuilder scribe = new StringBuilder();
		for (Account account : accounts) {
			scribe.append("<tr>").
			append("<td><a href=\"Transactions?accountID=").
			append(account.getAccountNumber()).append("\">").
			append(account.getAccountNumber()).append("</a></td>").
			append("<td>").append(account.getType().string).append("</td>").
			append("<td>").append(account.getBalance()).append("</td>").
			append("<td>").append(account.isFrozen()).append("</td>");
			scribe.append("</tr>");
		}
		out.write(scribe.toString());
	%>
</td>
</tr>
</table>
</td>
</tr>
</table>
<br>
<H3>Account Transactions</H3>
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
				<td width="15%">
					Reverse
				</td>
			</tr>
	<%
		scribe = new StringBuilder();
		if (null!= trans && trans.size() > 0) {
			for (Transaction tx : trans) {
				scribe.append("<tr>");
				scribe.append("<td>").append(tx.getTransID()).append("</td>");
				scribe.append("<td>").append(tx.getAccountNumber()).append("</td>");
				scribe.append("<td>").append(tx.getIssuer()).append("</td>");
				scribe.append("<td>").append(tx.getAmount()).append("</td>");
				
				if (!tx.isReversed()) {
					scribe.append("<td><input type=\"checkbox\" name=\"reversedTxID\"" 
							+ "onclick=\"submit()\"/></td>");
				}
				scribe.append("</tr>");
				out.write(scribe.toString());					
			}
		} else {
			out.write("<tr><td>No account selected. Select an account to view transactions</td></tr>");
		}
	%>
</table>

</html>