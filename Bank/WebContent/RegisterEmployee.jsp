<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function size() {
		return 25;
	}
	
	function validate() {
		var p1 = document.getElementById("password1").value;
		var p2 = document.getElementById("password2").value;
		var warn = document.getElementById("passwordWarn");
		var returnVal = false;
		
		if (p1 == p2) {
			returnVal = true;
			warn.hidden = true;
		} else {
			warn.hidden = false;
		}
		
		return returnVal;
	}
</script>
</head>
<body>
<H1>Credit Dauphine</H1>
<H2>New Employee Registration</H2>
<form name="registrationForm" action="Register" onsubmit="return validate()">
	
		<fieldset width="150">
		<legend>User Account Credentials</legend>
		<table>
		<tr>
			<td>
				<label for="username">Choose a username</label>
			</td>
			<td>
				<input id="username" name="username" required="true" size="size()" maxlength="size()" type="text"/>
			</td>
		</tr>
		<tr>
			<td>
				<label for="password1">Choose a password</label>
			</td>
			<td>
				<input id="password1" name="password1" required="true" size="size()" maxlength="size()" type="password"/>
			</td>
		</tr>
		<tr>
			<td>
				<label for="password2">Confirm password</label>
			</td>
			<td>
				<input id="password2" name="password2" required="true" size="size()" maxlength="size()" type="password"/>
			</td>
			<td>
				<label hidden="true" id="passwordWarn" color="#FF0000">Passwords do not match!</label>
			</td>
		</tr>
		</table>
		</fieldset>
		<br>
		<fieldset>
		<legend>Identifying Information</legend>
		<table>
		<tr>
			<td>
				<label for="firstName">First Name</label>
			</td>
			<td>
				<input id="firstName" name="firstName" required="true" size="size()" maxlength="size()" type="text"/>
			</td>
		</tr>
		<tr>
			<td>
				<label for="middleName">Middle Name</label>
			</td>
			<td>
				<input id="middleName" name="middleName" size="size()*2" maxlength="size()*2" type="text"/>
			</td>
		</tr>
		<tr>
			<td>
				<label for="lastName">Last Name</label>
			</td>
			<td>
				<input id="lastName" name="lastName" required="true" size="return size() * 2" maxlength="size()*2" type="text"/>
			</td>
		</tr>
		
		<tr>
			<td>
				<label for="employeeID">Employee ID</label>
			</td>
			<td>
				<input id="employeeID" name="employeeID" required="true" size="size()" maxlength="size()" type="password"/>
			</td>
		</tr>
		</table>
		</fieldset>
		<br>
		
		<tr>
			<td>
			</td>
			<td>
				<input id="submit" name="submit" size="size()" value="Register!" type="submit"/>
			</td>
		</tr>
	</table>
	
</form>
</body>
</html>