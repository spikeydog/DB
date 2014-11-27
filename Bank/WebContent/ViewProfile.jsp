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

<header>
<jsp:include page="CustomerHeader.jsp"/>
	<H2>Customer Profile</H2>
	<%= session.getAttribute("customer").toString() %>
</header>
<form name="viewForm" action="UpdateProfile.jsp" submit="validate()">
	
	<fieldset><legend>Update Contact Information</legend>
	
	<table>
		<tr>
				<td>
					<label for="email">E-mail Address</label>
				</td>
				<td>
					<label id="email" name="email" required="true" size="size()*2" maxlength="size()*2" type="email">Stuff</label>
				</td>
			</tr>
			<tr>
				<td>
					<label for="phone">Telephone Number</label>
				</td>
				<td>
					<label id="phone" name="phone" required="true" type="tel"/>Stuff</label>
				</td>
			</tr>
			</table>
	</fieldset>
	<fieldset><legend>Update Billing Address</legend>
	<table>
		<tr>
			<td>
				<label for="address1">Address Line 1</label>
			</td>
			<td>
				<label id="address1" name="address1" required="true" size="size()*2" maxlength="size()*2" type="text"/>Stuff</label>
			</td>
		</tr>
		<tr>
			<td>
				<label for="address2">Address Line 2</label>
			</td>
			<td>
				<label id="address2" name="address2" size="size()*2" maxlength="size()*2" type="text">Stuff</label>
			</td>
		</tr>
		<tr>
			<td>
				<label for="city">City</label>
			</td>
			<td>
				<label id="city" name="city" required="true" size="size()*2" maxlength="size()*2" type="text">Stuff</label>
			</td>
		</tr>
		<tr>
			<td>
				<label for="state">State Code</label>
			</td>
			<td>
				<label id="state" name="state" required="true" size="2" maxlength="2" type="text">Stuff</label>
			</td>
		</tr>
		<tr>
			<td>
				<label for="zip">Zip Code</label>
			</td>
			<td>
				<label id="zip" name="zip" required="true" size="5" maxlength="5" type="text">Stuff</label>
			</td>
		</tr>
		</table>
	</fieldset>
	<tr>
		<td>
		</td>
		<td>
		</td>
		<td>
			<input id="udpate" name="update" size="size()" value="Update" type="submit"/>
		</td>
	</tr>
</form>
<p>This page allows a customer to update various pieces of customer information.
</body>
</html>