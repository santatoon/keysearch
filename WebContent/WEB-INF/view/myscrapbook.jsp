<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Keysearch - Cosmetics</title>

<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
</script>
</head>
<body>

	<div class="banner">
		<div class="container">
			<div class="header">
				<div class="header-left">
					<a href="index"><img src="../images/homebutton.png" alt="" /></a>
				</div>
				<div class="header-left1">
					<h3>Keysearch</h3>
				</div>
				<!-- #push_sidebar -->
				<div class="top-nav">
					<span class="menu"><img src="../images/menu.png" alt="" /></span>
					<ul class="nav1">
						<li><a href="index">Home</a></li>
						<li class="active"><a href="myscrapbook">My scrapbook</a></li>
						<li><a href="mypage">My page</a></li>
						<c:if test="${currentUser!=null}"><li><a href="logout">Logout</a></li></c:if>
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
				
				
				<!-- banner-bottom-slider -->
	<div class="banner-bottom-slider">
		<div class="container">
			<div class="col-md-4 banner-bottom-slider-left animated wow slideInLeft" data-wow-delay=".5s">
				<h3>Let's look back on your <span>Keysearch History</span></h3>
				<p>Check out your past searches and the content you’ve browsed in Keysearch</p>
			</div>
			<div class="col-md-8 banner-bottom-slider-right animated wow slideInRight" data-wow-delay=".5s">
				<ul id="flexiselDemo1">
					<c:forEach var="scrapbook" items="${scrapbookList}">
						<li>
							<div class="banner-bottom-slider-right1">
								<a href="${scrapbook.link }" target="_blank"><img src="${scrapbook.imageurl }" alt=" " class="img-responsive" /></a>
								<ul>
									<li><a href="#"><span class="glyphicon glyphicon-tags" aria-hidden="true"></span> ${fn:substring(scrapbook.tags, 1, 25) }</a></li>
								</ul>
								<p><i>${scrapbook.caption }</i></p>
							</div>
						</li>
					</c:forEach>
					
				</ul>
					<script type="text/javascript">
						$(window).load(function() {
						$("#flexiselDemo1").flexisel({
							visibleItems: 3,
							animationSpeed: 1000,
							autoPlay: true,
							autoPlaySpeed: 3000,    		
							pauseOnHover: true,
							enableResponsiveBreakpoints: true,
							responsiveBreakpoints: { 
								portrait: { 
									changePoint:480,
									visibleItems: 1
								}, 
								landscape: { 
									changePoint:640,
									visibleItems: 2
								},
								tablet: { 
									changePoint:768,
									visibleItems: 3
								}
							}
						});
						
					 });
					</script>
					<script type="text/javascript" src="../js/jquery.flexisel.js"></script>
			</div>
			<div class="clearfix"> </div>
		</div>
	</div>
<!-- //banner-bottom-slider -->
			</div>

		</div>
	</div>
	
	
</body>
</html>
