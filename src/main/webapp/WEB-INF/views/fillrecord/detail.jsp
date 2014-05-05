<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head>
<title>充装记录详细查询</title>


</head>
<section class="vbox"> <header class="header bg-dark">
<ul class="nav nav-tabs">
	<li class=""><a data-toggle="tab" data-target="#content" data-pjax="" href="${ctx}/fillrecord/analysis">统计分析</a></li>
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
		充装记录详细查询
		</header>
	<div class="panel-body">
		<form id="id_query_form"  action="${ctx}/fillrecord/getFillRecordGridData" role="form" class="form-horizontal"  >
			<div class="row">
				<div class="col-xs-3">
					
					<div class="form-group">
						<label  class="col-xs-4 control-label">充装气站:</label> 
						<div class="col-xs-8">
							<select value="" name="search_EQ_officecode" class="form-control input-sm" placeholder="充装气站">
							 
							<option value="">全部</option>
							<c:forEach var="office" items="${principal.offices }"  varStatus="status">
								<option value="${office.key }">${ office.value}</option>		
							</c:forEach>
							
							</select>
						</div>
					</div>
				</div>
				
				<div class="col-xs-2">
					<div class="form-group ">
							<label  class="col-xs-6 control-label">工位号:</label> 
							<div class="col-xs-6">
							<input name="search_EQ_cjqbm" type="text" placeholder="工位号"  class="form-control input-sm">
							</div>
						</div>
				</div>
				<div class="col-xs-2">
					<div class="form-group ">
							<label  class="col-xs-6 control-label">钢印号:</label> 
							<div class="col-xs-6">
							<input name="search_LIKE_gpbm" type="text" placeholder="钢印号"  class="form-control input-sm">
							</div>
						</div>
				</div>
				<div class="col-xs-3">
						<div class="form-group ">
						<label  class="col-xs-4 control-label">充装时间:</label> 
						<div class="col-xs-8">
						<input id="id_czsj" name="czsj" type="text" placeholder="充装时间"  class="form-control input-sm">
						</div>
					</div>
				</div>
				<div class="col-xs-1">
						<div class="col-xs-5">
							<button class="btn btn-default btn-s-md btn-sm   btn-block" type="submit">查询</button>
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
<!-- 					<th width="10%">企业</th>
 -->					<th width="10%">气站</th>
 						<th width="10%">工位号</th>
					 	<th width="10%">钢瓶编码</th>
					 	<th width="10%">芯片编码</th>
					 	<th width="10%">充装净重</th>
 						<th width="45%">充装时间</th>
 						
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
					"sAjaxSource" : "${ctx}/fillrecord/queryFillRecordPageData",
					"sDom" : "<'row'<'col-sm-6'l><'col-sm-6'f>r>t<'row'<'col-sm-6'i><'col-sm-6'p>>",
					"bServerSide" : true,
					"bFilter" : false,
					"bLengthChange" : false,
						"oLanguage" : {
						"sEmptyTable" : "没有数据显示",
						"sProcessing" : "加载中...",
						"sSearch" : "搜索:",
						"sInfoEmpty" : "对不起，查询不到任何相关数据",
						"sZeroRecords" : "没有数据",
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
					"sPaginationType" : "full_numbers",
					"iDisplayLength" : 20,
					 bStateSave:true, //开关，是否打开客户端状态记录功能。这个数据是记录在cookies中的，打开了这个记录后，即使刷新一次页面，或重新打开浏览器，之前的状态都是保存下来的
					bSort : false, //开关，是否让各列具有按列排序功能
					//bScrollInfinite:true, //开关，以指定是否无限滚动（与sScrollY配合使用），在大数据量的时候很有用。当这个标志为true的时候，分页器就默认关闭
					"aoColumns" : [/*  {
						"mData" : "tenantcode"
					}, */ {
						"mData" : "officecode",fnRender:function(oObj){
						
						var key = oObj.aData["officecode"];
						//alert(key);
						return  principal.offices[key];
						
						//return "${principal.offices['"+key+"']}";
					}
					}, {
						"mData" : "cjqbm"
					}, {
						"mData" : "gpbm"
					}, {
						"mData" : "rfidcode"
					},{
						"mData" : "czjz"
					},  {
						"mData" : "czkssj"
					} ]
				});
	
	$('#id_czsj').daterangepicker({
		timePicker : false,
		//timePickerIncrement : 30,
		format : 'YYYY-MM-DD',
		separator:'~',
		showWeekNumbers: false, 
		startDate: moment().subtract('day',7).format('YYYY-MM-DD'),
        endDate: moment().format('YYYY-MM-DD'),
       // minDate:  moment().subtract('month',12).format('YYYY-MM-DD'),
        maxDate: moment().format('YYYY-MM-DD'),
        showDropdowns: true,
        showWeekNumbers:true,
        opens: 'right',
        ranges: {
            '今天': [moment(), moment()],
    		'昨天': [
    		         moment().subtract('day', 1).startOf('day'), 
    		         moment().subtract('day', 1).startOf('day')
    		         ],
			'前天': [
			         moment().subtract('day', 2).startOf('day'), 
			         moment().subtract('day', 2).startOf('day') 
			        ]

        },
        locale: {
            applyLabel: '确定',
            cancelLabel: '取消',
            fromLabel: '从',
            toLabel: '到',
            customRangeLabel: '其他',
            firstDay: 1
        }
	}, 
	function(start, end, label) {
		
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
	
	
</script>


