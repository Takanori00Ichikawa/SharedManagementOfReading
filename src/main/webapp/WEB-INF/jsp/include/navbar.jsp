<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- navbar -->
<div class="navbar">
	<nav>
		<div class="row">

			<div class="col-8">
				<ul>
					<li><a href="${ root_path }/Main">HOME</a></li>
					<li><a href="${ root_path }/RecordOfReadingRegister">読書する本の登録画面へ</a>
					</li>
					<li><a href="${ root_path }/UserUpdate">アカウント更新画面へ</a></li>
					<li><a href="${ root_path }/Logout">ログアウト</a></li>
				</ul>
			</div>

			<div class="col-4">
				<div class="form-search">
					<form action="${ root_path }/Main" method="get">
						<div class="search">
							<input class="search-object search-text" type="search" size="34"
								placeholder="本のタイトル or 著者" name="key" id="key"
								value="<c:out value="${ key }" />"> <input
								class="search-object search-btn" type="submit" value="検索">
						</div>
					</form>
				</div>
			</div>

		</div>
	</nav>
</div>