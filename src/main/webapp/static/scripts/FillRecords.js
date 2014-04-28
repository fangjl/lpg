

$(function(){
	
	
	alert($('#DataTables_Table_0').dataTable({}));
	alert($('#DataTables_Table_0').attr("url"));
	$('#DataTables_Table_0').dataTable( {
		"bProcessing": true,
	    "bServerSide": true,
	    "bFilter":false, 
	    "sAjaxSource": $('#DataTables_Table_0').attr("url"),
	    //sPaginationType:"full_numbers",
	    "oLanguage": {
	    	  "sEmptyTable": "没有数据显示",
	    	  "sProcessing": "加载中...",
	    	  "sSearch": "搜索:",
	    	  "sZeroRecords": "没有数据",
	    	  "sInfo": "第 _START_ 到 _END_条  共 _TOTAL_ 条记录"
		 },
		 bInfo:true,
		 bScrollCollapse:true,
		 "iDisplayLength": 10,
	     "aLengthMenu": [
	        [10, 15, 20,25],
	        [10, 15, 20,25] // change per page values here
	    ],
	  "aoColumns": [
			{ "mData": "id" },
			{ "mData": "tenantcode" },
			{ "mData": "officecode" },
			{ "mData": "rfidcode" },
			{ "mData": "barcode" },
			{ "mData": "gpbm" },
			{ "mData": "czkssj" },
			{ "mData": "czyxsj" },
			{ "mData": "cszl" },
			{ "mData": "ggxh" },
			{ "mData": "czzt" }
	    ]
	} );

	

	
});