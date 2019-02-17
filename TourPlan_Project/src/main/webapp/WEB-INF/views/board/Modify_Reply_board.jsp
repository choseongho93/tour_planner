<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 
   <style>
      
   .text-stroke {
          text-shadow:
       -1px -1px 0 #000,
       1px -1px 0 #000,
          -1px 1px 0 #000,
         1px 1px 0 #000;  
   }
   
    td {
      border-radius: 5px;
   }
   table {
      border-radius: 5px;
      text-align: center;
   }
   
   .mainButton{
      background-color: #0064CD;
      color: white;
      border: none;
      text-align: center;
       text-decoration: none;
         display: inline-block;
         border-radius: 5px;
       font-family: 휴먼모음T;
       cursor: pointer;
   }
   
   .subButton{
      background-color: white;
      color: black;
      border: none;
      text-align: center;
       text-decoration: none;
         display: inline-block;
         border-radius: 5px;
       font-family: 휴먼모음T;
       cursor: pointer;
   }
   
   select {
      background-color: #0064CD;
      color: white;
      border: none;
      text-align: center;
       text-decoration: none;
         display: inline-block;
         border-radius: 5px;
       font-family: 휴먼모음T;
       cursor: pointer;
   }
   
   .textbox {
      background-color: white;
      color: #0064CD;
      border: none;
       text-decoration: none;
         display: inline-block;
         border-radius: 5px;
       font-family: 휴먼모음T;
       cursor: pointer;
   }
   
    </style>

</head>
<body style="font-faimily:돋움; background-color:#ECEBFF; font-size:10pt;">
<div align="center">
<form action="Modify_Reply_board" method="post">
		<div align="center" style="width: 450px; height: 200px;">
		<input type="hidden" name="board"  value="${lists.board }">
		<input type="hidden" name="num"  value="${lists.num }">
			<table style="border-radius: 5px; width: 450px; height: 200px;">
				<tr height="30px;">
					<Td colspan="4" style="text-align:center;"><font class="text-stroke" style="color:white; font-family:Boradway; font-size:18pt;">Modify</font></Td>
				</tr>
				<tr height="20px;">
					<td style="background-color:#0064CD; color:white; font-weight:bold">작성자</td>
					<td style="#0064CD; background-color:white;">${lists.nickname}</td>
					
					<td style="background-color:#0064CD;color:white; font-weight:bold;">작성일시</td>
					<td style="#0064CD; background-color:white;">${lists.savedate}</td>
				</tr>
				<tr style="height:20px;">
					<td colspan="4" style="background-color:#0064CD; color:white; font-weight:bold;"> 내용 </td>
				</tr>
				<tr>
					<td colspan="4"><textarea class="textbox" rows="5" cols="50" name="content">${lists.content}</textarea></td>
				</tr>
				<tr align="right">
					<td colspan="4" align="center" style="height:20px;">
						<button class="mainButton" style="height:25px; width:40px;" type="button" onclick="cancle()">취소</button>
						<input class="mainButton" style="height:25px; width:40px; "type="submit" value="수정">
						<!-- <input type="button" value="수정" onclick="modify()"> -->
						<input type="hidden" name="num" value="${num}">
						<input type="hidden" name="rpnum" value="${lists.rpnum}">
					</td>
				</tr>
			</table>
		</div>
	</form>
	   <script>//취소 버튼 눌렀을 때 화면 닫는 기능.
      function cancle(){
         self.close();
      }
   </script>
</div>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>