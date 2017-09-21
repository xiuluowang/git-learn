$(function(){
   $("#table").bootstrapTable({
		datatype : "json",
		method : "get",
		cache:false,
		sortable: true, 
		clickToSelect: true,
		columns : [
			{
				checkbox : true
			},
			{
				field : "id",
				index : "id",
				title : "用户编号",
				visible : false
			},
			{
				field : "uname",
				index : "uname",
				title : "用户名",
				width : 110,
				align: 'center',
				sortable:true,
				formatter : function(value, row, index) {
					return "<a href='#' onclick=\"showDetail(\'"
							+ row.id
							+ "\')\">"
							+ value + "</a>";
				}
			},
			{
				field : "name",
				index : "name",
				title : "姓名",
				width : 100,
				align: 'center',
			},
			{
				field : "email",
				index : "email",
				title : "邮箱",
				width : 100,
				align: 'center',
			},{
				field : "telephone",
				index : "telephone",
				title : "联系电话",
				width : 100,
				align: 'center',
			},
			{
				title : "编辑",
				width : 16,
				align: 'center',
				formatter : function(value, row, index) {
					return "<a href='#' onclick=\"modify(\'"
							+ row.id
							+ "\')\" class='glyphicon glyphicon-edit'></a>";
				}
			}],
		striped : true,
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

	$("#add_btn").click(function(){
		 $('#modal').modal({
			  show:true,
			  remote:"/ersp/site/user/add.html"
		 });
	 });
	 
	 $("#del_btn").click(function(){
		 deleteUser();
	 });
});

function refreshTable(){
	$("#table").bootstrapTable('refresh', {
		url: '/ersp/api/users'
	});  
}

//详情页面
function showDetail(userId){
	$('#modal').modal({
		  show:true,
		  remote:"/ersp/api/users/"+userId
	});
}

//编辑页面
function modify(userId){
	$('#modal').modal({
		  show:true,
		  remote:"/ersp/api/users/"+userId+"/edit"
	});
}

function deleteUser(){
	var userIds = "";
	var row = $("#table").bootstrapTable("getSelections");
	if (row != null && row.length>0) {
		for (var i=0;i<row.length;i++) {
			userIds = userIds+row[i].id+",";
		}
		if (userIds != "") {
			userIds = userIds.substring(0,userIds.lastIndexOf(","));
		}
		$.ajax({
			type: 'DELETE',
 			dataType:'json',
 			url:'/ersp/api/users/'+userIds,
 			success:function(data){
 				if (data.result) {
 					refreshTable();
 	            } else {
 	            	alert("删除失败");
 	            }
 			}
		});
	} else {
		alert("请选择需要删除的用户");
	}
}

function closeModal() {
	location.reload(false);
	$('#modal').modal('hide');
}