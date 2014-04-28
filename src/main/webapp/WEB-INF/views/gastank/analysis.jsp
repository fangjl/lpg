<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head>
<title>钢瓶档案分析</title>

</head>
<section class="vbox"> <header class="header bg-dark">
<ul class="nav nav-tabs">
	<li class=""><a data-toggle="tab" data-target="#content" data-pjax="" href="${ctx}/gastank/refluxRatio">回流率分析</a></li>
	<li class="active"><a>统计分析</a></li>
	<li class=""><a  data-toggle="tab" data-target="#content" data-pjax="" href="${ctx}/gastank/detail">详细查询</a></li>
</ul>
</header> 

<section class="scrollable wrapper">


	
	<div class="row">
		<div class="col-lg-12">
		<section class="panel"> 
		<header class="panel-heading">
		钢瓶注册趋势图
		</header>
		
		<div id="hero-graph" class="graph"></div>
		
		<footer class="panel-footer text-sm">
			<p>X:代表钢瓶出厂日期,Y:代表回流瓶数和钢瓶总的百分比</p>
		</footer>
		</section>
			
		</div>
		
	</div>
		

	<div class="row">
		<div class="col-lg-6">
			<section class="panel"> 
				<header class="panel-heading">钢瓶占有比</header>
				<div id="hero-donut" class="graph"></div>
				<div  class="panel-footer">各个加气站钢瓶占有比  </div>
			</section>	
		</div>
		<div class="col-lg-6">
			<section class="panel"> 
				<header class="panel-heading">钢瓶使用年限分析</header>
				<div class="panel-body" >
					<div id="hero-bar" class="graph"></div>
				</div>
					
				<div  class="panel-footer">X：钢瓶已使用年限 ,Y:钢瓶保有量</div>
			</section>	
		</div>
	</div>
		

</section>


<script type="text/javascript">
	

var bar = Morris.Bar({
	element : 'hero-bar',
	parseTime : false,
	data : [],
	xkey : 'x',
	ykeys : [ 'y' ],
	labels : ['瓶', '个' ],
	 integerYLabels: true,
	 barColors : [ '#afcf6f', '#CCC' ],
	 stacked:true,
	// hideHover:'auto',
	//axes:false,
	//grid:false,
	 resize:true,
});
(function setBarData(obj) {

	$.ajax({
		url : "${ctx}/gastank/queryGasTankYearKPI",
		dataType : "json",
		success : function(data, textStauts, xhr) {
			var arr = jQuery.makeArray(data);
			//	alert(arr);
			bar.setData(arr);
			//return arr;
		}

	});

})($);
	

$('#reservationtime').daterangepicker({
	timePicker : true,
	timePickerIncrement : 30,
	format : 'YYYY-MM',
}, function(start, end, label) {
});

/* 
$('#reservationtime').daterangepicker({
singleDatePicker:false,
  format: 'YYYY-MM',

  locale : {
  	
  	
  	daysOfWeek: [ "一", "二", "三", "四", "五", "六","日"],
	monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
	today: "今日",
	format: "yyyy年mm月dd日",

          applyLabel: '确定',
          cancelLabel: '取消',
          fromLabel: '从',
          toLabel: '到',
          weekLabel: 'W',
          customRangeLabel: 'Custom Range',
         // daysOfWeek: moment()._lang._weekdaysMin.slice(),
         // monthNames: moment()._lang._monthsShort.slice(),
          firstDay: 0
      },
  
}, function(start, end, label) {
  //alert(start);
  //alert(end);

}); */

var line = Morris.Area({
	element : 'hero-graph',
	data : [],
	xkey : 'x',
	ykeys : [ 'y' ],
	grid : true,
	behaveLikeLine: true,
	labels : [ '瓶' ],
	xLabels : "month",
	
	parseTime : true,
	//postUnits : "日期",
	lineColors : [ '#59dbbf' ]
});

(function setLineData(obj) {

	$.ajax({
		url : "${ctx}/gastank/queryGasTankBatchReturnRate",
		dataType : "json",
		success : function(data, textStauts, xhr) {
			var arr = jQuery.makeArray(data);
			line.setData(arr);
			//return arr;
		}

	});

})($);




/* 各站钢瓶占有比 */
(function setDonutData(obj) {
	$.ajax({
		url : "${ctx}/gastank/queryGasTankDonutChart",
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


