<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head>
<title>充装记录分析</title>


</head>
<section class="vbox"> <header class="header bg-dark">
<ul class="nav nav-tabs">
	<li class="active"><a>统计分析</a></li>
	<li class="">     <a data-toggle="tab" data-target="#content" data-pjax="" href="${ctx}/fillrecord/detail">详细查询</a></li>
</ul>
</header> 
<section class="scrollable wrapper">
	<div class="row">
		<div class="col-lg-12">
		<section class="panel"> 
			<header class="panel-heading">
			<ul class="nav nav-pills pull-right ">
				<li><a class="panel-toggle text-muted " href="#"> <i class="fa fa-caret-down text-active">
				</i> <i class="fa fa-caret-up text"></i></a></li>
			</ul>
			充装走势
			</header>
			<div class="panel-body collapse">
					<form id="id_czzs" role="form" class="form-horizontal" action="${ctx}/fillrecord/queryFillRecordTrendChart">
						<div class="form-group">
							<label class="col-sm-2 control-label">加气站</label>
							<div class="col-sm-5">
								<c:forEach var="office" items="${principal.offices }"  varStatus="status">
									<div class="radio">
									<label>
										<input type="radio" ${status.index == '0' ?"checked=checked":""} value="${office.key }" name="officecode"> ${office.value}
									</label>
									</div>
								</c:forEach>
							</div>
						</div>
					</form>
				<div class="line line-lg pull-in"></div>
			</div>
			<div id="hero-graph" class="graph"></div>
			<footer class="panel-footer text-sm">
				<p>X:充装时间,Y:充装量</p>
			</footer>
		</section>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-8">
			<section class="panel"> 
				<header class="panel-heading">工位充装量分析比
					<ul class="nav nav-pills pull-right ">
						<li><a class="panel-toggle text-muted " href="#"> <i class="fa fa-caret-down text-active">
						</i> <i class="fa fa-caret-up text"></i></a></li>
					</ul>
				</header>
				<div class="panel-body collapse" >
				
					<form id="id_czgw" role="form" class="form-horizontal" action="${ctx}/fillrecord/queryFillRecordRankingChart">
							
							<div class="form-group">
								<label class="col-sm-2 control-label">加气站</label>
									<div class="col-sm-5">
										<c:forEach var="office" items="${principal.offices }"  varStatus="status">
											<div class="radio">
											<label>
												<input type="radio"  ${status.index == '0' ?"checked=checked":""} value="${office.key }"name="officecode" >${office.value}
											</label>
											</div>
										</c:forEach>
									</div>
							</div>
							<div class="line line-dashed line-lg pull-in"></div>
							<div class="form-group">
								<label class="col-sm-2 control-label">比较时段</label>
								<div class="col-sm-5">
									<label class="radio-inline">
										  <input type="radio"  name="type"  value="1" checked="checked"> 今天
									</label>
									<label class="radio-inline">
										  <input type="radio" name="type" value="2"> 本月
									</label>
			                     </div>
			                     
		                     </div>
					</form>	
                   <div class="line line-lg pull-in"></div>
				</div>
					<div id="hero-bar" class="graph"></div>
				<div  class="panel-footer">X：充装工位编号 ,Y:充装瓶数
				
						
				</div>
			</section>	
		</div>
		<div class="col-lg-4">
			<section class="panel"> 
				<header class="panel-heading">充装比分析</header>
				<div id="hero-donut" class="graph"></div>
				<div  class="panel-footer">各个加气站充装重量占有比  </div>
			</section>	
		</div>
	</div>
</section>
<script type="text/javascript">
	
	
	

/* 充装走势 */
var line = Morris.Line({
	element : 'hero-graph',
	data : [],
	xkey : 'x',
	ykeys : [ 'y' ],
	grid : true,
	labels : [ '次数' ],
	xLabels : "day",
	parseTime : true,
	//postUnits : "日期",
	xLabels:"month",
	smooth:true,
	lineColors : [ '#59dbbf' ]
});

$('#id_czzs').ajaxForm({
	beforeSubmit:function(formData, jqForm, options){
		
	 return true; 
	},
	dataType:"json",
	success:function(data, statusText, xhr, $form){
		var arr = jQuery.makeArray(data);
		line.setData(arr);
	},
}); 


$('#id_czzs').submit();


$('#id_czzs input').each(function(i,v){
	$(this).on("click",function(){
		
		$('#id_czzs').submit();
	});
});
	
	
/* 个站 工位充装量 */
var bar = Morris.Bar({
	element : 'hero-bar',
	parseTime : false,
	data : [],
	xkey : 'x',
	ykeys : [ 'y' ],
	labels : [ '瓶' ],
	integerYLabels: true,
	stacked:true,
	resize:true,

});


$('#id_czgw').ajaxForm({
	beforeSubmit:function(formData, jqForm, options){
	 return true; 
	},
	dataType:"json",
	success:function(data, statusText, xhr, $form){
		var arr = jQuery.makeArray(data);
		bar.setData(arr);
	},
}); 

$('#id_czgw').submit();

$('#id_czgw input').each(function(i,v){
	$(this).on("click",function(){
		$('#id_czgw').submit();
	});
});




/* 各站充装比 */
(function setDonutData(obj) {
	$.ajax({
		url : "${ctx}/fillrecord/queryFillRecordDonutChart",
		dataType : "json",
		success : function(data, textStauts, xhr) {
			var d = jQuery.makeArray(data);
			var c = 0;
			 $.each(d,function(i,v){
				c = c+v["value"];
			});
			
			Morris.Donut({
				element: 'hero-donut',
				data: d,
				colors:['#afcf6f','#33C','#939','#999','#C40937','#33C'],
				formatter: function (y,data) { 
					return  Math.round((y/c)*10000)/100+"%"}
					
			});
		}
			

	});
})($);

</script>


