<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Keysearch - Cosmetics</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Walk Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!-- //for-mobile-apps -->
<link href="../css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<!-- js -->
<script src="../js/jquery-1.11.1.min.js"></script>
<!-- //js -->
<!-- animation-effect -->
<link href="../css/animate.min.css" rel="stylesheet">
<script src="../js/wow.min.js"></script>
<script>
	new WOW().init();
</script>
<!-- //animation-effect -->
<link
	href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Bad+Script'
	rel='stylesheet' type='text/css'>
<!-- start-smoth-scrolling -->
<script type="text/javascript" src="../js/move-top.js"></script>
<script type="text/javascript" src="../js/easing.js"></script>
<script src="../js/tagcanvas.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
	window.onload = function() {
		TagCanvas.interval = 20;
		TagCanvas.textFont = 'Impact,한컴 윤체 B,한컴 윤체 B';
		TagCanvas.textColour = '#00f';
		TagCanvas.textHeight = 25;
		TagCanvas.outlineColour = '#f96';
		TagCanvas.outlineThickness = 5;
		TagCanvas.maxSpeed = 0.04;
		TagCanvas.minBrightness = 0.1;
		TagCanvas.depth = 0.92;
		TagCanvas.pulsateTo = 0.2;
		TagCanvas.pulsateTime = 0.75;
		TagCanvas.initial = [ 0.1, -0.1 ];
		TagCanvas.decel = 0.98;
		TagCanvas.reverse = true;
		TagCanvas.hideTags = true;
		TagCanvas.shadow = '#ccf';
		TagCanvas.shadowBlur = 3;
		TagCanvas.weight = true;
		TagCanvas.weightFrom = 'data-weight';
		TagCanvas.weightMode = 'both';
		TagCanvas.fadeIn = 800;
		try {
			TagCanvas.Start('tagcanvas', 'weightTags');
		} catch (e) {
		}
	};
</script>
<!-- start-smoth-scrolling -->
</head>

<body>
	<!-- banner -->
	<div class="banner">
		<div class="container">
			<div class="header">
				<div class="header-left">
					<a href="../index"><img src="../images/homebutton.png" alt="" /></a>
				</div>
				<div class="header-left1">
					<h3>Keysearch</h3>
					<div class="result">
						<form:form modelAttribute="search">
							<form:input path="query" name="query" type="text" value="${query}" />
							<form:errors cssClass="error" path="query" />
							<input type="submit" value="Search" />
						</form:form>
					</div>

				</div>
				<!-- #push_sidebar -->
				<div class="top-nav">
					<span class="menu"><img src="../images/menu.png" alt="" /></span>
					<ul class="nav1">
						<li><a href="../index">Home</a></li>
						<li><a href="../myscrapbook">My scrapbook</a></li>
						<li><a href="../mypage">My page</a></li>
						<c:if test="${currentUser!=null}"><li><a href="../logout">Logout</a></li></c:if>
					</ul>
					<div class="clearfix"></div>
					<!-- script-for-menu -->
					<script>
						$("span.menu").click(function() {
							$("ul.nav1").slideToggle(300, function() {
								// Animation complete.
							});
						});
					</script>
					<!-- /script-for-menu -->
				</div>
				<!-- /#push_sidebar -->
				<div class="clearfix"></div>
			</div>
			<div class="banner-info2 centred animated wow slideInUp"
				data-wow-delay="800ms">
				<ul class="weighted" style="font-size: 50%" id="weightTags">
					<c:forEach var="word" items="${result.occurrences}">
						<form:form modelAttribute="result" id="form${word.key }">
						<li><a href="javascript:;" onclick="document.getElementById('form${word.key}').submit();" data-weight="${word.value*4}" style="font-size: ${word.value}ex">${word.key}</a>
							<form:hidden path="secondquery" value="${word.key }"/>	
						</li>
						<input type="hidden" name="mess"/>
						</form:form>
					</c:forEach>
				</ul>
				<canvas id="tagcanvas" width="700" height="500"
					style="margin-bottom: 20px"></canvas>
				
			</div>
		</div>
	</div>
	<!-- //banner -->


	<!-- here stars scrolling icon -->
	<script type="text/javascript">
		$(document).ready(function() {
			/*
				var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
				};
			 */

			$().UItoTop({
				easingType : 'easeOutQuart'
			});

		});
	</script>
	<!-- //here ends scrolling icon -->
</body>
</html>