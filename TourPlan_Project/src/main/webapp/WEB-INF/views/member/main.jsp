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
.jumbotron {
	background-image: url("../resources/image/london.png");
	background-size: cover;
	text-shadow: black 0.2em 0.2em 0.2em;
	color: #FFFFFF;
}

.text-center {
	color: white;
}
</style>


</head>
<body>
	<c:import url="/default/mainHeader" />
	<div class="container">
		<div class="jumbotron">
			<h1 class="text-center" style="color: white;">JJa Ja</h1>
			<p class="text-center">여행자들에게 여행일정계획을 편리하게 작성할수있게 도와주는 사이트입니다.</p>
			<p class="text-center">
				<a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/plan/plan" role="button">계획짜기</a>
			</p>
		</div>
		<div class="row">
			<div class="col-md-4">
				<h4>JJa Ja(짜자)만의 특징</h4>
				<p>JJa Ja는 여행의 다양한 정보뿐만 아니라, 여행계획을 한눈에 한번에 짤수있도록 보다 효과적인 사이트입니다.</p>
				<p>
					<a class="btn btn-default" data-target="#modal" data-toggle="modal">자세히
						알아보기</a>
			</div>
			<div class="col-md-4">
				<h4>해외 여행 꿀팁</h4>
				<p>외국 화장실에 있는 이것의 정체는?!</p>
				<p>
					<a class="btn btn-default"
						href="https://m.post.naver.com/viewer/postView.nhn?volumeNo=17329347&memberNo=11036773&vType=VERTICAL">자세히
						알아보기</a>
			</div>
			<div class="col-md-4">
				<h4>한국인이 사랑하는 여행지 날씨 총정리</h4>
				<p>같은 여행지를 가도 언제 가느냐에 따라 다르다!</p>
				<p>
					<a class="btn btn-default"
						href="https://m.post.naver.com/viewer/postView.nhn?volumeNo=16866317&memberNo=522648&vType=VERTICAL">자세히
						알아보기</a>
			</div>
		</div>
		<hr>
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">
					<span class="glyphicon glyphicon-pencil"></span> &nbsp;&nbsp;최신 여행
					게시판글 목록
				</h3>
			</div>
			<div class="panel-body">
				<div class="media">
					<div class="media-left">
						<a href="#"><img class="media-object"
							src="../resources/image/image01.jpg" alt="tip 게시판"></a>
					</div>
					<div class="media-body">
						<h4 class="media-heading">
							<a href="#">홍콩/미키오4일 자유일정* 홍콩갔다왔습니다</a>&nbsp;<span class="badge">New</span>
						</h4>
						1월11일~ 15일 3박5일 봄날처럼 맑고 포근한 다낭 여행을 다녀왔습니다. 훈남의 배철수 가이드와 밝게 웃는 긍정의
						미소가 귀여운 현지 가이드 투, 재치있는 말들로 분위기를 업시켜준 천안부부팀, 조용하면서 부드러운 대구 언니와 형부,
						적은 인원이지만 모두가 적절히 조합이 잘된 팀이어서 더욱 즐거운 여행이었습니다. 여행이 멋진 풍경과 좋은 숙소, 맛있는
						음식 등도 중요하지만 무엇보다 함께 하는 사람들과의 관계가 가장 중요하다고 생각합니다. 최선을 다해준 배철수 가이드님과
						투 가이드 그리고 함께 했던 12명의 즐거움이 있었기에 푸른 하늘과 바다, 곳곳이 정원인 아름다운 다낭이 더욱 기억에
						남는 여행이 되었습니다. 기회가 된다면 좀 더 여유로운 시간을 가지고 다낭을 다시 방문하고 싶네요. ^^
					</div>
				</div>
				<hr style="border: soild 2px black;">
				<div class="media">
					<div class="media-left">
						<a href="#"><img class="media-object"
							src="../resources/image/image03.jpg" alt="tip 게시판"></a>
					</div>
					<div class="media-body">
						<h4 class="media-heading">
							<a href="#">다시가고싶은 호주...</a>&nbsp;<span class="badge">New</span>
						</h4>
						어렵게 준비한 해외여행 .. 최고의 여행이었다. 비가왔던게 살짝 아쉬웠지만! 다음에 기회가된다면 뉴질랜드도 꼭
						가볼것이다. 참 좋은 여행사를 이용해서~~~^^ 가이드님을 정말 잘 만났다. 김정호 가이드님 최고십니다. 다음에도
						뵈었음 좋겠네요...
					</div>
				</div>
				<hr style="border: soild 2px black;">
				<div class="media">
					<div class="media-left">
						<a href="#"><img class="media-object"
							src="../resources/image/image02.jpg" alt="tip 게시판"></a>
					</div>
					<div class="media-body">
						<h4 class="media-heading">
							<a href="#">미동부 캐나다여행을 다녀왔습니다~</a>&nbsp;<span class="badge">New</span>
						</h4>
						11월 7일~16일 가울 미동부와 캐나다 여행을 다녀왔습니다. 좀 늦은 가을이었지만 뉴욕에서는 늦은 단풍을 볼 수
						있었고 퀘백에서는 색다르게 눈 온 겨울 풍경을 볼 수 있어 좋았습니다. 시즌이 아니라 인원도 많지않고 한가롭고
						여유로웠으며 호텔이며 식당도 가격대비 훌륭했습니다. 특히 나이아가라에서는 호텔이 너무 좋아 숙소에서 바라본 그날 밤의
						불꽃놀이와 어우러진 폭포의 밤 야경은 그야말로 환상이었습니다. 캐나다 국경 면세점에서 가이드의 말만 믿고 산 물건이
						미국 월마트와서 보니까 터무니 없이 비싸 속이 조금 상했지만 여행에서의 작은 에피소드로 남겨두고 싶습니다. 아무튼 이번
						여행은 좋은 추억거리가 생긴 대체로 만족스런 여행이었습니다. 감사합니다~~
					</div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/default/mainBottom" />
	<div class="row">
		<div class="modal" id="modal" tabindex="-1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						JJa Ja만의 특징
						<button class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body" style="text-align: center;">
						JJa Ja만의 특징은 여행을 다녀온 사람들의 생생한 정보를<br> 다양한 게시판을 통해 여행을 준비하는
						사람들에게 정보를 제공합니다.<br> 여행계획표, Search,경비계산을 한번에 계획을 한곳에서 할수있다는
						유용한점이 있습니다.<br> <br> <img
							src="../resources/image/modal.jpg" id="imagepreview"
							style="width: 200px; height: 200px;">
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https:/ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="../resources/js/bootstrap.min.js"></script>
</body>
</html>