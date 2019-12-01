<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>班级问题交流</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
	<script type='text/javascript' async='' src='js/ga.js'></script>
	<script type='text/javascript' src='js/modernizr.custom.66147.js'></script>
	<script type='text/javascript' src='js/qio6inw.js'></script>
	<script type='text/javascript' src='js/25093.js'></script>
	<link rel='stylesheet' href='css/responsive-tables.css'>
	<link rel='stylesheet' href='css/style.css'>
	
	<script>
	/*
	$.getJSON("javaHomework/servlet/httphandler?type=6", 
	function(json){
		var id = 0;
		$.each(json, function(i){
			alert("hello");
			$("#questions").append(
				"<li style='border:2px solid #000'>"+
				"<p><button onclick=\"openShutManager(this," + "'" + id +"'"+",false,'收起','展开')\">点击展开</button></p>"+
				"<article>"+
				"<h3>"+ json[i].detail[0].editor + "</h3> asked "  + json[i].detail[0].title + " at " + json[i].detail[0].time +
				"<div id=" + "'" + id + "'" + " style='display:none'>"
				);
//			$.each(json[i].detail, function(j){
//				$("#questions").append(
//					"<br/>" + json[i].detail[j].time + 
//					"<strong>" + json[i].detail[j].editor + "说  " + "</strong><br/>" +
//					json[i].detail[j].content
//				);
//			});
			$("#questions").append(
				"<form action='httphandler' method='get'>" +
				"<label for='editor'>你的姓名</label>"  +
				"<input type='text' name='editor' value='a visitor'>" +
				"<br/><label for='detail'>你想说</label>" +
				"<input type='text' name='detail'>" +
				"<input type='text' name='id' style='display:none' value=" + id + ">" +
				"<input type='text' name='type' style='display:none' value=3>" +
				"<input type='submit' value='提交'>" +
				"</form>" +
				"</div>" +
				"</article>" + "</li>"
			);
		});
	});
	*/
	var pathArray = window.location.pathname.split('/');
	var curProjectLocation = "/" + pathArray[1];
	$.get(curProjectLocation + "/httphandler", function(data) {
		$("#questions").html(data);
	});
	</script>
	<script type='text/javascript'>
		function openShutManager(oSourceObj,oTargetObj,shutAble,oOpenTip,oShutTip){
			var sourceObj = typeof oSourceObj == 'string' ? document.getElementById(oSourceObj) : oSourceObj; 
			var targetObj = typeof oTargetObj == 'string' ? document.getElementById(oTargetObj) : oTargetObj; 
			var openTip = oOpenTip || '';  
			var shutTip = oShutTip || '';  
			if(targetObj.style.display!='none'){  
			   if(shutAble) return;  
			   targetObj.style.display='none';  
			   if(openTip  &&  shutTip){  
			    sourceObj.innerHTML = shutTip;   
			   }  
			} else {  
			   targetObj.style.display='block';  
			   if(openTip  &&  shutTip){  
			    sourceObj.innerHTML = openTip;  
			    }
		 	} 
		 }  
		 function search(){
		 	var locstr = $("#search").val();
		 	self.location = "javaHomework/index.jsp#" + locstr;
		 }
	</script>
  </head>
  
  <body>
    <div class="wrapper">
    	<header class="header">
    		<div class="page-width">
    			<ul class="nav left-align">
    				<li><a style="font-family: 微软雅黑; font-size:35px" href="javaHomework/index.jsp#newtip" title='发表新帖子' tabindex=''>发表新帖</a></li>
    				<li><a style="font-family: 微软雅黑; font-size:35px" href="javaHomework/index.jsp#questions" title='查看别的问题' tabindex=''>查看问题</a></li>
    				<li style="float:right">
    					<input type="text" id="search" style="height:50px">
    					<button class="btn" onclick="search()">查找</button>
    				</li>
    			</ul>
    		</div>
    	</header>
    </div>
	<div class="main">
		<section class="intro">
			<div class="page-width">
				<div class="intro-text">
					<strong><font face='微软雅黑' size=30>OOP心得交流<br>只要你要<br>只要我有</font></strong>
				</div>
			</div>
		</section>
    </div>
    <hr/><hr/><hr/>
    <div id="newtip" style="height: 700px">
    	<strong><font face="微软雅黑" style="margin-left:120px" size=10>提问</font></strong><br><br>
    	<div style="margin-left:140px">
    	<form action="javaHomework/servlet/httphandler" method="get" accept-charset="utf-8">
    		<font face="华文行楷" size=6>你的姓名</font>
    		<input type="text" name="editor" value="a visitor"><br/>
    		<font face="华文行楷" size=6>你想问：</font>
    		<input type="text" name="title" value="问题"><br/><br/>
    		<font face="华文行楷" size=6>详细内容</font>
    		<textarea name="detail" style="height:300px; width:750px">如题</textarea>
    		<input type="text" name="type" value="2" style="display: none">
    		<br/><button type="submit" class="btn">提交</button>
    	</form>
    		<a style="font-family:微软雅黑; margin-right:30px; float:right" href="javaHomework/index.jsp" class="btn">TOP</a>
    		
    	</div>
    </div>
    <hr/><hr/><hr/>
    <strong><font face="微软雅黑" style="margin-left:120px" size=10>问题</font></strong><br><br>
    <div id="questions" style="margin-left:200px; margin-right:200px">
    
    </div>
    <a style="font-family:微软雅黑; margin-right:30px; float:right" href="javaHomework/index.jsp" class="btn">TOP</a><br/>
  </body>
</html>
