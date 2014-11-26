<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<H1>Credit Dauphine</H1>
<table style="width:100%">
	<tr>
		<td class="login">
			You are logged in as ${username}
		</td>
		<td>
			<a href="Transfer.jsp">Transfer Funds</a>
		</td >
		<td>
			<a href="CloseAccount.jsp">Close an Account</a>
		</td>
		<td>
			<a href="ViewProfile.jsp">My Profile</a>
		</td>
		<td>
			<a href="Logout">Logout</a>
		</td>
	</tr>
</table>
</body>
</html>