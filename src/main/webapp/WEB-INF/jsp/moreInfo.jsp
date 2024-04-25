<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- ページのタイトルを設定する。 --%>
<% pageContext.setAttribute("title", "読書記録詳細", PageContext.PAGE_SCOPE); %>

<!DOCTYPE html>
<html lang="jp">

<head>
<%-- head部の内容を読み込み --%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<%@ include file="/WEB-INF/jsp/include/css/mainCss.jsp"%>
<%@ include file="/WEB-INF/jsp/include/css/moreInfoCss.jsp"%>
</head>

<body>
	<div class="container mt-4">

		<%-- アプリタイトル・ユーザー部分の読み込み --%>
		<%@ include file="/WEB-INF/jsp/include/appTitleAndUser.jsp"%>

		<%-- navbar --%>
		<%@ include file="/WEB-INF/jsp/include/navbar.jsp"%>

		<div
			<c:choose>
		 		<c:when test="${ recordOfReading.isFinished == 0 }">
		 			class="moreInfo reading"
		 		</c:when>
		 		<c:when test="${ recordOfReading.isFinished == 1 }">
		 			class="moreInfo is-finished"
		 		</c:when>
		 	</c:choose>>
			<div>
				<h2>読書記録詳細</h2>
			</div>
			<div class="row">
				<div class="col">
					読書開始日 :
					<fmt:formatDate var="date" value="${ recordOfReading.startedDate }"
						pattern="yyyy/MM/dd" />
					<strong><c:out value="${ date }" /></strong>
				</div>
				<div class="col">
					読了日 :
					<fmt:formatDate var="date"
						value="${ recordOfReading.finishedDate }" pattern="yyyy/MM/dd" />
					<strong><c:out value="${ date }" /></strong>
				</div>
				<div class="col"></div>
			</div>

			<div class="record-item">
				本のタイトル: <strong><c:out
						value="${ recordOfReading.bookTitle }" /></strong>
			</div>

			<div class="record-item">
				本の著者 : <strong><c:out value="${ recordOfReading.author }" /></strong>
			</div>

			<div class="record-item">
				本の発行年月(日) : <strong><c:out
						value="${ recordOfReading.dateOfIssue }" /></strong>
			</div>

			<div class="record-item">
				読み終えた箇所までのページ数本のページ数 : <strong><c:out
						value="${ recordOfReading.bookmark }" /></strong>
			</div>

			<div class="record-item">
				本の総ページ数 : <strong><c:out
						value="${ recordOfReading.totalPages }" /></strong>
			</div>

			<div class="record-item">
				この本を読書する目的 : <strong><c:out
						value="${ recordOfReading.purpose }" /></strong>
			</div>

			<div class="record-item">
				<div>この本の感想・内容等 :</div>
				<div class="memo">
					<strong><c:out value="${ recordOfReading.memo }" /></strong>
				</div>
			</div>

		</div>

		<div class="row buttons">
			<div class="col">
				<form action="${ root_path }/Main" method="get">
					<button type="submit" class="btn btn-return">一覧へ戻る</button>
				</form>
			</div>
			<div class="col">
				<form action="${ root_path }/RecordOfReadingUpdate" method="get">
					<input type="hidden" name="id"
						value="<c:out value="${ recordOfReading.id }"/>">
					<button type="submit" class="btn btn-update">更新する</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>