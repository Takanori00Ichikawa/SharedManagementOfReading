<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="row tops">
	<div class="col app-title">
		<a href="${ root_path }/Main"><div class="">
				<h1>読書管理アプリ</h1>
			</div></a>
	</div>
	<div class="col user-name">
		<p>
			ようこそ<strong><c:out value="${ user.name }" /></strong>さん
		</p>
	</div>
</div>