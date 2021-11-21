<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>

<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>

	<div class="header">
		<jsp:include page="header.jsp"></jsp:include>
	</div>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${user != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${user == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${user != null}">
                                    Edit User
                                </c:if>
						<c:if test="${user == null}">
                                    Add New User
                                </c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					<input type="hidden" name="id" value="<c:out value='${user.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>User username</label> <input type="text"
						value="<c:out value='${user.username}' />" class="form-control"
						name="username" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>User password</label> <input type="text"
						value="<c:out value='${user.password}' />" class="form-control"
						name="password">
				</fieldset>

				<fieldset class="form-group">
					<label>User fullname</label> <input type="text"
						value="<c:out value='${user.User.fullname}' />" class="form-control"
						name="User fullname">
				</fieldset>
				
				<fieldset class="form-group">
					<label>User role</label> <input type="text"
						value="<c:out value='${user.User.role}' />" class="form-control"
						name="User role">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>

</html>