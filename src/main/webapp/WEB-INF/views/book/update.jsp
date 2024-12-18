<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1" />
<jsp:include page="../deco.jsp"></jsp:include>
<link rel="stylesheet" href="/resources/css/book/add.css">
<script type="text/javascript" src="/resources/js/add.js"></script>
<title></title>
</head>
<body>
	<div class="container">
		<form method="post" enctype="multipart/form-data">
			<div class="inner_container">
				<div class="imgContainer">
					<label>사진</label>
					<div class="file-preview">
						<label for="img" class="file-label">
							<img id="file-preview" src="/upload/book/${item.path }" alt="도서 사진">
						</label>
					</div>
					<input type="file" id="img" class="file-input" name="uploadFile">
				</div>
				<div class="book_info_box">
					<div class="dataContainer">
						<select name="category">
							<option value="ko" ${item.category == "ko" ? 'selected' : '' }>국내</option>
							<option value="nko" ${item.category == "nko" ? 'selected' : '' }>외국</option>
						</select>
					</div>
					<div class="dataContainer">
						<p class="data">제목</p>
						<input type="text" name="name" value="${item.name }">
					</div>
					<div class="dataContainer">
						<p class="data">출판사</p>
						<input type="text" name="publisher" value="${item.publisher }">
					</div>
					<div class="dataContainer">
						<p class="data">저자</p>
						<input type="text" name="writer" value="${item.writer }">
					</div>
					<div class="dataContainer">
						<p class="data">가격</p>
						<input type="number" name="price" value="${item.price }">
					</div>
					<div class="dataContainer">
						<p class="data">저자 소개</p>
						<textarea name="writerInfo" rows="" cols="">${item.writerInfo }</textarea>
					</div>
					<div class="dataContainer">
						<p class="data">책 소개</p>
						<textarea name="info" rows="" cols="">${item.info }</textarea>
					</div>
					<div class="button">
						<div>
							<button class="update">수정</button>
						</div>
						<div>
							<a href="../"><button>취소</button></a>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>