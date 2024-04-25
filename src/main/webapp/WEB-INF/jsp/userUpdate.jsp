<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- ページのタイトルを設定する。 --%>
<%
pageContext.setAttribute("title", "アカウント情報の更新", PageContext.PAGE_SCOPE);
%>

<!DOCTYPE html>
<html lang="jp">

<head>
<%-- head部の内容を読み込み --%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<%@ include file="/WEB-INF/jsp/include/css/mainCss.jsp"%>
<%-- アカウント登録画面用のCSS --%>
<%@ include file="/WEB-INF/jsp/include/css/userRegisterCss.jsp"%>
</head>

<body>
	<div class="container container-user">
		<div class="row">
			<div class="col-1"></div>
			<div class="col-10">
				<div class="please">
					<h2>アカウント情報を更新出来ます</h2>
					<p>(※全ての項目を入力してください)</p>
				</div>
				<c:if test="${ db_error != null }">
					<div class="alert alert-danger" role="alert">${ db_error }</div>
				</c:if>
				<div>
					<form action="${ root_path }/UserUpdate" method="post">
						<div class="form-object">
							<label for="email">E-mailアドレス</label> <input type="text"
								name="email" id="email" size="60"
								class="form-control <c:if test="${ errors.email != null }">is-invalid</c:if>"
								value="<c:out value="${ user.email }"/>">
							<c:if test="${ errors.email != null }">
								<span class="text-danger">${ errors.email }</span>
							</c:if>
						</div>
						<div class="form-object">
							<label for="password">パスワード<span>
									※英数大文字小文字を取り混ぜて8～20文字で入力してください</span></label> <input type="password"
								name="password" id="password" size="40"
								class="form-control <c:if test="${ errorors.password || errors.passwordConf != null }">is-invalid</c:if>"
								value="<c:out value="${ user.password }"/>">
							<c:if test="${ errors.password != null }">
								<span class="text-danger">${ errors.password }</span>
							</c:if>
							<br>
							<c:if test="${ errors.passwordConf != null }">
								<span class="text-danger">${ errors.passwordConf }</span>
							</c:if>
						</div>
						<div class="form-object">
							<label for="passwordForConfirmation">パスワード（確認用）<span>※英数大文字小文字を取り混ぜて8～20文字で入力してください</span></label>
							<input type="password" name="passwordForConfirmation"
								id="passwordForConfirmation" size="40"
								class="form-control <c:if test="${ errors.passwordConf != null }">is-invalid</c:if>"
								value="<c:out value="${ user.password }"/>">
							<c:if test="${ errors.passwordConf != null }">
								<span class="text-danger">${ errors.passwordConf }</span>
							</c:if>
						</div>
						<div class="form-object">
							<label for="name">ニックネーム<span> ※50文字以内で入力してください</span></label> <input
								type="text" name="name" id="name" size="60"
								class="form-control <c:if test="${ errors.name != null }">is-invalid</c:if>"
								value="<c:out value="${ user.name }"/>">
							<c:if test="${ errors.name != null }">
								<span class="text-danger">${ errors.name }</span>
							</c:if>
						</div>
						<div class="row">
							<div class="col form-object">
								<div>
									<button type="submit" class="button">更新</button>
								</div>
							</div>
					</form>
					<div class="col form-object">
						<form action="${ root_path }/Main" method="get">
							<div>
								<button type="submit" class="button button-rejection">キャンセル</button>
							</div>
						</form>
					</div>
				</div>
			</div>

		</div>
		<div class="col-1"></div>
	</div>
	</div>
</body>
</html>