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
<%@ page import="bank.bean.Customer" %>
<% 
	Customer c = (Customer) session.getAttribute("customer"); 
 	String empty = ""; 
 	%>
<header>
	<jsp:include page="BankerHeader.jsp"/>
	<H2>Update Profile</H2>
</header>
<form name="updateForm" action="ChangePassword" submit="validate()">
<input type="hidden" name="referer" value="BankerHome"/>
	<fieldset><legend>Change Password</legend>
	<table>
		<tr>
			<td>
				<label for="password1">Choose a password</label>
			</td>
			<td>
				<input id="password1" name="password1" size="size()" maxlength="size()" type="password"/>
			</td>
		</tr>
		<tr>
			<td>
				<label for="password2">Confirm password</label>
			</td>
			<td>
				<input id="password2" name="password2" size="size()" maxlength="size()" type="password"/>
			</td>
			<td>
				<label hidden="true" id="passwordWarn" color="#FF0000">Passwords do not match!</label>
			</td>
		</tr>
		</table>
	</fieldset>
	<tr>
		<td>
		</td>
		<td>
			<input id="submit" name="submit" size="size()" value="Save" type="submit"/>
		</td>
		<td>
		</td>
		<td>
			<input id="cancel" name="cancel" size="size()" value="Cancel" type="reset"/>
		</td>
	</tr>
</form>
</body>
</html>