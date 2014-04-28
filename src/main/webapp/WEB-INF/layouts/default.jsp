<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
<meta charset="utf-8" content="" />
<title>好气站-<sitemesh:title></sitemesh:title></title>
<meta http-equiv="x-pjax-version" content="v123">
<meta name="description"
	content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="${ctx}/static/css/bootstrap.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/animate.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/font.css" type="text/css"
	cache="false" />
<link rel="stylesheet" href="${ctx}/static/js/fuelux/fuelux.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/static/js/datatables/datatables.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/plugin.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/app.css" type="text/css" />

<link rel="stylesheet" href="${ctx}/static/js/select2-3.4.6/select2.css"
	type="text/css" />
<link rel="stylesheet" href="${ctx}/static/js/select2-3.4.6/select2-bootstrap-css/css/select2-bootstrap.css" type="text/css" />


<link rel="stylesheet" href="${ctx}/static/js/bootstrap-daterangepicker/daterangepicker-bs3.css" type="text/css" />


<!--[if lt IE 9]>
    <script src="${ctx}/static/js/ie/respond.min.js" cache="false"></script>
    <script src="${ctx}/static/js/ie/html5.js" cache="false"></script>
    <script src="${ctx}/static/js/ie/fix.js" cache="false"></script>
  <![endif]-->


<script src="${ctx}/static/js/jquery.min.js" type="text/javascript"></script>
<!-- Bootstrap -->
<script src="${ctx}/static/js/bootstrap.js" type="text/javascript"></script>
<!-- App -->
<script src="${ctx}/static/js/app.js" type="text/javascript"></script>
<script src="${ctx}/static/js/app.plugin.js" type="text/javascript"></script>

<script src="${ctx}/static/js/fuelux/fuelux.js" type="text/javascript"></script>
<script src="${ctx}/static/js/libs/jquery.pjax.js" cache="false" type="text/javascript"></script>
<script src="${ctx}/static/js/libs/underscore-min.js" type="text/javascript"></script>
<!-- datatables -->
<script src="${ctx}/static/js/datatables/jquery.dataTables.min.js" type="text/javascript"></script>

<!-- bootstrap-datepicker -->
<script src="${ctx}/static/js/bootstrap-daterangepicker/moment.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>

<script src="${ctx}/static/js/charts/morris/raphael-min.js" cache="false" type="text/javascript"></script>
<script src="${ctx}/static/js/charts/morris/morris.min.js" cache="false" type="text/javascript"></script>
<!-- jquery - form -->
<script src="${ctx}/static/js/jquery-form/jquery.form.js" cache="false" type="text/javascript"></script>

<!-- jquery - select2 -->
<script src="${ctx}/static/js/select2-3.4.6/select2.min.js" cache="false" type="text/javascript"></script>
<script src="${ctx}/static/js/select2-3.4.6/select2_locale_zh-CN.js" cache="false" type="text/javascript"></script>


<sitemesh:head />
</head>
<body>
	<section class="hbox stretch"> <!-- .aside --> <aside
		class="bg-dark aside-sm" id="nav"> <section class="vbox">
	<header class="dker nav-bar"> <a
		class="btn btn-link visible-xs" data-toggle="class:nav-off-screen"
		data-target="#nav"> <i class="fa fa-bars"></i>
	</a> <a href="#" class="nav-brand" data-toggle="fullscreen">好气站</a> <a
		class="btn btn-link visible-xs" data-toggle="class:show"
		data-target=".nav-user"> <i class="fa fa-comment-o"></i>
	</a> </header> <section>
	<div class="lter nav-user hidden-xs pos-rlt">
		<div class="nav-avatar pos-rlt">
			<a href="#" class="thumb-sm avatar animated rollIn"
				data-toggle="dropdown"> <img
				src="${ctx}/static/images/Desert.jpg" alt="" class=""> <span
				class="caret caret-white"></span>
			</a>
			<ul class="dropdown-menu m-t-sm animated fadeInLeft">
				<span class="arrow top"></span>
				<li><a href="#">设置</a></li>
				<!-- <li><a href="profile.html">Profile</a></li>
				<li><a href="#"> <span class="badge bg-danger pull-right">3</span>
						Notifications
				</a></li> -->
				<li class="divider"></li>
				<!-- 				<li><a href="docs.html">Help</a></li>
 -->
				<li><a href="/logout">退出</a></li>
			</ul>
			<div class="visible-xs m-t m-b">
				<a href="#" class="h3">方金林</a>
				<p>
					<i class="fa fa-map-marker"></i> 多立恒(北京)信息技术有限公司
				</p>
			</div>
		</div>
		<div class="nav-msg">

			<section class="dropdown-menu m-l-sm pull-left animated fadeInRight">
			<div class="arrow left"></div>
			<section class="panel bg-white">
			<div class="list-group">
				<a href="#" class="media list-group-item"> <span
					class="pull-left thumb-sm"> <img
						src="${ctx }/static/images/avatar.jpg" alt="John said"
						class="img-circle">
				</span>
				</a>
			</div>
			<footer class="panel-footer text-sm"> <a href="#"
				class="pull-right"><i class="fa fa-cog"></i></a> <a href="#">See
				all the notifications</a> </footer> </section> </section>
		</div>
	</div>
	<nav class="nav-primary hidden-xs">
	<ul class="nav">
		<li class="active">
			<a data-toggle="tab"  data-target="#content" data-pjax="" href="${ctx}/dashboard"> 
				<i class="fa fa-eye fa-3x"></i> 
				<span>控制台</span>
			</a>
		</li>
		<li>
			<a data-toggle="tab" data-target="#content" data-pjax="" href="${ctx}/gastank/refluxRatio">
				 <i class="fa fa-flask"></i> 
				 <span>钢瓶档案</span>
			</a>
		</li>
		<li>
			<a data-toggle="tab" data-target="#content" data-pjax="" href="${ctx}/fillrecord/analysis"> 
				<i class="fa fa-flask"></i> 
				<span>充装记录</span>
			</a>
		</li>
		
		<li>
			<a href="http://115.29.141.81:8080/ucenter" target="_top">
				<i class="fa fa-flask"></i> <span>系统设置</span>
			</a>
		</li>
		
	</ul>
	</nav> </section> <footer class="footer bg-gradient hidden-xs"> <a
		href="http://115.29.141.81:8080/cas/logout"
		class="btn btn-sm btn-link m-r-n-xs pull-right"> <i
		class="fa fa-power-off"></i>
	</a> <a href="#nav" data-toggle="class:nav-vertical"
		class="btn btn-sm btn-link m-l-n-sm"> <i class="fa fa-bars"></i>
	</a> </footer> </section> </aside> <!-- /.aside --> <!-- .vbox --> <section id="content"> <sitemesh:body />
	</section> <!-- /.vbox --> </section>

</body>
</html>