<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh">
<head>
  <meta charset="utf-8" />
  <title>Web Application | todo</title>
  <meta http-equiv="x-pjax-version" content="v123">
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="${ctx}/static/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="${ctx}/static/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="${ctx}/static/css/font.css" type="text/css" cache="false" />
  <link rel="stylesheet" href="${ctx}/static/js/fuelux/fuelux.css" type="text/css" />
  <link rel="stylesheet" href="${ctx}/static/js/datatables/datatables.css" type="text/css" />
  <link rel="stylesheet" href="${ctx}/static/css/plugin.css" type="text/css" />
  <link rel="stylesheet" href="${ctx}/static/css/app.css" type="text/css" />
  <!--[if lt IE 9]>
    <script src="js/ie/respond.min.js" cache="false"></script>
    <script src="js/ie/html5.js" cache="false"></script>
    <script src="js/ie/fix.js" cache="false"></script>
  <![endif]-->
</head>
<body>
  <section class="hbox stretch">
    <!-- .aside -->
    <aside class="bg-dark aside-sm" id="nav">
      <section class="vbox">
        <header class="dker nav-bar">
          <a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen" data-target="#nav">
            <i class="fa fa-bars"></i>
          </a>
          <a href="#" class="nav-brand" data-toggle="fullscreen">好气站</a>
          <a class="btn btn-link visible-xs" data-toggle="class:show" data-target=".nav-user">
            <i class="fa fa-comment-o"></i>
          </a>
        </header>
        <section>
          <div class="lter nav-user hidden-xs pos-rlt">            
            <div class="nav-avatar pos-rlt">
              <a href="#" class="thumb-sm avatar animated rollIn" data-toggle="dropdown">
                <img src="${ctx}/static/images/avatar.jpg" alt="" class="">
                <span class="caret caret-white"></span>
              </a>
              <ul class="dropdown-menu m-t-sm animated fadeInLeft">
              	<span class="arrow top"></span>
                <li>
                  <a href="#">Settings</a>
                </li>
                <li>
                  <a href="profile.html">Profile</a>
                </li>
                <li>
                  <a href="#">
                    <span class="badge bg-danger pull-right">3</span>
                    Notifications
                  </a>
                </li>
                <li class="divider"></li>
                <li>
                  <a href="docs.html">Help</a>
                </li>
                <li>
                  <a href="signin.html">Logout</a>
                </li>
              </ul>
              <div class="visible-xs m-t m-b">
                <a href="#" class="h3">John.Smith</a>
                <p><i class="fa fa-map-marker"></i> London, UK</p>
              </div>
            </div>
            <div class="nav-msg">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <b class="badge badge-white count-n">2</b>
              </a>
              <section class="dropdown-menu m-l-sm pull-left animated fadeInRight">
                <div class="arrow left"></div>
                <section class="panel bg-white">
                  <header class="panel-heading">
                    <strong>You have <span class="count-n">2</span> notifications</strong>
                  </header>
                  <div class="list-group">
                    <a href="#" class="media list-group-item">
                      <span class="pull-left thumb-sm">
                        <img src="${ctx }/static/images/avatar.jpg" alt="John said" class="img-circle">
                      </span>
                      <span class="media-body block m-b-none">
                        Use awesome animate.css<br>
                        <small class="text-muted">28 Aug 13</small>
                      </span>
                    </a>
                    <a href="#" class="media list-group-item">
                      <span class="media-body block m-b-none">
                        1.0 initial released<br>
                        <small class="text-muted">27 Aug 13</small>
                      </span>
                    </a>
                  </div>
                  <footer class="panel-footer text-sm">
                    <a href="#" class="pull-right"><i class="fa fa-cog"></i></a>
                    <a href="#">See all the notifications</a>
                  </footer>
                </section>
              </section>
            </div>
          </div>
          <nav class="nav-primary hidden-xs">
            <ul class="nav">
              <li class="active">
                <a data-toggle="tab" id="main_dashboard" data-target="#content" data-pjax="" href="${ctx}/dashboard">
                  <i class="fa fa-eye fa-3x"></i>
                  <span>控制台</span>
                </a>
              </li>              
              <li>
                 <a data-toggle="tab" data-target="#content" data-pjax="" href="${ctx}/gastank">
                  <i class="fa fa-flask"></i>
                  <span>钢瓶档案</span>
                </a>
                <!-- <ul class="dropdown-menu">                
                  <li>
                    <a href="buttons.html">Buttons</a>
                  </li>
                </ul> -->
              </li>
              
              <li>
                
				<a data-toggle="tab" data-target="#content" data-pjax="" href="${ctx}/fillrecord">
                  <i class="fa fa-flask"></i>
                  <span>充装记录</span>
                </a>
              </li>
              
            </ul>
          </nav>
        </section>
        <footer class="footer bg-gradient hidden-xs">
          <a href="modal.lockme.html" data-toggle="ajaxModal" class="btn btn-sm btn-link m-r-n-xs pull-right">
            <i class="fa fa-power-off"></i>
          </a>
          <a href="#nav" data-toggle="class:nav-vertical" class="btn btn-sm btn-link m-l-n-sm">
            <i class="fa fa-bars"></i>
          </a>
        </footer>
      </section>
    </aside>
    <!-- /.aside -->
    <!-- .vbox -->
    <section id="content">
      
    </section>
    <!-- /.vbox -->
  </section>
	<script src="${ctx}/static/js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="${ctx}/static/js/bootstrap.js"></script>
  <!-- App -->
  <script src="${ctx}/static/js/app.js"></script>
  <script src="${ctx}/static/js/app.plugin.js"></script>
<%--   <script src="${ctx}/static/js/app.data.js"></script>
 --%>  <!-- fuelux -->
  <script src="${ctx}/static/js/fuelux/fuelux.js"></script>
   <script src="${ctx}/static/js/libs/jquery.pjax.js" cache="false"></script>
  <script src="${ctx}/static/js/libs/underscore-min.js"></script>
  <!-- datatables -->
  <script src="${ctx}/static/js/datatables/jquery.dataTables.min.js"></script>
  <!-- Sparkline Chart -->
  <script src="${ctx}/static/js/charts/sparkline/jquery.sparkline.min.js"></script>
  <!-- Easy Pie Chart -->
  <script src="${ctx}/static/js/charts/easypiechart/jquery.easy-pie-chart.js"></script>
  <script type="text/javascript">
 	 $.pjax({url: "${ctx}/dashboard", container: '#content'});
  	
 	 //$(document).pjax('#main_dashboard', '#content');
  
  </script>
</body>
</html>