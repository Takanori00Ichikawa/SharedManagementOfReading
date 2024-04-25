<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- ページのタイトルを設定する。 --%>
<% pageContext.setAttribute("title", "登録した本の一覧", PageContext.PAGE_SCOPE); %>

<!DOCTYPE html>
<html lang="jp">

<head>
<%-- head部の内容を読み込み --%>
<%@ include file="/WEB-INF/jsp/include/head.jsp"%>
<%@ include file="/WEB-INF/jsp/include/css/mainCss.jsp"%>
</head>

<body>
	<div class="container mt-4">

		<%-- アプリタイトル・ユーザー部分の読み込み --%>
		<%@ include file="/WEB-INF/jsp/include/appTitleAndUser.jsp"%>

		<%-- navbar --%>
		<%@ include file="/WEB-INF/jsp/include/navbar.jsp"%>

		<div class="counters">
			<div class="counter">
				読書中の本の冊数は<strong>${ countReading }</strong>冊です。
			</div>
			<div class="counter">
				読了した本の冊数は<strong>${ countIsFinished }</strong>冊です。
			</div>
			<div class="counter">
				登録した本の冊数は<strong>${ countTotal }</strong>冊です。
			</div>
		</div>

		<div class="reading-table">
			<h2>読書中の本の一覧</h2>
			<div class="table">
				<table class="table table-hover reading">
					<thead>
						<tr class="row">
							<th class="col-2">読書開始日</th>
							<th class="col-4">本のタイトル</th>
							<th class="col-4">著者</th>
							<th class="col-2"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="record" items="${ recordsReading }">
							<tr class="row">
								<fmt:formatDate var="date" value="${ record.startedDate }"
									pattern="yyyy/MM/dd" />
								<td class="col-2"><c:out value="${ date }" /></td>
								<td class="col-4"><a
									href="${ root_path }/MoreInfo?id=${ record.id }"><c:out
											value="${ record.bookTitle }" /></a></td>
								<td class="col-4"><c:out value="${ record.author }" /></td>
								<td class="col-2">
									<div class="row form-items">
										<div class="col-4">
											<form action="${ root_path }/RecordOfReadingUpdateIsFinished"
												method="post">
												<input type="hidden" name="id"
													value="<c:out value="${ record.id }"/>"> <input
													type="submit" value="読了" class="book-btn finished">
											</form>
										</div>
										<div class="col-4">
											<form action="${ root_path }/RecordOfReadingUpdate"
												method="get">
												<input type="hidden" name="id"
													value="<c:out value="${ record.id }"/>"> <input
													type="submit" value="更新" class="book-btn update">
											</form>
										</div>
										<div class="col-4">
											<form action="${ root_path }/RecordOfReadingUpdateIsDeleted"
												method="post">
												<input type="hidden" name="id"
													value="<c:out value="${ record.id }"/>"> <input
													type="submit" value="削除" class="book-btn delete">
											</form>
										</div>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="finished-table">
			<h2>読了本の一覧</h2>
			<div class="table">
				<table class="table table-hover is-finished">
					<thead>
						<tr class="row">
							<th class="col-2">読了日</th>
							<th class="col-4">本のタイトル</th>
							<th class="col-4">著者</th>
							<th class="col-2"></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="record" items="${ recordsFinishedReading }">
							<tr class="row">
								<fmt:formatDate var="date" value="${ record.finishedDate }"
									pattern="yyyy/MM/dd" />
								<td class="col-2"><c:out value="${ date }" /></td>
								<td class="col-4"><a
									href="${ root_path }/MoreInfo?id=${ record.id }"><c:out
											value="${ record.bookTitle }" /></a></td>
								<td class="col-4"><c:out value="${ record.author }" /></td>
								<td class="col-2">
									<div class="row form-items">
										<div class="col-4"></div>
										<div class="col-4">
											<form action="${ root_path }/RecordOfReadingUpdate"
												method="get">
												<input type="hidden" name="id"
													value="<c:out value="${ record.id }"/>"> <input
													type="submit" value="更新" class="book-btn update">
											</form>
										</div>
										<div class="col-4">
											<form action="${ root_path }/RecordOfReadingUpdateIsDeleted"
												method="post">
												<input type="hidden" name="id"
													value="<c:out value="${ record.id }"/>"> <input
													type="submit" value="削除" class="book-btn delete">
											</form>
										</div>
									</div>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</div>

</body>
</html>