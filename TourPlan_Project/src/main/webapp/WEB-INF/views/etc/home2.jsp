<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- C로 지정하고 사용한다. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 게시판</title>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

	<style type="text/css">
		.banner {
   		background-image: url('https://image.utoimage.com/preview/cp780416/2016/05/201605010316_500.jpg');
   		-webkit-background-size: cover;
    	-moz-background-size: cover;
    	-o-background-size: cover;
    	background-size: cover;
    	font-family: 돋움;
    	border-color: balck;
    	border-radius:10px;
      }
  	 .chate_box_mine{
   	   	float: right;
      	padding: 5px;
      	width: 200px;
      	background-color: #0064CD;
      	border-radius: 10px;
      	color: white;
      	margin-bottom: 5px;
      	margin-top: 5px;
      }
      
      .chate_box_other{
      	float: left;
     	padding: 5px;
      	width: 200px;
      	background-color: #ECEBFF;
      	border-radius: 10px;
      	color: #0064CD;
      	margin-bottom: 5px;
      	margin-top: 5px;
      }
      
      .chat_box_title{
		padding: 5px;
      	background-color: #030066;
      	color: white;
      	font-family: 휴먼모음T;
      	font-size: 13pt;
      	text-align: center;
		}  
		
		.chat_text{
			padding: 5px;
      		background-color: #030066;
      		color: white;
      		border-radius: 5px;
      		font-family: 휴먼모음T;
      		border: none;
    		text-decoration: none;
   			display: inline-block;
    		cursor: pointer;
		}
		.chate_button{
			background-color: white;
			color: black;
			text-align: center;
    		text-decoration: none;
   			display: inline-block;
   			border-radius: 5px;
    		font-family: 휴먼모음T;
    		cursor: pointer;
		} 
	</style>

</head>
<body>
<header>
	<c:import url="etc/header.jsp"/>
</header>
<form id="submit_chat" name="submit_chat">
<div align="center">
	<div class="banner" align="center" style="width: 800px; height: 200px;"></div><br><br>
	<div align="center">
		<table border="0" style="border-spacing: 10px;">
			<tr style="height: 150px;">
				<td rowspan="2" style="border-radius: 5px; width: 500px;">
					<c:import url="best_Tip_board.jsp"/>
				</td>
				<td style="border-radius: 5px; width: 300px;">
					<iframe width="300" height="150" src="https://www.youtube.com/embed/xRbPAVnqtcs" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
				</td>
			</tr>
			<tr style="height: 150px;">
				<td rowspan="3" style="border-radius: 5px; width: 300px;">
					<div style="width: 300px; height: 450px;">
						<div class="chat_box_title" style="height: 30px;">Chat</div>
						<!-- 채팅이 나타나는 부분 -->
							<div id="hi" style="background-color: white; height: 396px; overflow:auto;"></div>
							<!-- 전송버튼과 입력칸이 있는 곳 -->
						<div style="background-color: #030066;">
							<input class="chat_text" type="text" name="input_chat_box" id ="input_chat_box" style="width: 250px;">
					 		<input class="chate_button" type="button" value="전송" name="sendChatButton" id="sendChatButton" onclick="SendChat()"> 
						</div>
					</div>
				</td>
			</tr>
			<tr style="height: 150px;">
				<td rowspan="2" style="border-radius: 5px; width: 500px;">5
				</td>
			</tr>
			<tr style="height: 150px;">
			<% 
				HttpSession Session = request.getSession(true);
			
				if(Session.getAttribute("lastRefreshTime") == null){
					Session.setAttribute("lastRefreshTime", 0);
				}
				else{
					Session.setAttribute("lastRefreshTime", System.currentTimeMillis());
				}
				
				System.out.println("session : " + Session.getAttribute("lastRefreshTime"));
			%>
			</tr>
		</table>
	</div>
</div>
</form>
<br><br>
<footer>
	<c:import url="etc/footer.jsp"/>
</footer>
<script>//채팅 관련 스크립트
	//home 실행되면 자동으로 적용됨
	$(document).ready(function(){
		$.ajax({
			url:'/controller/getHistoryFirst',
			dataType: 'jsonp',
			
			success: function(input){
				$("#input_chat_box").focus();
				console.log(input);
				console.log(input.chat);
				for(let i=0;i<(input.chat).length;i++){
					//let chat = (input.chat)[i].userNickname+"<br>"+(input.chat)[i].content+"<br>"+(input.chat)[i].str_time+"<br>";
					//console.log(chat);
					
					if((input.chat)[i].userId == input.userId){						
						 var name = $('<div align=left style="font-weight:bold; font-size:10pt; text-align:cneter;"></div>').append("나 (" + (input.chat)[i].userNickname + ")");
						 var date = $('<font style="font-size:8pt; text-align:cneter;" align=center></font>').append((input.chat)[i].str_time);
						 
						 $('<div class="chate_box_mine"></div>').append(name,"<br>"+(input.chat)[i].content+"<br><br>",date,"<br>").appendTo('#hi');
						//$('<div class="chate_box_mine"></div>').append(chat).appendTo('#hi');//값에 추가도 되는거 같군
					}
					else if((input.chat)[i].userId != input.userId){
						var name = $('<div align=right style="font-weight:bold; font-size:10pt; text-align:cneter;"></div>').append((input.chat)[i].userNickname+"<br>");
						 var date = $('<font style="font-size:8pt; text-align:cneter" align=center></font>').append((input.chat)[i].str_time);
						 
						 $('<div class="chate_box_other"></div>').append(name,"<br>"+(input.chat)[i].content+"<br><br>",date,"<br>").appendTo('#hi');
						//$('<div class="chate_box_other"></div>').append(chat).appendTo('#hi');//값에 추가도 되는거 같군
					}
				}
				$("#hi").scrollTop($("#hi")[0].scrollHeight);//스크롤바의 스크롤을 가장 아래로 내려주는 기능
			}
		});
	});		


	//글 적어서 글 전송하는 기능 + 현재까지 추가내용 업데이트.
	function SendChat(){
		var params = jQuery("#input_chat_box").serialize();//폼 안에 들어있는 모든 element의 내용을 serialize함(내용은 정상동작)
		var value = $("#input_chat_box").val();		
		
		if(value == null || value==''){//입력칸이 비었을 경우
			alert("내용을 입력해주세요.");
		}
		else{//정상동작			
			$.ajax({
				url:'/controller/sendTheChat',
				data: params,
				dataType: 'jsonp',
				
				success: function(input){
					$("#input_chat_box").focus();
					console.log(input);
					console.log(input.chat);
					for(let i=0;i<(input.chat).length;i++){
						let chat = (input.chat)[i].userNickname+"<br>"+(input.chat)[i].content+"<br>"+(input.chat)[i].str_time+"<br>";
						console.log(chat);
						
						if((input.chat)[i].userId == input.userId){
							 var name = $('<div align=left style="font-weight:bold; font-size:10pt; text-align:cneter;"></div>').append("나 (" + (input.chat)[i].userNickname + ")");
							 var date = $('<font style="font-size:8pt; text-align:cneter" align=center></font>').append((input.chat)[i].str_time);
							 
							 $('<div class="chate_box_mine"></div>').append(name,"<br>"+(input.chat)[i].content+"<br><br>",date,"<br>").appendTo('#hi');
							//$('<div class="chate_box_mine"></div>').append(name,chat).appendTo('#hi');//값에 추가도 되는거 같군
						}
						else if((input.chat)[i].userId != input.userId){
							var name = $('<div align=right style="font-weight:bold; font-size:10pt; text-align:cneter;"></div>').append((input.chat)[i].userNickname+"<br>");
							 var date = $('<font style="font-size:8pt; text-align:cneter" align=center></font>').append((input.chat)[i].str_time);
 
							 $('<div class="chate_box_other"></div>').append(name,"<br>"+(input.chat)[i].content+"<br><br>",date,"<br>").appendTo('#hi');
							//$('<div class="chate_box_other"></div>').append(chat).appendTo('#hi');//값에 추가도 되는거 같군
						}
					}
					$("#input_chat_box").val("");//입력직후 입력칸 비우기
					$("#hi").scrollTop($("#hi")[0].scrollHeight);//스크롤바의 스크롤을 가장 아래로 내려주는 기능
				}
			})
		}//else 짝
	}
	
//일정시간마다 리뉴얼하기(순서 3)
	$(document).ready(function(){
		setInterval(function(){
			$.ajax({
				url:'/controller/getHistoryFirst',
				dataType: 'jsonp',
				
				success: function(input){
					//$("#input_chat_box").focus();
					console.log(input);
					console.log(input.chat);
					for(let i=0;i<(input.chat).length;i++){
						let chat = (input.chat)[i].userNickname+"<br>"+(input.chat)[i].content+"<br>"+(input.chat)[i].str_time+"<br>";
						//console.log(chat);
						
						if((input.chat)[i].userId == input.userId){
							var name = $('<div align=left style="font-weight:bold; font-size:10pt; text-align:cneter;"></div>').append("나 (" + (input.chat)[i].userNickname + ")");
							 var date = $('<font style="font-size:8pt; text-align:cneter" align=center></font>').append((input.chat)[i].str_time);
							 
							 $('<div class="chate_box_mine""></div>').append(name,"<br>"+(input.chat)[i].content+"<br><br>",date,"<br>").appendTo('#hi');
							//$('<div class="chate_box_mine"></div>').append(chat).appendTo('#hi');//값에 추가도 되는거 같군
						}
						else if((input.chat)[i].userId != input.userId){
							var name = $('<div align=right style="font-weight:bold; font-size:10pt; text-align:cneter;"></div>').append((input.chat)[i].userNickname+"<br>");
							 var date = $('<font style="font-size:8pt; text-align:cneter" align=center></font>').append((input.chat)[i].str_time);

							 $('<div class="chate_box_other"></div>').append(name,"<br>"+(input.chat)[i].content+"<br><br>",date,"<br>").appendTo('#hi');
							//$('<div class="chate_box_other"></div>').append(chat).appendTo('#hi');//값에 추가도 되는거 같군
						}
					}
					//시간마다 리뉴얼 되는 기능에서는 스크롤을 굳이 맨 아래로 내리지 않고 혹시 검색이나 기록을 확인하고 있을 경우를 대비하여 스크롤 위치를 유지하도록 한다.
					//$("#hi").scrollTop($("#hi")[0].scrollHeight);//스크롤바의 스크롤을 가장 아래로 내려주는 기능
				}
			//return false;
			});
		}, 3000);//시간 설정(현재는 5초로 해둠)
	});	
</script>
</body>
</html>