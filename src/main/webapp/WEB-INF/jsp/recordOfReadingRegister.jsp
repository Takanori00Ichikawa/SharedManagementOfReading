<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- ページタイトルを設定する。 --%>
<% pageContext.setAttribute("title", "本の登録", PageContext.PAGE_SCOPE); %>


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
			<div>
				<h2>
					読書する本を登録してください<span class="subtitel warning">
						(※のある箇所は必ず入力してください。)</span>
				</h2>
			</div>
			<div class="form-space">
				<form action="${ root_path }/RecordOfReadingRegister" method="post">
					<div class="form-object">
						<label for="startedDate">読書開始日<span class="warning">
								※</span></label> <input type="date" name="startedDate" id="startedDate"
							size="20"
							class="form-control <c:if test="${ errors.startedDate != null }">is-invalid</c:if>"
							value="<c:out value="${ recordOfReading.startedDate }"/>">
						<span class="text-danger">${ errors.startedDate }</span>
					</div>
					<div class="form-object">
						<label for="finishedDate">読了日</label> <input type="date"
							name="finishedDate" id="finishedDate"
							class="form-control <c:if test="${ errors.finishedDate != null }">is-invalid</c:if>"
							value="<c:out value="${ recordOfReading.finishedDate }"/>">
						<span class="text-danger">${ errors.finishedDate }</span>

					</div>
					<div class="form-object">
						<label for="bookTitle">本のタイトル<span class="warning">
								※</span></label> <input type="text" name="bookTitle" id="bookTitle" size="20"
							class="form-control <c:if test="${ errors.bookTitle != null }">is-invalid</c:if>"
							value="<c:out value="${ recordOfReading.bookTitle }"/>">
						<span class="text-danger">${ errors.bookTitle }</span>
					</div>
					<div class="form-object">
						<label for="author">本の著者</label> <input type="text" name="author"
							id="author" size=""
							class="form-control <c:if test="${ errors.author != null }">is-invalid</c:if>"
							value="<c:out value="${ recordOfReading.author }"/>"> <span
							class="text-danger">${ errors.author }</span>
					</div>
					<div class="form-object">
						<label for="dateOfIssue">本の発行年月(日)</label> <input type="text"
							name="dateOfIssue" id="dateOfIssue" size=""
							class="form-control <c:if test="${ errors.dateOfIssue != null }">is-invalid</c:if>"
							value="<c:out value="${ recordOfReading.dateOfIssue }"/>">
						<span class="text-danger">${ errors.dateOfIssue }</span>
					</div>
					<div class="form-object">
						<label for="bookmark">読み終えた箇所までのページ数<span class="warning">
								(数字で入力してください。)</span></label> <input type="text" name="bookmark" id="bookmark"
							size=""
							class="form-control <c:if test="${ errors.bookmark != null }">is-invalid</c:if>"
							value="<c:out value="${ recordOfReading.bookmark }"/>"> <span
							class="text-danger">${ errors.bookmark }</span>
					</div>
					<div class="form-object">
						<label for="totalPages">本の総ページ数<span class="warning">
								(数字で入力してください。)</span></label> <input type="text" name="totalPages"
							id="totalPages" size=""
							class="form-control <c:if test="${ errors.totalPages != null }">is-invalid</c:if>"
							value="<c:out value="${ recordOfReading.totalPages }"/>">
						<span class="text-danger">${ errors.totalPages }</span>
					</div>
					<div class="form-object">
						<label for="purpose">この本を読書する目的</label> <input type="text"
							name="purpose" id="purpose" size=""
							class="form-control <c:if test="${ errors.purpose != null }">is-invalid</c:if>"
							value="<c:out value="${ recordOfReading.purpose }"/>"> <span
							class="text-danger">${ errors.purpose }</span>
					</div>
					<div class="form-object">
						<label for="memo">この本の感想・内容等</label> <input type="textarea"
							name="memo" id="memo" size=""
							class="form-control <c:if test="${ errors.memo != null }">is-invalid</c:if>"
							value="<c:out value="${ recordOfReading.memo }"/>"> <span
							class="text-danger">${ errors.memo }</span>
					</div>
					<div class="row form-object">
						<div class="col">
							<button type="submit" class="btn btn-approval">登 録</button>
						</div>
				</form>
				<div class="col">
					<form action="${ root_path }/Main" method="get">
						<button type="submit" class="btn btn-rejection">キャンセル</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	</div>
</body>
</html>