<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Credit Dauphine</title>
<script>
	function size() {
		return 25;
	}
</script>

</head>
<body>

<header>
<%@ page import="bank.bean.Customer" %>
<% Customer customer = (Customer) session.getAttribute("customer"); %>
<jsp:include page="CustomerHeader.jsp"/>
	<H2>Customer Profile</H2>
	
</header>
<form name="viewForm" action="UpdateProfile.jsp" submit="validate()">
	
	<fieldset><legend>Update Contact Information</legend>
	
	<table>
		<tr>
				<td>
					<label for="email">E-mail Address: </label>
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
			</table>
	</fieldset>
	<br>
	<br>
	<fieldset><legend>Update Billing Address</legend>
	<table>
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
				<label for="city">City</label>
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
</body>
</html>