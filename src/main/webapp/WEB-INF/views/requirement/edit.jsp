<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/ersp/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="/ersp/css/bootstrap/validator/0.5.2/bootstrapValidator.css" rel="stylesheet" />
<title>编辑需求</title>
</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h4 class="modal-title">管理需求-编辑需求</h4>
</div>
<div class="modal-body">
	<div class="panel-body">
	<form class="form-horizontal" method="PUT" action="/ersp/api/requirements/${req.reqId}" name="requirementform" id="requirementform">
		<div class="form-group">
			<input type="hidden" name="reqId" value="${req.reqId}" />
			<input type="hidden" name="_method" value="PUT" />
		   <label class="col-sm-2 control-label" for="reqName">需求简述</label>
		   <div class="col-sm-10">
			 <input type="text" class="form-control" name="reqName" id="reqName" value="${req.reqName}"/>
		   </div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="reqDesc">需求详述</label>
			<div class="col-sm-10">
				<textarea class="form-control" id="reqDesc" name="reqDesc" rows="10">${req.reqDesc}</textarea>
			</div>
		</div>
  </form>
</div>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭
	</button>
	<button type="button" class="btn btn-primary" id="btn_submit" >
		提交修改
	</button>
</div>
</body>
<script src="/ersp/js/base/jquery-1.10.2.js"></script>
<script src="/ersp/js/bootstrap.min.js"></script>
<script src="/ersp/js/bootstrap/validator/0.5.2/bootstrapValidator.js"></script>
<script type="text/javascript" src="/ersp/js/require/requirement.js"></script>
<!-- <script src='/ersp/js/tinymce/tinymce.min.js'></script> -->
<script type="text/javascript">
// tinymce.init({
// 	  selector: '#reqDesc',
// 	  height: 500,
// 	  menubar: false,
// 	  plugins: [
// 	    'advlist autolink lists link image charmap print preview anchor',
// 	    'searchreplace visualblocks code fullscreen',
// 	    'insertdatetime media table contextmenu paste code'
// 	  ],
// 	  toolbar: 'undo redo | insert | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image',
// 	  content_css: '//www.tinymce.com/css/codepen.min.css'
// 	});
</script>
</html>