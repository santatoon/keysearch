<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Keysearch - Cosmetics</title>
<link
	href='http://fonts.googleapis.com/css?family=Titillium+Web:400,300,600'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="../css/sign_normalize.css">
<link rel="stylesheet" href="../css/check_style.css">

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
						<li><a href="myscrapbook">My scrapbook</a></li>
						<li class="active"><a href="mypage">My page</a></li>
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
				<div class="form">
				<form:form modelAttribute="customer" class="radio" method="post">
					<div class="field-wrap">
					<span>Hello! ${customer.firstname}</span>
					</div>
					<div class="field-wrap">
					<span>Password: 	</span>
					<form:password path="password" size="20" maxlength="16" value="${customer.password }"/>
					<form:errors cssClass="error" path="password" />
					</div>				
					<div class="field-wrap">
					<span>Skin Type: 	</span>
					<form:radiobuttons items="${skintypeList }" path="skintype" cssClass="rad" itemLabel="name" itemValue="value"/>
					<form:errors cssClass="error" path="skintype" />
					</div>
					<div class="clearfix"></div>
					<div class="field-wrap">
					<span>Trouble Type: 	</span>
					<form:radiobuttons items="${troubletypeList }" path="troubletype" cssClass="rad" itemLabel="name" itemValue="value"/>
					<form:errors cssClass="error" path="troubletype" />
					</div>
					<div class="field-wrap button-block">
					<input type="submit" value="Edit" class="button" />
					</div>
				</form:form>
				</div>
			</div>

		</div>
	</div>
	
	
</body>
</html>
