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
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<link href="../../../css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="../../../css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<!-- js -->
<script src="../../../js/jquery-1.11.1.min.js"></script>
<!-- //js -->
<!-- animation-effect -->
<link href="../../../css/animate.min.css" rel="stylesheet">
<script src="../../../js/wow.min.js"></script>
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
<script type="text/javascript" src="../../../js/move-top.js"></script>
<script type="text/javascript" src="../../../js/easing.js"></script>
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
<!-- start-smoth-scrolling -->
</head>

<body>
	<!-- banner -->
	<div class="banner">
		<div class="container">
			<div class="header">
				<div class="header-left">
					<a href="../../../index"><img
						src="../../../images/homebutton.png" alt="" /></a>
				</div>
				<div class="header-left1">
					<h3>Keysearch</h3>
					<div class="result">
						<form:form modelAttribute="search">
							<form:input path="query" name="query" type="text"
								value="${query1}" />
							<input type="submit" value="Search" />
						</form:form>
					</div>
					<c:forEach var="filter" items="${filterlist}">
						<span class="filterFont">#${filter}</span>
					</c:forEach>

				</div>
				<!-- #push_sidebar -->
				<div class="top-nav">
					<span class="menu"><img src="../../../images/menu.png"
						alt="" /></span>
					<ul class="nav1">
						<li><a href="../../../index">Home</a></li>
						<li><a href="../../../myscrapbook">My scrapbook</a></li>
						<li><a href="../../../mypage">My page</a></li>
						<c:if test="${currentUser!=null}"><li><a href="../../../logout">Logout</a></li></c:if>
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
			<div class="banner-info2 gallery animated wow slideInUp"
				data-wow-delay="800ms">
				
				<div class="titlebar"><span>Image results</span></div>
				<c:forEach var="data" items="${instaresult}" varStatus="i">
					<c:if test="${(i.index+1)%8==1}">
						<div class="gallery-grids">
							<div class="gallery-grid2">
								<div class="gallery-grid">
					</c:if>
					
					<div class="gallery-grid1 animated wow slideInUp"
						data-wow-delay=".5s">
						<form:form modelAttribute="scrapbook" class="scrapform">
							<form:hidden path="link" value="${data.link }"/>
							<form:hidden path="tags" value="${data.tags }"/>
							<form:hidden path="caption" value="${data.caption.text }"/>
							<form:hidden path="imageurl" value="${data.images.standard_resolution.url }"/>
							<input type="submit" value="scrap"/>
						</form:form>
						<a href="${data.link }" rel="title" target="_blank" 
							class="b-link-stripe b-animate-go thickbox">
							<img src="${data.images.standard_resolution.url }" alt=" " class="img-responsive" />
							<div class="p-mask">
								<h4>${fn:substring(data.tags,0,20) }</h4>
								<p>${fn:substring(data.caption.text,0,50) }</p>
							</div>
						</a>
					</div>
					
					<c:choose>
						<c:when test="${(i.index+1)%8==1 }">
							<c:choose>
								<c:when test="${(i.index+1)==instaresult.size() }">
									</div>
									</div>
									</div>
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${(i.index+1)%8==2 }">
							<c:choose>
							<c:when test="${(i.index+1)==instaresult.size() }">
								</div>
								</div>
								</div>
							</c:when>
							<c:otherwise>
								</div>
								<div class="gallery-grid-sub">
							</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${(i.index+1)%8==3 }">
							<c:choose>
							<c:when test="${(i.index+1)==instaresult.size() }">
								</div>
								</div>
								</div>
							</c:when>
							<c:otherwise>
							</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${(i.index+1)%8==4 }">
							<c:choose>
							<c:when test="${(i.index+1)==instaresult.size() }">
								</div>
								</div>
								</div>
							</c:when>
							<c:otherwise>
								</div>
								<div class="clearfix"></div>
							</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${(i.index+1)%8==5 }">
							<c:choose>
							<c:when test="${(i.index+1)==instaresult.size() }">
								</div>
								</div>
							</c:when>
							<c:otherwise>
								</div>
								<div class="gallery-grid-sub1">
							</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${(i.index+1)%8==6 }">
							<c:choose>
							<c:when test="${(i.index+1)==instaresult.size() }">
								</div>
								</div>
							</c:when>
							<c:otherwise>
							</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${(i.index+1)%8==7 }">
							<c:choose>
							<c:when test="${(i.index+1)==instaresult.size() }">
								</div>
								</div>
							</c:when>
							<c:otherwise>
							</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${(i.index+1)%8==0 }">
							</div>
							<div class="clearfix"></div>
							</div>
						</c:when>
					</c:choose>	
				</c:forEach>
				<div class="clearfix"></div>
				<div class="titlebar"><span>Blog results</span></div>
				<div class="textresult">
				<c:forEach var="data" items="${naverresult}">
					<a href="${data.link }" target="_blank" >${data.type }</a>
					<div class="clearfix"></div>
				</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<!-- //banner -->

	<!-- gallery -->
	<!-- script -->
	<script src="../../../js/jquery.chocolat.js"></script>
	<link rel="stylesheet" href="../../../css/chocolat.css" type="text/css"
		media="screen" charset="utf-8">
	<!--light-box-files-->
	<!--
	<script type="text/javascript" charset="utf-8">
		$(function() {
			$('.gallery-grid1 a').Chocolat();
		});
	</script>
	 script -->
	<!-- //gallery -->


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