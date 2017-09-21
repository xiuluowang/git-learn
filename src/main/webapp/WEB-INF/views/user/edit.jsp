<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>编辑用户</title>
<link href="/ersp/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/ersp/css/bootstrap/validator/0.5.2/bootstrapValidator.css" rel="stylesheet" />
</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h4 class="modal-title">管理用户-编辑用户</h4>
</div>
<div class="modal-body">
	<div class="panel-body">
	<form class="form-horizontal" method="POST" action="/ersp/api/users/${user.id}" name="userForm" id="userForm">
		<input type="hidden" name="_method" value="PUT" />
		<input type="hidden" name="id" value="${user.id}" id="userId"/>
		<input type="hidden" name="utype" value="${user.utype}" />
		<div class="form-group">
		   <label class="col-sm-2 control-label" for="uname">用户名</label>
		   <div class="col-sm-10">
			 <input type="text" class="form-control" name="uname" id="uname" value="${user.uname}"/>
		   </div>
		</div>
		
		<div class="form-group">
		   <label class="col-sm-2 control-label" for="telephone">联系电话</label>
		   <div class="col-sm-10">
			 <input type="text" class="form-control" name="telephone" id="telephone" value="${user.telephone}"/>
		   </div>
		</div>
		
		<div class="form-group">
		   <label class="col-sm-2 control-label" for="email">邮箱</label>
		   <div class="col-sm-10">
			 <input type="email" class="form-control" name="email" id="email" value="${user.email}"/>
		   </div>
		</div>
		
		<div class="form-group">
		   <label class="col-sm-2 control-label" for="name">姓名</label>
		   <div class="col-sm-10">
			 <input type="text" class="form-control" name="name" id="name" value="${user.name}"/>
		   </div>
		</div>
		
		<div class="form-group">
		   <label class="col-sm-2 control-label" for="company">公司名称</label>
		   <div class="col-sm-10">
			 <input type="text" class="form-control" name="company" id="company" value="${user.company}"/>
		   </div>
		</div>
		
		<div class="form-group">
		   <label class="col-sm-2 control-label" for="caddress">公司地址</label>
		   <div class="col-sm-10">
			 <input type="text" class="form-control" name="caddress" id="caddress" value="${user.caddress}"/>
		   </div>
		</div>
  </form>
</div>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	<button type="button" class="btn btn-primary" id="btn_update">保存</button>
</div>
<script src="/ersp/js/base/jquery-1.10.2.js"></script>
<script src="/ersp/js/bootstrap.min.js"></script>
<script src="/ersp/js/bootstrap/validator/0.5.2/bootstrapValidator.js"></script>
<script src="/ersp/js/user/userform.js"></script>
</body>
</html>