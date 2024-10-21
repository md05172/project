<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resources/css/index.css">
<script type="text/javascript" src="/resources/js/index.js"></script>
<title></title>
</head>
<body>
	<div class="container">
		<form method="post" enctype="multipart/form-data">
			<div class="imgContainer">
				<label for="img" class="file-label">사진 업로드</label>
				<div class="file-preview">
					<img id="file-preview" src="${pageContext.request.contextPath }/upload/default-image.png" alt="이미지 미리보기">
				</div>
				<input type="file" id="img" class="file-input" name="uploadFile">
			</div>
			
			<div class="dataContainer">
				<p class="data">제목</p>
				<input type="text" name="name">
			</div>
			<div class="dataContainer">
				<p class="data">출판사</p>
				<input type="text" name="publisher">
			</div>
			<div class="dataContainer">
				<p class="data">저자</p>
				<input type="text" name="writer">
			</div>
			<div class="dataContainer">
				<p class="data">가격</p>
				<input type="number" name="price">
			</div>
			<div class="dataContainer">
				<p class="data">저자 소개</p>
				<textarea name="writerinfo" rows="" cols=""></textarea>
			</div>
			<div class="dataContainer">
				<p class="data">책 소개</p>
				<textarea name="info" rows="" cols=""></textarea>
			</div>
			<div class="button">
				<div>
					<button>등록</button>
				</div>
				<div>
					<button>취소</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>