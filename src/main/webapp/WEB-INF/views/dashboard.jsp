<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<title>首页</title>
</head>
      <section class="vbox"> <section class="scrollable"> <header>
<div class="row b-b m-l-none m-r-none">
	<div class="col-sm-4">
		<h3 class="m-t m-b-none">控制台</h3>
	</div>
	<div class="col-sm-8">
		<div class="clearfix m-t-lg m-b-sm pull-right pull-none-xs">
			<div class="pull-left">
				<div class="pull-left m-r-xs">
					<span>今日充装 :</span> <span class="h4">${today_count_fill_record }瓶</span>
				</div>

			</div>
			<div class="pull-left m-l-lg">
				<div class="pull-left m-r-xs">
					<span>今日建档:</span> <span class="h4">${today_count_gas_tank }瓶</span>
				</div>

			</div>
		</div>
	</div>
</div>
</header> <section class="hbox"> <aside class="b-l  bg-light">
<section class="wrapper">
<div class="col-md-12">
	<section class="panel"> <header class="panel-heading">钢瓶回流率</header>
	<div class="text-center clearfix">
		<div class="wrapper">
			<div id="hero-line" class="graph"></div>
		</div>
	</div>
	</section>
</div>
<div class="col-md-6">
	<section class="panel"> <header class="panel-heading">钢瓶使用年限</header>
	<div class="text-center clearfix">
		<div class="m-t-lg padder">
			<div id="hero-bar" class="graph"></div>
		</div>
	</div>
	</section>
</div>
<div class="col-md-6">
	<section class="panel"> <header class="panel-heading">钢瓶投用走势</header>
	<div id="gas_line_graph" class="graph"></div>
	</section>
</div>
<div></div>
<div></div>
</section> </aside> <aside class="b-l aside-lg bg-light"> <section class="wrapper">
<section class="panel"> <header class="panel-heading">各站点钢瓶占有比</header>
<div id="hero-donut" class="graph"></div>
</section>

<div class="panel-group m-b" id="accordion2">


	<div class="panel">



		<div class="panel-heading">
			<a class="accordion-toggle" data-toggle="collapse"
				data-parent="#accordion2" href="#collapseOne"> 各站点今日充装 </a>
		</div>




		<div id="collapseOne" class="panel-collapse in">
			<div class="panel-body text-sm">

				<c:forEach var="officeRecordVo"
					items="${today_count_fill_record_offices }">
					<p>${officeRecordVo.officename}   -----  ${officeRecordVo.filltotal } (瓶)</p>
					<div class="line line-lg pull-in"></div>
				</c:forEach>


			</div>
		</div>
	</div>



</div>
</section> </aside> </section> </section>
      </section>
<script type="text/javascript">

var line = Morris.Area({
	element : 'hero-line',
	data : [],
	xkey : 'x',
	ykeys : [ 'y' ],
	grid : true,
	labels : [ '回流比' ],
	xLabels : "month",
	parseTime : true,
    goals: [0, 1],
	lineColors : [ '#59dbbf' ]
});

(function setLineData() {
	
	$.ajax({
		url : "${ctx}/gastank/queryGasTankTotalReturnRate",
		dataType : "json",
		success : function(data, textStauts, xhr) {
			var arr = jQuery.makeArray(data);
			line.setData(arr);
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




/**
 * 使用年限比
 */
var bar = Morris.Bar({
	element : 'hero-bar',
	parseTime : false,
	data : [],
	xkey : 'x',
	ykeys : [ 'y' ],
	labels : ['个瓶' ],
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


/*钢瓶注册趋势图 */
var line1 = Morris.Line({
	element : 'gas_line_graph',
	data : [],
	xkey : 'x',
	ykeys : [ 'y' ],
	grid : true,
	labels : [ '瓶' ],
	xLabels : "year",
	parseTime : false,
	//postUnits : "日期",
	lineColors : [ '#59dbbf' ]
});

(function setLineData(obj) {

	$.ajax({
		url : "${ctx}/gastank/queryGasTankBatchReturnRate",
		dataType : "json",
		success : function(data, textStauts, xhr) {
			var arr = jQuery.makeArray(data);
			line1.setData(arr);
			//return arr;
		}

	});

})($);

</script>