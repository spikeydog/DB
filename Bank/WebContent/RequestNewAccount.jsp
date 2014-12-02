<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>
	<jsp:include page="CustomerHeader.jsp"/>
</header>
<br>
<H2>Request New Account</H2>
<section>
	<form method="post" action="RequestNewAccount">
		<table>
			<tr>
				<td>
					<label for="type">Type: </label>
				</td>
				<td>
					<select name=type>
						<option value="checking">checking</option>
						<option value="savings">savings</option>
						<option value="credit">credit</option>
						<option value="loan">loan</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>
					<label for="description">Description:</label>
				</td>
					
				<td>
					<input id="description" name="description" type="text"/>
				</td>
			</tr>
			<tr>
				<td>
					<label for="balance">Amount to Deposit:</label>
				</td>
					
				<td>
					<input id="balance" name="balance" type="text"/>
				</td>
			</tr>
			<tr>	
				<td>
					<input id="submit" name="submit" type="submit" value="Submit"/>
				</td>
			</tr>
		</table>
	</form>
</section>
</body>
</html>