<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<head>
<title>钢瓶总回流率分析</title>

</head>
<section class="vbox"> <header class="header bg-dark">
<ul class="nav nav-tabs">
	
	<li class="active"><a>回流率分析</a></li>
	<li class=""><a data-toggle="tab" data-target="#content" data-pjax="" href="${ctx}/gastank/analysis">统计分析</a></li>
	<li class=""><a  data-toggle="tab" data-target="#content" data-pjax="" href="${ctx}/gastank/detail">详细查询</a></li>
	
</ul>
</header> <section class="scrollable wrapper">
<div class="row">
	<div class="col-lg-12">
		<section class="panel"> 
		<header class="panel-heading">
		<ul class="nav nav-pills pull-right ">
			<li><a class="panel-toggle text-muted " href="#"> 
			<i class="fa fa-caret-down text-active"></i>
			 <i class="fa fa-caret-up text"></i>
			 </a>
			 </li>
		</ul>
		钢瓶回流 
		</header>
		<div class="panel-body collapse">
			<form id="id_query_form" role="form" class="form-horizontal" action="${ctx}/gastank/queryGasTankTotalReturnRate">
				<%-- <div class="form-group">
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
				<div class="line line-dashed line-lg pull-in"></div>
				 --%>
				<div class="form-group">
					<label class="col-sm-2 control-label">时间基线</label>
						<div class="col-sm-4 input-group m-b">
						<div class="input-group-btn">
                            <button data-toggle="dropdown" class="btn btn-sm btn-white dropdown-toggle" type="button"><span class="dropdown-label">钢瓶出厂时间 </span><span class="caret"></span></button>
                            <ul class="dropdown-menu dropdown-select" >
                           
                              <li class="active"><a href="#"><input type="radio" ame="type" value="1">钢瓶出厂时间 </a></li>
                              <li class=""><a href="#"><input type="radio" checked="checked" name="type" value="2" >钢瓶注册日期</a></li>
<!--                               <li class=""><a href="#"><input type="radio" name="type" value="3">首次充装日期</a></li>
 -->                            </ul>
                          </div><!-- /btn-group -->
                          
                        
                          <input name="ctime" type="text" placeholder="时间基线/默认代表所有钢瓶" id="reservationtime" class="form-control input-sm">
                         <span class="input-group-addon"><i class="glyphicon glyphicon-calendar fa fa-calendar"></i></span>
                        
                        </div>
                        <div class="input-group m-b">
                          <span class="text-primary help-block">时间基线： 以所选时间内的钢瓶总量开始计算，不选空标示所有钢瓶 </span>
                        </div>
                        
				</div>
				<div class="line line-dashed line-lg pull-in"></div>
				<div class="form-group">
					<label class="col-sm-2 control-label">回流时段</label>
					<div class="col-sm-4">
						<select value="6" class="form-control input-sm" name="monthNumber">
							<option value="3">3个月内</option>
							<option value="6" selected="selected">6个月内</option>
							<option value="9">9个月内</option>
							<option value="12">12个月内</option>
						</select>
					</div>
					<div class="input-group m-b">
                          <span class="text-primary help-block">从当前时间,往前推所选月份之内，有多少钢瓶回流 </span>
                     </div>
				</div>
				<div class="form-group">
					<div class="col-sm-2 col-sm-offset-2">
						<button class="btn btn-primary  btn-lg btn-block" type="submit">查询</button>
					</div>
				</div>
			</form>
			<div class="line line-lg pull-in"></div>
		</div>

		<div id="hero-graph" class="graph"></div>
			
		<footer class="panel-footer text-sm">
		<!-- 	<p>X:代表钢瓶出厂日期</p>
			<p>Y:代表回流瓶数和钢瓶总的百分比</p> -->
		</footer>
		</section>
	</div>

</div>
</section>
<div></div>

<div></div>
</section>

</section>



<script type="text/javascript">
		$('#reservationtime').daterangepicker({
			timePicker : false,
			format : 'YYYY-MM-DD',
			separator:'~',
			showWeekNumbers: false,
			startDate: moment().subtract('month',7).format('YYYY-MM-DD'),
	        endDate: moment().format('YYYY-MM-DD'),
	        minDate:  moment().subtract('month',12).format('YYYY-MM-DD'),
	        maxDate: moment().format('YYYY-MM-DD'),
	        showDropdowns: true,
	        showWeekNumbers:true,
	        opens: 'right',
	        ranges: {
	            '三个月内': [moment().subtract('month', 3).startOf('month'), moment().subtract('month', 1).endOf('month')],
	    		'六个月内': [moment().subtract('month', 9).startOf('month'), moment().subtract('month', 1).endOf('month')],
				'一年内': [moment().subtract('month', 12).startOf('month'), moment().subtract('month', 1).endOf('month')]

	        },
	        locale: {
	            applyLabel: '确定',
	            cancelLabel: '取消',
	            fromLabel: '从',
	            toLabel: '到',
	            customRangeLabel: '其他',
	            daysOfWeek: ["日", "一", "二", "三", "四", "五", "六"],
	            monthNames: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
	            firstDay: 1
	        }
		}, 
		function(start, end, label) {
			
		});

	
	var line = Morris.Area({
		element : 'hero-graph',
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

	function setLineData(obj) {

		$.ajax({
			url : "${ctx}/gastank/queryGasTankTotalReturnRate",
			dataType : "json",
			success : function(data, textStauts, xhr) {
				var arr = jQuery.makeArray(data);
				line.setData(arr);
			}

		});

	};
	
	$('#id_query_form').ajaxForm({
		beforeSubmit:function(formData, jqForm, options){
			 return true; 
		},
		dataType:"json",
		success:function(data, statusText, xhr, $form){
			var arr = jQuery.makeArray(data);
			line.setData(arr);
		},
	}); 
	$('#id_query_form').submit();
	
</script>