<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<%@ include file="/WEB-INF/jsp/include/css/userRegisterCss.jsp"%>
</head>
<body>

	<div class="container container-user">
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
				<div class="please">
					<h2>ログインをお願いします</h2>
				</div>
				<c:if test="${ error != null }">
					<div class="alert alert-danger" role="alert">${ error }</div>
				</c:if>
				<c:if test="${ success != null }">
					<div class="alert alert-success" role="alert">${ success }</div>
				</c:if>
				<c:if test="${ userRegisterSuccess != null }">
					<div class="alert alert-success" role="alert">${ userRegisterSuccess  }</div>
				</c:if>
				<div>
					<form action="${ root_path }/Login" method="post">
						<div class="form-object">
							<div>
								<label for="email">E-mailアドレス</label>
							</div>
							<div>
								<input type="email" name="email" id="email" size="60"
									value="<c:out value="${ user.email }"/>">
							</div>
						</div>
						<div class="form-object">
							<div>
								<label for="password">パスワード</label>
							</div>
							<div>
								<input type="password" name="password" id="password" size="40"
									value="<c:out value="${ user.password }"/>">
							</div>
						</div>

						<div class="form-object">
							<div>
								<button type="submit" class="button">ログイン</button>
							</div>
						</div>
					</form>
				</div>

			</div>
			<div class="col-1"></div>
		</div>
	</div>

</body>
</html>