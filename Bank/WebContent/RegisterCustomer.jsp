<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Credit Dauphine</title>
<script>
	function validateForm() {
		var p1 = document.getElementById("password1").value;
		var p2 = document.getElementById("password2").value;
		var warn = document.getElementById("passwordWarn");
		var returnVal = false;
		
		if (p1 == p2) {
			returnVal = true;
			warn.value = '';
		} else {
			warn.value = 'Passwords do not match!';
		}
		
		return returnVal;
	}
	
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
<H2>New Customer Registration</H2>
<br>
	<%= session.getAttribute("message")%>
<br>
<form name="registrationForm" method="post" action="RegisterCustomer" onsubmit="return validate()">
	<fieldset> <legend>Account Credentials</legend>
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
	<fieldset> <legend>Identity Information</legend>
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
					<label for="lastName">Last Name</label>
				</td>
				<td>
					<input id="lastName" name="lastName" required="true" size="return size() * 2" maxlength="size()*2" type="text"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="ssn">Social Security Number</label>
				</td>
				<td>
					<input id="ssn" name="ssn" required="true" size="size()/2" maxlength="size()/2" type="password"/>
				</td>
			</tr>
		</table>
	</fieldset>
	<br>
	<fieldset> <legend>Contact Information</legend>
		<table>
			<tr>
				<td>
					<label for="email">E-mail Address</label>
				</td>
				<td>
					<input id="email" name="email" required="true" size="size()*2" maxlength="size()*2" type="email"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="phone">Telephone Number</label>
				</td>
				<td>
					<input id="phone" name="phone" required="true" type="tel"/>
				</td>
			</tr>
			
		</table>
	</fieldset>
	<br>
	<fieldset> <legend>Billing Address</legend>
		<table>
			<tr>
				<td>
					<label for="address1">Address Line 1</label>
				</td>
				<td>
					<input id="address1" name="address1" required="true" size="size()*2" maxlength="size()*2" type="text"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="address2">Address Line 2</label>
				</td>
				<td>
					<input id="address2" name="address2" size="size()*2" maxlength="size()*2" type="text"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="city">City</label>
				</td>
				<td>
					<input id="city" name="city" required="true" size="size()*2" maxlength="size()*2" type="text"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="state">State Code</label>
				</td>
				<td>
					<input id="state" name="state" required="true" size="2" maxlength="2" type="text"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="zip">Zip Code</label>
				</td>
				<td>
					<input id="zip" name="zip" required="true" size="5" maxlength="5" type="text"/>
				</td>
			</tr>
			
		</table>
	</fieldset>
	<tr>
		<td>
		</td>
		<td>
			<input id="submit" name="submit" size="size()" value="Register!" type="submit"/>
		</td>
	</tr>
</form>
</body>
</html>