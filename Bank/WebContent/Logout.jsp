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
<H2>Secure Logout</H2>
<br>
You have been logged out of your account.
<br><br><br>
<a href="http://localhost:8080/Banking_App/Login.jsp?">Return to the login portal</a>

</body>
</html>