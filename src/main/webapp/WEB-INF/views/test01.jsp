<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<head>
<title>测试</title>

</head>
<section class="vbox">


 <header class="header bg-dark">
	测试 报表
</header> 



<section class="scrollable wrapper">
<div class="row">
                <div class="col-lg-12">
                  <section class="panel">
                    <header class="panel-heading">
                    充装走势
                    </header>
                    <div class="panel-body">
                      <div class="btn-group" data-toggle="buttons">
                        <label class="btn btn-sm btn-white active">
                          <input type="radio" name="options"> Day
                        </label>
                        <label class="btn btn-sm btn-white">
                          <input type="radio" name="options"> Week
                        </label>
                        <label class="btn btn-sm btn-white">
                          <input type="radio" name="options"> Month
                        </label>
                        <label class="btn btn-sm btn-white">
                          <input type="radio" name="options"> Year
                        </label>
                      </div>
                      <div class="line line-lg pull-in"></div>
                    
                    <div id="hero-graph" class="graph"></div>
                  </section>
                </div>
                
                
</div>
<div class="row">
			<div class="col-lg-8">
				<section class="panel"> <header class="panel-heading">
				Jaguar 'E' Type vehicles in the UK </header>
				<div class="panel-body">
					<div class="btn-group" data-toggle="buttons">
						<label class="btn btn-sm btn-white active"> <input
							type="radio" name="options"> Day
						</label> <label class="btn btn-sm btn-white"> <input type="radio"
							name="options"> Week
						</label> <label class="btn btn-sm btn-white"> <input type="radio"
							name="options"> Month
						</label> <label class="btn btn-sm btn-white"> <input type="radio"
							name="options"> Year
						</label>
					</div>
					<div class="line line-lg pull-in"></div>

					<div id="bar" class="graph"></div>
				</section>
	</div>
	<div class="col-lg-4">
				<section class="panel"> <header class="panel-heading">
				充装排行</header>
				<div class="panel-body">
					
					<div class="graph"></div>
					<div class="line line-lg pull-in"></div>
					
					
				</section>
	</div>
	</div>
</section></section>


<script type="text/javascript">
	var line = Morris.Line({
		element : 'hero-graph',
		data : [],
		xkey : 'x',
		ykeys : [ 'y' ],
		grid : true,
		labels : [ '次数' ],
		xLabels : "day",
		parseTime : true,
		postUnits : "日期",
		lineColors : [ '#59dbbf' ]
	});

	var bar = Morris.Bar({
		element : 'bar',
		parseTime : false,
		data : [],
		xkey : 'x',
		ykeys : [ 'y' ],
		labels : [ '次数' ],
		colors : [ '#afcf6f', '#CCC' ],

	});

	(function setLineData(obj) {

		$.ajax({
			url : "${ctx}/test/line",
			dataType : "json",
			success : function(data, textStauts, xhr) {
				var arr = jQuery.makeArray(data);
				line.setData(arr);
				//return arr;
			}

		});

	})($);

	(function setBarData(obj) {

		$.ajax({
			url : "${ctx}/test/bar",
			dataType : "json",
			success : function(data, textStauts, xhr) {
				var arr = jQuery.makeArray(data);
				//	alert(arr);
				bar.setData(arr);

				//return arr;
			}

		});

	})($);
	//	setData();
</script>