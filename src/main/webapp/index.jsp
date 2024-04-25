<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<%@ include file="/WEB-INF/jsp/include/css/indexCss.jsp"%>

</head>
<body>
	<div class="container">
		<div>
			<h1>読書管理アプリ</h1>
		</div>
		<div class="row">
			<div class="col-2"></div>
			<div class="col-8">
				<div class="form-style">
					<h5>アカウントをお持ちの方</h5>
					<form action="/jspSharedManagementOfReading/Login">
						<button type="submit" class="button">ログイン画面へ</button>
					</form>
				</div>
				<div class="form-style">
					<h5>アカウント登録をお願いします</h5>
					<form action="/jspSharedManagementOfReading/UserRegister">
						<button type="submit" class="button">アカウント登録画面へ</button>
					</form>
				</div>
				<p>※アカウントをお持ちの方のみご利用出来ます</p>
			</div>
			<div class="col-2"></div>
		</div>
	</div>
</body>
</html>