<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>用户详情</title>
<link href="/ersp/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h4 class="modal-title">管理用户-用户详情</h4>
</div>
<div class="modal-body">
	<div class="panel-body">
	<form class="form-horizontal" method="post" action="">
		<div class="form-group">
		   <label class="col-sm-2 control-label" for="uname">用户名</label>
		   <div class="col-sm-10">
			 <input type="text" class="form-control" id="uname" value="${user.uname}" disabled/>
		   </div>
		</div>
		
		<div class="form-group">
		   <label class="col-sm-2 control-label" for="telephone">联系电话</label>
		   <div class="col-sm-10">
			 <input type="text" class="form-control" id="telephone" value="${user.telephone}" disabled/>
		   </div>
		</div>
		
		<div class="form-group">
		   <label class="col-sm-2 control-label" for="email">邮箱</label>
		   <div class="col-sm-10">
			 <input type="text" class="form-control" id="email" value="${user.email}" disabled/>
		   </div>
		</div>
		
		<div class="form-group">
		   <label class="col-sm-2 control-label" for="name">姓名</label>
		   <div class="col-sm-10">
			 <input type="text" class="form-control" id="name" value="${user.name}" disabled/>
		   </div>
		</div>
		
		<div class="form-group">
		   <label class="col-sm-2 control-label" for="email">邮箱</label>
		   <div class="col-sm-10">
			 <input type="email" class="form-control" id="email" value="${user.email}" disabled/>
		   </div>
		</div>
		
		<div class="form-group">
		   <label class="col-sm-2 control-label" for="company">公司名称</label>
		   <div class="col-sm-10">
			 <input type="text" class="form-control" id="company" value="${user.company}" disabled/>
		   </div>
		</div>
		
		<div class="form-group">
		   <label class="col-sm-2 control-label" for="caddress">公司地址</label>
		   <div class="col-sm-10">
			 <input type="text" class="form-control" id="caddress" value="${user.caddress}" disabled/>
		   </div>
		</div>
  </form>
</div>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>
<script src="/ersp/js/base/jquery-1.10.2.js"></script>
<script src="/ersp/js/bootstrap.min.js"></script>
</body>
</html>