<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>JJa Ja</title>
<link href="../resources/css/bootstrap.min.css" rel="stylesheet">
<link href="../resources/css/codingBooster.css" rel="stylesheet">
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<style type="text/css">
blockquote {
	background: #f9f9f9;
	border-left: 10px solid #cccccc;
	margin: 1.5em 10px;
	padding: 0.5em 10px;
	quotes: "\201C" "\201D" "\2018" "\2019";
}

blockquote:before {
	color: #cccccc;
	content: open-quote;
	font-size: 3em;
	line-height: 0.1em;
	margin-left: 0.25em;
	vertical-align: -0.3em;
}

blockquote:after {
	color: #cccccc;
	content: close-quote;
	font-size: 3em;
	line-height: 0.1em;
	margin-left: 0.25em;
	vertical-align: -0.3em;
}
</style>
</head>
<body>

	<c:import url="/default/mainHeader"/>

	<!-- Contact -->
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span class="glyphicon glyphicon-tags"></span> &nbsp;&nbsp;조성호
						</h3>
					</div>
					<div class="panel-body">
						<div class="media">
							<div class="media-left">
								<a href="#"> <img class="media-object"
									src="../resources/image/logo.jpg" alt="JJa Ja이미지">
								</a>
							</div>
							<div class="media-body">
								<h4 class="media-heading">JJa Ja</h4>
								<hr>
								여행을 계획하는 사람들에게 보다 편리하게 계획할수있게 도와주는 사이트입니다.
							</div>
						</div>
					</div>
					<table class="table">
						<thead>
							<tr>
								<th>번호</th>
								<th>개발자명</th>
								<th>역할</th>
								<th>소속</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>고영찬</td>
								<td>팀장</td>
								<td>KG아이티뱅크</td>
							</tr>
							<tr>
								<td>2</td>
								<td>조성호</td>
								<td>팀원</td>
								<td>KG아이티뱅크</td>
							</tr>
							<tr>
								<td>3</td>
								<td>류세정</td>
								<td>팀원</td>
								<td>KG아이티뱅크</td>
							</tr>
							<tr>
								<td>4</td>
								<td>백현정</td>
								<td>팀원</td>
								<td>KG아이티뱅크</td>
							</tr>
							<tr>
								<td>5</td>
								<td>주영하</td>
								<td>팀원</td>
								<td>KG아이티뱅크</td>
							</tr>
						</tbody>
					</table>
					<div class="panel-footer">
						<blockquote>&nbsp;세계는 한권의 책이다. 여행하지 않는 자는 그 책의 단지 한
							페이지만 읽을 뿐이다.&nbsp;</blockquote>
					</div>
				</div>
			</div>
		</div>
	</div>



	<c:import url="/default/mainBottom"/>


	<script
		src="https:/ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>