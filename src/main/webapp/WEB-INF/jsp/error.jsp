<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- ページタイトルを設定する。 --%>
<% pageContext.setAttribute("title", "エラー", PageContext.PAGE_SCOPE); %>


<!DOCTYPE html>
<html lang="jp">
<head>
<%-- head部の内容を読み込み --%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<%@ include
	file="/WEB-INF/jsp/include/css/recordOfReadingRegisterCss.jsp"%>
</head>

<body>
	<div class="container my-4">

		<%-- アプリタイトル・ユーザー部分の読み込み --%>
		<%@ include file="/WEB-INF/jsp/include/appTitleAndUser.jsp"%>

		<%-- navbar --%>
		<%@ include file="/WEB-INF/jsp/include/navbar.jsp"%>

		<div class="m-4">
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6">
					<div class="alert alert-danger" role="alert">エラーが発生しました。</div>
				</div>
				<div class="col-md-3"></div>
			</div>
		</div>

	</div>
</body>
</html>