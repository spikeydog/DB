<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Credit Dauphine Login Portal</title>
<script>
	function employeeLogin(isEmployee) {
		var entry = document.getElementById("employeeID");
		entry.hidden = !isEmployee;
		entry.value = isEmployee? entry.value : '';
		document.getElementById("labelEmployeeID").hidden = !isEmployee;
	}
	
	function size() {
		return 25;
	}
	
	function validate() {
		var isEmployee = document.getElementById("isEmployee").checked;
		var employeeID = document.getElementById("employeeID").value;
		var action = document.getElementById("loginForm").action;
		var returnVal = false;
		
		if (isEmployee && (employeeID.length > 1)) {
			returnVal = true;
		} else if (!isEmployee && employeeID.length == 0) {
			returnVal = true;
		}
		
		return returnVal;
	}
</script>
</head>
<body>
<H1>Credit Dauphine</H1>
<H2>User Login</H2>
<form id="loginForm" method="post" action="Login" onsubmit="return validate()">
	Enter your name and password to login <br>
	<table>
	<tr>
	<td width="300">
		<table cellpadding="5pt" frame="box">
			<tr>
				<td>
					<label id="labelEmployeeID" hidden="true" for="employeeID">Employee ID</label>
				</td>
				<td>
					<input hidden="true" type="text" id="employeeID" name = "employeeID" maxlength="size()" size="size()"/> <br>
				</td>
			</tr>
			<tr>
				<td>
					<label for="username">Username</label>
				</td>
				<td>
					<input type="text" id="username" name="username" value="" maxlength="size()" required="true" size="size()"/><br>
				</td>
				
			</tr>
			<tr>
				<td>
					<label for="password">Password</label>
				</td>
				<td>
					<input type="password" id="password" name="password" maxlength="size()" required="true" size="size()"/><br>
				</td>
			</tr>
			<tr>
				<td>
					<label for="submit"></label>
				</td>
				<td>
					<input id="submit" type="submit" value="Login" /> <br>
				</td>
			</tr>
			<tr>
				<td>
					<label for="isEmployee">Login as employee</label>
				</td>
				<td>
					<input id="isEmployee" type="checkbox" value="true" onchange="employeeLogin(this.checked)"/> <br>
				</td>
			</tr>
		</table>
	</td>
	
	<td>
		<table>
			<tr>
				<td width="150" align="center">
					<label hidden="true">FOO</label>
				</td>
				<td>
					<a href="RegisterCustomer.jsp?">Register a new Credit Dauphine customer account</a>
				</td>
			<tr>
				<td width="150" align="center">OR</td>
			</tr>
			<tr>
				<td width="150" align="center">
				<td>
					<a href="RegisterEmployee.jsp?">Register a new Credit Dauphine employee account</a>
				</td>
			</tr>
		</table>
	</td>
	</tr>
	</table>
</form>

</body>
</html>