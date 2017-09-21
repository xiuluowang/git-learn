/**
 * 需求管理列表
 */
$(document).ready(function(){
    $("#del_btn").click(deleteRequirement);
    $("#add_btn").click(publishRequirment);
    
    initTable();
});
var bs_table;
function initTable(){
	/**
	 *	时间格式化工具
	 *  @param value 当前字段值
	 *  @param row 当前行对象
	 *  @param index 行index
	 */
	var dateformatter = function(value,row,index){
		if(value != null && "" != value){
			var d = new Date();
			d.setTime(value);
			return d.toLocaleString();
		}
		return value;
	};
	
	bs_table = $('#table').bootstrapTable({
	    columns: [
			{
				checkbox : true,
				width: 30
			},
			{
			    field: 'reqId',
			    width: 60,
			    align: 'center',
			    title: '需求编号',
			    visible : false
			}, {
			    field: 'reqName',
			    width: 120,sortable:true,
			    align: 'center',
			    title: '需求简述'
			}, {
			    field: 'reqDesc',
			    width: 300,sortable:true,
			    align: 'center',
			    title: '需求详述'
			},{
			    field: 'publishDate',
			    width: 120,sortable:true,
			    align: 'center',
			    title: '发布时间',
			    formatter:dateformatter
			},{
			    field: 'enterprise.company',
			    width: 120,
			    align: 'center',
			    title: '发布企业'
			},{
			    title: '操作',
			    align: 'center',
			    width: 30,
			    formatter:function(value,row,index){
			        var link = "<a onclick='viewRequirment(\"" + row.reqId + "\")' class='glyphicon glyphicon-edit'></a>";
			        return link;
			    } 
			}],
		sortable: true, 
		clickToSelect: true,
		//height:472,
		striped : true,
		pagination : true,
		/* ajax 参数配置 */
		cache:false,method:'get',dataType:'json',
		
		queryParams : function getParams(params) {
			return {
				page : this.pageNumber,
				rows : params.limit,
				sord:this.sortOrder,
				sidx:this.sortName,
				search:this.searchText
			};
		}
	});	
}

/**
 * 删除
 */
function deleteRequirement(){
	var tids = $('#table').bootstrapTable('getAllSelections');
	if(tids == null || tids == ""){
		BootstrapDialog.show({
	         title: '系统删除提示',
	         message: '请至少选择一条需求记录。',
	         buttons: [{
	             label: '明白了',
	             cssClass: 'btn-primary',
	             action: function(dialog) {
	            	 dialog.close();
	             }
	         }]
	     });
	}else if(tids.length==1){
		postDeleteRequest(tids[0].reqId);
	}else{
		
	}
}

function postDeleteRequest(reqId){
	$.ajax({
		type: "delete",dataType:'json',
		url: "/ersp/api/requirements/"+reqId,
		success: function(data){
			if(data.result){
				$('#table').bootstrapTable('refresh', {silent:true});
			}else{
				BootstrapDialog.show({
			         title: '系统删除提示2',
			         message: '系统执行删除失败,请刷新页面重试,重试无效请告知系统管理员。',
			         buttons: [{
			             label: '明白了',
			             cssClass: 'btn-primary',
			             action: function(dialog) {
			            	 dialog.close();
			             }
			         }]
			     });
			}
		},
		error:function(data){
			BootstrapDialog.show({
		         title: '系统删除提示1',
		         message: '系统执行删除失败,请刷新页面重试,重试无效请告知系统管理员。',
		         buttons: [{
		             label: '明白了',
		             cssClass: 'btn-primary',
		             action: function(dialog) {
		            	 dialog.close();
		             }
		         }]
		     });
		}
	});
}
var modal;
function publishRequirment(){
	modal = $('#modal').modal({
		  show:true,backdrop:"static",
		  remote:"/ersp/site/requirement/add.html"
	});
}

function viewRequirment(requireId){
	modal = $('#modal').modal({
		  show:true,backdrop:"static",
		  remote:"/ersp/api/requirements/"+requireId+"/edit"
	});
}

function closeModal() {
	bs_table.bootstrapTable('refresh', {silent:true});
	modal.modal('hide');
}