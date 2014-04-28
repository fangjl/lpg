<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head>
<title>钢瓶档案查询</title>


</head>
<section class="vbox"> <header class="header bg-dark">
<ul class="nav nav-tabs">

	<li class=""><a data-toggle="tab" data-target="#content" data-pjax="" href="${ctx}/gastank/refluxRatio">回流率分析</a></li>
	<li class=""><a data-toggle="tab" data-target="#content" data-pjax="" href="${ctx}/gastank/analysis">统计分析</a></li>
	<li class="active"><a>详细查询</a></li>
</ul>
</header> 

<section class="scrollable wrapper">
<div id="datatable" class="tab-pane active">
	<section class="panel"> 
	<header class="panel-heading">
		<ul class="nav nav-pills pull-right ">
			<li><a class="panel-toggle text-muted " href="#"> <i class="fa fa-caret-down text-active">
			</i> <i class="fa fa-caret-up text"></i></a></li>
		</ul>
		钢瓶详细查询
		</header>
	<div class="panel-body ">
		<form id="id_query_form"  action="${ctx}/gastank/queryGasTankPageData" role="form" class="form-horizontal"  >
		
			<div class="row">
				<div class="col-lg-4">
					<div class="form-group ">
						<label  class="col-lg-4 control-label">钢瓶编码</label> 
						<div class="col-lg-8">
						<input name="search_LIKE_gpbm" type="text" placeholder="钢瓶编码"  class="form-control input-sm">
						</div>
					</div>
					
					<div class="form-group">
						<label  class="col-lg-4 control-label">充装气站</label> 
						<div class="col-lg-8">
							<select value="" name="search_EQ_officecode" class="form-control input-sm" placeholder="充装气站">
							<option value="">全部</option>
							<c:forEach var="office" items="${principal.offices }"  varStatus="status">
								<option value="${office.key}">${office.value}</option>		
							</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label  class="col-lg-4 control-label">钢瓶厂家</label> 
						<div class="col-lg-8">
							<select value="" name="search_EQ_sccj" class="form-control input-sm   " placeholder="钢瓶厂家">
							<option value="">全部</option>
							
							<option value="1">A</option>
							<option value="2">B</option>
							<option value="3">C</option>
							<option value="4">A</option>
							<option value="5">B</option>
							<option value="6">C</option>
							</select>
						</div>
					</div>
					
				</div>
				<div class="col-lg-4">
					<div class="form-group ">
						<label  class="col-lg-4 control-label">出厂日期</label> 
						<div class="col-lg-8">
						<input id="id_ccsj" name="scrq" type="text" placeholder="出厂日期"  class="form-control input-sm">
						</div>
					</div>
					
					<div class="form-group ">
						<label  class="col-lg-4 control-label">充装日期</label> 
						<div class="col-lg-8">
						<input id="id_czsj" name="czsj" type="text" placeholder="充装日期"  class="form-control input-sm">
						</div>
					</div>
					<div class="form-group ">
						<label  class="col-lg-4 control-label">注册日期</label> 
						<div class="col-lg-8">
						<input id="id_jdsj" name="jdsj" type="text" placeholder="注册日期"  class="form-control input-sm">
						</div>
					</div>
				</div>
				
				<div class="col-lg-4">
				
					<div class="form-group ">
						<label class="col-lg-4 control-label">充装次数大于</label>
						<div class="col-lg-8">
							<div class="spinner input-group" data-min="1">
								<input type="text" class="form-control input-sm spinner-input" 
									name="search_GT_zzcs">
								<div class="btn-group btn-group-vertical input-group-btn">
									<button type="button" class="btn btn-white spinner-up">
										<i class="fa fa-chevron-up text-muted"></i>
									</button>
									<button type="button" class="btn btn-white spinner-down">
										<i class="fa fa-chevron-down text-muted"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group ">
						<label class="col-lg-4 control-label">流失天数></label>
						<div class="col-lg-8">
							<div class="spinner input-group" data-min="1">
								<input type="text" class="form-control input-sm spinner-input" 
									name="search_GT_lsts">
								<div class="btn-group btn-group-vertical input-group-btn">
									<button type="button" class="btn btn-white spinner-up">
										<i class="fa fa-chevron-up text-muted"></i>
									</button>
									<button type="button" class="btn btn-white spinner-down">
										<i class="fa fa-chevron-down text-muted"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
					
					<div class="form-group">
						<label  class="col-lg-4 control-label">钢瓶状态</label> 
						<div class="col-lg-8">
							<select   name="search_EQ_gpzt" class="form-control input-sm" placeholder="钢瓶状态" value="">
							<option value="" >全部</option>
							<option value="0">正常</option>
							<option value="1">停用</option>
							<option value="3">报废</option>
							</select>
						</div>
					</div>

				</div>
				
			
			
			</div>
			
   
			<div class="row">
				 <div class="col-lg-4 ">
				 <div class="col-lg-8 col-lg-offset-4  ">
				 	<button class="btn btn-default btn-s-md btn-sm  btn-lg btn-block" type="submit">查询</button>
				 
				 </div>
				    </div>
			</div>
		</form>
		
		
	</div>
	<div class="table-responsive">
		<table id="DataTables_Table_0" class="table table-striped m-b-none"
			data-ride="datatables">
			<thead>
				<tr>
						<th width="12%">所属<br>气站</th>
 						<th width="8%">编码</th>
 						<th width="8%">规格</th>
 						<th width="8%">出厂<br>日期</th>
 						<th width="8%">注册<br>日期</th>
 						<th width="4%">下次<br>检验</th>
 						<th width="14%">最后充装时间</th>
 						<th width="8%">充装<br>次数</th>
 						<th width="10%">流失<br>(天)</th>
 						<th width="4%" >皮重(KG)</th>
 						<th width="8%" >注册人</th>
 						<th width="8%">状态</th>
				</tr>
			</thead>
			<tbody>
			</tbody>
		</table>
	</div>

	</section>
</div>

<div>

</div>
</section> </section>


<script type="text/javascript">


	var principal = ${jsonprincipal};

	
	var oTable = $('#DataTables_Table_0').dataTable(
				{
					"bProcessing" : true,
					"sAjaxSource" : "${ctx}/gastank/queryGasTankPageData",
					"sDom" : "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
					"bServerSide" : true,
					"bFilter" : false,
					"bLengthChange" : false,
						"oLanguage" : {
						"sEmptyTable" : "没有数据显示",
						"sProcessing" : "加载中...",
						"sSearch" : "搜索:",
						"sInfoEmpty" : "对不起，查询不到任何相关数据",
						"sInfo" : "第 _START_ 到 _END_条  共 _TOTAL_ 条记录",
						"oPaginate" : {
							"sPrevious" : "上一页",
							"sNext" : "下一页",
							"sLast" : "最后一页",
							"sFirst" : "首页"
						}
					},
					"bScrollAutoCss" : false,
					//   "sScrollY": "200px",
					bInfo : true, //显示 也数据  和 当期在那一条
					bScrollCollapse : true,
					bAutoWidth : true,
				//	fnServerData:loadData,
					"sPaginationType" : "full_numbers",
					"iDisplayLength" : 20,
					// bStateSave:false, //开关，是否打开客户端状态记录功能。这个数据是记录在cookies中的，打开了这个记录后，即使刷新一次页面，或重新打开浏览器，之前的状态都是保存下来的
					bSort : false, //开关，是否让各列具有按列排序功能
					//bScrollInfinite:true, //开关，以指定是否无限滚动（与sScrollY配合使用），在大数据量的时候很有用。当这个标志为true的时候，分页器就默认关闭
					
					
					"aoColumns" : [{
						"mData" : "officecode",fnRender:function(oObj){


							
								var key = oObj.aData["officecode"];
								//alert(key);
								return  principal.offices[key];
								
								//return "${principal.offices['"+key+"']}";
							}
					},{
						"mData" : "gpbm"
					},{
						"mData" : "ggxh"
					},{
						"mData" : "scrq"
					},{
						"mData" : "jdsj"
					},{
						"mData" :"lastCheckTime"
					},{
						"mData" : "czsj"
					},{
						"mData" : "zzcs"
					},{"mData" : "lsts",fnRender:function(oObj){
						
						 	if( oObj.aData["lsts"]!=null && oObj.aData["lsts"]!='' && oObj.aData["lsts"]!='null' ){
						 		return "<font color='red'>"+oObj.aData["lsts"]+"</font>";
							}else{
								return "<font color='blue'>从未充装</font>";
							} 
						
						}
					},{
						"mData" : "pz"
					},{
						"mData" : "fpr"
					},{
						
						"mData" : "gpzt",fnRender:function(oObj){

							switch(oObj.aData["gpzt"]){
							  case 0: return "<font color='green'>正常</font>";
							  case 1: return "停用";
							  case 3: return "<font color='red'>报废</font>";
							  default:return "未知";
							}
							//return aoData[*];
						}
					}]
				});
	
	$('#id_query_form').ajaxForm({
		beforeSubmit:function(formData, jqForm, options){
			 var oSettings = oTable.fnSettings();
			 oSettings.aoServerParams[0]={
			 fn: function (aoData) {
				 $.each(formData, function( index, value ) {
					 aoData.push(value);
					 
				 });
 			  }
			 }; 
		  oTable.fnDraw();
		 return false; 
		}
	}); 
	
	
	
	
	$('#id_ccsj').daterangepicker({
		timePicker : false,
		format : 'YYYY-MM-DD',
		separator:'~',
		showWeekNumbers: false,
	/* 	startDate: moment().subtract('month',7).format('YYYY-MM-DD'),
        endDate: moment().format('YYYY-MM-DD'),
 */    
 	    minDate:  moment().subtract('year',8).format('YYYY-MM-DD'),
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
	
	$('#id_czsj').daterangepicker({
		timePicker : false,
		format : 'YYYY-MM-DD',
		separator:'~',
		showWeekNumbers: false,
		//startDate: moment().subtract('month',7).format('YYYY-MM-DD'),
        //endDate: moment().format('YYYY-MM-DD'),
        minDate:  moment().subtract('month',48).format('YYYY-MM-DD'),
        maxDate: moment().format('YYYY-MM-DD'),
        showDropdowns: true,
        showWeekNumbers:true,
        opens: 'right',
        ranges: {
    		'昨天': [moment().subtract('day', 1), moment().subtract('day', 1)],
			'本月': [moment().startOf('month'), moment()]
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
	
	$('#id_jdsj').daterangepicker({
		timePicker : false,
		format : 'YYYY-MM-DD',
		separator:'~',
		showWeekNumbers: false,
	//startDate: moment().subtract('month',7).format('YYYY-MM-DD'),
     //   endDate: moment().format('YYYY-MM-DD'),
        minDate:  moment().subtract('year',8).format('YYYY-MM-DD'),
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
</script>


